package com.kuyumcu.kuyumcu_backend.service;

import com.kuyumcu.kuyumcu_backend.entity.Sepet;
import com.kuyumcu.kuyumcu_backend.entity.SepetUrun;
import com.kuyumcu.kuyumcu_backend.entity.Urun;
import com.kuyumcu.kuyumcu_backend.repository.SepetRepository;
import com.kuyumcu.kuyumcu_backend.repository.SepetUrunRepository;
import com.kuyumcu.kuyumcu_backend.repository.UrunRepository;
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
class SepetServiceTest {

    @Mock
    private SepetRepository sepetRepository;

    @Mock
    private SepetUrunRepository sepetUrunRepository;

    @Mock
    private UrunRepository urunRepository;

    @InjectMocks
    private SepetService sepetService;

   

    @Test
    void sepetOlustur_basarili() {
        Sepet sepet = new Sepet();
        when(sepetRepository.save(any())).thenReturn(sepet);

        Sepet sonuc = sepetService.sepetOlustur();

        assertNotNull(sonuc);
        verify(sepetRepository).save(any());
    }

    

    @Test
    void sepeteUrunEkle_basarili() {
        Sepet sepet = new Sepet();
        sepet.setId(1L);

        Urun urun = new Urun();
        urun.setId(2L);

        when(sepetRepository.findById(1L))
                .thenReturn(Optional.of(sepet));
        when(urunRepository.findById(2L))
                .thenReturn(Optional.of(urun));
        when(sepetUrunRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SepetUrun sonuc = sepetService.sepeteUrunEkle(1L, 2L, 3);

        assertEquals(3, sonuc.getAdet());
        assertEquals(sepet, sonuc.getSepet());
        assertEquals(urun, sonuc.getUrun());
    }

    @Test
    void sepeteUrunEkle_adetSifir_hata() {
        assertThrows(IllegalArgumentException.class,
                () -> sepetService.sepeteUrunEkle(1L, 2L, 0));
    }

    @Test
    void sepeteUrunEkle_sepetYok_hata() {
        when(sepetRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> sepetService.sepeteUrunEkle(1L, 2L, 1));
    }

    @Test
    void sepeteUrunEkle_urunYok_hata() {
        Sepet sepet = new Sepet();
        when(sepetRepository.findById(1L))
                .thenReturn(Optional.of(sepet));
        when(urunRepository.findById(2L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> sepetService.sepeteUrunEkle(1L, 2L, 1));
    }

   

    @Test
    void sepetUrunleri_basarili() {
        SepetUrun su1 = new SepetUrun();
        SepetUrun su2 = new SepetUrun();

        when(sepetUrunRepository.findBySepetId(1L))
                .thenReturn(List.of(su1, su2));

        List<SepetUrun> liste = sepetService.sepetUrunleri(1L);

        assertEquals(2, liste.size());
        verify(sepetUrunRepository).findBySepetId(1L);
    }

   

    @Test
    void sepetBosalt_basarili() {
        SepetUrun su1 = new SepetUrun();
        SepetUrun su2 = new SepetUrun();

        List<SepetUrun> urunler = List.of(su1, su2);

        when(sepetUrunRepository.findBySepetId(1L))
                .thenReturn(urunler);

        sepetService.sepetBosalt(1L);

        verify(sepetUrunRepository).deleteAll(urunler);
    }
}
