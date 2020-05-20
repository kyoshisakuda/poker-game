package org.example;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HandCardComparableTest {

    @Test
    public void compareTo_whenSameRepetitionAndSameValue_returnZero() {
        HandCard card = new HandCard(5, 1);
        HandCard otherCard = new HandCard(5, 1);
        assertThat(card.compareTo(otherCard), is(0));
    }

    @Test
    public void compareTo_whenSameRepetitionAndLesserValue_returnOne() {
        HandCard card = new HandCard(5, 1);
        HandCard otherCard = new HandCard(3, 1);
        assertThat(card.compareTo(otherCard), is(1));
    }

    @Test
    public void compareTo_whenSameRepetitionAndGreaterValue_returnMinusOne() {
        HandCard card = new HandCard(5, 1);
        HandCard otherCard = new HandCard(8, 1);
        assertThat(card.compareTo(otherCard), is(-1));
    }

    @Test
    public void compareTo_whenLesserRepetitionAndSameValue_returnOne() {
        HandCard card = new HandCard(5, 2);
        HandCard otherCard = new HandCard(5, 1);
        assertThat(card.compareTo(otherCard), is(1));
    }

    @Test
    public void compareTo_whenLesserRepetitionAndLesserValue_returnOne() {
        HandCard card = new HandCard(5, 2);
        HandCard otherCard = new HandCard(2, 1);
        assertThat(card.compareTo(otherCard), is(1));
    }

    @Test
    public void compareTo_whenLesserRepetitionAndGreaterValue_returnOne() {
        HandCard card = new HandCard(5, 2);
        HandCard otherCard = new HandCard(9, 1);
        assertThat(card.compareTo(otherCard), is(1));
    }

    @Test
    public void compareTo_whenGreaterRepetitionAndSameValue_returnMinusOne() {
        HandCard card = new HandCard(5, 2);
        HandCard otherCard = new HandCard(5, 3);
        assertThat(card.compareTo(otherCard), is(-1));
    }

    @Test
    public void compareTo_whenGreaterRepetitionAndLesserValue_returnMinusOne() {
        HandCard card = new HandCard(5, 2);
        HandCard otherCard = new HandCard(4, 3);
        assertThat(card.compareTo(otherCard), is(-1));
    }

    @Test
    public void compareTo_whenGreaterRepetitionAndGreaterValue_returnMinusOne() {
        HandCard card = new HandCard(3, 2);
        HandCard otherCard = new HandCard(12, 3);
        assertThat(card.compareTo(otherCard), is(-1));
    }

}