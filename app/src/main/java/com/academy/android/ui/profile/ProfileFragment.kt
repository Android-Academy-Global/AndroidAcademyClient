package com.academy.android.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.academy.android.R
import com.academy.android.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val notificationsViewModel: ProfileViewModel by viewModels()
    private val vb by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        lifecycleScope.launchWhenResumed {
            notificationsViewModel.text.collectLatest {
                vb.textProfile.text = it
            }
        }
    }
}