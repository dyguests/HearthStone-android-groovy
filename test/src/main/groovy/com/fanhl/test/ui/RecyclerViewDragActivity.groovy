package com.fanhl.test.ui

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import com.fanhl.test.R
import com.fanhl.test.model.Item
import com.fanhl.test.widget.AbstractContainerView
import com.fanhl.test.widget.AbstractItemView
import com.fanhl.test.widget.OvalContainerView
import com.fanhl.test.widget.OvalView
import com.fanhl.test.widget.RectContainerView
import com.fanhl.test.widget.RectView

public class RecyclerViewDragActivity extends AbstractBaseActivity {

    private RectContainerView rectContainerView
    private OvalContainerView ovalContainerView
    private List<Item> items1


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_drag)
        assignViews()
        initData()
    }

    private void assignViews() {
          rectContainerView = (RectContainerView) findViewById(R.id.rectContainerView)
        ovalContainerView = (OvalContainerView) findViewById(R.id.ovalContainerView)

//        rectContainerView.recyclerView.layoutTransition = new LayoutTransition()
        rectContainerView.recyclerView.itemAnimator=new DefaultItemAnimator()
        rectContainerView.recyclerView.onDragListener = new OnContainerDragListener()

//        ovalContainerView.recyclerView.layoutTransition = new LayoutTransition()
        ovalContainerView.recyclerView.itemAnimator=new DefaultItemAnimator()
        ovalContainerView.recyclerView.onDragListener = new OnContainerDragListener()
    }

    void initData() {
        items1 = []
        this.items1 << new Item(title: "张三")
        this.items1 << new Item(title: "张四")
        this.items1 << new Item(title: "张五")
        this.items1 << new Item(title: "张六")
        rectContainerView.bind(this.items1)

        List<Item> items2 = []
        items2 << new Item(title: "王三")
        items2 << new Item(title: "王四")
        items2 << new Item(title: "王五")
        ovalContainerView.bind(items2)
    }

    class OnContainerDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:/*do nothing*/ break
                case DragEvent.ACTION_DRAG_ENTERED:/*v.setBackgroundDrawable(enterShape)*/ break
                case DragEvent.ACTION_DRAG_EXITED:/*v.setBackgroundDrawable(normalShape)*/ break
                case DragEvent.ACTION_DROP:
                    View scrItemView = (View) event.getLocalState()
                    Item scrItem = ((AbstractItemView) scrItemView).data

                    RecyclerView srcContainer = (RecyclerView) scrItemView.getParent()

                    def srcItems = ((AbstractContainerView.ItemAdapter) srcContainer.getAdapter()).items
                    int srcIndex = srcItems.indexOf(scrItem)

//                    srcItems.remove(srcIndex)
//                    srcContainer.getAdapter().notifyItemRemoved(srcIndex)

                    RecyclerView destContainer = (RecyclerView) v
                    int destIndex = getAddViewIndex(destContainer, event.getX())



                    List<Item> destItems = ((AbstractContainerView.ItemAdapter) destContainer.getAdapter()).items

                    srcItems.remove(srcIndex)
                    destItems.add(destIndex, scrItem)

                    //FIXME 从一个布局拖到另一个布局后,再从右一个布局拖回来就不行了
                    srcContainer.getAdapter().notifyItemRemoved(srcIndex)
                    destContainer.getAdapter().notifyItemInserted(destIndex)

                    //scrItem.setVisibility(View.VISIBLE)
                    break
                case DragEvent.ACTION_DRAG_ENDED:/*v.setBackgroundDrawable(normalShape)*/ break
                default: break
            }
            return true
        }

        View createDestItem(View view) {
            def ovalView = new OvalView(RecyclerViewDragActivity.this)

            ovalView.bind(((RectView) view).data)

            ovalView
        }
        /**
         * 仅适用于横向布局
         *
         * @param container
         * @param x
         */
        private int getAddViewIndex(ViewGroup container, float x) {
            int childCount = container.getChildCount()

            for (int i = 0; i < childCount; i++) {
                View child = container.getChildAt(i)
                float childX = child.getX() + child.getWidth() / 2//FIXME 还要算上margin
                if (x <= childX) return i
            }

            return childCount
        }
    }
}
