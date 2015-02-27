package com.fanhl.hearthstone.model

/**
 * 英雄当前法力槽
 *
 * 法力水晶
 *
 * Created by fanhl on 15/2/5.
 */
class Crystal {
    int base
    int current
    /** 当前回合初始不可用费的过载*/
    int currentOverload
    /** 下个回合初始不可用费的过载*/
    int nextOverload

    def Crystal(int base) {
        this.base = base
        this.current = base
        this.currentOverload = 0
        this.nextOverload = 0
    }
}
