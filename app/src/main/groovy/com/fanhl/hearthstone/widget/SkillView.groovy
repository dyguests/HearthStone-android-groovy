package com.fanhl.hearthstone.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.fanhl.hearthstone.R
import com.fanhl.hearthstone.factory.CardBuilder
import com.fanhl.hearthstone.factory.SkillBuilder
import com.fanhl.hearthstone.model.Skill
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
public class SkillView extends AbstractView {
    /**高:宽*/
    public static final float HEIGHT2WIDTH_RATE = 1.0f

    public static final float TITLE2WIDTH_RATE = 0.3f
    /**费|攻击|血|耐久:宽*/
    public static final float MINT2WIDTH_RATE = 0.3f

    Skill skill

    /**图案*/
    Drawable pattern
    Drawable cardBackground

    //以下用来绘制文字
    TextDrawerHolder costHolder
    TextDrawerHolder titleHolder

    private Paint errPaint

    public SkillView(Context context) {
        super(context)
        init(null, 0)
    }

    public SkillView(Context context, AttributeSet attrs) {
        super(context, attrs)
        init(attrs, 0)
    }

    public SkillView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle)
        init(attrs, defStyle)
    }

    def bind(Skill skill) {
        this.skill = skill
//        pattern=getResources().getDrawable(card.patternId)//FIXME
        cardBackground = getResources().getDrawable(R.drawable.weapon_view_background)//FIXME

        initPaint()

        invalidate()
    }

    private void init(AttributeSet attrs, int defStyle) {
        errPaint = new Paint()
        errPaint.setColor(Color.RED)

        //FIXME 测试用
        SkillBuilder.init()
        bind(SkillBuilder.createSkill(100001))
    }

    private void initPaint() {
        costHolder = new TextDrawerHolder()
        titleHolder = new TextDrawerHolder()

    }

    private void invalidatePaintAndMeasurements() {
        costHolder.setParams(skill.cost.current.toString(), width * MINT2WIDTH_RATE as float,
                width * 0.9 as float, height * 0.7f as float, Datum.CENTER)
//标题
        titleHolder.setParams(skill.title, width * TITLE2WIDTH_RATE as float,
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
        if (!skill) {
            canvas.drawRect(0, 0, width, height, errPaint)
            return
        }

        invalidatePaintAndMeasurements()

        cardBackground?.with {
            setBounds(0, 0, width, height)
            draw(canvas)
        }
        titleHolder.draw(canvas)
        costHolder.draw(canvas)
    }


}
