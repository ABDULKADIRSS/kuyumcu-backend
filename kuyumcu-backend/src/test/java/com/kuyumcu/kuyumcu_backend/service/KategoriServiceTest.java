package com.kuyumcu.kuyumcu_backend.service;

import com.kuyumcu.kuyumcu_backend.entity.Kategori;
import com.kuyumcu.kuyumcu_backend.repository.KategoriRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KategoriServiceTest {

    @Mock
    private KategoriRepository kategoriRepository;

    @InjectMocks
    private KategoriService kategoriService;

    @Test
    void kategoriEkle_basarili() {
        Kategori kategori = new Kategori();
        kategori.setAd("Altın");

        when(kategoriRepository.save(any())).thenReturn(kategori);

        Kategori kaydedilen = kategoriService.kategoriEkle(kategori);

        assertEquals("Altın", kaydedilen.getAd());
    }

    @Test
    void kategoriEkle_bosAd_hata() {
        Kategori kategori = new Kategori();
        kategori.setAd("");

        assertThrows(IllegalArgumentException.class,
                () -> kategoriService.kategoriEkle(kategori));
    }
}
