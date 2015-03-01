package com.fanhl.hearthstone.factory

import android.util.SparseArray
import com.fanhl.hearthstone.lang.MInt
import com.fanhl.hearthstone.model.Skill
import com.fanhl.util.Lg

/**
 * Created by fanhl on 15/2/5.
 */
class SkillBuilder {
    static lgd = Lg.d.curry SkillBuilder.class.getSimpleName()

//    static /*SparseArray*/ Map<Integer, Card> cards =  /*SparseArray*/ [:]
    static SparseArray<Skill> skills = new SparseArray<>()

    static init() {
        lgd "初始化所有Hero开始"

        //之后放到DB中去
        skills.put(100001, createSkill("增援", 0, "列兵", 2))

        lgd "初始化所有Hero结束"
    }
    //FIXME 改成从map中去取

    static Skill createSkill(String title, Integer patternId, String description, Integer cost) {
        new Skill(title: title, patternId: patternId, description: description, cost: new MInt(cost))
    }

    static Skill newSkill(int id) { skills.get(id).clone() }
}
