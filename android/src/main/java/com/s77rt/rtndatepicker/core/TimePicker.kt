package com.s77rt.rtndatepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePickerColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun TimePickerTitle(
    title: String,
    contentColor: Color,
) {
    Text(
        text = if (title.isEmpty()) "Select time" else title,
        modifier = Modifier.padding(bottom = 20.dp),
        style = MaterialTheme.typography.labelMedium,
        color = contentColor,
    )
}

// Based on compose/material3/material3/src/androidMain/kotlin/androidx/compose/material3/DatePickerDialog.android.kt
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun TimePickerDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    colors: TimePickerColors,
    title: (@Composable () -> Unit)?,
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
            color = colors.containerColor,
        ) {
            Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.SpaceBetween) {
                if (title != null) {
                    title()
                }
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
