package com.s77rt.rtndatepicker

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.events.Event

internal class RTNDatePickerChangeEvent(
    surfaceId: Int,
    viewId: Int,
    private val value: Double?,
) : Event<RTNDatePickerChangeEvent>(surfaceId, viewId) {
    override fun getEventName(): String = EVENT_NAME

    override fun getEventData(): WritableMap? =
        Arguments.createMap().also {
            if (value == null) {
                it.putNull("value")
            } else {
                it.putDouble("value", value)
            }
        }

    public companion object {
        public const val EVENT_NAME = "topChange"
    }
}
