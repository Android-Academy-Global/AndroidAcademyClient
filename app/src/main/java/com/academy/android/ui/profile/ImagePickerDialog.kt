package com.academy.android.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.academy.android.R
import com.academy.android.databinding.AddProfPicDialogBinding

class ImagePickerDialog(private val listener: ImageSelectInterface): DialogFragment() {

    private val vb by viewBinding(AddProfPicDialogBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_prof_pic_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        //setting camera listener
        vb.btnCamera.setOnClickListener{
            listener.getImageFromCamera()
            dismiss()
        }
        //setting gallery listener
        vb.btnGallery.setOnClickListener {
            listener.getImageFromGallery()
            dismiss()
        }
    }

    interface ImageSelectInterface{
        fun getImageFromCamera()
        fun getImageFromGallery()
    }
}