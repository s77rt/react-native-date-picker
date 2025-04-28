package com.s77rt.rtndatepicker

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
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

    companion object {
        const val NAME = "RTNDatePicker"
    }
}
