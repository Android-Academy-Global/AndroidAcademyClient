package com.academy.android.ui.profile

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

class ProfileFragment : Fragment() {

  private var notificationsViewModel: ProfileViewModel? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    notificationsViewModel =
      ViewModelProvider(this).get(ProfileViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_profile, container, false)
    val textView: TextView = root.findViewById(R.id.text_profile)

    CoroutineScope(Dispatchers.IO).launch {
      notificationsViewModel?.text?.collect {
        textView.text = it

      }
    }
    return root
  }
}