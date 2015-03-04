package com.fanhl.hearthstone.widget.container

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.fanhl.hearthstone.R
import com.fanhl.hearthstone.factory.CardBuilder
import com.fanhl.hearthstone.model.card.Minion
import com.fanhl.hearthstone.model.container.Hands
import com.fanhl.hearthstone.model.container.Site
import com.fanhl.hearthstone.widget.item.CardView
import com.fanhl.hearthstone.widget.item.MinionView

/**
 * 场地(用于放置随从)
 *
 * Created by fanhl on 15/1/21.
 */
public class HandsView extends RelativeLayout {
    Hands data

    private RecyclerView recyclerView
    HandsAdapter siteAdapter

    public HandsView(Context context) {
        super(context)
        init(context, null, 0)
    }


    public HandsView(Context context, AttributeSet attrs) {
        super(context, attrs)
        init(context, attrs, 0)
    }

    public HandsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle)
        init(context, attrs, defStyle)
    }

    def init(Context context, AttributeSet attributeSet, int i) {
        LayoutInflater inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
        inflater.inflate(R.layout.view_hands, this)

        assignViews()
        editModeDemo()
    }

    private void assignViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView)

        // 设置LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext())
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setHasFixedSize(true)


    }

    private boolean editModeDemo() {
//        if (!isInEditMode()) return


        def hands = new Hands()
        CardBuilder.init()
        hands << CardBuilder.newCard(100001)
        hands << CardBuilder.newCard(100002)
//        hands << CardBuilder.newCard(200001)
//        hands << CardBuilder.newCard(400001)
        bindData(hands)
    }

    def notifyOperate() {
        //FIXME 通知当前操作
    }

    def bindData(Hands data) {
        this.data = data
        siteAdapter = new HandsAdapter(getContext(), data)
        recyclerView.setAdapter(siteAdapter)
//        siteAdapter.notifyDataSetChanged()
    }

    /**
     * 取得要插入的位置
     * 横向布局用
     *
     * @param container
     * @param view
     * @param x
     */
    private int getAddViewIndex(LinearLayout /*FIXME*/ container, View view, float x) {
        int childCount = container.getChildCount()
        0.upto(childCount) {
            View child = container.getChildAt(it)
            float childX = child.getX() + child.getWidth() / 2//FIXME 还要算上margin
            if (x <= childX) {
                Log.d("横向布局用", "viewX:" + x + " <= childX:" + childX)
                return it
            }
        }

        return childCount
    }

    /**
     * 用于显示场地牌的adapter
     *
     * Created by fanhl on 15/1/24.
     */
    static class HandsAdapter extends RecyclerView.Adapter<Holder> {

        public static final int MINION_WIDTH = 200

        final Context context
        private ViewGroup.LayoutParams layoutParams

        Hands hands

        public HandsAdapter(Context context, Hands hands) {
            this.context = context
            this.hands = hands
            init()
        }

        def init() {
            layoutParams = new ViewGroup.LayoutParams()
            this.layoutParams.width = MINION_WIDTH
        }

        @Override
        Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
            def cardView = new CardView(context)
//            cardView.getLayoutParams().width=MINION_WIDTH
            cardView.setLayoutParams(layoutParams)
            new Holder(cardView)
        }

        @Override
        void onBindViewHolder(Holder holder, int i) { holder.bindData(hands[i]) }

        @Override
        int getItemCount() { return hands.size() }

        static class Holder extends RecyclerView.ViewHolder {
            Holder(View itemView) { super(itemView) }

            def bindData(Minion minion) { itemView.bind(minion) }
        }
    }
}