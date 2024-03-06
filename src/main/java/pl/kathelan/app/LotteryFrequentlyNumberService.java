package pl.kathelan.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.*;

@Service
@Slf4j
public class LotteryFrequentlyNumberService {

    public List<List<Integer>> generateMainNumberSets(LotteryData lotteryData, int numberOfSets) {
        List<List<Integer>> sets = new ArrayList<>();
        Map<Integer, Integer> frequencyMap = countMainNumbersFrequency(lotteryData);
        log.info("freguencyMap :{}", frequencyMap);

        if (frequencyMap.size() < 5) {
            throw new IllegalArgumentException("Not enough unique main numbers available for generating sets.");
        }

        for (int i = 0; i < numberOfSets; i++) {
            List<Integer> set = chooseMainNumbers(frequencyMap);
            sets.add(set);
            for (int number : set) {
                frequencyMap.remove(number);
            }
        }
        return sets;
    }

    private Map<Integer, Integer> countMainNumbersFrequency(LotteryData lotteryData) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (List<Integer> numbers : lotteryData.getMainNumbers().values()) {
            for (int number : numbers) {
                frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
            }
        }
        return frequencyMap;
    }

    private List<Integer> chooseMainNumbers(Map<Integer, Integer> frequencyMap) {
        List<Map.Entry<Integer, Integer>> sortedEntries = new ArrayList<>(frequencyMap.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        log.info("sorted entries: {}", sortedEntries);

        List<Integer> chosenNumbers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            chosenNumbers.add(sortedEntries.get(i).getKey());
        }
        return chosenNumbers;
    }
}

