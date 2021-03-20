package com.academy.android.ui.videos

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

class VideosFragment : Fragment() {

    private var homeViewModel: VideosViewModel? = null

    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(VideosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_videos, container, false)
        val textView: TextView = root.findViewById(R.id.text_videos)

        CoroutineScope(Dispatchers.IO).launch {
            homeViewModel?.text?.collect {
                textView.text = it
            }
        }
        return root
    }
}