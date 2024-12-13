package miage.m1.dice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoriqueDiceRollController {
    @Autowired
    private DiceService service;

    @GetMapping("diceLogs")
    public List<DiceRollLog> getRollLogs() {
        return service.getRollLogs();
    }

}
