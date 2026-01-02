package com.kuyumcu.kuyumcu_backend.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HataliLoginSeleniumTest extends BaseSeleniumTest {

    @Test
    void kullanici_hatali_sifre_ile_giris_yapamaz() {

        driver.get("http://localhost:8080/login.html");

        driver.findElement(By.id("email"))
                .sendKeys("ali@mail.com");

        driver.findElement(By.id("sifre"))
                .sendKeys("yanlis_sifre");

        driver.findElement(By.id("loginBtn"))
                .click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("sonuc"), "Hatalı"
        ));

        assertTrue(driver.getPageSource().contains("Hatalı"));
    }
}
