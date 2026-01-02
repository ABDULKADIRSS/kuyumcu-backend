package com.kuyumcu.kuyumcu_backend.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSeleniumTest extends BaseSeleniumTest {

    @Test
    void kullanici_gercek_login_yapar() {

        driver.get("http://localhost:8080/login.html");

        driver.findElement(By.id("email"))
              .sendKeys("ali@mail.com");

        driver.findElement(By.id("sifre"))
              .sendKeys("1234");

        driver.findElement(By.id("loginBtn"))
              .click();

        // 🔴 YÖNLENDİRMEYİ BEKLE
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("urunler.html"));

        // ✅ Login başarılıysa urunler.html açılmış olmalı
        assertTrue(driver.getCurrentUrl().contains("urunler.html"));
    }
}
