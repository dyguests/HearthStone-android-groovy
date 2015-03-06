package com.fanhl.test.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Created by fanhl on 15/3/6.
 */
abstract class AbstractItemView extends View {
    public static final int WIDTH = 150
    public static final int HEIGHT = 180

    AbstractItemView(Context context) {
        super(context)
    }

    AbstractItemView(Context context, AttributeSet attrs) {
        super(context, attrs)
    }

    AbstractItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr)
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = android.view.View.MeasureSpec.makeMeasureSpec(WIDTH, android.view.View.MeasureSpec.EXACTLY)
        int height = android.view.View.MeasureSpec.makeMeasureSpec(HEIGHT, android.view.View.MeasureSpec.EXACTLY)
        super.onMeasure(width, height)
    }
}
