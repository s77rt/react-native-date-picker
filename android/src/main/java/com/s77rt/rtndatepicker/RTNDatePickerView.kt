package com.s77rt.rtndatepicker

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RTNDatePickerViewModel : ViewModel() {
    private val _isOpen = MutableStateFlow(false)

    val isOpen: StateFlow<Boolean> get() = _isOpen

    fun updateIsOpen(newIsOpen: Boolean) {
        _isOpen.value = newIsOpen
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun RTNDatePickerView(
    viewModel: RTNDatePickerViewModel,
    onChange: () -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
) {
    val isOpen by viewModel.isOpen.collectAsState()
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)

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
            DatePicker(state = datePickerState)
        }
    }
}
