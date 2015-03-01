package com.fanhl.hearthstone.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.fanhl.hearthstone.R
import com.fanhl.hearthstone.factory.CardBuilder
import com.fanhl.hearthstone.factory.HeroBuilder
import com.fanhl.hearthstone.model.Hero
import com.fanhl.hearthstone.model.card.Weapon

/**
 * 英雄视图
 *
 * 由于长宽比是固定的,所以只需要设定宽度(具体值),设定高度没有用
 *
 * 不接收padding参数
 *
 * Created by fanhl on 15/3/1.
 */
public class HeroView extends AbstractView {
    /**高:宽*/
    public static final float HEIGHT2WIDTH_RATE = 1.0f

    public static final float TITLE2WIDTH_RATE = 0.15f
    /**费|攻击|血|耐久:宽*/
    public static final float MINT2WIDTH_RATE = 0.2f

    Hero hero

    /**图案*/
    Drawable pattern
    Drawable cardBackground

    //以下用来绘制文字
    TextDrawerHolder attackHolder
    TextDrawerHolder bloodHolder
    TextDrawerHolder titleHolder

    private Paint errPaint

    public HeroView(Context context) {
        super(context)
        init(null, 0)
    }

    public HeroView(Context context, AttributeSet attrs) {
        super(context, attrs)
        init(attrs, 0)
    }

    public HeroView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle)
        init(attrs, defStyle)
    }

    def bind(Hero hero) {
        this.hero = hero
//        pattern=getResources().getDrawable(card.patternId)//FIXME
        cardBackground = getResources().getDrawable(R.drawable.hero_view_background)//FIXME

        initPaint()

        invalidate()
    }

    private void init(AttributeSet attrs, int defStyle) {
        errPaint = new Paint()
        errPaint.setColor(Color.RED)

        //FIXME 测试用
        HeroBuilder.init()
        bind(HeroBuilder.newHero(100001))
    }

    private void initPaint() {
        attackHolder = new TextDrawerHolder()
        bloodHolder = new TextDrawerHolder()
        titleHolder = new TextDrawerHolder()

    }

    private void invalidatePaintAndMeasurements() {
        attackHolder.setParams(hero.attack.current.toString(), width * MINT2WIDTH_RATE as float,
                width * 0.1 as float, height * 0.8f as float, Datum.CENTER)
        bloodHolder.setParams(hero.blood.current.toString(), width * MINT2WIDTH_RATE as float,
                width * 0.9 as float, height * 0.8f as float, Datum.CENTER)
//标题
        titleHolder.setParams(hero.title, width * TITLE2WIDTH_RATE as float,
                width / 2 as float, height * 0.6f as float, Datum.CENTER)

        //FIXME 测试用
//        throw new Exception("""width:$width height:$height
//cost[x:${costHolder.x} y:${costHolder.y}
//attack[x:${attackHolder.x} y:${attackHolder.y}
//blood[x:${bloodHolder.x} y:${bloodHolder.y}
//""")
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
        if (!hero) {
            canvas.drawRect(0, 0, width, height, errPaint)
            return
        }

        invalidatePaintAndMeasurements()

        cardBackground?.with {
            setBounds(0, 0, width, height)
            draw(canvas)
        }
        titleHolder.draw(canvas)
        attackHolder.draw(canvas)//FIXME 之前改成英雄专用 holder,攻击为0时不显示
        bloodHolder.draw(canvas)
    }


}
