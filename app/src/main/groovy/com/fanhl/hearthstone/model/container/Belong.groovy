package com.fanhl.hearthstone.model.container

import com.fanhl.hearthstone.model.Crystal
import com.fanhl.hearthstone.model.Hero
import com.fanhl.hearthstone.model.card.Weapon

/**
 * 玩家所属区域(用来区分游戏双方)
 *
 * Created by fanhl on 15/1/24.
 */
class Belong {
    Hero hero

    Library library

    Weapon weapon

    Hands hands
    Site site

    Crystal crystal

    def Belong(Deck deck) {
        hero = deck.hero
        library = deck.library

        weapon = new Weapon()
        hands = new Hands()
        site = new Site()

        crystal = new Crystal(0)
    }
}
