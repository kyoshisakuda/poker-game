package org.example.data.mapper;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.example.data.Card;
import org.example.data.mapper.CardMapper;
import org.example.data.CardSuit;
import org.example.data.CardValue;
import org.junit.Test;

public class CardMapperTest {

    @Test
    public void testFromString() {
        String cardString = "AS";
        Card card = CardMapper.fromString(cardString);
        assertThat(card.getValue(), is(CardValue.ACE));
        assertThat(card.getSuit(), is(CardSuit.SPADE));
    }
}