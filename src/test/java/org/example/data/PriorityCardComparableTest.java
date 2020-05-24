package org.example.data;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.example.data.PriorityCard;
import org.junit.Test;

public class PriorityCardComparableTest {

    @Test
    public void compareTo_whenSameRepetitionAndSameValue_returnZero() {
        PriorityCard card = new PriorityCard(5, 1);
        PriorityCard otherCard = new PriorityCard(5, 1);
        assertThat(card.compareTo(otherCard), is(0));
    }

    @Test
    public void compareTo_whenSameRepetitionAndLesserValue_returnOne() {
        PriorityCard card = new PriorityCard(5, 1);
        PriorityCard otherCard = new PriorityCard(3, 1);
        assertThat(card.compareTo(otherCard), is(1));
    }

    @Test
    public void compareTo_whenSameRepetitionAndGreaterValue_returnMinusOne() {
        PriorityCard card = new PriorityCard(5, 1);
        PriorityCard otherCard = new PriorityCard(8, 1);
        assertThat(card.compareTo(otherCard), is(-1));
    }

    @Test
    public void compareTo_whenLesserRepetitionAndSameValue_returnOne() {
        PriorityCard card = new PriorityCard(5, 2);
        PriorityCard otherCard = new PriorityCard(5, 1);
        assertThat(card.compareTo(otherCard), is(1));
    }

    @Test
    public void compareTo_whenLesserRepetitionAndLesserValue_returnOne() {
        PriorityCard card = new PriorityCard(5, 2);
        PriorityCard otherCard = new PriorityCard(2, 1);
        assertThat(card.compareTo(otherCard), is(1));
    }

    @Test
    public void compareTo_whenLesserRepetitionAndGreaterValue_returnOne() {
        PriorityCard card = new PriorityCard(5, 2);
        PriorityCard otherCard = new PriorityCard(9, 1);
        assertThat(card.compareTo(otherCard), is(1));
    }

    @Test
    public void compareTo_whenGreaterRepetitionAndSameValue_returnMinusOne() {
        PriorityCard card = new PriorityCard(5, 2);
        PriorityCard otherCard = new PriorityCard(5, 3);
        assertThat(card.compareTo(otherCard), is(-1));
    }

    @Test
    public void compareTo_whenGreaterRepetitionAndLesserValue_returnMinusOne() {
        PriorityCard card = new PriorityCard(5, 2);
        PriorityCard otherCard = new PriorityCard(4, 3);
        assertThat(card.compareTo(otherCard), is(-1));
    }

    @Test
    public void compareTo_whenGreaterRepetitionAndGreaterValue_returnMinusOne() {
        PriorityCard card = new PriorityCard(3, 2);
        PriorityCard otherCard = new PriorityCard(12, 3);
        assertThat(card.compareTo(otherCard), is(-1));
    }

}