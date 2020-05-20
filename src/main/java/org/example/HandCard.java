package org.example;

public class HandCard implements Comparable<HandCard> {

    private int value;
    private int repetition;

    public HandCard() {
    }

    public HandCard(int value, int repetition) {
        this.value = value;
        this.repetition = repetition;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    @Override
    public int compareTo(HandCard card) {
        if (this.repetition > card.repetition) {
            return 1;
        } else if (this.repetition < card.repetition) {
            return -1;
        }

        return this.value > card.value ? 1 : (this.value < card.value ? -1 : 0);
    }
}
