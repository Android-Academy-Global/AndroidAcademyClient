package com.academy.android.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.academy.android.R
import com.academy.android.databinding.FragmentEditProfileBinding
import com.academy.android.databinding.FragmentProfBecomeSpeakerBinding

class ProfBecomeSpeakerFragment : Fragment() {

    private val vb by viewBinding(FragmentProfBecomeSpeakerBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prof_become_speaker, container, false)
    }

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