package com.s77rt.rtndatepicker

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy

class RTNDatePicker : FrameLayout {
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
        ComposeView(context).also {
            it.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            it.setContent {
                RTNDatePickerView()
            }

            addView(it)
        }
    }
}
