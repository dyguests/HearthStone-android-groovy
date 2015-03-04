package com.fanhl.hearthstone.widget.container

import android.content.Context
import android.content.res.TypedArray
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
    public static final int OWNER_ME = 0
    public static final int OWNER_RIVAL = 1
    Belong data

    int owner = OWNER_ME

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

        // Load attributes
        final TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.BelongView, defStyle, 0)
        owner = typedArray.getInt(R.styleable.BelongView_owner, owner)
        typedArray.recycle()

        if (owner == OWNER_ME) {
            inflater.inflate(R.layout.view_belong_me, this)
        } else {
            inflater.inflate(R.layout.view_belong_rival, this)
        }
        assignViews()
    }

    private void assignViews() {

    }

}