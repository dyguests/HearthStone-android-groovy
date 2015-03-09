package com.fanhl.test.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import com.fanhl.test.R
import groovy.transform.InheritConstructors

/**
 *
 */
@InheritConstructors
public class OvalView extends AbstractItemView {
    @Override
    protected Drawable initItemBackgroundId() {
        getResources().getDrawable(R.drawable.oval_view_background)
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas)

        itemBackground.setBounds(0, 0, width, height)
        itemBackground.draw(canvas)

        def paint = new TextPaint()
        paint.setFlags(Paint.ANTI_ALIAS_FLAG)
        paint.setTextAlign(Paint.Align.LEFT)
        paint.setColor(Color.YELLOW)
        paint.setTextSize(50)

        if (data) canvas.drawText(data.title, 50, 100, paint)
    }
}
