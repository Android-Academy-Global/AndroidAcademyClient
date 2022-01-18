package com.academy.android.domain.models

import com.academy.android.R

data class CourseYear(override val name: String) : FilterEntity, International {
    override val hintResId: Int =
        R.string.filter_course_year
}