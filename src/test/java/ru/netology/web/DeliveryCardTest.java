package ru.netology.web;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {

    @Test
    void successfulCardOrderWithDelivery() {
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.DAY_OF_MONTH, 3);
        Date newDate = instance.getTime();
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd.MM.yyyy");

        String datePlus3Days = formatForDate.format(newDate);

        open("http://localhost:9999/");
        $("span[data-test-id='city'] input").setValue("Севастополь");
        $("span[data-test-id='date'] input[placeholder]").setValue(datePlus3Days);
        $("span[data-test-id='name'] input").setValue("Иван Иванов-Петров");
        $("span[data-test-id='phone'] input").setValue("+79854585033");
        $("span[class='checkbox__box']").click();
        $(".button_view_extra").click();
        $(".spin_size_m.spin_visible").shouldBe(visible);
        $x("//*[contains(text(),'Встреча успешно забронирована на ')]").shouldBe(visible, Duration.ofSeconds(16));

    }
}
