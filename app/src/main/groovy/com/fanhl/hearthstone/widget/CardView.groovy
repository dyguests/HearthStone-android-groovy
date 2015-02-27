package com.fanhl.hearthstone.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.fanhl.hearthstone.R
import com.fanhl.hearthstone.constants.C

/**
 * 卡牌视图(只显示卡牌形状时的视图,其它形态使用其它的View来表示)
 *
 * 由于长宽比是固定的,所以只需要设定宽度(具体值),设定高度没有用
 *
 * 不接收padding参数
 *
 * Created by fanhl on 15/2/25.
 */
public class CardView extends View {
    int direction = 0//0b0:正面,0b1:背面,0b10:上下倒转

    int type = Type.SPELL.id
    int species = Species.Neutral.id

    String cardTitle = "标题"
    String description = "描述"
    String explain = "台词"

    int race = Race.NONE.id
    String raceString

    /**图案*/
    Drawable pattern
    Drawable foreground
    Drawable cardBackground

    private TextPaint mRacePaint
    private float mRaceWidth
    private float mRaceHeight

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

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.CardView, defStyle, 0)

        direction = typedArray.getInt(R.styleable.CardView_direction, direction)
        type = typedArray.getInt(R.styleable.CardView_type, type)
        species = typedArray.getInt(R.styleable.CardView_species, species)
        cardTitle = typedArray.getString(R.styleable.CardView_cardTitle) ?: cardTitle
        description = typedArray.getString(R.styleable.CardView_description) ?: description
        explain = typedArray.getString(R.styleable.CardView_explain) ?: explain
        race = typedArray.getInt(R.styleable.CardView_race, race)

        if (typedArray.hasValue(R.styleable.CardView_pattern)) {
            pattern = typedArray.getDrawable(R.styleable.CardView_pattern)
        }
        if (typedArray.hasValue(R.styleable.CardView_foreground)) {
            foreground = typedArray.getDrawable(R.styleable.CardView_foreground)
        }
        if (typedArray.hasValue(R.styleable.CardView_cardBackground)) {
            cardBackground = typedArray.getDrawable(R.styleable.CardView_cardBackground)
        }

        typedArray.recycle()

        initPaint()
    }

    private void initPaint() {
        //种族
        mRacePaint = new TextPaint()
        mRacePaint.setFlags(Paint.ANTI_ALIAS_FLAG)
        mRacePaint.setTextAlign(Paint.Align.LEFT)
        mRacePaint.setColor(Color.WHITE)//FIXME 写入资源文件
    }

    private void invalidatePaintAndMeasurements() {

        //种族
        mRacePaint.setTextSize(width * C.RACE2WIDTH_RATE as float)
        raceString = Race.values()[race].toString()
        mRaceWidth = mRacePaint.measureText(raceString)
        Paint.FontMetrics fontMetrics = mRacePaint.getFontMetrics()
        mRaceHeight = fontMetrics.bottom
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = android.view.View.MeasureSpec.getSize(widthMeasureSpec)
        int widthMode = android.view.View.MeasureSpec.getMode(widthMeasureSpec)

        if (widthMode != android.view.View.MeasureSpec.EXACTLY) {
            throw new Exception("not MeasureSpec.EXACTLY mode!")
        }

        int heightSize = widthSize * C.HEIGHT2WIDTH_RATE
        int newHeightMeasureSpec = android.view.View.MeasureSpec.makeMeasureSpec(heightSize, widthMode)

        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
    }

    @Override
    protected void onDraw(Canvas canvas) { direction & 1 ? onDrawBack(canvas) : onDrawFace(canvas) }

    def onDrawBack(Canvas canvas) {
        cardBackground?.with {
            setBounds(0, 0, width, height)
            draw(canvas)
        }
    }

    def onDrawFace(Canvas canvas) {
        foreground?.with {
            setBounds(0, 0, width, height)
            draw(canvas)
        }

        invalidatePaintAndMeasurements()

        //race 种族
        canvas.drawText(raceString,
                (width - mRaceWidth) / 2 as float,
                (height - mRaceHeight) as float,
                mRacePaint)
    }

    /**
     * 卡牌类型
     * FIXME 之后把这个移动到Card中去
     */
    public static enum Type {
        SPELL(0), MINION(1), WEAPON(2)

        int id

        private Type(int id) { this.id = id }
    }

    /**
     * 卡牌种别
     * FIXME 之后把这个移动到Card中去
     */
    public static enum Species {
        Neutral(0),
        Druid(1),
        Hunter(2),
        Master(3),
        Paladin(4),
        Pastor(5),
        Thieves(6),
        Shaman(7),
        Warlock(8),
        Warrior(9)

        int id

        private Species(int id) { this.id = id }
    }

    /**
     * 卡牌种族
     * FIXME 之后把这个移动到Card中去
     */
    public static enum Race {
        NONE(0, ""), BEAST(1, "野兽"), MACHINE(2, "机械"), MURLOC(3, "鱼人"), DRAGON(4, "龙")

        int id
        String name

        private Race(int id, String name) { this.id = id; this.name = name }

        @Override
        String toString() { name }
    }
}
