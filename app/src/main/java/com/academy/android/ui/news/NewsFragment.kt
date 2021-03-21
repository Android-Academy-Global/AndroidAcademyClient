package com.academy.android.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.academy.android.R
import com.academy.android.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    private val newsViewModel: NewsViewModel by viewModels()
    private val vb by viewBinding(FragmentNewsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        lifecycleScope.launchWhenResumed {
            newsViewModel.text.collectLatest {
                vb.textNews.text = it
            }
        }
    }
}