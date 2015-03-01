package com.fanhl.hearthstone.factory

import android.util.SparseArray
import com.fanhl.hearthstone.lang.MInt
import com.fanhl.hearthstone.model.Hero
import com.fanhl.util.Lg

/**
 * Created by fanhl on 15/1/27.
 */
class HeroBuilder {
    static lgd = Lg.d.curry HeroBuilder.class.getSimpleName()

//    static /*SparseArray*/ Map<Integer, Card> cards =  /*SparseArray*/ [:]
    static SparseArray<Hero> heros = new SparseArray<>()

    static init() {
        lgd "初始化所有Hero开始"

        //之后放到DB中去
        heros.put(100001, createHero("圣骑", 0, "这是个圣骑", "圣光即正义", 0, 30, 100001))

        lgd "初始化所有Hero结束"
    }

    static Hero createHero(String title, int patternId, String description = "暂无", String explain = "", int attack = 1, int blood = 1, int skillId = 100001) {
        def skill = SkillBuilder.newSkill(skillId)
        new Hero(title: title, patternId: patternId, description: description, explain: explain,
                attack: new MInt(attack), blood: new MInt(blood), skill: skill)
    }

    static Hero newHero(int id) { heros.get(id).clone() }
}
