package com.fanhl.hearthstone.model.card

import groovy.transform.ToString

/**
 * 法术
 * Created by fanhl on 15/1/24.
 */
@ToString
class Spell extends Card {
    @Override
    Type getType() { Type.SPELL }
}
