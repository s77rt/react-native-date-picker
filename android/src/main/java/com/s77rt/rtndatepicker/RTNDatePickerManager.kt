package com.s77rt.rtndatepicker

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
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
        // Codegen passes null as 0f (0.0)
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
            view.setRange(null, null)
            return
        }

        val lowerBound = if (range.hasKey("lowerBound")) range.getDouble("lowerBound").toLong() else null
        val upperBound = if (range.hasKey("upperBound")) range.getDouble("upperBound").toLong() else null

        view.setRange(lowerBound, upperBound)
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
