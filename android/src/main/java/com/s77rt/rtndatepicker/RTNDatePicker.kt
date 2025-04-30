package com.s77rt.rtndatepicker

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.facebook.react.bridge.ReactContext
import com.facebook.react.uimanager.UIManagerHelper

class RTNDatePicker : FrameLayout {
    internal lateinit var viewModel: RTNDatePickerViewModel

    constructor(context: Context) : super(context) {
        configureComponent(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        configureComponent(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        configureComponent(context)
    }

    private fun configureComponent(context: Context) {
        viewModel = RTNDatePickerViewModel()

        ComposeView(context).also {
            it.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            it.setContent {
                RTNDatePickerView(
                    viewModel = viewModel,
                    onChange = {
                        val reactContext = context as ReactContext
                        val surfaceId = UIManagerHelper.getSurfaceId(reactContext)
                        val eventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(reactContext, id)
                        val event = RTNDatePickerChangeEvent(surfaceId, id)

                        eventDispatcher?.dispatchEvent(event)
                    },
                    onConfirm = {
                        val reactContext = context as ReactContext
                        val surfaceId = UIManagerHelper.getSurfaceId(reactContext)
                        val eventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(reactContext, id)
                        val event = RTNDatePickerConfirmEvent(surfaceId, id)

                        eventDispatcher?.dispatchEvent(event)
                    },
                    onCancel = {
                        val reactContext = context as ReactContext
                        val surfaceId = UIManagerHelper.getSurfaceId(reactContext)
                        val eventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(reactContext, id)
                        val event = RTNDatePickerCancelEvent(surfaceId, id)

                        eventDispatcher?.dispatchEvent(event)
                    },
                )
            }

            addView(it)
        }
    }
}
