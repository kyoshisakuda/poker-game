package org.example.data.mapper;

import org.example.data.Card;
import org.example.data.CardSuit;
import org.example.data.CardValue;

import java.util.HashMap;
import java.util.Map;

public class CardMapper {

    private static final Map<String, CardValue> VALUE_MAP = new HashMap<String, CardValue>();
    private static final Map<String, CardSuit> SUIT_MAP = new HashMap<String, CardSuit>();

    static {
        VALUE_MAP.put("2", CardValue.TWO);
        VALUE_MAP.put("3", CardValue.THREE);
        VALUE_MAP.put("4", CardValue.FOUR);
        VALUE_MAP.put("5", CardValue.FIVE);
        VALUE_MAP.put("6", CardValue.SIX);
        VALUE_MAP.put("7", CardValue.SEVEN);
        VALUE_MAP.put("8", CardValue.EIGHT);
        VALUE_MAP.put("9", CardValue.NINE);
        VALUE_MAP.put("T", CardValue.TEN);
        VALUE_MAP.put("J", CardValue.JACK);
        VALUE_MAP.put("Q", CardValue.QUEEN);
        VALUE_MAP.put("K", CardValue.KING);
        VALUE_MAP.put("A", CardValue.ACE);

        SUIT_MAP.put("S", CardSuit.SPADE);
        SUIT_MAP.put("D", CardSuit.DIAMOND);
        SUIT_MAP.put("H", CardSuit.HEARTS);
        SUIT_MAP.put("C", CardSuit.CLUBS);
    }

    private CardMapper() {
    }

    public static Card fromString(String cardString) {
        return new Card(VALUE_MAP.get(cardString.substring(0, 1)), SUIT_MAP.get(cardString.substring(1, 2)));
    }

}
