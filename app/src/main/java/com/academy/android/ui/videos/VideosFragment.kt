package com.academy.android.ui.videos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.academy.android.R
import com.academy.android.databinding.FragmentVideosBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class VideosFragment : Fragment(R.layout.fragment_videos) {

    private val homeViewModel: VideosViewModel by viewModels()
    private val vb by viewBinding(FragmentVideosBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        lifecycleScope.launchWhenResumed {
            homeViewModel.text.collectLatest {
                vb.textVideos.text = it
            }
        }
    }
}