package com.s77rt.rtndatepicker

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DatePickerState
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
fun DatePickerWrapper(
    isInline: Boolean,
    isOpen: Boolean,
    state: DatePickerState,
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
                DatePickerTitle(
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
                DatePickerHeadline(
                    headline = headlineText,
                    selectedDateMillis = state.selectedDateMillis,
                    displayMode = state.displayMode,
                    dateFormatter = formatter,
                    contentColor = colors.headlineContentColor,
                )
            }
        }

    if (isInline) {
        DatePicker(
            state = state,
            dateFormatter = formatter,
            colors = colors,
            // Explicitly set requiredWidth because DatePicker uses LazyRow
            // and measuring it with no constraints results in an infinite width and/or OutOfMemoryError exception.
            modifier = Modifier.requiredWidth(360.dp),
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
            DatePicker(
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
