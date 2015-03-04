package com.fanhl.hearthstone.widget.container

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.fanhl.hearthstone.R
import com.fanhl.hearthstone.model.container.Belong

/**
 * 玩家所属区域(用来区分游戏双方)
 *
 * Created by fanhl on 15/1/28.
 */
public class BelongView extends LinearLayout {
    Belong data

    public BelongView(Context context) {
        super(context)
        init(context, null, 0)
    }

    public BelongView(Context context, AttributeSet attrs) {
        super(context, attrs)
        init(context, attrs, 0)
    }


    public BelongView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle)
        init(context, attrs, defStyle)
    }

    def init(Context context, AttributeSet attributeSet, int defStyle) {
        LayoutInflater inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
        inflater.inflate(R.layout.view_belong, this)
        assignViews()
    }

    private void assignViews() {

    }

}