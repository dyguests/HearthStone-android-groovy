package com.fanhl.hearthstone.model.card

import com.fanhl.hearthstone.lang.MInt
import groovy.transform.ToString

/**
 * Created by fanhl on 15/1/24.
 */
@ToString
class Minion extends Card {
    MInt attack
    MInt blood

    @Override
    Type getType() { Type.MINION }

    @Override
    protected Minion clone() throws CloneNotSupportedException {
        new Minion(id: id, title: title,description: description,explain: explain, patternId: patternId,
                 cost: cost.clone(), attack: attack.clone(), blood: blood.clone())
    }
}
