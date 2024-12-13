package miage.m1.dice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiceController {

    @Autowired
    private DiceService service;

    @GetMapping("/rollDice")
    public int getRoll() {
        return service.roll(1).get(0);
    }

    @GetMapping("/rollDices/{x}")
    public int[] getRolls(@PathVariable String x) {
        int rollCount = Integer.parseInt(x);
        List<Integer> rolls = service.roll(rollCount);

        int[] results = new int[rollCount];
        for (int i = 0; i < rollCount; i++) {
            results[i] = rolls.get(i);
        }
        return results;
    }
}
