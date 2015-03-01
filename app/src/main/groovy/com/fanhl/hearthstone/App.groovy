package com.fanhl.hearthstone

import android.app.Application
import com.fanhl.hearthstone.factory.CardBuilder
import com.fanhl.hearthstone.factory.HeroBuilder
import com.fanhl.hearthstone.factory.SkillBuilder
import com.fanhl.util.Lg

/**
 * Created by fanhl on 15/2/27.
 */
class App extends Application {
    static lgd = Lg.d.curry App.class.getSimpleName()


    @Override
    void onCreate() {
        super.onCreate()

        init()
    }

    def init() {
        CardBuilder.init()
        SkillBuilder.init()
        HeroBuilder.init()
    }

}
