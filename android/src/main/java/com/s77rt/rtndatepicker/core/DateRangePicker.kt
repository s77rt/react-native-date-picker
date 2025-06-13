package com.s77rt.rtndatepicker

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerDefaults
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun DateRangePickerWrapper(
    isInline: Boolean,
    isOpen: Boolean,
    state: DateRangePickerState,
    colors: DatePickerColors,
    titleText: String?,
    headlineText: String?,
    showModeToggle: Boolean,
    confirmText: String,
    cancelText: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
) {
    val formatter = remember { DatePickerDefaults.dateFormatter() }
    val textButtonColors = ButtonDefaults.textButtonColors(contentColor = colors.selectedDayContainerColor)
    val title =
        if (titleText == null) {
            null
        } else {
            @Composable {
                DateRangePickerTitle(
                    title = titleText,
                    displayMode = state.displayMode,
                    contentColor = colors.titleContentColor,
                )
            }
        }
    val headline =
        if (headlineText == null) {
            null
        } else {
            @Composable {
                DateRangePickerHeadline(
                    headline = headlineText,
                    selectedStartDateMillis = state.selectedStartDateMillis,
                    selectedEndDateMillis = state.selectedEndDateMillis,
                    displayMode = state.displayMode,
                    dateFormatter = formatter,
                    contentColor = colors.headlineContentColor,
                )
            }
        }

    if (isInline) {
        DateRangePicker(
            state = state,
            dateFormatter = formatter,
            colors = colors,
            // Explicitly set requiredHeight because DateRangePicker uses LazyColumn
            // and measuring it with no constraints results in an infinite width and/or OutOfMemoryError exception.
            modifier = Modifier.requiredHeight(432.dp),
            title = title,
            headline = headline,
            showModeToggle = showModeToggle,
        )
    } else if (isOpen) {
        DatePickerDialog(
            onDismissRequest = onCancel,
            confirmButton = {
                TextButton(onClick = onConfirm, colors = textButtonColors) {
                    Text(confirmText)
                }
            },
            dismissButton = {
                TextButton(onClick = onCancel, colors = textButtonColors) {
                    Text(cancelText)
                }
            },
            colors = colors,
        ) {
            DateRangePicker(
                state = state,
                dateFormatter = formatter,
                colors = colors,
                title = title,
                headline = headline,
                showModeToggle = showModeToggle,
            )
        }
    }
}

// Based on compose/material3/material3/src/commonMain/kotlin/androidx/compose/material3/DateRangePicker.kt
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun DateRangePickerTitle(
    title: String,
    displayMode: DisplayMode,
    contentColor: Color,
) {
    val modifier = Modifier.padding(PaddingValues(start = 64.dp, end = 12.dp))

    if (title.isEmpty()) {
        DateRangePickerDefaults.DateRangePickerTitle(
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

// Based on compose/material3/material3/src/commonMain/kotlin/androidx/compose/material3/DateRangePicker.kt
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun DateRangePickerHeadline(
    headline: String,
    selectedStartDateMillis: Long?,
    selectedEndDateMillis: Long?,
    displayMode: DisplayMode,
    dateFormatter: DatePickerFormatter,
    contentColor: Color,
) {
    val modifier = Modifier.padding(PaddingValues(start = 64.dp, end = 12.dp, bottom = 12.dp))

    if (headline.isEmpty()) {
        DateRangePickerDefaults.DateRangePickerHeadline(
            selectedStartDateMillis = selectedStartDateMillis,
            selectedEndDateMillis = selectedEndDateMillis,
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
