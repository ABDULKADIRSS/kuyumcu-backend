package com.kuyumcu.kuyumcu_backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kuyumcu.kuyumcu_backend.repository.UrunRepository;
import com.kuyumcu.kuyumcu_backend.entity.Urun;
import com.kuyumcu.kuyumcu_backend.service.UrunService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UrunServiceTest {

    @Mock
    private UrunRepository urunRepository;

    @InjectMocks
    private UrunService urunService;

    private Urun urun;

    @BeforeEach
    void setUp() {
        urun = new Urun();
        urun.setId(1L);
        urun.setAd("Altın Yüzük");
        urun.setFiyat(10000.0);
        urun.setStok(10);
    }

   

    @Test
    void urunEkle_basarili() {
        when(urunRepository.save(any(Urun.class))).thenReturn(urun);

        Urun kaydedilen = urunService.urunEkle(urun);

        assertNotNull(kaydedilen);
        assertEquals("Altın Yüzük", kaydedilen.getAd());
        verify(urunRepository).save(urun);
    }

    @Test
    void urunEkle_negatifStok_hataFirlatir() {
        urun.setStok(-5);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> urunService.urunEkle(urun));

        assertEquals("Stok negatif olamaz", ex.getMessage());
        verify(urunRepository, never()).save(any());
    }

    @Test
    void urunEkle_sifirFiyat_hataFirlatir() {
        urun.setFiyat(0.0);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> urunService.urunEkle(urun));

        assertEquals("Fiyat sıfırdan büyük olmalı", ex.getMessage());
    }


    @Test
    void tumUrunler_basarili() {
        when(urunRepository.findAll()).thenReturn(List.of(urun));

        List<Urun> urunler = urunService.tumUrunler();

        assertEquals(1, urunler.size());
        verify(urunRepository).findAll();
    }

    

    @Test
    void urunGetir_varsa_getirir() {
        when(urunRepository.findById(1L)).thenReturn(Optional.of(urun));

        Urun bulunan = urunService.urunGetir(1L);

        assertEquals("Altın Yüzük", bulunan.getAd());
    }

    @Test
    void urunGetir_yoksa_hataFirlatir() {
        when(urunRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> urunService.urunGetir(1L));

        assertEquals("Ürün bulunamadı", ex.getMessage());
    }


    @Test
    void stokDusur_basarili() {
        when(urunRepository.findById(1L)).thenReturn(Optional.of(urun));

        urunService.stokDusur(1L, 3);

        assertEquals(7, urun.getStok());
        verify(urunRepository).save(urun);
    }

    @Test
    void stokDusur_yetersizStok_hataFirlatir() {
        when(urunRepository.findById(1L)).thenReturn(Optional.of(urun));

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> urunService.stokDusur(1L, 20));

        assertEquals("Yetersiz stok", ex.getMessage());
    }

    @Test
    void stokDusur_sifirAdet_hataFirlatir() {
        when(urunRepository.findById(1L)).thenReturn(Optional.of(urun));

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> urunService.stokDusur(1L, 0));

        assertEquals("Adet 0'dan büyük olmalı", ex.getMessage());
    }

    

    @Test
    void urunSil_basarili() {
        when(urunRepository.findById(1L)).thenReturn(Optional.of(urun));

        urunService.urunSil(1L);

        verify(urunRepository).delete(urun);
    }
}
