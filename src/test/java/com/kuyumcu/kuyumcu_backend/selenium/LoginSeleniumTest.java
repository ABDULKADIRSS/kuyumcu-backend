package com.kuyumcu.kuyumcu_backend.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSeleniumTest extends BaseSeleniumTest {

    @Test
    void login_sayfasi_acilir() {
        driver.get("http://localhost:8080/login.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginBtn")));

        assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    void login_basarili() {
        driver.get("http://localhost:8080/login.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")))
                .sendKeys("test@mail.com");

        driver.findElement(By.id("sifre")).sendKeys("1234");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("loginBtn"))).click();

        wait.until(ExpectedConditions.urlContains("urunler"));
        assertTrue(driver.getCurrentUrl().contains("urunler"));
    }

    @Test
    void yanlis_sayfa_404() {
        driver.get("http://localhost:8080/olmayan.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Error"));

        assertTrue(driver.getPageSource().contains("Whitelabel"));
    }
}
