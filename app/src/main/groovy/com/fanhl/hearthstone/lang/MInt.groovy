package com.fanhl.hearthstone.lang

import groovy.transform.ToString

/**
 * 用于存放由多个值组成的数值
 *
 * Created by fanhl on 15/1/23.
 */
@ToString
class MInt {
    /**基本值*/
    int base
    /**加成值*/
    int add
    /**回合加成值*/
    int tmpAdd
    /**实际值(对某些类型无效,给血量用,像这样显示 current/(base+add+tmpAdd+site))*/
    int current

    public MInt(int base = 0, int add = 0, int tmpAdd = 0) {
        this.base = base
        this.add = add
        this.tmpAdd = tmpAdd
        this.current = sum()
    }

    int sum() { base + add + tmpAdd }

    @Override
    protected MInt clone() throws CloneNotSupportedException {
        new MInt(base: base, add: add, tmpAdd: tmpAdd, current: current)
    }
}