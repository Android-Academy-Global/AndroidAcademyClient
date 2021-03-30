package com.academy.android.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Checkable
import androidx.appcompat.widget.AppCompatImageView

/**
 * Custom ImageView containing two states, like a checkbox.
 */
class CheckableImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr), Checkable, View.OnClickListener {

    private var checked = false

    companion object {
        private val CHECKED_STATE_SET = intArrayOf(android.R.attr.state_checked)
    }

    init {
        setOnClickListener(this)
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (isChecked) View.mergeDrawableStates(drawableState, CHECKED_STATE_SET)
        return drawableState
    }

    override fun isChecked(): Boolean = checked

    override fun toggle() {
        isChecked = !checked
    }

    override fun setChecked(check: Boolean) {
        if (checked == check) return
        checked = check
        refreshDrawableState()
    }

    override fun onClick(v: View?) {
        toggle()
    }
}