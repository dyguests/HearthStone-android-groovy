package com.fanhl.hearthstone.factory

import android.util.SparseArray
import com.fanhl.hearthstone.lang.MInt
import com.fanhl.hearthstone.model.card.Card
import com.fanhl.hearthstone.model.card.Minion
import com.fanhl.util.Lg


class CardBuilder {
    static lgd = Lg.d.curry CardBuilder.class.getSimpleName()

//    static /*SparseArray*/ Map<Integer, Card> cards =  /*SparseArray*/ [:]
    static SparseArray<Card> cards = new SparseArray<>()

    static init() {
        lgd "初始化所有Card开始"

        //之后放到DB中去
        cards.put(100001, createMinion("小兵", 0, "这是个小兵", 1, 1, 2))
        cards.put(100002, createMinion("大兵", 0, "这是个大兵", 2, 2, 3))
        cards.put(100003, createMinion("老兵", 0, "这是个老兵", 3, 4, 4))
        cards.put(100004, createMinion("小屁股", 0, "这是个司令", 1, 1, 2))
        cards.put(100005, createMinion("中屁股", 0, "这是个司令", 2, 1, 4))
        cards.put(100006, createMinion("大屁股", 0, "这是个司令", 3, 1, 6))
        cards.put(100007, createMinion("萝卜", 0, "这是个司令", 8, 10, 8))
        cards.put(100008, createMinion("苹果", 0, "这是个司令", 8, 10, 8))
        cards.put(100009, createMinion("这是啥", 0, "这是个司令", 8, 10, 8))
        cards.put(100010, createMinion("飞机", 0, "这是个司令", 8, 10, 8))
        cards.put(100011, createMinion("飞机场", 0, "这是个司令", 8, 10, 8))
        cards.put(100012, createMinion("考虑", 0, "这是个司令", 8, 10, 8))
        cards.put(100013, createMinion("真的", 0, "这是个司令", 8, 10, 8))
        cards.put(100014, createMinion("好烦", 0, "这是个司令", 8, 10, 8))
        cards.put(100015, createMinion("哈哈哈", 0, "这是个司令", 8, 10, 8))

        lgd "初始化所有Card结束"
    }

    static Minion createMinion(String title, int patternId, String description = "暂无",
                               int cost = 1, int attack = 1, int blood = 1) {
        new Minion(title: title, patternId: 0, description: description, explain: "测试用种族", race: Card.Race.MACHINE,
                cost: new MInt(cost), attack: new MInt(attack), blood: new MInt(blood))
    }

    static Minion newCard(int id) { cards.get(id).clone() }
}