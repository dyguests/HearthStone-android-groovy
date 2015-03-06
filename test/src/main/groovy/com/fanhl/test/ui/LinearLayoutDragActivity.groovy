package com.fanhl.test.ui

import android.animation.LayoutTransition
import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.fanhl.test.R
import com.fanhl.test.widget.OvalView
import com.fanhl.test.widget.RectView

public class LinearLayoutDragActivity extends AbstractBaseActivity {
    private LinearLayout container1
    private LinearLayout container2

    LinearLayout[] containers

    private RectView item1
    private RectView item2
    private RectView item3
    private RectView item4
    private RectView item5

    RectView[] items

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_layout_drag)
        assignViews()
        bindListener()
    }

    private void assignViews() {
        container1 = (LinearLayout) findViewById(R.id.container1)
        container2 = (LinearLayout) findViewById(R.id.container2)

        containers = [container1, container2]

        item1 = (RectView) findViewById(R.id.item1)
        item2 = (RectView) findViewById(R.id.item2)
        item3 = (RectView) findViewById(R.id.item3)
        item4 = (RectView) findViewById(R.id.item4)
        item5 = (RectView) findViewById(R.id.item5)

        items = [item1, item2, item3, item4, item5]
    }

    void bindListener() {
        containers[1].onDragListener = containerOnDragListener
        containers*.layoutTransition = new LayoutTransition()
        items*.onTouchListener = itemOnTouchListener
    }


    View.OnDragListener containerOnDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:/*do nothing*/ break
                case DragEvent.ACTION_DRAG_ENTERED:/*v.setBackgroundDrawable(enterShape)*/ break
                case DragEvent.ACTION_DRAG_EXITED:/*v.setBackgroundDrawable(normalShape)*/ break
                case DragEvent.ACTION_DROP:
                    View scrItem = (View) event.getLocalState()
                    ViewGroup srcContainer = (ViewGroup) scrItem.getParent()


                    View destItem = createDestItem(scrItem)
                    LinearLayout destContainer = (LinearLayout) v
                    int addViewIndex = getAddViewIndex(destContainer, destItem, event.getX())

                    srcContainer.removeView(scrItem)
                    destContainer.addView(destItem, addViewIndex)
                    //scrItem.setVisibility(View.VISIBLE)
                    break
                case DragEvent.ACTION_DRAG_ENDED:/*v.setBackgroundDrawable(normalShape)*/ break
                default: break
            }
            return true
        }

        View createDestItem(View view) {
            def ovalView = new OvalView(LinearLayoutDragActivity.this)

            ovalView.text = ((RectView) view).text

            ovalView
        }
        /**
         * 仅适用于横向布局
         *
         * @param container
         * @param view
         * @param x
         */
        private int getAddViewIndex(LinearLayout container, View view, float x) {
            int childCount = container.getChildCount()

            for (int i = 0; i < childCount; i++) {
                View child = container.getChildAt(i)
                float childX = child.getX() + child.getWidth() / 2//FIXME 还要算上margin
                if (x <= childX) return i
            }

            return childCount
        }
    }



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
}