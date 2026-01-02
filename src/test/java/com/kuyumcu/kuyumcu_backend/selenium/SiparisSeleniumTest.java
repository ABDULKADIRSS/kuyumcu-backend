package com.kuyumcu.kuyumcu_backend.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiparisSeleniumTest extends BaseSeleniumTest {

    @Test
    void siparis_olusturulur() {
        driver.get("http://localhost:9090/sepet.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("siparisBtn"))).click();

        wait.until(ExpectedConditions.urlContains("siparis"));
        assertTrue(driver.getCurrentUrl().contains("siparis"));
    }

    @Test
    void siparis_basarili() {
        driver.get("http://localhost:8080/siparis.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        assertTrue(driver.getPageSource().contains("Sipariş"));
    }

    @Test
    void alisverise_devam() {
        driver.get("http://localhost:8080/siparis.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("devamBtn"))).click();

        wait.until(ExpectedConditions.urlContains("urunler"));
        assertTrue(driver.getCurrentUrl().contains("urunler"));
    }
}
