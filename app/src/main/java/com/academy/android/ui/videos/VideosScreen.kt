package com.academy.android.ui.videos

import android.content.res.Resources
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.academy.android.R
import com.academy.android.domain.models.City
import com.academy.android.domain.models.CourseLevel
import com.academy.android.domain.models.CourseYear
import com.academy.android.domain.models.FilterEntity
import com.academy.android.domain.models.International
import com.academy.android.domain.models.Localized
import com.academy.android.ui.custom.DropdownBox
import com.academy.android.ui.custom.TextSearchBar

@Composable
fun VideosScreen(vm: VideosViewModel) {
    val filterParameters by vm.filterParameters.collectAsState()
    val videoList by vm.videosList.collectAsState()

    val paddingStandard = dimensionResource(id = R.dimen.spacing_16)
    Column {
        FilterEntity(
            filterEntities = filterParameters.cities,
            paddingTop = paddingStandard,
            paddingBottom = paddingStandard,
            paddingStart = paddingStandard,
            paddingEnd = paddingStandard,
            onItemSelected = { city: City -> vm.handleCityFilterUpdated(city = city) },
            onClear = { vm.handleCityFilterUpdated(city = null) }
        )
        val weightHalf = 1f
        Row {
            Column(modifier = Modifier.weight(weightHalf)) {
                FilterEntity(
                    filterEntities = filterParameters.levels,
                    paddingBottom = paddingStandard,
                    paddingStart = paddingStandard,
                    paddingEnd = paddingStandard,
                    onItemSelected = { level: CourseLevel -> vm.handleLevelFilterUpdated(level = level) },
                    onClear = { vm.handleLevelFilterUpdated(level = null) }
                )
            }
            Column(modifier = Modifier.weight(weightHalf)) {
                FilterEntity(
                    filterEntities = filterParameters.years,
                    paddingBottom = paddingStandard,
                    paddingEnd = paddingStandard,
                    onItemSelected = { year: CourseYear -> vm.handleYearFilterUpdated(year = year) },
                    onClear = { vm.handleYearFilterUpdated(year = null) }
                )
            }
        }

        VideoList(videoList = videoList)
    }
}

@Composable
fun <T : FilterEntity> FilterEntity(
    filterEntities: List<T>,
    paddingTop: Dp = 0.dp,
    paddingBottom: Dp = 0.dp,
    paddingStart: Dp = 0.dp,
    paddingEnd: Dp = 0.dp,
    onItemSelected: (T) -> Unit,
    onClear: () -> Unit
) {
    val resources = LocalContext.current.resources
    DropdownBox(
        items = filterEntities,
        itemContent = { filterEntity ->
            DropdownItem(
                getFilterItemName(filterEntity, resources)
            )
        }
    ) {
        var value by remember { mutableStateOf("") }
        val view = LocalView.current

        onItemSelected { filterEntity ->
            value = getFilterItemName(filterEntity, resources)
            onItemSelected(filterEntity)
            view.clearFocus()
        }

        TextSearchBar(
            value = value,
            label = stringResource(id = filterEntities.last().hintResId),
            onDoneActionClick = {
                view.clearFocus()
            },
            onClearClick = {
                value = ""
                onClear()
                view.clearFocus()
            },
            onFocusChanged = { focusState ->
                isDown = focusState.isFocused
            },
            onValueChanged = { query ->
                value = query
            },
            modifier = Modifier
                .padding(
                    top = paddingTop,
                    bottom = paddingBottom,
                    start = paddingStart,
                    end = paddingEnd
                )
                .fillMaxWidth()
        )
    }
}

private fun getFilterItemName(
    filterEntity: FilterEntity,
    resources: Resources
) = when (filterEntity) {
    is Localized -> resources.getString(filterEntity.nameResId)
    is International -> filterEntity.name
    else -> throw UnsupportedOperationException("${filterEntity::class} is not supported yet")
}

@Composable
fun DropdownItem(item: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.spacing_24),
                bottom = dimensionResource(id = R.dimen.spacing_16)
            )
    ) {
        Text(text = item, style = MaterialTheme.typography.subtitle2)
    }
}