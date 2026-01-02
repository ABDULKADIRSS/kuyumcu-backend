package com.kuyumcu.kuyumcu_backend.service;

import com.kuyumcu.kuyumcu_backend.entity.Sepet;
import com.kuyumcu.kuyumcu_backend.entity.SepetUrun;
import com.kuyumcu.kuyumcu_backend.entity.Siparis;
import com.kuyumcu.kuyumcu_backend.entity.SiparisDurum;
import com.kuyumcu.kuyumcu_backend.entity.SiparisUrun;
import com.kuyumcu.kuyumcu_backend.entity.Urun;
import com.kuyumcu.kuyumcu_backend.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SiparisServiceTest {

    @Mock
    SiparisRepository siparisRepository;
    @Mock
    SiparisUrunRepository siparisUrunRepository;
    @Mock
    SepetRepository sepetRepository;
    @Mock
    SepetUrunRepository sepetUrunRepository;
    @Mock
    UrunRepository urunRepository;

    @InjectMocks
    SiparisService siparisService;

    
    @Test
    void sepetBos_ise_hata() {
        when(sepetRepository.findById(1L))
                .thenReturn(Optional.of(new Sepet()));
        when(sepetUrunRepository.findBySepetId(1L))
                .thenReturn(List.of());

        assertThrows(RuntimeException.class,
                () -> siparisService.siparisOlustur(1L));
    }

    
    @Test
    void siparisOlustur_basarili() {

        Sepet sepet = new Sepet();
        Urun urun = new Urun();
        urun.setStok(10);
        urun.setFiyat(1000.0);

        SepetUrun sepetUrun = new SepetUrun();
        sepetUrun.setUrun(urun);
        sepetUrun.setAdet(2);

        when(sepetRepository.findById(1L))
                .thenReturn(Optional.of(sepet));
        when(sepetUrunRepository.findBySepetId(1L))
                .thenReturn(List.of(sepetUrun));
        when(siparisRepository.save(any()))
                .thenAnswer(inv -> inv.getArgument(0));

        Siparis siparis = siparisService.siparisOlustur(1L);

        assertNotNull(siparis);
        assertEquals(SiparisDurum.OLUSTURULDU, siparis.getDurum());
        assertEquals(2000.0, siparis.getToplamTutar());
    }

    
    @Test
    void stokYetersiz_ise_hata() {

        Sepet sepet = new Sepet();
        Urun urun = new Urun();
        urun.setStok(1);
        urun.setAd("Altın Yüzük");

        SepetUrun sepetUrun = new SepetUrun();
        sepetUrun.setUrun(urun);
        sepetUrun.setAdet(3);

        when(sepetRepository.findById(1L))
                .thenReturn(Optional.of(sepet));
        when(sepetUrunRepository.findBySepetId(1L))
                .thenReturn(List.of(sepetUrun));

        assertThrows(RuntimeException.class,
                () -> siparisService.siparisOlustur(1L));
    }

    
    @Test
    void siparisIptal_edilince_stokGeriEklenir() {

        Siparis siparis = new Siparis();
        siparis.setDurum(SiparisDurum.OLUSTURULDU);

        Urun urun = new Urun();
        urun.setStok(5);

        SiparisUrun su = new SiparisUrun();
        su.setUrun(urun);
        su.setAdet(2);

        when(siparisRepository.findById(1L))
                .thenReturn(Optional.of(siparis));
        when(siparisUrunRepository.findBySiparisId(1L))
                .thenReturn(List.of(su));
        when(siparisRepository.save(any()))
                .thenAnswer(inv -> inv.getArgument(0));

        Siparis iptal = siparisService.siparisIptal(1L);

        assertEquals(SiparisDurum.IPTAL_EDILDI, iptal.getDurum());
        assertEquals(7, urun.getStok());
    }

    
    @Test
    void zatenIptal_edilmis_siparis_hata() {

        Siparis siparis = new Siparis();
        siparis.setDurum(SiparisDurum.IPTAL_EDILDI);

        when(siparisRepository.findById(1L))
                .thenReturn(Optional.of(siparis));

        assertThrows(RuntimeException.class,
                () -> siparisService.siparisIptal(1L));
    }
}

