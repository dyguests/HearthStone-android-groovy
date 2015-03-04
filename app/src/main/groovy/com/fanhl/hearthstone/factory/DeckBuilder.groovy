package com.fanhl.hearthstone.factory

import com.fanhl.hearthstone.model.container.Deck
import com.fanhl.hearthstone.model.container.Library
import com.fanhl.hearthstone.model.card.Card


/**
 * Created by fanhl on 15/1/27.
 */
class DeckBuilder {
    static Deck createDemoDeck() {
        def deck = new Deck(HeroBuilder.newHero(100001))
        addCard(deck.library, 100001)
        addCard(deck.library, 100002)
        addCard(deck.library, 100003)
        addCard(deck.library, 100004)
        addCard(deck.library, 100005)
        addCard(deck.library, 100006)
        addCard(deck.library, 100007)
        addCard(deck.library, 100008)
        addCard(deck.library, 100009)
        addCard(deck.library, 100010)
        addCard(deck.library, 100011)
        addCard(deck.library, 100012)
        addCard(deck.library, 100013)
        addCard(deck.library, 100014)
        addCard(deck.library, 100015)
        addCard(deck.library, 100001)
        addCard(deck.library, 100002)
        addCard(deck.library, 100003)
        addCard(deck.library, 100004)
        addCard(deck.library, 100005)
        addCard(deck.library, 100006)
        addCard(deck.library, 100007)
        addCard(deck.library, 100008)
        addCard(deck.library, 100009)
        addCard(deck.library, 100010)
        addCard(deck.library, 100011)
        addCard(deck.library, 100012)
        addCard(deck.library, 100013)
        addCard(deck.library, 100014)
        addCard(deck.library, 100015)
        deck
    }

    private static Collection<Card> addCard(Library library, int cardId) {
        library << CardBuilder.newCard(cardId)
    }
}
