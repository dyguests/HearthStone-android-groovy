package com.fanhl.test.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBarActivity

/**
 * Created by fanhl on 15/3/6.
 */
abstract class AbstractBaseActivity extends ActionBarActivity {
    protected def fV = { int id -> findViewById(id) }

    protected void openActivity(Class<?> pClass) { openActivity(pClass, null) }

    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass)
        if (pBundle) intent.putExtras(pBundle)
        startActivity(intent)
    }

    protected void openActivity(String pAction) { openActivity(pAction, null) }

    protected void openActivity(String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction)
        if (pBundle) intent.putExtras(pBundle)
        startActivity(intent)
    }
}
