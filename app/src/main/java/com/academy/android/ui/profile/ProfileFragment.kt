package com.academy.android.ui.profile


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.academy.android.R
import com.academy.android.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val profileViewModel: ProfileViewModel by viewModels()
    private val vb by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        lifecycleScope.launchWhenResumed {
            profileViewModel.profileData.collectLatest { profile ->
                vb.editProfileCard.load(profile.profPic)
                //vb.editProfileCard.setImageURI(profileUri)
                vb.textUserEmail.text = profile.email
            }
        }
    }

    private fun setupListeners() {
        //setting "become speaker button"
        vb.arrowBecomeSpeaker.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_become_speaker)
        }
        //setting edit profile button
        vb.btnProfEdit.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_edit_profile)
        }
    }
}