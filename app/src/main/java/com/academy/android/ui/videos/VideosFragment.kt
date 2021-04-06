package com.academy.android.ui.videos

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.academy.android.R
import com.academy.android.databinding.FragmentVideosBinding
import com.academy.android.databinding.FragmentVideosItemBinding
import com.academy.android.ui.base.BaseRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class VideosFragment : Fragment(R.layout.fragment_videos) {

    private val viewModel: VideosViewModel by viewModels()
    private val vb by viewBinding(FragmentVideosBinding::bind)
    private val videosAdapter by lazy(::setupRecyclerViewAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupFilterView()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        vb.rvVideosList.adapter = videosAdapter

        lifecycleScope.launchWhenResumed {
            viewModel.videosList.collectLatest {
                videosAdapter.updateData(it)
            }
        }
    }

    private fun setupFilterView() {
        val citiesAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, viewModel.filterParameters.cities)
        vb.cityDropdown.setAdapter(citiesAdapter)
        vb.cityDropdown.setOnItemClickListener { _, _, _, _ ->
            viewModel.handleCityFilterUpdated(vb.cityDropdown.text.toString())
        }
        vb.cityDropdown.setText(viewModel.initialFilterState.city, false)

        val levelsAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, viewModel.filterParameters.levels)
        vb.levelDropdown.setAdapter(levelsAdapter)
        vb.levelDropdown.setOnItemClickListener { _, _, _, _ ->
            viewModel.handleLevelFilterUpdated(vb.levelDropdown.text.toString())
        }
        vb.levelDropdown.setText(viewModel.initialFilterState.level, false)

        val yearAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, viewModel.filterParameters.years)
        vb.yearDropdown.setAdapter(yearAdapter)
        vb.yearDropdown.setOnItemClickListener { _, _, _, _ ->
            viewModel.handleYearFilterUpdated(vb.yearDropdown.text.toString())
        }
        vb.yearDropdown.setText(viewModel.initialFilterState.year, false)
    }

    private fun setupRecyclerViewAdapter() =
        BaseRVAdapter<FragmentVideosItemBinding, VideosItemData>(
            viewHolderInflater = { layoutInflater, parent, attachToParent ->
                FragmentVideosItemBinding.inflate(layoutInflater, parent, attachToParent)
            },
            viewHolderBinder = { holder, itemData ->
                with(holder) {
                    tvVideoName.text = itemData.name
                    tvVideoDate.text = itemData.date
                    ivVideoThumbnail.load(itemData.picture)
                }
            }
        )
}