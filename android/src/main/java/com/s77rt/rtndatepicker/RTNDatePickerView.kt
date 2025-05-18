package com.s77rt.rtndatepicker

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerSelectionMode
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.Instant
import java.time.ZoneId
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
class RTNDatePickerViewModel : ViewModel() {
    private var lowerBound: Long? = null
    private var upperBound: Long? = null

    private val _type = MutableStateFlow("date")
    private val _isOpen = MutableStateFlow(false)
    private val _isInline = MutableStateFlow(false)
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
    private val _timePickerState =
        MutableStateFlow(
            TimePickerState(
                initialHour = 0,
                initialMinute = 0,
                is24Hour = false,
            ),
        )

    val type: StateFlow<String> get() = _type
    val isOpen: StateFlow<Boolean> get() = _isOpen
    val isInline: StateFlow<Boolean> get() = _isInline
    val datePickerState: StateFlow<DatePickerState> get() = _datePickerState
    val timePickerState: StateFlow<TimePickerState> get() = _timePickerState

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

    fun resetTimeSelection() {
        _timePickerState.value.selection = TimePickerSelectionMode.Hour
    }

    fun updateType(newType: String) {
        _type.value = newType
    }

    fun updateIsOpen(newIsOpen: Boolean) {
        if (newIsOpen) {
            syncDisplayedMonth()
            resetTimeSelection()
        }
        _isOpen.value = newIsOpen
    }

    fun updateIsInline(newIsInline: Boolean) {
        _isInline.value = newIsInline
    }

    fun updateValue(newValue: Long?) {
        // The selected date is expected to be at the start of the day in UTC
        // https://developer.android.com/reference/kotlin/androidx/compose/material3/DatePickerState#selectedDateMillis()
        _datePickerState.value.selectedDateMillis =
            if (newValue == null) {
                null
            } else {
                Instant
                    .ofEpochMilli(newValue)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .atStartOfDay(ZoneId.of("UTC"))
                    .toEpochSecond() * 1000
            }

        if (newValue != null) {
            val time =
                Instant
                    .ofEpochMilli(newValue)
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime()

            _timePickerState.value.hour = time.getHour()
            _timePickerState.value.minute = time.getMinute()
        }

        syncDisplayedMonth()
    }

    fun updateRange(
        newLowerBound: Long?,
        newUpperBound: Long?,
    ) {
        lowerBound =
            if (newLowerBound == null) {
                null
            } else {
                Instant
                    .ofEpochMilli(newLowerBound)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .atStartOfDay(ZoneId.of("UTC"))
                    .toEpochSecond() * 1000
            }

        upperBound =
            if (newUpperBound == null) {
                null
            } else {
                Instant
                    .ofEpochMilli(newUpperBound)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .atStartOfDay(ZoneId.of("UTC"))
                    .toEpochSecond() * 1000
            }

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
            DatePicker(state = datePickerState, showModeToggle = false)
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
