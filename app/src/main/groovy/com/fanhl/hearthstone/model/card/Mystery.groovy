package com.fanhl.hearthstone.model.card

import groovy.transform.ToString

/**
 * 奥秘
 *
 * Created by fanhl on 15/2/27.
 */
@ToString
class Mystery extends Spell {

    @Override
    Card.Type getType() { Type.MYSTERY }

    @Override
    protected Mystery clone() throws CloneNotSupportedException {
        new Mystery(id: id, title: title, description: description, explain: explain, patternId: patternId,
                cost: cost.clone())
    }
}
