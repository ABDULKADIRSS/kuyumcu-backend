package com.kuyumcu.kuyumcu_backend.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiparisSeleniumTest extends BaseSeleniumTest {

    @Test
    void siparis_olusturulur() {

        driver.get("http://localhost:8080/sepet.html");

        driver.findElement(By.id("siparisBtn")).click();

        // 🔴 Sipariş sayfasına yönlendirmeyi bekle
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("siparis.html"));

        assertTrue(driver.getCurrentUrl().contains("siparis.html"));
    }
}
