package com.fanhl.test.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import com.fanhl.test.R
import com.fanhl.test.model.Item

/**
 *
 */
public class RectView extends AbstractItemView {
    Item item
    String text

    private Drawable drawable

    public RectView(Context context) {
        super(context)
        init(null, 0)
    }

    public RectView(Context context, AttributeSet attrs) {
        super(context, attrs)
        init(attrs, 0)
    }

    public RectView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle)
        init(attrs, defStyle)
    }

    private void init(AttributeSet attrs, int defStyle) {
        drawable = getResources().getDrawable(R.drawable.rect_view_background)

        text = new Random().nextInt(100).toString()
//        setTag(text)
    }

    def bind(Item item) { this.item = item }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas)

        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)


        def paint = new TextPaint()
        paint.setFlags(Paint.ANTI_ALIAS_FLAG)
        paint.setTextAlign(Paint.Align.LEFT)
        paint.setColor(Color.YELLOW)
        paint.setTextSize(50)

        if (item) {
            canvas.drawText(item.title, 50, 100, paint)
        } else {
            canvas.drawText(text, 50, 100, paint)
        }
    }
}
