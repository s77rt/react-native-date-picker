package com.s77rt.rtndatepicker

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager

class RTNDatePickerPackage : ReactPackage {
    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> =
        listOf(RTNDatePickerManager(reactContext))

    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> = emptyList()
}
