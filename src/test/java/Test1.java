import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class Test1 {
    private String dateGenerator(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldTest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").sendKeys("Уфа");
        String date = dateGenerator(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(date);
        $("[data-test-id='name'] input").sendKeys("Ермолаев Александр");
        $("[data-test-id='phone'] input").sendKeys("+79174612235");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(".notification__content").find(exactText("Встреча успешно забронирована на " + date)).shouldBe(visible, Duration.ofSeconds(15));
    }

}
