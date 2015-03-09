package com.fanhl.test.ui

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import com.fanhl.test.R
import com.fanhl.test.model.Item
import com.fanhl.test.widget.OvalContainerView
import com.fanhl.test.widget.RectContainerView

public class RecyclerViewDragActivity extends AbstractBaseActivity {

    private RectContainerView rectContainerView
    private OvalContainerView ovalContainerView


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_drag)
        assignViews()
        initData()
    }

    private void assignViews() {
        fV(R.id.demo).onClickListener={

        }

        rectContainerView = (RectContainerView) findViewById(R.id.rectContainerView)
        ovalContainerView = (OvalContainerView) findViewById(R.id.ovalContainerView)
    }

    void initData() {
        List<Item> items1 = []
        items1 << new Item(title: "张三")
        items1 << new Item(title: "张四")
        items1 << new Item(title: "张五")
        items1 << new Item(title: "张六")
        rectContainerView.bind(items1)

        List<Item> items2 = []
        items2 << new Item(title: "王三")
        ovalContainerView.bind(items2)
    }
}
