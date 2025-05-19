package com.s77rt.rtndatepicker

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
            AlertDialog(
                onDismissRequest = onCancel,
                confirmButton = {
                    TextButton(onClick = onConfirm) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onCancel) {
                        Text("Cancel")
                    }
                },
                text = { TimePicker(state = timePickerState) },
            )
        }
    } else {
        if (isInline) {
            DatePicker(state = datePickerState, headline = null, title = null, showModeToggle = false)
        } else if (isOpen) {
            DatePickerDialog(
                onDismissRequest = onCancel,
                confirmButton = {
                    TextButton(onClick = onConfirm) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onCancel) {
                        Text("Cancel")
                    }
                },
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}
