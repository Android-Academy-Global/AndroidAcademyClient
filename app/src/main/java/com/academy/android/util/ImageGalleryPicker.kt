package com.academy.android.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_OPEN_DOCUMENT
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract

class ImageGalleryPicker: ActivityResultContract<Unit, Uri>() {

    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent().apply {
            type = "image/*"
            action = ACTION_OPEN_DOCUMENT
        }
    }

    override fun parseResult(resultCode: Int, result: Intent?): Uri? {
        if (resultCode != Activity.RESULT_OK) {
            return null
        }
        return result?.data
    }
}