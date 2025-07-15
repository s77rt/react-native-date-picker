package com.s77rt.rtndatepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import java.text.DateFormatSymbols
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun YearMonthPickerWrapper(
    isInline: Boolean,
    isOpen: Boolean,
    state: YearMonthPickerState,
    titleText: String?,
    titleTextColor: Color,
    confirmText: String,
    cancelText: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
) {
    val title =
        if (titleText == null) {
            null
        } else {
            @Composable {
                YearMonthPickerTitle(
                    title = titleText,
                    contentColor = titleTextColor,
                )
            }
        }

    if (isInline) {
        YearMonthPicker(
            state = state,
            // Explicitly set requiredHeight because YearMonthPicker uses LazyColumn
            // and measuring it with no constraints results in an infinite width and/or OutOfMemoryError exception.
            modifier = Modifier.requiredHeight(428.dp),
        )
    } else if (isOpen) {
        YearMonthPickerDialog(
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
            title = title,
        ) {
            YearMonthPicker(state = state)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun YearMonthPickerTitle(
    title: String,
    contentColor: Color,
) {
    Text(
        text = if (title.isEmpty()) "Select month" else title,
        modifier = Modifier.padding(bottom = 20.dp),
        style = MaterialTheme.typography.labelMedium,
        color = contentColor,
    )
}

// Based on compose/material3/material3/src/androidMain/kotlin/androidx/compose/material3/DatePickerDialog.android.kt
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun YearMonthPickerDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    title: (@Composable () -> Unit)?,
    content: @Composable ColumnScope.() -> Unit,
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.wrapContentHeight(),
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier =
                Modifier
                    .requiredWidth(360.dp)
                    .heightIn(max = 548.dp),
            shape = DatePickerDefaults.shape,
            tonalElevation = DatePickerDefaults.TonalElevation,
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
        ) {
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                if (title != null) {
                    Box(Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp)) {
                        title()
                    }
                }
                Box(Modifier.weight(1f, fill = false)) {
                    this@Column.content()
                }
                Box(
                    modifier =
                        Modifier.align(Alignment.End).padding(bottom = 12.dp, start = 12.dp, end = 12.dp),
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

@OptIn(ExperimentalMaterial3Api::class)
interface YearMonthPickerState {
    var selection: YearMonthPickerSelectionMode
    val yearRange: IntRange

    var month: Int
    var year: Int

    val locale: Locale
}

@JvmInline
@OptIn(ExperimentalMaterial3Api::class)
value class YearMonthPickerSelectionMode private constructor(
    val value: Int,
) {
    companion object {
        val Month = YearMonthPickerSelectionMode(0)
        val Year = YearMonthPickerSelectionMode(1)
    }

    override fun toString(): String =
        when (this) {
            Month -> "Month"
            Year -> "Year"
            else -> ""
        }
}

private class YearMonthPickerStateImpl(
    locale: Locale,
    yearRange: IntRange,
) : YearMonthPickerState {
    override var selection by mutableStateOf(YearMonthPickerSelectionMode.Month)
    override val yearRange = yearRange

    val monthState = mutableIntStateOf(1)

    override var month: Int
        get() = monthState.intValue
        set(value) {
            monthState.intValue = value
        }

    val yearState = mutableIntStateOf(2000)

    override var year: Int
        get() = yearState.intValue
        set(value) {
            yearState.intValue = value
        }

    override val locale = locale
}

@OptIn(ExperimentalMaterial3Api::class)
fun YearMonthPickerState(
    locale: Locale,
    yearRange: IntRange = IntRange(1900, 2100),
): YearMonthPickerState = YearMonthPickerStateImpl(locale = locale, yearRange = yearRange)

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun YearMonthPicker(
    state: YearMonthPickerState,
    modifier: Modifier = Modifier,
) {
    val dateFormatSymbols = remember(state.locale) { DateFormatSymbols(state.locale) }

    val monthsNames = remember(dateFormatSymbols) { dateFormatSymbols.getMonths() }
    val monthsShortNames = remember(dateFormatSymbols) { dateFormatSymbols.getShortMonths() }
    val years = remember(state.yearRange) { state.yearRange.toList() }

    val monthsListState = rememberLazyListState(initialFirstVisibleItemIndex = state.month - 1)
    val yearsListState = rememberLazyListState(initialFirstVisibleItemIndex = state.year - state.yearRange.start)

    val isSelectionMonth = state.selection == YearMonthPickerSelectionMode.Month

    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            TextButton(
                onClick = {
                    state.selection =
                        YearMonthPickerSelectionMode.Month
                },
            ) {
                val color =
                    if (isSelectionMonth) {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    } else {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = .38f)
                    }
                Text(
                    text = monthsShortNames[state.month - 1],
                    style = MaterialTheme.typography.labelLarge,
                    color = color,
                )
                if (isSelectionMonth) {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Months list", tint = color)
                }
            }
            TextButton(
                onClick = { state.selection = YearMonthPickerSelectionMode.Year },
            ) {
                val color =
                    if (isSelectionMonth) {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = .38f)
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }

                Text(
                    text = state.year.toString(),
                    style = MaterialTheme.typography.labelLarge,
                    color = color,
                )
                if (!isSelectionMonth) {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Years list", tint = color)
                }
            }
        }
        HorizontalDivider()
        if (isSelectionMonth) {
            LazyColumn(state = monthsListState, contentPadding = PaddingValues(vertical = 8.dp)) {
                items(monthsNames.size) { index ->
                    val monthName = monthsNames[index]
                    val month = index + 1
                    val isSelected = month == state.month
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "$monthName",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        },
                        onClick = { state.month = month },
                        leadingIcon = {
                            if (isSelected) {
                                Icon(Icons.Filled.Check, contentDescription = "Selected")
                            }
                        },
                        modifier =
                            Modifier
                                .height(48.dp)
                                .background(
                                    if (isSelected) MaterialTheme.colorScheme.surfaceVariant else Color.Unspecified,
                                ),
                    )
                }
            }
        } else {
            LazyColumn(state = yearsListState, contentPadding = PaddingValues(vertical = 8.dp)) {
                items(years) { year ->
                    val isSelected = year == state.year
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "$year",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        },
                        onClick = { state.year = year },
                        leadingIcon = {
                            if (isSelected) {
                                Icon(Icons.Filled.Check, contentDescription = "Selected", modifier = Modifier.size(24.dp))
                            }
                        },
                        modifier =
                            Modifier
                                .height(48.dp)
                                .background(
                                    if (isSelected) MaterialTheme.colorScheme.surfaceVariant else Color.Unspecified,
                                ),
                    )
                }
            }
        }
    }
}
