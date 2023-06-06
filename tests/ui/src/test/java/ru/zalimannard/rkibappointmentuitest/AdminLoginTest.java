package ru.zalimannard.rkibappointmentuitest;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class AdminLoginTest extends BaseTest {

    @Test
    void loginTest() {
        open(startUrl);
        $(By.id("input-0")).setValue("admin");
        $(By.id("input-2")).setValue("admin");
        $(By.xpath("//*[@id=\"app\"]/div/div/main/div/div/div/form/div[3]/div[1]/div")).click();
        $(By.xpath("/html/body/div[2]/div/div/div/div[3]")).click();
        $(By.xpath("//*[@id=\"app\"]/div/div/main/div/div/div/form/div[3]/div[2]/button")).click();
        $(By.xpath("//*[@id=\"app\"]/div/div/main/div/div/header/div/div[1]/div/div/a[5]")).click();
        $(By.xpath("//*[@id=\"app\"]/div/div/main/div/div/main/div/div/div[1]/div/button[1]")).shouldHave(text("СОЗДАТЬ"));
    }

}
