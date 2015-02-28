package com.fanhl.hearthstone.model.card

import com.fanhl.hearthstone.lang.MInt
import groovy.transform.ToString


/**
 * Created by fanhl on 15/1/24.
 */
@ToString
class Weapon extends Card {
    MInt attack
    MInt blood

    @Override
    Type getType() { Type.WEAPON }

    @Override
    protected Weapon clone() throws CloneNotSupportedException {
        new Weapon(id: id, title: title, description: description, explain: explain, patternId: patternId,
                cost: cost.clone(), attack: attack.clone(), blood: blood.clone())
    }
}
