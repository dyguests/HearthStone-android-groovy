package com.fanhl.test.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.fanhl.test.model.Item

/**
 * Created by fanhl on 15/3/6.
 */
abstract class AbstractItemView extends View {
    public static final int WIDTH = 150
    public static final int HEIGHT = 180

    protected Item data

    protected Drawable itemBackground

    AbstractItemView(Context context) {
        super(context)
        init(null, 0)
    }

    AbstractItemView(Context context, AttributeSet attrs) {
        super(context, attrs)
        init(attrs, 0)
    }

    AbstractItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr)
        init(attrs, defStyleAttr)
    }

    protected void init(AttributeSet attrs, int defStyle) {
        itemBackground = initItemBackgroundId()
    }

    protected abstract Drawable initItemBackgroundId()

    def bind(Item data) { this.data = data }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = android.view.View.MeasureSpec.makeMeasureSpec(WIDTH, android.view.View.MeasureSpec.EXACTLY)
        int height = android.view.View.MeasureSpec.makeMeasureSpec(HEIGHT, android.view.View.MeasureSpec.EXACTLY)
        super.onMeasure(width, height)
    }
}
