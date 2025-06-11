package com.s77rt.rtndatepicker

import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    val isInline by viewModel.isInline.collectAsState()
    val datePickerState by viewModel.datePickerState.collectAsState()
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

    val datePickerFormatter = remember { DatePickerDefaults.dateFormatter() }

    val datePickerColors =
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
        )
    val timePickerColors =
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
        )

    val datePickerTextButtonColors = ButtonDefaults.textButtonColors(contentColor = datePickerColors.selectedDayContainerColor)
    val timePickerTextButtonColors = ButtonDefaults.textButtonColors(contentColor = timePickerColors.selectorColor)

    val titleValue = title
    val datePickerTitle =
        if (titleValue == null) {
            null
        } else {
            @Composable {
                DatePickerTitle(
                    title = titleValue,
                    displayMode = datePickerState.displayMode,
                    contentColor = datePickerColors.titleContentColor,
                )
            }
        }
    val timePickerTitle =
        if (titleValue == null) {
            null
        } else {
            @Composable {
                TimePickerTitle(
                    title = titleValue,
                    contentColor = datePickerColors.titleContentColor,
                )
            }
        }

    val headlineValue = headline
    val datePickerHeadline =
        if (headlineValue == null) {
            null
        } else {
            @Composable {
                DatePickerHeadline(
                    headline = headlineValue,
                    selectedDateMillis = datePickerState.selectedDateMillis,
                    displayMode = datePickerState.displayMode,
                    dateFormatter = datePickerFormatter,
                    contentColor = datePickerColors.headlineContentColor,
                )
            }
        }

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

    if (type == "time") {
        if (isInline) {
            TimePicker(state = timePickerState, colors = timePickerColors)
        } else if (isOpen) {
            TimePickerDialog(
                onDismissRequest = onCancel,
                confirmButton = {
                    TextButton(onClick = onConfirm, colors = timePickerTextButtonColors) {
                        Text(confirmText)
                    }
                },
                dismissButton = {
                    TextButton(onClick = onCancel, colors = timePickerTextButtonColors) {
                        Text(cancelText)
                    }
                },
                colors = timePickerColors,
                title = timePickerTitle,
            ) {
                TimePicker(state = timePickerState, colors = timePickerColors)
            }
        }
    } else {
        if (isInline) {
            DatePicker(
                state = datePickerState,
                dateFormatter = datePickerFormatter,
                colors = datePickerColors,
                // Explicitly set requiredWidth because DatePicker uses LazyRow
                // and measuring it with no constraints results in an infinite width and/or OutOfMemoryError exception.
                modifier = Modifier.requiredWidth(360.dp),
                title = datePickerTitle,
                headline = datePickerHeadline,
                showModeToggle = showModeToggle,
            )
        } else if (isOpen) {
            DatePickerDialog(
                onDismissRequest = onCancel,
                confirmButton = {
                    TextButton(onClick = onConfirm, colors = datePickerTextButtonColors) {
                        Text(confirmText)
                    }
                },
                dismissButton = {
                    TextButton(onClick = onCancel, colors = datePickerTextButtonColors) {
                        Text(cancelText)
                    }
                },
                colors = datePickerColors,
            ) {
                DatePicker(
                    state = datePickerState,
                    dateFormatter = datePickerFormatter,
                    colors = datePickerColors,
                    title = datePickerTitle,
                    headline = datePickerHeadline,
                    showModeToggle = showModeToggle,
                )
            }
        }
    }
}
