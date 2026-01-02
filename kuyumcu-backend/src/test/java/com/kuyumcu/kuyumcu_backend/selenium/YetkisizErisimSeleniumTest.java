package com.kuyumcu.kuyumcu_backend.selenium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class YetkisizErisimSeleniumTest extends BaseSeleniumTest {

    @Test
    void login_olmadan_urunler_sayfasi_acilabilir() {

        driver.get("http://localhost:8080/urunler.html");

        assertTrue(driver.getPageSource().contains("Ürünler"));
    }
}
