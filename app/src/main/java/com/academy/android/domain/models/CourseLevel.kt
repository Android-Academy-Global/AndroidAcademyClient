package com.academy.android.domain.models

import com.academy.android.R

enum class CourseLevel(override val nameResId: Int) : FilterEntity, Localized {
    FUNDAMENTALS(R.string.course_fundamentals),
    ADVANCED(R.string.course_advanced),
    UNKNOWN(-1);

    override val hintResId: Int =
        R.string.filter_course_level
}