package miage.m1.dice;

import org.springframework.stereotype.Component;

@Component
public class Dice {
    private int value;

    public Dice() {
        roll();
    }

    public void roll() {
        this.value = (int) (Math.random() * 6) + 1;
    }

    public int getValue() {
        return this.value;
    }
}
