package com.fanhl.hearthstone.widget.item

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.fanhl.hearthstone.R
import com.fanhl.hearthstone.factory.CardBuilder
import com.fanhl.hearthstone.model.Crystal
import com.fanhl.hearthstone.model.container.Library

/**
 * 牌堆视图
 *
 * 由于长宽比是固定的,所以只需要设定宽度(具体值),设定高度没有用
 *
 * 不接收padding参数
 *
 * Created by fanhl on 15/2/28.
 */
public class CrystalView extends AbstractView {
    /**高:宽*/
    public static final float HEIGHT2WIDTH_RATE = 3.0f
    public static final float COUNT2WIDTH_RATE = 0.8f

    Crystal data

    /**图案*/
    Drawable pattern
    Drawable cardBackground

    //以下用来绘制文字
    TextDrawerHolder countHolder

    private Paint errPaint

    public CrystalView(Context context) {
        super(context)
        init(null, 0)
    }

    public CrystalView(Context context, AttributeSet attrs) {
        super(context, attrs)
        init(attrs, 0)
    }

    public CrystalView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle)
        init(attrs, defStyle)
    }

    def bind(Crystal data) {
        this.data = data
//        pattern=getResources().getDrawable(card.patternId)//FIXME
        cardBackground = getResources().getDrawable(R.drawable.library_view_background)//FIXME

        initPaint()

        invalidate()
    }

    private void init(AttributeSet attrs, int defStyle) {
        errPaint = new Paint()
        errPaint.setColor(Color.RED)

        //FIXME 测试用
        Crystal crystal = new Crystal(5)
        bind(crystal)
    }

    private void initPaint() {
        countHolder = new TextDrawerHolder()

    }

    private void invalidatePaintAndMeasurements() {
        //数量
        countHolder.setParams("${data.current}|${data.base}", width * COUNT2WIDTH_RATE as float,
                width / 2 as float, height * 0.5f as float, Datum.CENTER)
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
        if (!data) {
            canvas.drawRect(0, 0, width, height, errPaint)
            return
        }

        invalidatePaintAndMeasurements()

        cardBackground?.with {
            setBounds(0, 0, width, height)
            draw(canvas)
        }
        countHolder.draw(canvas)
    }


}
