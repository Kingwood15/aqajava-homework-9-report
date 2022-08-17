package ru.netology.domain;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Keys;
import ru.netology.domain.entities.User;
import ru.netology.domain.utils.DataGenerator;
import ru.netology.domain.utils.ScreenShooterReportPortalExtension;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.domain.utils.LoggingUtils.logInfo;

@ExtendWith({ScreenShooterReportPortalExtension.class})
public class AppCardDeliveryTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestOrderAdminCenter() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        logInfo("Встреча успешно запланирована на " + date);
    }

    @Test
    void shouldTestOrderAdminCenterHyphenated() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String cityHyphenated = "Южно-Сахалинск";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(cityHyphenated);
        logInfo("В поле ввода введён город " + cityHyphenated);
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        logInfo("Встреча успешно запланирована на " + date);
    }

    @Test
    void shouldTestOrderCityNotAdminCenter() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String cityNotAdminCenter = "Стерлитамак";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(cityNotAdminCenter);
        logInfo("В поле ввода введён город " + cityNotAdminCenter);
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id='city'] [class='input__sub']").shouldHave(exactText("Доставка в выбранный город" +
                " недоступна"));
        logInfo("Доставка в выбранный город недоступна");
    }

    @Test
    void shouldTestOrderCityLatin() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String latinCity = "Kazan";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(latinCity);
        logInfo("В поле ввода введён город " + latinCity);
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id='city'] [class='input__sub']").shouldHave(exactText("Доставка в выбранный город" +
                " недоступна"));
        logInfo("Доставка в выбранный город недоступна");
    }

    @Test
    void shouldTestOrderDateOld() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(-3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id='date'] [class='input__sub']").shouldHave(exactText("Заказ на выбранную дату" +
                " невозможен"));
        logInfo("Заказ на выбранную дату невозможен");
    }

    @Test
    void shouldTestOrderDateActual() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(0);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id='date'] [class='input__sub']").shouldHave(exactText("Заказ на выбранную дату" +
                " невозможен"));
        logInfo("Заказ на выбранную дату невозможен");
    }

    @Test
    void shouldTestOrderDateFuture() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(150);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        logInfo("Встреча успешно запланирована на " + date);
    }

    @Test
    void shouldTestOrderNameHyphen() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String hyphenName = "Сергеев-Петров Андрей";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(hyphenName);
        logInfo("В поле ввода введено имя " + hyphenName);
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        logInfo("Встреча успешно запланирована на " + date);
    }

    @Test
    void shouldTestOrderNameLatin() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String latinName = "Sirotkin Dmitry";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(latinName);
        logInfo("В поле ввода введено имя " + latinName);
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id='name'] [class='input__sub']").shouldHave(exactText("Имя и Фамилия указаные неверно." +
                " Допустимы только русские буквы, пробелы и дефисы."));
        logInfo("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.");
    }

    @Test
    void shouldTestOrderPhoneLowLength() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String lowLengthPhone = "123";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(lowLengthPhone);
        logInfo("В поле ввода введен номер телефона " + lowLengthPhone);
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        logInfo("Встреча успешно запланирована на " + date);
    }

    @Test
    void shouldTestOrderPhoneHighLength() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String highLengthPhone = "+71234567890123456";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(highLengthPhone);
        logInfo("В поле ввода введен номер телефона " + highLengthPhone);
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        logInfo("Встреча успешно запланирована на " + date);
    }

    @Test
    void shouldTestOrderPhoneText() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String textPhone = "number";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(textPhone);
        logInfo("В поле ввода введен номер телефона " + textPhone);
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        logInfo("Встреча успешно запланирована на " + date);
    }

    @Test
    void shouldTestOrderCheckboxOff() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id='agreement'] [class='checkbox__text']").shouldHave(exactText("Я соглашаюсь с" +
                " условиями обработки и использования моих персональных данных"));
        logInfo("Я соглашаюсь с условиями обработки и использования моих персональных данных");
    }

    @Test
    void shouldTestReplanOrder() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(3);
        String replanDate = DataGenerator.generateDate(10);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        logInfo("Встреча успешно запланирована на " + date);
        refresh();

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(replanDate);
        logInfo("В поле ввода введена дата " + replanDate);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        $("[data-test-id=replan-notification] .notification__content button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + replanDate), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        logInfo("Встреча успешно запланирована на " + replanDate);
    }

    @Test
    void shouldTestReplanOrderBackToTime() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(10);
        String replanDate = DataGenerator.generateDate(5);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        logInfo("Встреча успешно запланирована на " + date);
        refresh();

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(replanDate);
        logInfo("В поле ввода введена дата " + replanDate);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        $("[data-test-id=replan-notification] .notification__content button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + replanDate), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        logInfo("Встреча успешно запланирована на " + replanDate);
    }

    @Test
    void shouldTestReplanOrderBackToPast() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(3);
        String replanDate = DataGenerator.generateDate(-3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        logInfo("Встреча успешно запланирована на " + date);
        refresh();

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(replanDate);
        logInfo("В поле ввода введена дата " + replanDate);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id='date'] [class='input__sub']").shouldHave(exactText("Заказ на выбранную дату" +
                " невозможен"));
        logInfo("Заказ на выбранную дату невозможен");
    }

    @Test
    void shouldTestReplanOrderBackToToday() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(5);
        String replanDate = DataGenerator.generateDate(0);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        logInfo("В поле ввода введена дата " + date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        logInfo("Встреча успешно запланирована на " + date);
        refresh();

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        logInfo("В поле ввода введён город " + user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(replanDate);
        logInfo("В поле ввода введена дата " + replanDate);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        logInfo("В поле ввода введено имя " + user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        logInfo("В поле ввода введен номер телефона " + user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        logInfo("Выполнен клик по чекбоксу");
        form.$("form button.button").click();
        logInfo("Выполнен клик по кнопке запланировать");

        $("[data-test-id='date'] [class='input__sub']").shouldHave(exactText("Заказ на выбранную дату" +
                " невозможен"));
        logInfo("Заказ на выбранную дату невозможен");
    }
}
