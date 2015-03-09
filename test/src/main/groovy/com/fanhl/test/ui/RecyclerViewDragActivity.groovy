package com.fanhl.test.ui

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import com.fanhl.test.R
import com.fanhl.test.model.Item
import com.fanhl.test.widget.RectContainerView

public class RecyclerViewDragActivity extends ActionBarActivity {

    private RectContainerView rectContainerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_drag)
        assignViews()
        initData()
    }

    private void assignViews() {
        rectContainerView = (RectContainerView) findViewById(R.id.rectContainerView)
    }

    void initData() {
        List<Item> items = []
        items << new Item(title: "张三")
        items << new Item(title: "张四")
        items << new Item(title: "张五")
        items << new Item(title: "张六")

        rectContainerView.bind(items)
    }
}
