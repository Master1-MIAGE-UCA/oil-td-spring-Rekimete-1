package miage.m1.dice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class Dice {
    private int value;

    public Dice() {
        roll();
    }

    public int roll() {
        this.value = (int) (Math.random() * 6) + 1;
        return getValue();
    }

}
