package com.kuyumcu.kuyumcu_backend.service;

import com.kuyumcu.kuyumcu_backend.entity.Kullanici;
import com.kuyumcu.kuyumcu_backend.repository.KullaniciRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KullaniciServiceTest {

    @Mock
    private KullaniciRepository kullaniciRepository;

    @InjectMocks
    private KullaniciService kullaniciService;

    @Test
    void kullaniciEkle_basarili() {
        Kullanici k = new Kullanici();
        k.setEmail("test@mail.com");

        when(kullaniciRepository.save(any())).thenReturn(k);

        Kullanici sonuc = kullaniciService.kullaniciEkle(k);

        assertEquals("test@mail.com", sonuc.getEmail());
    }

    @Test
    void kullaniciEkle_emailBos_hata() {
        Kullanici k = new Kullanici();

        assertThrows(IllegalArgumentException.class,
                () -> kullaniciService.kullaniciEkle(k));
    }
}
