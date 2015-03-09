package com.fanhl.test.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

/**
 * Created by fanhl on 15/3/9.
 */
class AbstractContainerView extends RelativeLayout {
    AbstractContainerView(Context context) {
        super(context)
    }

    AbstractContainerView(Context context, AttributeSet attrs) {
        super(context, attrs)
    }

    AbstractContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr)
    }
}
