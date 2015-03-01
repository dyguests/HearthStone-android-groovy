package com.fanhl.hearthstone.ui

import android.os.Bundle
import com.fanhl.hearthstone.R
import com.fanhl.util.Lg

/**
 * Created by fanhl on 15/1/21.
 */
class BoardActivity extends AbstractBaseActivity {
    static lgd = Lg.d.curry BoardActivity.class.getSimpleName()


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)
        assignViews()
        loadGame()
    }

    private void assignViews() {
    }

    def loadGame() {

    }
}
