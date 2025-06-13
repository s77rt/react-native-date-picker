package com.s77rt.rtndatepicker

import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerDefaults
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
    onChange: (dates: LongArray) -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
) {
    val type by viewModel.type.collectAsState()
    val isOpen by viewModel.isOpen.collectAsState()
    val isMultiple by viewModel.isMultiple.collectAsState()
    val isInline by viewModel.isInline.collectAsState()
    val datePickerState by viewModel.datePickerState.collectAsState()
    val dateRangePickerState by viewModel.dateRangePickerState.collectAsState()
    val timePickerState by viewModel.timePickerState.collectAsState()
    val confirmText by viewModel.confirmText.collectAsState()
    val cancelText by viewModel.cancelText.collectAsState()
    val title by viewModel.title.collectAsState()
    val headline by viewModel.headline.collectAsState()
    val showModeToggle by viewModel.showModeToggle.collectAsState()
    val containerColor by viewModel.containerColor.collectAsState()
    val titleContentColor by viewModel.titleContentColor.collectAsState()
    val headlineContentColor by viewModel.headlineContentColor.collectAsState()
    val weekdayContentColor by viewModel.weekdayContentColor.collectAsState()
    val subheadContentColor by viewModel.subheadContentColor.collectAsState()
    val navigationContentColor by viewModel.navigationContentColor.collectAsState()
    val yearContentColor by viewModel.yearContentColor.collectAsState()
    val disabledYearContentColor by viewModel.disabledYearContentColor.collectAsState()
    val currentYearContentColor by viewModel.currentYearContentColor.collectAsState()
    val selectedYearContentColor by viewModel.selectedYearContentColor.collectAsState()
    val disabledSelectedYearContentColor by viewModel.disabledSelectedYearContentColor.collectAsState()
    val selectedYearContainerColor by viewModel.selectedYearContainerColor.collectAsState()
    val disabledSelectedYearContainerColor by viewModel.disabledSelectedYearContainerColor.collectAsState()
    val dayContentColor by viewModel.dayContentColor.collectAsState()
    val disabledDayContentColor by viewModel.disabledDayContentColor.collectAsState()
    val selectedDayContentColor by viewModel.selectedDayContentColor.collectAsState()
    val disabledSelectedDayContentColor by viewModel.disabledSelectedDayContentColor.collectAsState()
    val selectedDayContainerColor by viewModel.selectedDayContainerColor.collectAsState()
    val disabledSelectedDayContainerColor by viewModel.disabledSelectedDayContainerColor.collectAsState()
    val todayContentColor by viewModel.todayContentColor.collectAsState()
    val todayDateBorderColor by viewModel.todayDateBorderColor.collectAsState()
    val dayInSelectionRangeContainerColor by viewModel.dayInSelectionRangeContainerColor.collectAsState()
    val dayInSelectionRangeContentColor by viewModel.dayInSelectionRangeContentColor.collectAsState()
    val dividerColor by viewModel.dividerColor.collectAsState()
    val clockDialColor by viewModel.clockDialColor.collectAsState()
    val selectorColor by viewModel.selectorColor.collectAsState()
    val periodSelectorBorderColor by viewModel.periodSelectorBorderColor.collectAsState()
    val clockDialSelectedContentColor by viewModel.clockDialSelectedContentColor.collectAsState()
    val clockDialUnselectedContentColor by viewModel.clockDialUnselectedContentColor.collectAsState()
    val periodSelectorSelectedContainerColor by viewModel.periodSelectorSelectedContainerColor.collectAsState()
    val periodSelectorUnselectedContainerColor by viewModel.periodSelectorUnselectedContainerColor.collectAsState()
    val periodSelectorSelectedContentColor by viewModel.periodSelectorSelectedContentColor.collectAsState()
    val periodSelectorUnselectedContentColor by viewModel.periodSelectorUnselectedContentColor.collectAsState()
    val timeSelectorSelectedContainerColor by viewModel.timeSelectorSelectedContainerColor.collectAsState()
    val timeSelectorUnselectedContainerColor by viewModel.timeSelectorUnselectedContainerColor.collectAsState()
    val timeSelectorSelectedContentColor by viewModel.timeSelectorSelectedContentColor.collectAsState()
    val timeSelectorUnselectedContentColor by viewModel.timeSelectorUnselectedContentColor.collectAsState()

    LaunchedEffect(datePickerState.selectedDateMillis, timePickerState.hour, timePickerState.minute) {
        val date = datePickerState.selectedDateMillis
        if (date == null) {
            onChange(longArrayOf())
        } else {
            onChange(
                longArrayOf(
                    Instant
                        .ofEpochMilli(date)
                        .atZone(ZoneId.of("UTC"))
                        .toLocalDate()
                        .atStartOfDay(ZoneId.systemDefault())
                        .withHour(timePickerState.hour)
                        .withMinute(timePickerState.minute)
                        .toEpochSecond() * 1000,
                ),
            )
        }
    }

    if (isMultiple) {
        DateRangePickerWrapper(
            isInline = isInline,
            isOpen = isOpen,
            state = dateRangePickerState,
            colors =
                DatePickerDefaults.colors(
                    containerColor = containerColor,
                    titleContentColor = titleContentColor,
                    headlineContentColor = headlineContentColor,
                    weekdayContentColor = weekdayContentColor,
                    subheadContentColor = subheadContentColor,
                    navigationContentColor = navigationContentColor,
                    yearContentColor = yearContentColor,
                    disabledYearContentColor = disabledYearContentColor,
                    currentYearContentColor = currentYearContentColor,
                    selectedYearContentColor = selectedYearContentColor,
                    disabledSelectedYearContentColor = disabledSelectedYearContentColor,
                    selectedYearContainerColor = selectedYearContainerColor,
                    disabledSelectedYearContainerColor = disabledSelectedYearContainerColor,
                    dayContentColor = dayContentColor,
                    disabledDayContentColor = disabledDayContentColor,
                    selectedDayContentColor = selectedDayContentColor,
                    disabledSelectedDayContentColor = disabledSelectedDayContentColor,
                    selectedDayContainerColor = selectedDayContainerColor,
                    disabledSelectedDayContainerColor = disabledSelectedDayContainerColor,
                    todayContentColor = todayContentColor,
                    todayDateBorderColor = todayDateBorderColor,
                    dayInSelectionRangeContainerColor = dayInSelectionRangeContainerColor,
                    dayInSelectionRangeContentColor = dayInSelectionRangeContentColor,
                    dividerColor = dividerColor,
                ),
            titleText = title,
            headlineText = headline,
            showModeToggle = showModeToggle,
            confirmText = confirmText,
            cancelText = cancelText,
            onConfirm = onConfirm,
            onCancel = onCancel,
        )
    } else {
        if (type == "time") {
            TimePickerWrapper(
                isInline = isInline,
                isOpen = isOpen,
                state = timePickerState,
                colors =
                    TimePickerDefaults.colors(
                        clockDialColor = clockDialColor,
                        selectorColor = selectorColor,
                        containerColor = containerColor,
                        periodSelectorBorderColor = periodSelectorBorderColor,
                        clockDialSelectedContentColor = clockDialSelectedContentColor,
                        clockDialUnselectedContentColor = clockDialUnselectedContentColor,
                        periodSelectorSelectedContainerColor = periodSelectorSelectedContainerColor,
                        periodSelectorUnselectedContainerColor = periodSelectorUnselectedContainerColor,
                        periodSelectorSelectedContentColor = periodSelectorSelectedContentColor,
                        periodSelectorUnselectedContentColor = periodSelectorUnselectedContentColor,
                        timeSelectorSelectedContainerColor = timeSelectorSelectedContainerColor,
                        timeSelectorUnselectedContainerColor = timeSelectorUnselectedContainerColor,
                        timeSelectorSelectedContentColor = timeSelectorSelectedContentColor,
                        timeSelectorUnselectedContentColor = timeSelectorUnselectedContentColor,
                    ),
                titleText = title,
                titleTextColor = titleContentColor,
                confirmText = confirmText,
                cancelText = cancelText,
                onConfirm = onConfirm,
                onCancel = onCancel,
            )
        } else {
            DatePickerWrapper(
                isInline = isInline,
                isOpen = isOpen,
                state = datePickerState,
                colors =
                    DatePickerDefaults.colors(
                        containerColor = containerColor,
                        titleContentColor = titleContentColor,
                        headlineContentColor = headlineContentColor,
                        weekdayContentColor = weekdayContentColor,
                        subheadContentColor = subheadContentColor,
                        navigationContentColor = navigationContentColor,
                        yearContentColor = yearContentColor,
                        disabledYearContentColor = disabledYearContentColor,
                        currentYearContentColor = currentYearContentColor,
                        selectedYearContentColor = selectedYearContentColor,
                        disabledSelectedYearContentColor = disabledSelectedYearContentColor,
                        selectedYearContainerColor = selectedYearContainerColor,
                        disabledSelectedYearContainerColor = disabledSelectedYearContainerColor,
                        dayContentColor = dayContentColor,
                        disabledDayContentColor = disabledDayContentColor,
                        selectedDayContentColor = selectedDayContentColor,
                        disabledSelectedDayContentColor = disabledSelectedDayContentColor,
                        selectedDayContainerColor = selectedDayContainerColor,
                        disabledSelectedDayContainerColor = disabledSelectedDayContainerColor,
                        todayContentColor = todayContentColor,
                        todayDateBorderColor = todayDateBorderColor,
                        dayInSelectionRangeContainerColor = dayInSelectionRangeContainerColor,
                        dayInSelectionRangeContentColor = dayInSelectionRangeContentColor,
                        dividerColor = dividerColor,
                    ),
                titleText = title,
                headlineText = headline,
                showModeToggle = showModeToggle,
                confirmText = confirmText,
                cancelText = cancelText,
                onConfirm = onConfirm,
                onCancel = onCancel,
            )
        }
    }
}
