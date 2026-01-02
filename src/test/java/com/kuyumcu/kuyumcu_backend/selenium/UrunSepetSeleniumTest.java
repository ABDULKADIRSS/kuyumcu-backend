package com.kuyumcu.kuyumcu_backend.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrunSepetSeleniumTest extends BaseSeleniumTest {

    @Test
    void urunler_sayfasi_acilir() {
        driver.get("http://localhost:8080/urunler.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        assertTrue(driver.getCurrentUrl().contains("urunler"));
    }

    @Test
    void urun_sepete_eklenir() {
        driver.get("http://localhost:8080/urunler.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("sepeteEkleBtn"))).click();

        assertTrue(driver.getPageSource().contains("Sepete"));
    }

    @Test
    void sepete_gidilir() {
        driver.get("http://localhost:8080/urunler.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("sepetGit"))).click();

        wait.until(ExpectedConditions.urlContains("sepet"));
        assertTrue(driver.getCurrentUrl().contains("sepet"));
    }

    @Test
    void sepet_sayfasi_acilir() {
        driver.get("http://localhost:8080/sepet.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        assertTrue(driver.getCurrentUrl().contains("sepet"));
    }
}
