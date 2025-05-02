package com.s77rt.rtndatepicker

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
class RTNDatePickerViewModel : ViewModel() {
    private val _isOpen = MutableStateFlow(false)
    private val _datePickerState = MutableStateFlow(DatePickerState(Locale.getDefault()))

    val isOpen: StateFlow<Boolean> get() = _isOpen
    val datePickerState: StateFlow<DatePickerState> get() = _datePickerState

    fun updateIsOpen(newIsOpen: Boolean) {
        _isOpen.value = newIsOpen
    }

    fun updateValue(newValue: Long) {
        _datePickerState.value.displayedMonthMillis = newValue
        _datePickerState.value.selectedDateMillis = newValue
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun RTNDatePickerView(
    viewModel: RTNDatePickerViewModel,
    onChange: (date: Long?) -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
) {
    val isOpen by viewModel.isOpen.collectAsState()
    val datePickerState by viewModel.datePickerState.collectAsState()

    LaunchedEffect(datePickerState.selectedDateMillis) {
        onChange(datePickerState.selectedDateMillis)
    }

    if (isOpen) {
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
            DatePicker(state = datePickerState, showModeToggle = false)
        }
    }
}
