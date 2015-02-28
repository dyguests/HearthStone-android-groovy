package com.fanhl.hearthstone.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.fanhl.hearthstone.R
import com.fanhl.hearthstone.factory.CardBuilder
import com.fanhl.hearthstone.model.card.Mystery
import com.fanhl.hearthstone.model.card.Weapon

/**
 * 奥秘视图(只显示奥秘装上的视图,其它形态使用其它的View来表示)
 *
 * 由于长宽比是固定的,所以只需要设定宽度(具体值),设定高度没有用
 *
 * 不接收padding参数
 *
 * Created by fanhl on 15/2/28.
 */
public class MysteryView extends AbstractView {
    /**高:宽*/
    public static final float HEIGHT2WIDTH_RATE = 1.0f


    Mystery mystery

    /**图案*/
//    Drawable pattern
    Drawable cardBackground


    private Paint errPaint

    public MysteryView(Context context) {
        super(context)
        init(null, 0)
    }

    public MysteryView(Context context, AttributeSet attrs) {
        super(context, attrs)
        init(attrs, 0)
    }

    public MysteryView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle)
        init(attrs, defStyle)
    }

    def bind(Mystery mystery) {
        this.mystery = mystery
//        pattern=getResources().getDrawable(card.patternId)//FIXME
        cardBackground = getResources().getDrawable(R.drawable.mystery_normal)//FIXME

        initPaint()

        invalidate()
    }

    private void init(AttributeSet attrs, int defStyle) {
        errPaint = new Paint()
        errPaint.setColor(Color.RED)

        //FIXME 测试用
        CardBuilder.init()

        bind(CardBuilder.newCard(400001))
    }

    private void initPaint() {
    }

    private void invalidatePaintAndMeasurements() {
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = android.view.View.MeasureSpec.getSize(widthMeasureSpec)
        int widthMode = android.view.View.MeasureSpec.getMode(widthMeasureSpec)

        if (widthMode != android.view.View.MeasureSpec.EXACTLY) {
            throw new Exception("not MeasureSpec.EXACTLY mode!")
        }

        int heightSize = widthSize * HEIGHT2WIDTH_RATE
        int newHeightMeasureSpec = android.view.View.MeasureSpec.makeMeasureSpec(heightSize, widthMode)

        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas)
        if (!mystery) {
            canvas.drawRect(0, 0, width, height, errPaint)
            return
        }

        invalidatePaintAndMeasurements()

        cardBackground?.with {
            setBounds(0, 0, width, height)
            draw(canvas)
        }
    }

}
