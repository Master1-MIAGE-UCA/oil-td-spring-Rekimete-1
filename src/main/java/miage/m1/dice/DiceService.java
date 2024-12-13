package miage.m1.dice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiceService {
    @Autowired
    private DiceRepository diceRepository;

    public List<Integer> roll(int rollCount) {
        DiceRollLog diceLog = new DiceRollLog();

        diceLog.setDiceCount(rollCount);

        List<Integer> results = new ArrayList<Integer>();
        for (int i = 0; i < rollCount; i++) {
            Dice dice = new Dice();
            results.add(dice.roll());
        }
        diceLog.setResults(results);

        diceLog.setTimestamp(java.time.LocalDateTime.now());
        diceRepository.save(diceLog);
        return results;
    }

    public List<DiceRollLog> getRollLogs() {
        return diceRepository.findAll();
    }

}
