package com.fanhl.hearthstone.model.container

import com.fanhl.hearthstone.model.Hero
import com.fanhl.hearthstone.model.container.Library
import groovy.transform.ToString

/**
 * 套牌(英雄+30张牌)
 *
 * Created by fanhl on 15/1/27.
 */
@ToString
class Deck {
    Hero hero
    Library library

    def Deck(Hero hero, Library library = new Library()) {
        this.hero = hero
        this.library = library
    }


}