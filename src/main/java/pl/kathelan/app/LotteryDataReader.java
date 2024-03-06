package pl.kathelan.app;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kathelan.app.LotteryData;
import pl.kathelan.app.Pair;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class LotteryDataReader {

    private static final String FILEPATH = "src/main/resources/csv/eurojackpot.csv";

    public LotteryData readLotteryData() {
        try {
            CSVReader reader = new CSVReader(new FileReader(FILEPATH));
            List<String[]> records = reader.readAll();
            reader.close();
            return parseLotteryData(records);
        } catch (IOException | CsvException e) {
            log.error("An error occurred while reading lottery data from CSV file", e);
            return new LotteryData(new HashMap<>(), new HashMap<>());
        }
    }

    private LotteryData parseLotteryData(List<String[]> records) {
        Map<Integer, List<Integer>> mainNumbers = new HashMap<>();
        Map<Integer, Pair<Integer, Integer>> additionalNumbers = new HashMap<>();
        for (String[] record : records) {
            int drawId = Integer.parseInt(record[0]);
            List<Integer> numbers = new ArrayList<>();
            for (int i = 2; i < 7; i++) {
                numbers.add(Integer.parseInt(record[i]));
            }
            mainNumbers.put(drawId, numbers);
            int additionalNumber1 = Integer.parseInt(record[7]);
            int additionalNumber2 = Integer.parseInt(record[8]);
            additionalNumbers.put(drawId, new Pair<>(additionalNumber1, additionalNumber2));
        }
        return new LotteryData(mainNumbers, additionalNumbers);
    }

}
