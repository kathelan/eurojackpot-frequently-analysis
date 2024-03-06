package pl.kathelan.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LotteryChoosingService {
    private final LotteryDataReader lotteryDataReader;
    private final LotteryFrequentlyNumberService frequentlyNumberService;

    public void zxc() {
        LotteryData lotteryData = lotteryDataReader.readLotteryData();
        List<List<Integer>> mainNumbers = frequentlyNumberService.generateMainNumberSets(lotteryData, 15);
//        List<Integer> additional = frequentlyNumberService.chooseAdditionalNumbers(lotteryData);
        log.info("najczestsze liczby: {}", mainNumbers);
//        log.info("najczestsze liczby dodatkowe: {}", additional);
    }


}
