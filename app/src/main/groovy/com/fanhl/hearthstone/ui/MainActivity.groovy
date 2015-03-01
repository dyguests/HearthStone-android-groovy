package com.fanhl.hearthstone.ui

import android.os.Bundle
import com.fanhl.hearthstone.R

public class MainActivity extends AbstractBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        assignViews()
    }

    private void assignViews() {
//        findViewById(R.id.fright).onClickListener = { openActivity(FrightActivity.class) }
//        findViewById(R.id.practice).onClickListener = { openActivity(PracticeActivity.class) }
//        findViewById(R.id.arena).onClickListener = { openActivity(ArenaActivity.class) }
//        findViewById(R.id.myCollection).onClickListener = { openActivity(MyCollectionActivity.class) }
        findViewById(R.id.boardDemo).onClickListener = { openActivity(BoardActivity.class) }
        findViewById(R.id.demo).onClickListener = { openActivity(DemoActivity.class) }
    }
}
