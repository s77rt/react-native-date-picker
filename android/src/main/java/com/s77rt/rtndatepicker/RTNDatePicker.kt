package com.s77rt.rtndatepicker

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.facebook.react.bridge.ReactContext
import com.facebook.react.uimanager.UIManagerHelper
import java.time.Instant
import java.time.ZoneId

class RTNDatePicker : FrameLayout {
    private lateinit var reactContext: ReactContext

    private val viewModel = RTNDatePickerViewModel()
    private var lastValueUpdate: Long? = null // Default value in the view model

    constructor(context: Context) : super(context) {
        reactContext = context as ReactContext
        configureComponent()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        reactContext = context as ReactContext
        configureComponent()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        reactContext = context as ReactContext
        configureComponent()
    }

    private fun configureComponent() {
        ComposeView(reactContext).also {
            it.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            it.setContent {
                RTNDatePickerView(
                    viewModel = viewModel,
                    onChange = ::onChange,
                    onConfirm = ::onConfirm,
                    onCancel = ::onCancel,
                )
            }

            addView(it)
        }
    }

    private fun onChange(date: Long?) {
        if (lastValueUpdate == date) {
            return
        }
        lastValueUpdate = date

        val surfaceId = UIManagerHelper.getSurfaceId(reactContext)
        val eventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(reactContext, id)
        val event = RTNDatePickerChangeEvent(surfaceId, id, if (date == null) null else date.toDouble())

        eventDispatcher?.dispatchEvent(event)
    }

    private fun onConfirm() {
        val surfaceId = UIManagerHelper.getSurfaceId(reactContext)
        val eventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(reactContext, id)
        val event = RTNDatePickerConfirmEvent(surfaceId, id)

        eventDispatcher?.dispatchEvent(event)
    }

    private fun onCancel() {
        val surfaceId = UIManagerHelper.getSurfaceId(reactContext)
        val eventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(reactContext, id)
        val event = RTNDatePickerCancelEvent(surfaceId, id)

        eventDispatcher?.dispatchEvent(event)
    }

    public fun setIsOpen(isOpen: Boolean) {
        viewModel.updateIsOpen(isOpen)
    }

    public fun setValue(valueUncorrected: Long?) {
        // The selected date is expected to be at the start of the day in UTC
        // https://developer.android.com/reference/kotlin/androidx/compose/material3/DatePickerState#selectedDateMillis()
        val value =
            if (valueUncorrected == null) {
                null
            } else {
                Instant
                    .ofEpochMilli(valueUncorrected)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .atStartOfDay(ZoneId.of("UTC"))
                    .toEpochSecond() * 1000
            }

        // If the value is corrected, notify js regardless of the last value update
        if (value != valueUncorrected) {
            lastValueUpdate = null
            onChange(value)
        }

        // Changing the value programmatically shouldn't trigger the onChange event
        lastValueUpdate = value

        viewModel.updateValue(value)
    }

    public fun setRange(
        lowerBoundUncorrected: Long?,
        upperBoundUncorrected: Long?,
    ) {
        val lowerBound =
            if (lowerBoundUncorrected == null) {
                null
            } else {
                Instant
                    .ofEpochMilli(lowerBoundUncorrected)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .atStartOfDay(ZoneId.of("UTC"))
                    .toEpochSecond() * 1000
            }

        val upperBound =
            if (upperBoundUncorrected == null) {
                null
            } else {
                Instant
                    .ofEpochMilli(upperBoundUncorrected)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .atStartOfDay(ZoneId.of("UTC"))
                    .toEpochSecond() * 1000
            }

        viewModel.updateRange(lowerBound, upperBound)
    }
}
