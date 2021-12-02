package com.academy.android.domain.models

import com.academy.android.R

enum class City(override val nameResId: Int) : FilterEntity, Localized {
    MOSCOW(R.string.city_moscow),
    MINSK(R.string.city_minsk),
    TEL_AVIV(R.string.city_tel_aviv),
    UNKNOWN(-1);

    override val hintResId: Int =
        R.string.filter_city
}