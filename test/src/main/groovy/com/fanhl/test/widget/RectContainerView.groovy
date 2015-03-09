package com.fanhl.test.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanhl.test.R
import com.fanhl.test.model.Item

/**
 * Items的Container
 *
 * Created by fanhl on 15/3/8.
 */
public class RectContainerView extends AbstractContainerView {
    List<Item> items

    private RecyclerView recyclerView
    ItemAdapter itemAdapter

    public RectContainerView(Context context) {
        super(context)
        init(context, null, 0)
    }


    public RectContainerView(Context context, AttributeSet attrs) {
        super(context, attrs)
        init(context, attrs, 0)
    }

    public RectContainerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle)
        init(context, attrs, defStyle)
    }

    def init(Context context, AttributeSet attributeSet, int i) {
        LayoutInflater inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
        inflater.inflate(R.layout.view_rect_container, this)

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


    def bind(List<Item> items) {
        this.items = items
        itemAdapter = new ItemAdapter(getContext(), items)
        recyclerView.setAdapter(itemAdapter)
//        itemAdapter.notifyDataSetChanged()
    }

    /**
     * Created by fanhl on 15/3/9.
     */
    static class ItemAdapter extends RecyclerView.Adapter<Holder> {

        public static final int MINION_WIDTH = 200

        final Context context
        private ViewGroup.LayoutParams layoutParams

        List<Item> items

        public ItemAdapter(Context context, List<Item> site) {
            this.context = context
            this.items = site
            init()
        }

        def init() {
            layoutParams = new ViewGroup.LayoutParams()
            this.layoutParams.width = MINION_WIDTH
        }

        @Override
        Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
            def itemView = new RectView(context)
            itemView.setLayoutParams(layoutParams)
            new Holder(itemView)
        }

        @Override
        void onBindViewHolder(Holder holder, int i) { holder.bindData(items[i]) }

        @Override
        int getItemCount() { return items.size() }

        static class Holder extends RecyclerView.ViewHolder {
            Holder(View itemView) { super(itemView) }

            def bindData(Item minion) { itemView.bind(minion) }
        }
    }
}