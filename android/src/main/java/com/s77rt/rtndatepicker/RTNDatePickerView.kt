package com.s77rt.rtndatepicker

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.Instant
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
class RTNDatePickerViewModel : ViewModel() {
    private var lowerBound: Long? = null
    private var upperBound: Long? = null

    private val _isOpen = MutableStateFlow(false)
    private val _datePickerState =
        MutableStateFlow(
            DatePickerState(
                locale = Locale.getDefault(),
                selectableDates =
                    object : SelectableDates {
                        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                            val lb = lowerBound
                            val ub = upperBound

                            if (lb != null && utcTimeMillis < lb) {
                                return false
                            }

                            if (ub != null && utcTimeMillis > ub) {
                                return false
                            }

                            return true
                        }
                    },
            ),
        )

    val isOpen: StateFlow<Boolean> get() = _isOpen
    val datePickerState: StateFlow<DatePickerState> get() = _datePickerState

    fun syncDisplayedMonth() {
        var newDisplayedMonthMillis = _datePickerState.value.selectedDateMillis

        if (newDisplayedMonthMillis == null) {
            newDisplayedMonthMillis = Instant.now().toEpochMilli()
        }

        if (!_datePickerState.value.selectableDates.isSelectableDate(newDisplayedMonthMillis)) {
            val lb = lowerBound
            val ub = upperBound

            if (lb != null && newDisplayedMonthMillis < lb) {
                newDisplayedMonthMillis = lb
            } else if (ub != null && newDisplayedMonthMillis > ub) {
                newDisplayedMonthMillis = ub
            }
        }

        _datePickerState.value.displayedMonthMillis = newDisplayedMonthMillis
    }

    fun updateIsOpen(newIsOpen: Boolean) {
        if (newIsOpen) {
            syncDisplayedMonth()
        }
        _isOpen.value = newIsOpen
    }

    fun updateValue(newValue: Long?) {
        _datePickerState.value.selectedDateMillis = newValue
        syncDisplayedMonth()
    }

    fun updateRange(
        newLowerBound: Long?,
        newUpperBound: Long?,
    ) {
        lowerBound = newLowerBound
        upperBound = newUpperBound
        syncDisplayedMonth()
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
            DatePicker(state = datePickerState)
        }
    }
}
