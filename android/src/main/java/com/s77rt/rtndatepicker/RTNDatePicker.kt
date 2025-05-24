package com.s77rt.rtndatepicker

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
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
}
