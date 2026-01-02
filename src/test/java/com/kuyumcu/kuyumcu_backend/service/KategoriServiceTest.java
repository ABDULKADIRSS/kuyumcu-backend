package com.kuyumcu.kuyumcu_backend.service;

import com.kuyumcu.kuyumcu_backend.entity.Kategori;
import com.kuyumcu.kuyumcu_backend.repository.KategoriRepository;
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

        assertNotNull(kaydedilen);
        assertEquals("Altın", kaydedilen.getAd());
        verify(kategoriRepository, times(1)).save(kategori);
    }

    @Test
    void kategoriEkle_nullAd_hata() {
        Kategori kategori = new Kategori();
        kategori.setAd(null);

        assertThrows(IllegalArgumentException.class,
                () -> kategoriService.kategoriEkle(kategori));

        verify(kategoriRepository, never()).save(any());
    }

    @Test
    void kategoriEkle_bosAd_hata() {
        Kategori kategori = new Kategori();
        kategori.setAd("   ");

        assertThrows(IllegalArgumentException.class,
                () -> kategoriService.kategoriEkle(kategori));

        verify(kategoriRepository, never()).save(any());
    }

    

    @Test
    void tumKategoriler_basarili() {
        Kategori k1 = new Kategori();
        k1.setAd("Altın");

        Kategori k2 = new Kategori();
        k2.setAd("Gümüş");

        when(kategoriRepository.findAll()).thenReturn(List.of(k1, k2));

        List<Kategori> kategoriler = kategoriService.tumKategoriler();

        assertEquals(2, kategoriler.size());
        verify(kategoriRepository, times(1)).findAll();
    }

   

    @Test
    void kategoriGetir_bulundu() {
        Kategori kategori = new Kategori();
        kategori.setId(1L);
        kategori.setAd("Altın");

        when(kategoriRepository.findById(1L))
                .thenReturn(Optional.of(kategori));

        Kategori bulunan = kategoriService.kategoriGetir(1L);

        assertEquals("Altın", bulunan.getAd());
        verify(kategoriRepository).findById(1L);
    }

    @Test
    void kategoriGetir_bulunamadi() {
        when(kategoriRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> kategoriService.kategoriGetir(99L));
    }

    @Test
    void kategoriSil_basarili() {
        Kategori kategori = new Kategori();
        kategori.setId(1L);
        kategori.setAd("Altın");

        when(kategoriRepository.findById(1L))
                .thenReturn(Optional.of(kategori));

        kategoriService.kategoriSil(1L);

        verify(kategoriRepository).delete(kategori);
    }
}
