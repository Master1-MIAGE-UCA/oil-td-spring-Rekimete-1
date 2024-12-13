package miage.m1.dice;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class DiceRollLog {
    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private List<Integer> results;

    private int diceCount;

    private LocalDateTime timestamp;

}
