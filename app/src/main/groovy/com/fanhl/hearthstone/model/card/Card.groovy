package com.fanhl.hearthstone.model.card

import com.fanhl.hearthstone.lang.MInt

/**
 * Created by fanhl on 15/1/24.
 */
abstract class Card {
    /**id(暂时没用)*/
    String id
    /**标题(名称)*/
    String title
    /**描述*/
    String description
    /**台词*/
    String explain
    /**图片*/
    int patternId

    abstract Type getType()

    Occupation occupation

    /**基本费*/
    MInt cost

    /**
     * 卡牌类型
     */
    public static enum Type {
        SPELL(0), MINION(1), WEAPON(2), MYSTERY(3)

        int id

        private Type(int id) { this.id = id }
    }

    /**
     * 卡牌职业
     */
    public static enum Occupation {
        Neutral(0),
        Druid(1),
        Hunter(2),
        Master(3),
        Paladin(4),
        Pastor(5),
        Thieves(6),
        Shaman(7),
        Warlock(8),
        Warrior(9)

        int id

        private Occupation(int id) { this.id = id }
    }

    /**
     * 卡牌种族
     */
    public static enum Race {
        NONE(0, ""), BEAST(1, "野兽"), MACHINE(2, "机械"), MURLOC(3, "鱼人"), DRAGON(4, "龙")

        int id
        String name

        private Race(int id, String name) { this.id = id; this.name = name }

        @Override
        String toString() { name }
    }
}
