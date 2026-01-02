package com.kuyumcu.kuyumcu_backend.service;

import com.kuyumcu.kuyumcu_backend.entity.Kullanici;
import com.kuyumcu.kuyumcu_backend.repository.KullaniciRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KullaniciServiceTest {

    @Mock
    private KullaniciRepository kullaniciRepository;

    @InjectMocks
    private KullaniciService kullaniciService;

    @Test
    void girisYap_basarili() {
        Kullanici kullanici = new Kullanici();
        kullanici.setEmail("test@mail.com");
        kullanici.setSifre("1234");

        when(kullaniciRepository.findByEmailAndSifre("test@mail.com", "1234"))
                .thenReturn(Optional.of(kullanici));

        Kullanici sonuc = kullaniciService.girisYap("test@mail.com", "1234");

        assertEquals("test@mail.com", sonuc.getEmail());
        verify(kullaniciRepository).findByEmailAndSifre("test@mail.com", "1234");

    }

    @Test
    void girisYap_hataliBilgi_hata() {
        when(kullaniciRepository.findByEmailAndSifre(any(), any()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> kullaniciService.girisYap("x@mail.com", "yanlis"));
    }

    @Test
    void kullaniciEkle_basarili() {
        Kullanici k = new Kullanici();
        k.setEmail("test@mail.com");

        when(kullaniciRepository.save(any())).thenReturn(k);

        Kullanici sonuc = kullaniciService.kullaniciEkle(k);

        assertEquals("test@mail.com", sonuc.getEmail());
        verify(kullaniciRepository).save(k);
    }

    @Test
    void kullaniciEkle_emailNull_hata() {
        Kullanici k = new Kullanici();
        k.setEmail(null);

        assertThrows(IllegalArgumentException.class,
                () -> kullaniciService.kullaniciEkle(k));

        verify(kullaniciRepository, never()).save(any());
    }

    @Test
    void kullaniciEkle_emailBos_hata() {
        Kullanici k = new Kullanici();
        k.setEmail("   ");

        assertThrows(IllegalArgumentException.class,
                () -> kullaniciService.kullaniciEkle(k));

        verify(kullaniciRepository, never()).save(any());
    }

    @Test
    void tumKullanicilar_basarili() {
        Kullanici k1 = new Kullanici();
        k1.setEmail("a@mail.com");

        Kullanici k2 = new Kullanici();
        k2.setEmail("b@mail.com");

        when(kullaniciRepository.findAll())
                .thenReturn(List.of(k1, k2));

        List<Kullanici> liste = kullaniciService.tumKullanicilar();

        assertEquals(2, liste.size());
        verify(kullaniciRepository).findAll();
    }

    @Test
    void kullaniciGetir_bulundu() {
        Kullanici k = new Kullanici();
        k.setId(1L);
        k.setEmail("test@mail.com");

        when(kullaniciRepository.findById(1L))
                .thenReturn(Optional.of(k));

        Kullanici sonuc = kullaniciService.kullaniciGetir(1L);

        assertEquals("test@mail.com", sonuc.getEmail());
    }

    @Test
    void kullaniciGetir_bulunamadi() {
        when(kullaniciRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> kullaniciService.kullaniciGetir(99L));
    }

    @Test
    void kullaniciSil_basarili() {
        Kullanici k = new Kullanici();
        k.setId(1L);

        when(kullaniciRepository.findById(1L))
                .thenReturn(Optional.of(k));

        kullaniciService.kullaniciSil(1L);

        verify(kullaniciRepository).delete(k);
    }
}
