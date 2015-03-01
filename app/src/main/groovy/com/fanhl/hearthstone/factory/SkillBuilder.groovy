package com.fanhl.hearthstone.factory

import com.fanhl.hearthstone.lang.MInt
import com.fanhl.hearthstone.model.Skill


/**
 * Created by fanhl on 15/2/5.
 */
class SkillBuilder {

    static init() {

    }
    //FIXME 改成从map中去取

    static Skill createSkill(int id) {
        new Skill(title: "增援", patternId: 0, description: "列兵", cost: new MInt(2))
    }
}
