package com.fanhl.hearthstone.ui

import android.os.Bundle
import com.fanhl.hearthstone.R

/**
 * Created by fanhl on 15/2/25.
 */
class DemoActivity extends AbstractBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        assignViews()
    }

    private void assignViews() {
    }
}
