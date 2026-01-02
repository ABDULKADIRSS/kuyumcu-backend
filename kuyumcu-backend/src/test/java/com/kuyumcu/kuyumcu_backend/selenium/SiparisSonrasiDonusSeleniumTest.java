package com.kuyumcu.kuyumcu_backend.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiparisSonrasiDonusSeleniumTest extends BaseSeleniumTest {

    @Test
    void siparis_sonrasi_urunler_sayfasina_donulebilir() {

        driver.get("http://localhost:8080/siparis.html");

        driver.findElement(By.linkText("Alışverişe Devam Et")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("urunler.html"));

        assertTrue(driver.getCurrentUrl().contains("urunler.html"));
    }
}
