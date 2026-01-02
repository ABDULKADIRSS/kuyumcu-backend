package com.kuyumcu.kuyumcu_backend.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrunSepetSeleniumTest extends BaseSeleniumTest {

    
    @Test
    void urunler_sayfasi_acilir() {
        driver.get("http://localhost:8080/urunler.html");
        assertTrue(driver.getPageSource().contains("Ürünler"));
    }

    
    @Test
    void urun_sepete_eklenir() {
        driver.get("http://localhost:8080/urunler.html");

        driver.findElement(By.id("sepeteEkleBtn")).click();

        assertTrue(driver.getPageSource().contains("Sepete eklendi"));
    }

    
    @Test
    void sepete_gidilir() {
        driver.get("http://localhost:8080/urunler.html");

        driver.findElement(By.id("sepetGit")).click();

        assertTrue(driver.getCurrentUrl().contains("sepet"));
    }

    @Test
    void sepet_sayfasi_acilir() {
        driver.get("http://localhost:8080/sepet.html");
        assertTrue(driver.getPageSource().contains("Sepetim"));
    }
}
