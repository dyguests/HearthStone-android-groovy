package com.fanhl.hearthstone.model.card

import com.fanhl.hearthstone.lang.MInt


/**
 * Created by fanhl on 15/1/24.
 */
class Weapon extends Card {
    MInt attack
    MInt blood

    @Override
    Type getType() { Type.WEAPON }
}
