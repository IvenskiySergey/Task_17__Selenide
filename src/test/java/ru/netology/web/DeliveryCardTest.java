package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

    @Test
    void successfulCardOrderWithDelivery() {

        String planningDate = generateDate(4);

        open("http://localhost:9999/");
        $("span[data-test-id='city'] input").setValue("Севастополь");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("span[data-test-id='date'] input[placeholder]").setValue(planningDate);
        $("span[data-test-id='name'] input").setValue("Иван Иванов-Петров");
        $("span[data-test-id='phone'] input").setValue("+79854585033");
        $("span[class='checkbox__box']").click();
        $(".button_view_extra").click();
        $(".spin_size_m.spin_visible").shouldBe(visible);
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
