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
import com.fanhl.hearthstone.model.card.Card
import com.fanhl.hearthstone.model.card.Minion
import com.fanhl.hearthstone.model.card.Weapon

/**
 * AbstractView
 *
 * 包含两绘制器
 *
 * 将CardView/MinionView/WeaponView/... 的共通部分抽到这里来
 *
 *
 * 不接收padding参数
 *
 * Created by fanhl on 15/2/28.
 */
public abstract class AbstractView extends View {

    public AbstractView(Context context) {
        super(context)
    }

    public AbstractView(Context context, AttributeSet attrs) {
        super(context, attrs)
    }

    public AbstractView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle)
    }


    static class TextDrawerHolder {
        String text

        TextPaint paint
        float width
        float height
        float x
        float y

        public TextDrawerHolder() {
            paint = new TextPaint()
            paint.setFlags(Paint.ANTI_ALIAS_FLAG)
            paint.setTextAlign(Paint.Align.LEFT)//FIXME 这是原来的
//            paint.setTextAlign(Paint.Align.CENTER)
            paint.setColor(Color.GREEN)//FIXME 写入资源文件
        }

        /**
         * 绘制要用到的一些参数
         *
         * @param text
         * @param textSize
         * @param xx
         * @param yy
         * @param datum 基准点
         * @return
         */
        def setParams(String text, Float textSize, Float xx, Float yy, Datum datum) {
            this.text = text
            paint.setTextSize(textSize)

            width = paint.measureText(text)
            height = paint.getFontMetrics().top - paint.getFontMetrics().bottom

//            def metrics = paint.getFontMetrics()
//            throw new Exception("""metrics ${metrics.top} ${metrics.bottom}""")

            //因为本身是以水平居中为基点的
            switch (datum) {
                case Datum.TOP: x = xx - width / 2; y = yy; break
                case Datum.BOTTOM: x = xx - width / 2; y = yy - height; break
                case Datum.LEFT: x = xx; y = yy - height / 2; break
                case Datum.RIGHT: x = xx - width; y = yy - height / 2; break
                case Datum.CENTER: x = xx - width / 2; y = yy - height / 2; break
                default: x = xx; y = yy; break
            }
        }

        def draw(Canvas canvas) {
            canvas.drawText(text, x, y, paint)
        }
    }

    /**
     * 基准点
     *
     * 专门给 CardView.TextDrawerHolder#setParams 用的
     * 用来设置(x,y)对应的TextDrawerHolder的哪个位置
     */
    static enum Datum {
        TOP, BOTTOM, LEFT, RIGHT, CENTER
    }
}
