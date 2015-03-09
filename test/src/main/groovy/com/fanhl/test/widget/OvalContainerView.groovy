package com.fanhl.test.widget

import android.content.Context
import android.view.View
import com.fanhl.test.model.Item
import groovy.transform.InheritConstructors

/**
 * Items的Container
 *
 * Created by fanhl on 15/3/8.
 */
@InheritConstructors
public class OvalContainerView extends AbstractContainerView {
    @Override
    protected void bindAdapter(List<Item> items) {
        itemAdapter = new OvalContainerView.ItemAdapter(getContext(), items)
        recyclerView.setAdapter(itemAdapter)
//        itemAdapter.notifyDataSetChanged()
    }

    static class ItemAdapter extends AbstractContainerView.ItemAdapter {
        ItemAdapter(Context context, List<Item> items) { super(context, items) }

        @Override
        View createItemView(Context context) { new OvalView(context) }
    }
}