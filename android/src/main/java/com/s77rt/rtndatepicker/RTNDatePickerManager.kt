package com.s77rt.rtndatepicker

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.ReactStylesDiffMap
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.StateWrapper
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.RTNDatePickerManagerDelegate
import com.facebook.react.viewmanagers.RTNDatePickerManagerInterface

@ReactModule(name = RTNDatePickerManager.NAME)
class RTNDatePickerManager(
    context: ReactApplicationContext,
) : SimpleViewManager<RTNDatePicker>(),
    RTNDatePickerManagerInterface<RTNDatePicker> {
    private val delegate: RTNDatePickerManagerDelegate<RTNDatePicker, RTNDatePickerManager> = RTNDatePickerManagerDelegate(this)

    override fun getDelegate(): ViewManagerDelegate<RTNDatePicker> = delegate

    override fun getName(): String = NAME

    override fun createViewInstance(context: ThemedReactContext): RTNDatePicker = RTNDatePicker(context)

    @ReactProp(name = "type")
    override fun setType(
        view: RTNDatePicker,
        type: String?,
    ) {
        if (type == null) {
            return
        }

        view.setType(type)
    }

    @ReactProp(name = "isOpen")
    override fun setIsOpen(
        view: RTNDatePicker,
        isOpen: Boolean,
    ) {
        view.setIsOpen(isOpen)
    }

    @ReactProp(name = "isInline")
    override fun setIsInline(
        view: RTNDatePicker,
        isInline: Boolean,
    ) {
        view.setIsInline(isInline)
    }

    @ReactProp(name = "value")
    override fun setValue(
        view: RTNDatePicker,
        value: Double,
    ) {
        // Codegen passes null double as 0f (0.0)
        // https://github.com/facebook/react-native/blob/996be870713cd72df1f91db99e8f981bbc5406af/packages/react-native-codegen/src/generators/components/GeneratePropsJavaDelegate.js#L107
        if (value == 0.0) {
            view.setValue(null)
            return
        }

        view.setValue(value.toLong())
    }

    @ReactProp(name = "range")
    override fun setRange(
        view: RTNDatePicker,
        range: ReadableMap?,
    ) {
        if (range == null) {
            return
        }

        val lowerBound = if (range.hasKey("lowerBound")) range.getDouble("lowerBound").toLong() else null
        val upperBound = if (range.hasKey("upperBound")) range.getDouble("upperBound").toLong() else null

        view.setRange(lowerBound, upperBound)
    }

    @ReactProp(name = "step")
    override fun setStep(
        view: RTNDatePicker,
        step: Int,
    ) {
        // Unimplemented
        return
    }

    @ReactProp(name = "options")
    override fun setOptions(
        view: RTNDatePicker,
        options: ReadableMap?,
    ) {
        if (options == null) {
            return
        }

        val confirmText = options.getString("confirmText")
        val cancelText = options.getString("cancelText")
        val title = options.getString("title")
        val headline = options.getString("headline")
        val showModeToggle = if (options.hasKey("showModeToggle")) options.getBoolean("showModeToggle") else false
        val is24Hour = if (options.hasKey("is24Hour")) options.getBoolean("is24Hour") else false

        view.setConfirmText(confirmText)
        view.setCancelText(cancelText)
        view.setTitle(title)
        view.setHeadline(headline)
        view.setShowModeToggle(showModeToggle)
        view.setIs24Hour(is24Hour)
    }

    @ReactProp(name = "styles")
    override fun setStyles(
        view: RTNDatePicker,
        styles: ReadableMap?,
    ) {
        if (styles == null) {
            return
        }

        val containerColor = if (styles.hasKey("containerColor")) styles.getInt("containerColor") else null
        val titleContentColor = if (styles.hasKey("titleContentColor")) styles.getInt("titleContentColor") else null
        val headlineContentColor = if (styles.hasKey("headlineContentColor")) styles.getInt("headlineContentColor") else null
        val weekdayContentColor = if (styles.hasKey("weekdayContentColor")) styles.getInt("weekdayContentColor") else null
        val subheadContentColor = if (styles.hasKey("subheadContentColor")) styles.getInt("subheadContentColor") else null
        val navigationContentColor = if (styles.hasKey("navigationContentColor")) styles.getInt("navigationContentColor") else null
        val yearContentColor = if (styles.hasKey("yearContentColor")) styles.getInt("yearContentColor") else null
        val disabledYearContentColor = if (styles.hasKey("disabledYearContentColor")) styles.getInt("disabledYearContentColor") else null
        val currentYearContentColor = if (styles.hasKey("currentYearContentColor")) styles.getInt("currentYearContentColor") else null
        val selectedYearContentColor = if (styles.hasKey("selectedYearContentColor")) styles.getInt("selectedYearContentColor") else null
        val disabledSelectedYearContentColor =
            if (styles.hasKey(
                    "disabledSelectedYearContentColor",
                )
            ) {
                styles.getInt("disabledSelectedYearContentColor")
            } else {
                null
            }
        val selectedYearContainerColor =
            if (styles.hasKey(
                    "selectedYearContainerColor",
                )
            ) {
                styles.getInt("selectedYearContainerColor")
            } else {
                null
            }
        val disabledSelectedYearContainerColor =
            if (styles.hasKey(
                    "disabledSelectedYearContainerColor",
                )
            ) {
                styles.getInt("disabledSelectedYearContainerColor")
            } else {
                null
            }
        val dayContentColor = if (styles.hasKey("dayContentColor")) styles.getInt("dayContentColor") else null
        val disabledDayContentColor = if (styles.hasKey("disabledDayContentColor")) styles.getInt("disabledDayContentColor") else null
        val selectedDayContentColor = if (styles.hasKey("selectedDayContentColor")) styles.getInt("selectedDayContentColor") else null
        val disabledSelectedDayContentColor =
            if (styles.hasKey(
                    "disabledSelectedDayContentColor",
                )
            ) {
                styles.getInt("disabledSelectedDayContentColor")
            } else {
                null
            }
        val selectedDayContainerColor = if (styles.hasKey("selectedDayContainerColor")) styles.getInt("selectedDayContainerColor") else null
        val disabledSelectedDayContainerColor =
            if (styles.hasKey(
                    "disabledSelectedDayContainerColor",
                )
            ) {
                styles.getInt("disabledSelectedDayContainerColor")
            } else {
                null
            }
        val todayContentColor = if (styles.hasKey("todayContentColor")) styles.getInt("todayContentColor") else null
        val todayDateBorderColor = if (styles.hasKey("todayDateBorderColor")) styles.getInt("todayDateBorderColor") else null
        val dayInSelectionRangeContainerColor =
            if (styles.hasKey(
                    "dayInSelectionRangeContainerColor",
                )
            ) {
                styles.getInt("dayInSelectionRangeContainerColor")
            } else {
                null
            }
        val dayInSelectionRangeContentColor =
            if (styles.hasKey(
                    "dayInSelectionRangeContentColor",
                )
            ) {
                styles.getInt("dayInSelectionRangeContentColor")
            } else {
                null
            }
        val dividerColor = if (styles.hasKey("dividerColor")) styles.getInt("dividerColor") else null
        val clockDialColor = if (styles.hasKey("clockDialColor")) styles.getInt("clockDialColor") else null
        val selectorColor = if (styles.hasKey("selectorColor")) styles.getInt("selectorColor") else null
        val periodSelectorBorderColor = if (styles.hasKey("periodSelectorBorderColor")) styles.getInt("periodSelectorBorderColor") else null
        val clockDialSelectedContentColor =
            if (styles.hasKey(
                    "clockDialSelectedContentColor",
                )
            ) {
                styles.getInt("clockDialSelectedContentColor")
            } else {
                null
            }
        val clockDialUnselectedContentColor =
            if (styles.hasKey(
                    "clockDialUnselectedContentColor",
                )
            ) {
                styles.getInt("clockDialUnselectedContentColor")
            } else {
                null
            }
        val periodSelectorSelectedContainerColor =
            if (styles.hasKey(
                    "periodSelectorSelectedContainerColor",
                )
            ) {
                styles.getInt("periodSelectorSelectedContainerColor")
            } else {
                null
            }
        val periodSelectorUnselectedContainerColor =
            if (styles.hasKey(
                    "periodSelectorUnselectedContainerColor",
                )
            ) {
                styles.getInt("periodSelectorUnselectedContainerColor")
            } else {
                null
            }
        val periodSelectorSelectedContentColor =
            if (styles.hasKey(
                    "periodSelectorSelectedContentColor",
                )
            ) {
                styles.getInt("periodSelectorSelectedContentColor")
            } else {
                null
            }
        val periodSelectorUnselectedContentColor =
            if (styles.hasKey(
                    "periodSelectorUnselectedContentColor",
                )
            ) {
                styles.getInt("periodSelectorUnselectedContentColor")
            } else {
                null
            }
        val timeSelectorSelectedContainerColor =
            if (styles.hasKey(
                    "timeSelectorSelectedContainerColor",
                )
            ) {
                styles.getInt("timeSelectorSelectedContainerColor")
            } else {
                null
            }
        val timeSelectorUnselectedContainerColor =
            if (styles.hasKey(
                    "timeSelectorUnselectedContainerColor",
                )
            ) {
                styles.getInt("timeSelectorUnselectedContainerColor")
            } else {
                null
            }
        val timeSelectorSelectedContentColor =
            if (styles.hasKey(
                    "timeSelectorSelectedContentColor",
                )
            ) {
                styles.getInt("timeSelectorSelectedContentColor")
            } else {
                null
            }
        val timeSelectorUnselectedContentColor =
            if (styles.hasKey(
                    "timeSelectorUnselectedContentColor",
                )
            ) {
                styles.getInt("timeSelectorUnselectedContentColor")
            } else {
                null
            }

        view.setContainerColor(containerColor)
        view.setTitleContentColor(titleContentColor)
        view.setHeadlineContentColor(headlineContentColor)
        view.setWeekdayContentColor(weekdayContentColor)
        view.setSubheadContentColor(subheadContentColor)
        view.setNavigationContentColor(navigationContentColor)
        view.setYearContentColor(yearContentColor)
        view.setDisabledYearContentColor(disabledYearContentColor)
        view.setCurrentYearContentColor(currentYearContentColor)
        view.setSelectedYearContentColor(selectedYearContentColor)
        view.setDisabledSelectedYearContentColor(disabledSelectedYearContentColor)
        view.setSelectedYearContainerColor(selectedYearContainerColor)
        view.setDisabledSelectedYearContainerColor(disabledSelectedYearContainerColor)
        view.setDayContentColor(dayContentColor)
        view.setDisabledDayContentColor(disabledDayContentColor)
        view.setSelectedDayContentColor(selectedDayContentColor)
        view.setDisabledSelectedDayContentColor(disabledSelectedDayContentColor)
        view.setSelectedDayContainerColor(selectedDayContainerColor)
        view.setDisabledSelectedDayContainerColor(disabledSelectedDayContainerColor)
        view.setTodayContentColor(todayContentColor)
        view.setTodayDateBorderColor(todayDateBorderColor)
        view.setDayInSelectionRangeContainerColor(dayInSelectionRangeContainerColor)
        view.setDayInSelectionRangeContentColor(dayInSelectionRangeContentColor)
        view.setDividerColor(dividerColor)
        view.setClockDialColor(clockDialColor)
        view.setSelectorColor(selectorColor)
        view.setPeriodSelectorBorderColor(periodSelectorBorderColor)
        view.setClockDialSelectedContentColor(clockDialSelectedContentColor)
        view.setClockDialUnselectedContentColor(clockDialUnselectedContentColor)
        view.setPeriodSelectorSelectedContainerColor(periodSelectorSelectedContainerColor)
        view.setPeriodSelectorUnselectedContainerColor(periodSelectorUnselectedContainerColor)
        view.setPeriodSelectorSelectedContentColor(periodSelectorSelectedContentColor)
        view.setPeriodSelectorUnselectedContentColor(periodSelectorUnselectedContentColor)
        view.setTimeSelectorSelectedContainerColor(timeSelectorSelectedContainerColor)
        view.setTimeSelectorUnselectedContainerColor(timeSelectorUnselectedContainerColor)
        view.setTimeSelectorSelectedContentColor(timeSelectorSelectedContentColor)
        view.setTimeSelectorUnselectedContentColor(timeSelectorUnselectedContentColor)
    }

    public override fun updateState(
        view: RTNDatePicker,
        props: ReactStylesDiffMap,
        stateWrapper: StateWrapper,
    ): Any? {
        view.stateWrapper = stateWrapper
        return null
    }

    public override fun getExportedCustomBubblingEventTypeConstants(): MutableMap<String, Any> =
        (super.getExportedCustomBubblingEventTypeConstants() ?: mutableMapOf<String, Any>()).apply {
            put(
                RTNDatePickerChangeEvent.EVENT_NAME,
                mapOf(
                    "phasedRegistrationNames" to
                        mapOf(
                            "bubbled" to "onChange",
                            "captured" to "onChangeCapture",
                        ),
                ),
            )
        }

    public override fun getExportedCustomDirectEventTypeConstants(): MutableMap<String, Any> =
        (super.getExportedCustomDirectEventTypeConstants() ?: mutableMapOf<String, Any>()).apply {
            put(RTNDatePickerConfirmEvent.EVENT_NAME, mapOf("registrationName" to "onConfirm"))
            put(RTNDatePickerCancelEvent.EVENT_NAME, mapOf("registrationName" to "onCancel"))
        }

    companion object {
        const val NAME = "RTNDatePicker"
    }
}
