package com.kuyumcu.kuyumcu_backend.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrunSepetSeleniumTest extends BaseSeleniumTest {

    @Test
    void urun_sepete_eklenir() {

        driver.get("http://localhost:8080/urunler.html");

        driver.findElement(By.id("sepeteEkleBtn")).click();

        // 🔴 Metnin DOM'a düşmesini bekle
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("sonuc"), "Sepete eklendi"
        ));

        assertTrue(driver.getPageSource().contains("Sepete eklendi"));
    }
}
