package pl.kathelan.app;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ControllerTest {
    private final LotteryChoosingService choosingService;


    @GetMapping("test")
    public void zxc() {
        choosingService.zxc();
    }
}
