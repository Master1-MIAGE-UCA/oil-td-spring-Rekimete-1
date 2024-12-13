package miage.m1.dice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiceController {


    @GetMapping("/roll")
    public DiceRollLog roll() {
        return null;
    }
}
