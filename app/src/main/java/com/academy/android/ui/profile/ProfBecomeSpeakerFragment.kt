package com.academy.android.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.academy.android.R
import com.academy.android.databinding.FragmentProfBecomeSpeakerBinding

class ProfBecomeSpeakerFragment : Fragment(R.layout.fragment_prof_become_speaker) {

    private val vb by viewBinding(FragmentProfBecomeSpeakerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        vb.btnSendClaim.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }
}