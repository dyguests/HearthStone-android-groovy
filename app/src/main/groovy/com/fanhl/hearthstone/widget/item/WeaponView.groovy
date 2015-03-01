package com.fanhl.hearthstone.widget.item

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.fanhl.hearthstone.R
import com.fanhl.hearthstone.factory.CardBuilder
import com.fanhl.hearthstone.model.card.Weapon

/**
 * 武器视图(只显示武器被装备上的视图,其它形态使用其它的View来表示)
 *
 * 由于长宽比是固定的,所以只需要设定宽度(具体值),设定高度没有用
 *
 * 不接收padding参数
 *
 * Created by fanhl on 15/2/28.
 */
public class WeaponView extends AbstractView {
    /**高:宽*/
    public static final float HEIGHT2WIDTH_RATE = 1.0f

    public static final float TITLE2WIDTH_RATE = 0.15f
    /**费|攻击|血|耐久:宽*/
    public static final float MINT2WIDTH_RATE = 0.2f

    Weapon weapon

    /**图案*/
    Drawable pattern
    Drawable cardBackground

    //以下用来绘制文字
    TextDrawerHolder attackHolder
    TextDrawerHolder bloodHolder
    TextDrawerHolder titleHolder

    private Paint errPaint

    public WeaponView(Context context) {
        super(context)
        init(null, 0)
    }

    public WeaponView(Context context, AttributeSet attrs) {
        super(context, attrs)
        init(attrs, 0)
    }

    public WeaponView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle)
        init(attrs, defStyle)
    }

    def bind(Weapon weapon) {
        this.weapon = weapon
//        pattern=getResources().getDrawable(card.patternId)//FIXME
        cardBackground = getResources().getDrawable(R.drawable.weapon_view_background)//FIXME

        initPaint()

        invalidate()
    }

    private void init(AttributeSet attrs, int defStyle) {
        errPaint = new Paint()
        errPaint.setColor(Color.RED)

        //FIXME 测试用
        CardBuilder.init()
        bind(CardBuilder.newCard(200001))
    }

    private void initPaint() {
        attackHolder = new TextDrawerHolder()
        bloodHolder = new TextDrawerHolder()
        titleHolder = new TextDrawerHolder()

    }

    private void invalidatePaintAndMeasurements() {
        attackHolder.setParams(weapon.attack.current.toString(), width * MINT2WIDTH_RATE as float,
                width * 0.1 as float, height * 0.8f as float, Datum.CENTER)
        bloodHolder.setParams(weapon.blood.current.toString(), width * MINT2WIDTH_RATE as float,
                width * 0.9 as float, height * 0.8f as float, Datum.CENTER)
//标题
        titleHolder.setParams(weapon.title, width * TITLE2WIDTH_RATE as float,
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

        Object.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Object.onDraw(canvas)
        if (!weapon) {
            canvas.drawRect(0, 0, width, height, errPaint)
            return
        }

        invalidatePaintAndMeasurements()

        cardBackground?.with {
            setBounds(0, 0, width, height)
            draw(canvas)
        }
        titleHolder.draw(canvas)
        attackHolder.draw(canvas)
        bloodHolder.draw(canvas)
    }


}
