package com.s77rt.rtndatepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import java.time.Instant
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun RTNDatePickerView(
    viewModel: RTNDatePickerViewModel,
    onChange: (date: Long?) -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
) {
    val type by viewModel.type.collectAsState()
    val isOpen by viewModel.isOpen.collectAsState()
    val isInline by viewModel.isInline.collectAsState()
    val datePickerState by viewModel.datePickerState.collectAsState()
    val timePickerState by viewModel.timePickerState.collectAsState()
    val confirmText by viewModel.confirmText.collectAsState()
    val cancelText by viewModel.cancelText.collectAsState()

    LaunchedEffect(datePickerState.selectedDateMillis, timePickerState.hour, timePickerState.minute) {
        val date = datePickerState.selectedDateMillis
        if (date == null) {
            onChange(null)
        } else {
            onChange(
                Instant
                    .ofEpochMilli(date)
                    .atZone(ZoneId.systemDefault())
                    .withHour(timePickerState.hour)
                    .withMinute(timePickerState.minute)
                    .toEpochSecond() * 1000,
            )
        }
    }

    if (type == "time") {
        if (isInline) {
            TimePicker(state = timePickerState)
        } else if (isOpen) {
            TimePickerDialog(
                onDismissRequest = onCancel,
                confirmButton = {
                    TextButton(onClick = onConfirm) {
                        Text(confirmText)
                    }
                },
                dismissButton = {
                    TextButton(onClick = onCancel) {
                        Text(cancelText)
                    }
                },
            ) {
                TimePicker(state = timePickerState)
            }
        }
    } else {
        if (isInline) {
            DatePicker(state = datePickerState, headline = null, title = null, showModeToggle = false)
        } else if (isOpen) {
            DatePickerDialog(
                onDismissRequest = onCancel,
                confirmButton = {
                    TextButton(onClick = onConfirm) {
                        Text(confirmText)
                    }
                },
                dismissButton = {
                    TextButton(onClick = onCancel) {
                        Text(cancelText)
                    }
                },
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

// Based on compose/material3/material3/src/androidMain/kotlin/androidx/compose/material3/DatePickerDialog.android.kt
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun TimePickerDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.wrapContentHeight(),
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            shape = DatePickerDefaults.shape,
            tonalElevation = DatePickerDefaults.TonalElevation,
            color = DatePickerDefaults.colors().containerColor,
        ) {
            Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Select time",
                    modifier = Modifier.padding(bottom = 20.dp),
                    style = MaterialTheme.typography.labelMedium,
                )
                Box(Modifier.weight(1f, fill = false)) {
                    this@Column.content()
                }
                Box(
                    modifier =
                        Modifier.align(Alignment.End),
                ) {
                    Row {
                        CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 0.dp) {
                            dismissButton()
                            confirmButton()
                        }
                    }
                }
            }
        }
    }
}
