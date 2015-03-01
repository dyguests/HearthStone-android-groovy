package com.fanhl.hearthstone.model

import com.fanhl.hearthstone.lang.MInt
import groovy.transform.AutoClone


/**
 * Created by fanhl on 15/1/24.
 */
@AutoClone
class Skill {
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

    MInt cost
}
