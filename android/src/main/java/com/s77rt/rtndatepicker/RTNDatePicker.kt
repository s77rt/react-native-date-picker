package com.s77rt.rtndatepicker

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactContext
import com.facebook.react.uimanager.PixelUtil
import com.facebook.react.uimanager.StateWrapper
import com.facebook.react.uimanager.UIManagerHelper

class RTNDatePicker : FrameLayout {
    private lateinit var reactContext: ReactContext

    internal var stateWrapper: StateWrapper? = null

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

    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int,
    ) {
        val content = getChildAt(0)
        content.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)

        val width = PixelUtil.toDIPFromPixel(content.getMeasuredWidth().toFloat())
        val height = PixelUtil.toDIPFromPixel(content.getMeasuredHeight().toFloat())

        stateWrapper?.updateState(
            Arguments.createMap().apply {
                putDouble("width", width.toDouble())
                putDouble("height", height.toDouble())
            },
        )

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
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

    public fun setType(type: String) {
        viewModel.updateType(type)
    }

    public fun setIsOpen(isOpen: Boolean) {
        viewModel.updateIsOpen(isOpen)
    }

    public fun setIsInline(isInline: Boolean) {
        viewModel.updateIsInline(isInline)
    }

    public fun setValue(value: Long?) {
        // Changing the value programmatically shouldn't trigger the onChange event
        lastValueUpdate = value

        viewModel.updateValue(value)
    }

    public fun setRange(
        lowerBound: Long?,
        upperBound: Long?,
    ) {
        viewModel.updateRange(lowerBound, upperBound)
    }

    public fun setConfirmText(confirmText: String?) {
        if (confirmText == null) {
            viewModel.updateConfirmText(reactContext.getString(android.R.string.ok))
            return
        }

        viewModel.updateConfirmText(confirmText)
    }

    public fun setCancelText(cancelText: String?) {
        if (cancelText == null) {
            viewModel.updateCancelText(reactContext.getString(android.R.string.cancel))
            return
        }

        viewModel.updateCancelText(cancelText)
    }

    public fun setContainerColor(containerColor: Int?) {
        if (containerColor == null) {
            viewModel.updateContainerColor(Color.Unspecified)
            return
        }

        viewModel.updateContainerColor(Color(containerColor))
    }

    public fun setTitleContentColor(titleContentColor: Int?) {
        if (titleContentColor == null) {
            viewModel.updateTitleContentColor(Color.Unspecified)
            return
        }

        viewModel.updateTitleContentColor(Color(titleContentColor))
    }

    public fun setHeadlineContentColor(headlineContentColor: Int?) {
        if (headlineContentColor == null) {
            viewModel.updateHeadlineContentColor(Color.Unspecified)
            return
        }

        viewModel.updateHeadlineContentColor(Color(headlineContentColor))
    }

    public fun setWeekdayContentColor(weekdayContentColor: Int?) {
        if (weekdayContentColor == null) {
            viewModel.updateWeekdayContentColor(Color.Unspecified)
            return
        }

        viewModel.updateWeekdayContentColor(Color(weekdayContentColor))
    }

    public fun setSubheadContentColor(subheadContentColor: Int?) {
        if (subheadContentColor == null) {
            viewModel.updateSubheadContentColor(Color.Unspecified)
            return
        }

        viewModel.updateSubheadContentColor(Color(subheadContentColor))
    }

    public fun setNavigationContentColor(navigationContentColor: Int?) {
        if (navigationContentColor == null) {
            viewModel.updateNavigationContentColor(Color.Unspecified)
            return
        }

        viewModel.updateNavigationContentColor(Color(navigationContentColor))
    }

    public fun setYearContentColor(yearContentColor: Int?) {
        if (yearContentColor == null) {
            viewModel.updateYearContentColor(Color.Unspecified)
            return
        }

        viewModel.updateYearContentColor(Color(yearContentColor))
    }

    public fun setDisabledYearContentColor(disabledYearContentColor: Int?) {
        if (disabledYearContentColor == null) {
            viewModel.updateDisabledYearContentColor(Color.Unspecified)
            return
        }

        viewModel.updateDisabledYearContentColor(Color(disabledYearContentColor))
    }

    public fun setCurrentYearContentColor(currentYearContentColor: Int?) {
        if (currentYearContentColor == null) {
            viewModel.updateCurrentYearContentColor(Color.Unspecified)
            return
        }

        viewModel.updateCurrentYearContentColor(Color(currentYearContentColor))
    }

    public fun setSelectedYearContentColor(selectedYearContentColor: Int?) {
        if (selectedYearContentColor == null) {
            viewModel.updateSelectedYearContentColor(Color.Unspecified)
            return
        }

        viewModel.updateSelectedYearContentColor(Color(selectedYearContentColor))
    }

    public fun setDisabledSelectedYearContentColor(disabledSelectedYearContentColor: Int?) {
        if (disabledSelectedYearContentColor == null) {
            viewModel.updateDisabledSelectedYearContentColor(Color.Unspecified)
            return
        }

        viewModel.updateDisabledSelectedYearContentColor(Color(disabledSelectedYearContentColor))
    }

    public fun setSelectedYearContainerColor(selectedYearContainerColor: Int?) {
        if (selectedYearContainerColor == null) {
            viewModel.updateSelectedYearContainerColor(Color.Unspecified)
            return
        }

        viewModel.updateSelectedYearContainerColor(Color(selectedYearContainerColor))
    }

    public fun setDisabledSelectedYearContainerColor(disabledSelectedYearContainerColor: Int?) {
        if (disabledSelectedYearContainerColor == null) {
            viewModel.updateDisabledSelectedYearContainerColor(Color.Unspecified)
            return
        }

        viewModel.updateDisabledSelectedYearContainerColor(Color(disabledSelectedYearContainerColor))
    }

    public fun setDayContentColor(dayContentColor: Int?) {
        if (dayContentColor == null) {
            viewModel.updateDayContentColor(Color.Unspecified)
            return
        }

        viewModel.updateDayContentColor(Color(dayContentColor))
    }

    public fun setDisabledDayContentColor(disabledDayContentColor: Int?) {
        if (disabledDayContentColor == null) {
            viewModel.updateDisabledDayContentColor(Color.Unspecified)
            return
        }

        viewModel.updateDisabledDayContentColor(Color(disabledDayContentColor))
    }

    public fun setSelectedDayContentColor(selectedDayContentColor: Int?) {
        if (selectedDayContentColor == null) {
            viewModel.updateSelectedDayContentColor(Color.Unspecified)
            return
        }

        viewModel.updateSelectedDayContentColor(Color(selectedDayContentColor))
    }

    public fun setDisabledSelectedDayContentColor(disabledSelectedDayContentColor: Int?) {
        if (disabledSelectedDayContentColor == null) {
            viewModel.updateDisabledSelectedDayContentColor(Color.Unspecified)
            return
        }

        viewModel.updateDisabledSelectedDayContentColor(Color(disabledSelectedDayContentColor))
    }

    public fun setSelectedDayContainerColor(selectedDayContainerColor: Int?) {
        if (selectedDayContainerColor == null) {
            viewModel.updateSelectedDayContainerColor(Color.Unspecified)
            return
        }

        viewModel.updateSelectedDayContainerColor(Color(selectedDayContainerColor))
    }

    public fun setDisabledSelectedDayContainerColor(disabledSelectedDayContainerColor: Int?) {
        if (disabledSelectedDayContainerColor == null) {
            viewModel.updateDisabledSelectedDayContainerColor(Color.Unspecified)
            return
        }

        viewModel.updateDisabledSelectedDayContainerColor(Color(disabledSelectedDayContainerColor))
    }

    public fun setTodayContentColor(todayContentColor: Int?) {
        if (todayContentColor == null) {
            viewModel.updateTodayContentColor(Color.Unspecified)
            return
        }

        viewModel.updateTodayContentColor(Color(todayContentColor))
    }

    public fun setTodayDateBorderColor(todayDateBorderColor: Int?) {
        if (todayDateBorderColor == null) {
            viewModel.updateTodayDateBorderColor(Color.Unspecified)
            return
        }

        viewModel.updateTodayDateBorderColor(Color(todayDateBorderColor))
    }

    public fun setDayInSelectionRangeContainerColor(dayInSelectionRangeContainerColor: Int?) {
        if (dayInSelectionRangeContainerColor == null) {
            viewModel.updateDayInSelectionRangeContainerColor(Color.Unspecified)
            return
        }

        viewModel.updateDayInSelectionRangeContainerColor(Color(dayInSelectionRangeContainerColor))
    }

    public fun setDayInSelectionRangeContentColor(dayInSelectionRangeContentColor: Int?) {
        if (dayInSelectionRangeContentColor == null) {
            viewModel.updateDayInSelectionRangeContentColor(Color.Unspecified)
            return
        }

        viewModel.updateDayInSelectionRangeContentColor(Color(dayInSelectionRangeContentColor))
    }

    public fun setDividerColor(dividerColor: Int?) {
        if (dividerColor == null) {
            viewModel.updateDividerColor(Color.Unspecified)
            return
        }

        viewModel.updateDividerColor(Color(dividerColor))
    }

    public fun setClockDialColor(clockDialColor: Int?) {
        if (clockDialColor == null) {
            viewModel.updateClockDialColor(Color.Unspecified)
            return
        }

        viewModel.updateClockDialColor(Color(clockDialColor))
    }

    public fun setSelectorColor(selectorColor: Int?) {
        if (selectorColor == null) {
            viewModel.updateSelectorColor(Color.Unspecified)
            return
        }

        viewModel.updateSelectorColor(Color(selectorColor))
    }

    public fun setPeriodSelectorBorderColor(periodSelectorBorderColor: Int?) {
        if (periodSelectorBorderColor == null) {
            viewModel.updatePeriodSelectorBorderColor(Color.Unspecified)
            return
        }

        viewModel.updatePeriodSelectorBorderColor(Color(periodSelectorBorderColor))
    }

    public fun setClockDialSelectedContentColor(clockDialSelectedContentColor: Int?) {
        if (clockDialSelectedContentColor == null) {
            viewModel.updateClockDialSelectedContentColor(Color.Unspecified)
            return
        }

        viewModel.updateClockDialSelectedContentColor(Color(clockDialSelectedContentColor))
    }

    public fun setClockDialUnselectedContentColor(clockDialUnselectedContentColor: Int?) {
        if (clockDialUnselectedContentColor == null) {
            viewModel.updateClockDialUnselectedContentColor(Color.Unspecified)
            return
        }

        viewModel.updateClockDialUnselectedContentColor(Color(clockDialUnselectedContentColor))
    }

    public fun setPeriodSelectorSelectedContainerColor(periodSelectorSelectedContainerColor: Int?) {
        if (periodSelectorSelectedContainerColor == null) {
            viewModel.updatePeriodSelectorSelectedContainerColor(Color.Unspecified)
            return
        }

        viewModel.updatePeriodSelectorSelectedContainerColor(Color(periodSelectorSelectedContainerColor))
    }

    public fun setPeriodSelectorUnselectedContainerColor(periodSelectorUnselectedContainerColor: Int?) {
        if (periodSelectorUnselectedContainerColor == null) {
            viewModel.updatePeriodSelectorUnselectedContainerColor(Color.Unspecified)
            return
        }

        viewModel.updatePeriodSelectorUnselectedContainerColor(Color(periodSelectorUnselectedContainerColor))
    }

    public fun setPeriodSelectorSelectedContentColor(periodSelectorSelectedContentColor: Int?) {
        if (periodSelectorSelectedContentColor == null) {
            viewModel.updatePeriodSelectorSelectedContentColor(Color.Unspecified)
            return
        }

        viewModel.updatePeriodSelectorSelectedContentColor(Color(periodSelectorSelectedContentColor))
    }

    public fun setPeriodSelectorUnselectedContentColor(periodSelectorUnselectedContentColor: Int?) {
        if (periodSelectorUnselectedContentColor == null) {
            viewModel.updatePeriodSelectorUnselectedContentColor(Color.Unspecified)
            return
        }

        viewModel.updatePeriodSelectorUnselectedContentColor(Color(periodSelectorUnselectedContentColor))
    }

    public fun setTimeSelectorSelectedContainerColor(timeSelectorSelectedContainerColor: Int?) {
        if (timeSelectorSelectedContainerColor == null) {
            viewModel.updateTimeSelectorSelectedContainerColor(Color.Unspecified)
            return
        }

        viewModel.updateTimeSelectorSelectedContainerColor(Color(timeSelectorSelectedContainerColor))
    }

    public fun setTimeSelectorUnselectedContainerColor(timeSelectorUnselectedContainerColor: Int?) {
        if (timeSelectorUnselectedContainerColor == null) {
            viewModel.updateTimeSelectorUnselectedContainerColor(Color.Unspecified)
            return
        }

        viewModel.updateTimeSelectorUnselectedContainerColor(Color(timeSelectorUnselectedContainerColor))
    }

    public fun setTimeSelectorSelectedContentColor(timeSelectorSelectedContentColor: Int?) {
        if (timeSelectorSelectedContentColor == null) {
            viewModel.updateTimeSelectorSelectedContentColor(Color.Unspecified)
            return
        }

        viewModel.updateTimeSelectorSelectedContentColor(Color(timeSelectorSelectedContentColor))
    }

    public fun setTimeSelectorUnselectedContentColor(timeSelectorUnselectedContentColor: Int?) {
        if (timeSelectorUnselectedContentColor == null) {
            viewModel.updateTimeSelectorUnselectedContentColor(Color.Unspecified)
            return
        }

        viewModel.updateTimeSelectorUnselectedContentColor(Color(timeSelectorUnselectedContentColor))
    }
}
