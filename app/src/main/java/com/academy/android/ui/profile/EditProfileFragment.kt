package com.academy.android.ui.profile


import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.academy.android.R
import com.academy.android.databinding.FragmentEditProfileBinding
import com.academy.android.model.Profile
import com.academy.android.util.ImageGalleryPicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class EditProfileFragment: Fragment(R.layout.fragment_edit_profile), ImagePickerDialog.ImageSelectInterface {

    private val editProfViewModel: ProfileViewModel by viewModels()
    private val vb by viewBinding(FragmentEditProfileBinding::bind)
    private var profPicture: String? = null

    private val capturePictureFromCamera =
        registerForActivityResult(ActivityResultContracts.TakePicture()){ success ->
            if (success){
                setCameraPicture(profPicture)
            } else {
                //if failed to capture picture from camera suggest another way of choosing avatar
                showImagePickDialog()
            }
        }

    private val selectPictureFromGallery =
        registerForActivityResult(ImageGalleryPicker()){ galleryImage ->
            val takeFlags: Int = (Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            requireContext().contentResolver.takePersistableUriPermission(galleryImage, takeFlags)
            setGalleryPicture(galleryImage)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            //filling profile with all the necessary data
            editProfViewModel.profileData.collectLatest { profile ->
                //avatar
                profPicture = profile.profPic
                vb.profilePicture.load(profPicture)
                //name
                vb.fieldEditName.setText(profile.name)
                //surname
                vb.fieldEditSurname.setText(profile.surname)
                //phone number
                vb.fieldEditPhone.setText(profile.phoneNumber)
                //email
                vb.fieldEditEmail.setText(profile.email)
                //status
                vb.fieldEditStatus.setText(profile.status)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNestedScrollListener()
        setupListeners()
    }

    private fun setupNestedScrollListener() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            vb.fieldsScroll.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                if ((scrollY > oldScrollY) && vb.btnSaveProfile.isVisible){
                    vb.btnSaveProfile.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out))
                    vb.btnSaveProfile.isVisible = false
                } else if ((scrollY < oldScrollY) && !vb.btnSaveProfile.isVisible){
                    vb.btnSaveProfile.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in))
                    vb.btnSaveProfile.isVisible = true
                }
            }
        }
    }

    private fun setupListeners() {
        //select profile picture button
        vb.selectPic.setOnClickListener {
            showImagePickDialog()
        }
        //save profile button
        vb.btnSaveProfile.setOnClickListener {
            when {
                TextUtils.isEmpty(vb.fieldEditName.text) -> {
                    vb.fieldEditName.error = getString(R.string.field_name_error)
                }
                TextUtils.isEmpty(vb.fieldEditSurname.text) -> {
                    vb.fieldEditSurname.error = getString(R.string.field_surname_error)
                }
                (TextUtils.isEmpty(vb.fieldEditEmail.text)) || (!Patterns.EMAIL_ADDRESS
                    .matcher(vb.fieldEditEmail.text)
                    .matches()) -> {
                    vb.fieldEditEmail.error = getString(R.string.field_email_error)
                }
                TextUtils.isEmpty(vb.fieldEditStatus.text) -> {
                    vb.fieldEditStatus.error = getString(R.string.field_status_error)
                }
                else -> {
                    //forming Profile object's properties
                    val name = vb.fieldEditName.text.toString()
                    val surname = vb.fieldEditSurname.text.toString()
                    val phoneNumber = vb.fieldEditPhone.text.toString()
                    val email = vb.fieldEditEmail.text.toString()
                    val status = vb.fieldEditStatus.text.toString()
                    //sending newly formed object to viewModel
                    editProfViewModel.updateProfile(
                        Profile(
                            profPicture,
                            name,
                            surname,
                            phoneNumber,
                            email,
                            status
                        )
                    )
                    //going back to Profile
                    Navigation.findNavController(it).navigate(R.id.action_back_to_profile)
                }
            }
        }
    }

    private fun showImagePickDialog() {
        //showing dialog suggesting a way of getting a picture for avatar
        ImagePickerDialog(this).show(parentFragmentManager, "picPictureFrag")
    }

    override fun getImageFromCamera() {
        val uri = getUriForCameraPicture()
        profPicture = uri.toString()
        capturePictureFromCamera.launch(uri)
    }

    private fun getUriForCameraPicture(): Uri? {
        val resolver = requireContext().contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "ProfileAvatar")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/ProfilePictures")
            }
        }
        return resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    }

    private fun setCameraPicture(cameraImage: String?) {
        val profileUri = Uri.parse(cameraImage)
        vb.profilePicture.load(profileUri)
    }

    override fun getImageFromGallery() {
        selectPictureFromGallery.launch(Unit)
    }

    private fun setGalleryPicture(galleryImage: Uri) {
        profPicture = galleryImage.toString()
        vb.profilePicture.load(galleryImage)
    }

}