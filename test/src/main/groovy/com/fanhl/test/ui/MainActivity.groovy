package com.fanhl.test.ui

import android.os.Bundle
import com.fanhl.test.R

public class MainActivity extends AbstractBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fV(R.id.linearDrag).onClickListener = { openActivity(LinearLayoutDragActivity.class) }
    }
}
