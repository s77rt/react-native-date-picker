package com.s77rt.rtndatepicker

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.TimePickerSelectionMode
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
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
    private val _confirmText = MutableStateFlow("OK")
    private val _cancelText = MutableStateFlow("Cancel")
    private val _title = MutableStateFlow<String?>("")
    private val _headline = MutableStateFlow<String?>("")
    private val _containerColor = MutableStateFlow(Color.Unspecified)
    private val _titleContentColor = MutableStateFlow(Color.Unspecified)
    private val _headlineContentColor = MutableStateFlow(Color.Unspecified)
    private val _weekdayContentColor = MutableStateFlow(Color.Unspecified)
    private val _subheadContentColor = MutableStateFlow(Color.Unspecified)
    private val _navigationContentColor = MutableStateFlow(Color.Unspecified)
    private val _yearContentColor = MutableStateFlow(Color.Unspecified)
    private val _disabledYearContentColor = MutableStateFlow(Color.Unspecified)
    private val _currentYearContentColor = MutableStateFlow(Color.Unspecified)
    private val _selectedYearContentColor = MutableStateFlow(Color.Unspecified)
    private val _disabledSelectedYearContentColor = MutableStateFlow(Color.Unspecified)
    private val _selectedYearContainerColor = MutableStateFlow(Color.Unspecified)
    private val _disabledSelectedYearContainerColor = MutableStateFlow(Color.Unspecified)
    private val _dayContentColor = MutableStateFlow(Color.Unspecified)
    private val _disabledDayContentColor = MutableStateFlow(Color.Unspecified)
    private val _selectedDayContentColor = MutableStateFlow(Color.Unspecified)
    private val _disabledSelectedDayContentColor = MutableStateFlow(Color.Unspecified)
    private val _selectedDayContainerColor = MutableStateFlow(Color.Unspecified)
    private val _disabledSelectedDayContainerColor = MutableStateFlow(Color.Unspecified)
    private val _todayContentColor = MutableStateFlow(Color.Unspecified)
    private val _todayDateBorderColor = MutableStateFlow(Color.Unspecified)
    private val _dayInSelectionRangeContainerColor = MutableStateFlow(Color.Unspecified)
    private val _dayInSelectionRangeContentColor = MutableStateFlow(Color.Unspecified)
    private val _dividerColor = MutableStateFlow(Color.Unspecified)
    private val _clockDialColor = MutableStateFlow(Color.Unspecified)
    private val _selectorColor = MutableStateFlow(Color.Unspecified)
    private val _periodSelectorBorderColor = MutableStateFlow(Color.Unspecified)
    private val _clockDialSelectedContentColor = MutableStateFlow(Color.Unspecified)
    private val _clockDialUnselectedContentColor = MutableStateFlow(Color.Unspecified)
    private val _periodSelectorSelectedContainerColor = MutableStateFlow(Color.Unspecified)
    private val _periodSelectorUnselectedContainerColor = MutableStateFlow(Color.Unspecified)
    private val _periodSelectorSelectedContentColor = MutableStateFlow(Color.Unspecified)
    private val _periodSelectorUnselectedContentColor = MutableStateFlow(Color.Unspecified)
    private val _timeSelectorSelectedContainerColor = MutableStateFlow(Color.Unspecified)
    private val _timeSelectorUnselectedContainerColor = MutableStateFlow(Color.Unspecified)
    private val _timeSelectorSelectedContentColor = MutableStateFlow(Color.Unspecified)
    private val _timeSelectorUnselectedContentColor = MutableStateFlow(Color.Unspecified)

    val type: StateFlow<String> get() = _type
    val isOpen: StateFlow<Boolean> get() = _isOpen
    val isInline: StateFlow<Boolean> get() = _isInline
    val datePickerState: StateFlow<DatePickerState> get() = _datePickerState
    val timePickerState: StateFlow<TimePickerState> get() = _timePickerState
    val confirmText: StateFlow<String> get() = _confirmText
    val cancelText: StateFlow<String> get() = _cancelText
    val title: StateFlow<String?> get() = _title
    val headline: StateFlow<String?> get() = _headline
    val containerColor: StateFlow<Color> get() = _containerColor
    val titleContentColor: StateFlow<Color> get() = _titleContentColor
    val headlineContentColor: StateFlow<Color> get() = _headlineContentColor
    val weekdayContentColor: StateFlow<Color> get() = _weekdayContentColor
    val subheadContentColor: StateFlow<Color> get() = _subheadContentColor
    val navigationContentColor: StateFlow<Color> get() = _navigationContentColor
    val yearContentColor: StateFlow<Color> get() = _yearContentColor
    val disabledYearContentColor: StateFlow<Color> get() = _disabledYearContentColor
    val currentYearContentColor: StateFlow<Color> get() = _currentYearContentColor
    val selectedYearContentColor: StateFlow<Color> get() = _selectedYearContentColor
    val disabledSelectedYearContentColor: StateFlow<Color> get() = _disabledSelectedYearContentColor
    val selectedYearContainerColor: StateFlow<Color> get() = _selectedYearContainerColor
    val disabledSelectedYearContainerColor: StateFlow<Color> get() = _disabledSelectedYearContainerColor
    val dayContentColor: StateFlow<Color> get() = _dayContentColor
    val disabledDayContentColor: StateFlow<Color> get() = _disabledDayContentColor
    val selectedDayContentColor: StateFlow<Color> get() = _selectedDayContentColor
    val disabledSelectedDayContentColor: StateFlow<Color> get() = _disabledSelectedDayContentColor
    val selectedDayContainerColor: StateFlow<Color> get() = _selectedDayContainerColor
    val disabledSelectedDayContainerColor: StateFlow<Color> get() = _disabledSelectedDayContainerColor
    val todayContentColor: StateFlow<Color> get() = _todayContentColor
    val todayDateBorderColor: StateFlow<Color> get() = _todayDateBorderColor
    val dayInSelectionRangeContainerColor: StateFlow<Color> get() = _dayInSelectionRangeContainerColor
    val dayInSelectionRangeContentColor: StateFlow<Color> get() = _dayInSelectionRangeContentColor
    val dividerColor: StateFlow<Color> get() = _dividerColor
    val clockDialColor: StateFlow<Color> get() = _clockDialColor
    val selectorColor: StateFlow<Color> get() = _selectorColor
    val periodSelectorBorderColor: StateFlow<Color> get() = _periodSelectorBorderColor
    val clockDialSelectedContentColor: StateFlow<Color> get() = _clockDialSelectedContentColor
    val clockDialUnselectedContentColor: StateFlow<Color> get() = _clockDialUnselectedContentColor
    val periodSelectorSelectedContainerColor: StateFlow<Color> get() = _periodSelectorSelectedContainerColor
    val periodSelectorUnselectedContainerColor: StateFlow<Color> get() = _periodSelectorUnselectedContainerColor
    val periodSelectorSelectedContentColor: StateFlow<Color> get() = _periodSelectorSelectedContentColor
    val periodSelectorUnselectedContentColor: StateFlow<Color> get() = _periodSelectorUnselectedContentColor
    val timeSelectorSelectedContainerColor: StateFlow<Color> get() = _timeSelectorSelectedContainerColor
    val timeSelectorUnselectedContainerColor: StateFlow<Color> get() = _timeSelectorUnselectedContainerColor
    val timeSelectorSelectedContentColor: StateFlow<Color> get() = _timeSelectorSelectedContentColor
    val timeSelectorUnselectedContentColor: StateFlow<Color> get() = _timeSelectorUnselectedContentColor

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

    fun updateConfirmText(newConfirmText: String) {
        _confirmText.value = newConfirmText
    }

    fun updateCancelText(newCancelText: String) {
        _cancelText.value = newCancelText
    }

    fun updateTitle(newTitle: String?) {
        _title.value = newTitle
    }

    fun updateHeadline(newHeadline: String?) {
        _headline.value = newHeadline
    }

    fun updateContainerColor(newContainerColor: Color) {
        _containerColor.value = newContainerColor
    }

    fun updateTitleContentColor(newTitleContentColor: Color) {
        _titleContentColor.value = newTitleContentColor
    }

    fun updateHeadlineContentColor(newHeadlineContentColor: Color) {
        _headlineContentColor.value = newHeadlineContentColor
    }

    fun updateWeekdayContentColor(newWeekdayContentColor: Color) {
        _weekdayContentColor.value = newWeekdayContentColor
    }

    fun updateSubheadContentColor(newSubheadContentColor: Color) {
        _subheadContentColor.value = newSubheadContentColor
    }

    fun updateNavigationContentColor(newNavigationContentColor: Color) {
        _navigationContentColor.value = newNavigationContentColor
    }

    fun updateYearContentColor(newYearContentColor: Color) {
        _yearContentColor.value = newYearContentColor
    }

    fun updateDisabledYearContentColor(newDisabledYearContentColor: Color) {
        _disabledYearContentColor.value = newDisabledYearContentColor
    }

    fun updateCurrentYearContentColor(newCurrentYearContentColor: Color) {
        _currentYearContentColor.value = newCurrentYearContentColor
    }

    fun updateSelectedYearContentColor(newSelectedYearContentColor: Color) {
        _selectedYearContentColor.value = newSelectedYearContentColor
    }

    fun updateDisabledSelectedYearContentColor(newDisabledSelectedYearContentColor: Color) {
        _disabledSelectedYearContentColor.value = newDisabledSelectedYearContentColor
    }

    fun updateSelectedYearContainerColor(newSelectedYearContainerColor: Color) {
        _selectedYearContainerColor.value = newSelectedYearContainerColor
    }

    fun updateDisabledSelectedYearContainerColor(newDisabledSelectedYearContainerColor: Color) {
        _disabledSelectedYearContainerColor.value = newDisabledSelectedYearContainerColor
    }

    fun updateDayContentColor(newDayContentColor: Color) {
        _dayContentColor.value = newDayContentColor
    }

    fun updateDisabledDayContentColor(newDisabledDayContentColor: Color) {
        _disabledDayContentColor.value = newDisabledDayContentColor
    }

    fun updateSelectedDayContentColor(newSelectedDayContentColor: Color) {
        _selectedDayContentColor.value = newSelectedDayContentColor
    }

    fun updateDisabledSelectedDayContentColor(newDisabledSelectedDayContentColor: Color) {
        _disabledSelectedDayContentColor.value = newDisabledSelectedDayContentColor
    }

    fun updateSelectedDayContainerColor(newSelectedDayContainerColor: Color) {
        _selectedDayContainerColor.value = newSelectedDayContainerColor
    }

    fun updateDisabledSelectedDayContainerColor(newDisabledSelectedDayContainerColor: Color) {
        _disabledSelectedDayContainerColor.value = newDisabledSelectedDayContainerColor
    }

    fun updateTodayContentColor(newTodayContentColor: Color) {
        _todayContentColor.value = newTodayContentColor
    }

    fun updateTodayDateBorderColor(newTodayDateBorderColor: Color) {
        _todayDateBorderColor.value = newTodayDateBorderColor
    }

    fun updateDayInSelectionRangeContainerColor(newDayInSelectionRangeContainerColor: Color) {
        _dayInSelectionRangeContainerColor.value = newDayInSelectionRangeContainerColor
    }

    fun updateDayInSelectionRangeContentColor(newDayInSelectionRangeContentColor: Color) {
        _dayInSelectionRangeContentColor.value = newDayInSelectionRangeContentColor
    }

    fun updateDividerColor(newDividerColor: Color) {
        _dividerColor.value = newDividerColor
    }

    fun updateClockDialColor(newClockDialColor: Color) {
        _clockDialColor.value = newClockDialColor
    }

    fun updateSelectorColor(newSelectorColor: Color) {
        _selectorColor.value = newSelectorColor
    }

    fun updatePeriodSelectorBorderColor(newPeriodSelectorBorderColor: Color) {
        _periodSelectorBorderColor.value = newPeriodSelectorBorderColor
    }

    fun updateClockDialSelectedContentColor(newClockDialSelectedContentColor: Color) {
        _clockDialSelectedContentColor.value = newClockDialSelectedContentColor
    }

    fun updateClockDialUnselectedContentColor(newClockDialUnselectedContentColor: Color) {
        _clockDialUnselectedContentColor.value = newClockDialUnselectedContentColor
    }

    fun updatePeriodSelectorSelectedContainerColor(newPeriodSelectorSelectedContainerColor: Color) {
        _periodSelectorSelectedContainerColor.value = newPeriodSelectorSelectedContainerColor
    }

    fun updatePeriodSelectorUnselectedContainerColor(newPeriodSelectorUnselectedContainerColor: Color) {
        _periodSelectorUnselectedContainerColor.value = newPeriodSelectorUnselectedContainerColor
    }

    fun updatePeriodSelectorSelectedContentColor(newPeriodSelectorSelectedContentColor: Color) {
        _periodSelectorSelectedContentColor.value = newPeriodSelectorSelectedContentColor
    }

    fun updatePeriodSelectorUnselectedContentColor(newPeriodSelectorUnselectedContentColor: Color) {
        _periodSelectorUnselectedContentColor.value = newPeriodSelectorUnselectedContentColor
    }

    fun updateTimeSelectorSelectedContainerColor(newTimeSelectorSelectedContainerColor: Color) {
        _timeSelectorSelectedContainerColor.value = newTimeSelectorSelectedContainerColor
    }

    fun updateTimeSelectorUnselectedContainerColor(newTimeSelectorUnselectedContainerColor: Color) {
        _timeSelectorUnselectedContainerColor.value = newTimeSelectorUnselectedContainerColor
    }

    fun updateTimeSelectorSelectedContentColor(newTimeSelectorSelectedContentColor: Color) {
        _timeSelectorSelectedContentColor.value = newTimeSelectorSelectedContentColor
    }

    fun updateTimeSelectorUnselectedContentColor(newTimeSelectorUnselectedContentColor: Color) {
        _timeSelectorUnselectedContentColor.value = newTimeSelectorUnselectedContentColor
    }
}
