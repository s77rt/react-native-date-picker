package com.s77rt.rtndatepicker

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Based on compose/material3/material3/src/commonMain/kotlin/androidx/compose/material3/DatePicker.kt
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun DatePickerTitle(
    title: String,
    displayMode: DisplayMode,
    contentColor: Color,
) {
    val modifier = Modifier.padding(PaddingValues(start = 24.dp, end = 12.dp, top = 16.dp))

    if (title.isEmpty()) {
        DatePickerDefaults.DatePickerTitle(
            displayMode = displayMode,
            modifier = modifier,
            // TODO: Pass contentColor after upgrading material 3 to v1.4.0+
            // contentColor = contentColor,
        )
    } else {
        Text(
            text = title,
            modifier = modifier,
            color = contentColor,
        )
    }
}

// Based on compose/material3/material3/src/commonMain/kotlin/androidx/compose/material3/DatePicker.kt
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun DatePickerHeadline(
    headline: String,
    selectedDateMillis: Long?,
    displayMode: DisplayMode,
    dateFormatter: DatePickerFormatter,
    contentColor: Color,
) {
    val modifier = Modifier.padding(PaddingValues(start = 24.dp, end = 12.dp, bottom = 12.dp))

    if (headline.isEmpty()) {
        DatePickerDefaults.DatePickerHeadline(
            selectedDateMillis = selectedDateMillis,
            displayMode = displayMode,
            dateFormatter = dateFormatter,
            modifier = modifier,
            // TODO: Pass contentColor after upgrading material 3 to v1.4.0+
            // contentColor = contentColor,
        )
    } else {
        Text(
            text = headline,
            modifier = modifier,
            color = contentColor,
            maxLines = 1,
        )
    }
}
