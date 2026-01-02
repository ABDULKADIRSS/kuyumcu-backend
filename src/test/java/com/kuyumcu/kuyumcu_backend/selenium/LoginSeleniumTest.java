package com.kuyumcu.kuyumcu_backend.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSeleniumTest extends BaseSeleniumTest {

   
    @Test
    void login_sayfasi_acilir() {
        driver.get("http://localhost:8080/login.html");
        assertTrue(driver.getPageSource().contains("Giriş Yap"));
    }

    @Test
    void login_basarili() {
        driver.get("http://localhost:8080/login.html");

        driver.findElement(By.id("email")).sendKeys("test@mail.com");
        driver.findElement(By.id("sifre")).sendKeys("1234");
        driver.findElement(By.id("loginBtn")).click();

        assertTrue(driver.getCurrentUrl().contains("urunler"));
    }

    
    @Test
    void yanlis_sayfa_404() {
        driver.get("http://localhost:8080/olmayan.html");
        assertTrue(driver.getPageSource().contains("Whitelabel"));
    }
}
