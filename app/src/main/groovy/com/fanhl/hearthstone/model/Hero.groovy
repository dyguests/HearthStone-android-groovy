package com.fanhl.hearthstone.model

import com.fanhl.hearthstone.lang.MInt


/**
 * Created by fanhl on 15/1/24.
 */
class Hero {
    /**id(暂时没用)*/
    int id
    /**标题(名称)*/
    String title
    /**描述*/
    String description
    /**台词*/
    String explain
    /**图片*/
    int patternId

    MInt attack
    MInt blood

    Skill skill

    @Override
    protected Hero clone() throws CloneNotSupportedException {
        new Hero(id: id, title: title, description: description, explain: explain, patternId: patternId,
                attack: attack.clone(), blood: blood.clone(), skill: skill.clone())
    }
}