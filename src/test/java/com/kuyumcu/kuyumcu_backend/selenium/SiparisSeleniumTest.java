package com.kuyumcu.kuyumcu_backend.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiparisSeleniumTest extends BaseSeleniumTest {

    
    @Test
    void siparis_olusturulur() {
        driver.get("http://localhost:8080/sepet.html");

        driver.findElement(By.id("siparisBtn")).click();

        assertTrue(driver.getCurrentUrl().contains("siparis"));
    }

    
    @Test
    void siparis_basarili() {
        driver.get("http://localhost:8080/siparis.html");
        assertTrue(driver.getPageSource().contains("Sipariş oluşturuldu"));
    }

   
    @Test
    void alisverise_devam() {
        driver.get("http://localhost:8080/siparis.html");

        driver.findElement(By.id("devamBtn")).click();

        assertTrue(driver.getCurrentUrl().contains("urunler"));
    }
}
