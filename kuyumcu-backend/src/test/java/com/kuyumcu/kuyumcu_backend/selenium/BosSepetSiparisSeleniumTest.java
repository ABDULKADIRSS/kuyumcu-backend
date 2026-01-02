package com.kuyumcu.kuyumcu_backend.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BosSepetSiparisSeleniumTest extends BaseSeleniumTest {

    @Test
    void sepet_bosken_siparis_verilemez() {

        driver.get("http://localhost:8080/sepet.html");

        driver.findElement(By.id("siparisBtn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlContains("siparis"));

        assertTrue(driver.getCurrentUrl().contains("siparis"));
    }
}
