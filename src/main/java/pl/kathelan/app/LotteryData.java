package pl.kathelan.app;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class LotteryData {
    private Map<Integer, List<Integer>> mainNumbers;
    private Map<Integer, Pair<Integer, Integer>> additionalNumbers;
}

