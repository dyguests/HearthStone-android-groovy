package com.fanhl.hearthstone.ui

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.fanhl.hearthstone.R

public class SplashActivity extends AbstractBaseActivity {
    Handler mHandler = new Handler()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        View view = View.inflate(this, R.layout.activity_splash, null)
        setContentView(view)
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha)
        view.startAnimation(animation)
        animation.animationListener = [
                onAnimationStart : {},
                onAnimationRepeat: {},
                onAnimationEnd   : { mHandler.postDelayed({ goHome() }, 200) }
        ] as Animation.AnimationListener
    }

    private void goHome() {
        openActivity(MainActivity.class)
        finish()
    }
}
