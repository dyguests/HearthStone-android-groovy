package com.fanhl.test.widget

import android.content.ClipData
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.fanhl.test.R
import com.fanhl.test.model.Item

/**
 * Created by fanhl on 15/3/9.
 */
abstract class AbstractContainerView extends RelativeLayout {
    List<Item> items
    protected ItemAdapter itemAdapter

    protected RecyclerView recyclerView

    AbstractContainerView(Context context) {
        super(context)
        init(context, null, 0)
    }

    AbstractContainerView(Context context, AttributeSet attrs) {
        super(context, attrs)
        init(context, attrs, 0)
    }

    AbstractContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr)
        init(context, attrs, defStyleAttr)
    }

    def bind(List<Item> items) {
        this.items = items

        bindAdapter(items)
    }

    def init(Context context, AttributeSet attributeSet, int i) {
        LayoutInflater inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
        inflater.inflate(R.layout.view_recycler_container, this)

        assignViews()
    }

    private void assignViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView)

        // 设置LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext())
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setHasFixedSize(true)
    }

    protected abstract void bindAdapter(List<Item> items)


    abstract static class ItemAdapter extends RecyclerView.Adapter<Holder> {

        public static final int MINION_WIDTH = 200

        final Context context
        private ViewGroup.LayoutParams layoutParams

        List<Item> items

        public ItemAdapter(Context context, List<Item> items) {
            this.context = context
            this.items = items
            init()
        }

        def init() {
            layoutParams = new ViewGroup.LayoutParams()
            this.layoutParams.width = MINION_WIDTH
        }

        @Override
        Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
            def itemView = createItemView(context)
            itemView.setLayoutParams(layoutParams)
            itemView.onTouchListener = itemOnTouchListener
            new Holder(itemView)
        }

        abstract View createItemView(Context context)

        @Override
        void onBindViewHolder(Holder holder, int i) { holder.bind(items[i]) }

        View.OnTouchListener itemOnTouchListener = new View.OnTouchListener() {
            @Override
            boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "")
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v)
                    v.startDrag(data, shadowBuilder, v, 0)
                    v.setVisibility(View.INVISIBLE)
                    return true
                } else {
                    return false
                }
            }
        }

        @Override
        int getItemCount() { return items.size() }

        static class Holder extends RecyclerView.ViewHolder {
            Holder(View itemView) { super(itemView) }

            def bind(Item item) { ((AbstractItemView) itemView).bind(item) }
        }
    }
}
