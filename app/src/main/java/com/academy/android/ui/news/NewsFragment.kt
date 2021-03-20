package com.academy.android.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.academy.android.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {

  private var newsViewModel: NewsViewModel? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    newsViewModel =
      ViewModelProvider(this).get(NewsViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_news, container, false)
    val textView: TextView = root.findViewById(R.id.text_news)

    CoroutineScope(Dispatchers.IO).launch {
      newsViewModel?.text?.collect {
        textView.text = it
      }
    }
    return root
  }
}