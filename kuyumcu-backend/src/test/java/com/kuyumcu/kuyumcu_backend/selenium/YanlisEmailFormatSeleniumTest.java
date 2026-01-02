package com.kuyumcu.kuyumcu_backend.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class YanlisEmailFormatSeleniumTest extends BaseSeleniumTest {

    @Test
    void gecersiz_email_formatinda_login_olamaz() {

        driver.get("http://localhost:8080/login.html");

        driver.findElement(By.id("email")).sendKeys("ali");
        driver.findElement(By.id("sifre")).sendKeys("1234");
        driver.findElement(By.id("loginBtn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("sonuc"), "Hatalı"
        ));

        assertTrue(driver.getPageSource().contains("Hatalı"));
    }
}
