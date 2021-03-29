package com.academy.android.ui.news

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.academy.android.R
import com.academy.android.databinding.FragmentNewsBinding
import com.academy.android.databinding.FragmentNewsItemBinding
import com.academy.android.ui.base.BaseRVAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    private val viewModel: NewsViewModel by viewModels()
    private val vb by viewBinding(FragmentNewsBinding::bind)
    private val newsAdapter by lazy(::setupRecyclerViewAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupTabView()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        vb.rvNewsList.adapter = newsAdapter

        lifecycleScope.launchWhenResumed {
            viewModel.newsList.collectLatest {
                newsAdapter.updateData(it)
            }
        }
    }

    private fun setupTabView() {
        vb.tabLayout.apply {
            background = ResourcesCompat.getDrawable(resources, R.color.transparent, null)

            addTab(
                newTab().apply { text = resources.getString(R.string.news__tab_title_featured) },
                viewModel.getIsFilterStateNew()
            )
            addTab(
                newTab().apply { text = resources.getString(R.string.news__tab_title_passed) },
                !viewModel.getIsFilterStateNew(),
            )

            addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    if (tab.text == resources.getString(R.string.news__tab_title_featured)) {
                        viewModel.applyFilterNew()
                    } else {
                        viewModel.applyFilterOld()
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
                override fun onTabReselected(tab: TabLayout.Tab?) = Unit
            })
        }
    }

    private fun setupRecyclerViewAdapter() =
        BaseRVAdapter<FragmentNewsItemBinding, NewsItemData>(
            viewHolderInflater = { layoutInflater, parent, attachToParent ->
                FragmentNewsItemBinding.inflate(layoutInflater, parent, attachToParent)
            },
            viewHolderBinder = { holder, itemData ->
                with(holder) {
                    tvText.text = itemData.text
                    tvLink.text = itemData.link
                    ivPicture.load(itemData.picture)

                    icLike.isChecked = viewModel.getIsLiked(itemData.id)
                    icLike.setOnClickListener {
                        icLike.isChecked = viewModel.handleLike(itemData.id, !icLike.isChecked)
                    }

                    lifecycleScope.launchWhenResumed {
                        viewModel.getLikesCount(itemData.chatId).collectLatest {
                            tvLikes.text = it.toString()
                        }
                    }

                    icChat.setOnClickListener {
                        Toast.makeText(requireContext(), "Chat is clicked", Toast.LENGTH_SHORT)
                            .show()
                    }

                    lifecycleScope.launchWhenResumed {
                        viewModel.getChatMessagesCount(itemData.chatId).collectLatest {
                            tvChatMessages.text = it.toString()
                        }
                    }

                    icShare.setOnClickListener {
                        Toast.makeText(requireContext(), "Share is clicked", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            },
        )
}