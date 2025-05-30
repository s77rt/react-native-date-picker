package com.s77rt.rtndatepicker

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.events.Event

internal class RTNDatePickerCancelEvent(
    surfaceId: Int,
    viewId: Int,
) : Event<RTNDatePickerCancelEvent>(surfaceId, viewId) {
    override fun getEventName(): String = EVENT_NAME

    override fun getEventData(): WritableMap? = Arguments.createMap()

    public companion object {
        public const val EVENT_NAME = "topCancel"
    }
}
