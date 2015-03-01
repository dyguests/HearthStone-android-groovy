package com.fanhl.hearthstone.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.fanhl.hearthstone.R
import com.fanhl.hearthstone.factory.CardBuilder
import com.fanhl.hearthstone.model.card.Card
import com.fanhl.hearthstone.model.card.Minion
import com.fanhl.hearthstone.model.card.Weapon

/**
 * 卡牌视图(只显示卡牌形状时的视图,其它形态使用其它的View来表示)
 *
 * 由于长宽比是固定的,所以只需要设定宽度(具体值),设定高度没有用
 *
 * 不接收padding参数
 *
 * Created by fanhl on 15/2/25.
 */
public class CardView extends AbstractView {
    /**高:宽*/
    public static final float HEIGHT2WIDTH_RATE = 1.5f

    public static final float TITLE2WIDTH_RATE = 0.15f
    public static final float DESCRIPTION2WIDTH_RATE = 0.1f
    /**种族字体大小:宽*/
    public static final float RACE2WIDTH_RATE = 0.08f
    /**费|攻击|血|耐久:宽*/
    public static final float MINT2WIDTH_RATE = 0.2f


    int direction = 0//0b0:正面,0b1:背面,0b10:上下倒转

    Card card

    /**图案*/
    Drawable pattern
    Drawable foreground
    Drawable cardBackground

    //以下用来绘制文字
    TextDrawerHolder costHolder
    TextDrawerHolder attackHolder
    TextDrawerHolder bloodHolder
    TextDrawerHolder titleHolder
    TextDrawerHolder descriptionHolder
    TextDrawerHolder raceHolder

    private Paint errPaint

    public CardView(Context context) {
        super(context)
        init(null, 0)
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs)
        init(attrs, 0)
    }

    public CardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle)
        init(attrs, defStyle)
    }

    def bind(Card card) {
        this.card = card
//        pattern=getResources().getDrawable(card.patternId)//FIXME
        foreground = getResources().getDrawable(R.drawable.card_view_foreground_normal)//FIXME 之后改成根据card从Builder中算出来
        cardBackground = getResources().getDrawable(R.drawable.card_view_background_normal)//FIXME

        initPaint()

        invalidate()
    }

    private void init(AttributeSet attrs, int defStyle) {
        errPaint = new Paint()
        errPaint.setColor(Color.RED)

        //FIXME 测试用
        CardBuilder.init()
        bind(CardBuilder.newCard(100001))
    }

    private void initPaint() {
        titleHolder = new TextDrawerHolder()
        descriptionHolder = new TextDrawerHolder()

        costHolder = new TextDrawerHolder()

        if (card instanceof Minion || card instanceof Weapon) {
            attackHolder = new TextDrawerHolder()
            bloodHolder = new TextDrawerHolder()
        }
        if (card instanceof Minion) {
            //种族
            raceHolder = new TextDrawerHolder()
        }
    }

    private void invalidatePaintAndMeasurements() {

        //标题
        titleHolder.setParams(card.title, width * TITLE2WIDTH_RATE as float,
                width / 2 as float, height * 0.5f as float, Datum.CENTER)
        //描述
        descriptionHolder.setParams(card.description, width * DESCRIPTION2WIDTH_RATE as float,
                width / 2 as float, height * 0.67f as float, Datum.CENTER)

        costHolder.setParams(card.cost.current.toString(), width * MINT2WIDTH_RATE as float,
                width * 0.1f as float, height * 0.1f as float, Datum.CENTER)

        if (card instanceof Minion || card instanceof Weapon) {
            attackHolder.setParams(card.attack.current.toString(), width * MINT2WIDTH_RATE as float,
                    width * 0.1 as float, height * 0.9f as float, Datum.CENTER)
            bloodHolder.setParams(card.blood.current.toString(), width * MINT2WIDTH_RATE as float,
                    width * 0.9 as float, height * 0.9f as float, Datum.CENTER)
        }
        if (card instanceof Minion) {
            Minion minion = card

            //FIXME 之后这些x,y坐标全部要换成constants

            //种族
            raceHolder.setParams(minion.race.name, width * RACE2WIDTH_RATE as float,
                    width / 2 as float, height * 0.95f as float, Datum.CENTER)
        }

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
        card ? (direction & 1 ? onDrawBack(canvas) : onDrawFace(canvas))
                : canvas.drawRect(0, 0, width, height, errPaint)
    }

    def onDrawBack(Canvas canvas) {
        cardBackground?.with {
            setBounds(0, 0, width, height)
            draw(canvas)
        }
    }

    def onDrawFace(Canvas canvas) {
        invalidatePaintAndMeasurements()

        foreground?.with {
            setBounds(0, 0, width, height)
            draw(canvas)
        }

        titleHolder.draw(canvas)
        descriptionHolder.draw(canvas)
        costHolder.draw(canvas)

        if (card instanceof Minion || card instanceof Weapon) {
            attackHolder.draw(canvas)
            bloodHolder.draw(canvas)
        }

        if (card instanceof Minion) {
            Minion minion = card


            raceHolder.draw(canvas)
        }
    }
}
