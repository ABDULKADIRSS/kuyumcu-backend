package com.kuyumcu.kuyumcu_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuyumcu.kuyumcu_backend.entity.*;
import com.kuyumcu.kuyumcu_backend.repository.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SiparisService {

    private final SiparisRepository siparisRepository;
    private final SiparisUrunRepository siparisUrunRepository;
    private final SepetRepository sepetRepository;
    private final SepetUrunRepository sepetUrunRepository;
    private final UrunRepository urunRepository;

    
    @Transactional
    public Siparis siparisOlustur(Long sepetId) {

        Sepet sepet = sepetRepository.findById(sepetId)
                .orElseThrow(() -> new RuntimeException("Sepet bulunamadı"));

        List<SepetUrun> sepetUrunler =
                sepetUrunRepository.findBySepetId(sepetId);

        if (sepetUrunler.isEmpty()) {
            throw new RuntimeException("Sepet boş");
        }

        Siparis siparis = new Siparis();
        siparis.setOlusturmaTarihi(LocalDateTime.now());
        siparis.setDurum(SiparisDurum.OLUSTURULDU);

        Siparis kaydedilenSiparis = siparisRepository.save(siparis);

        double toplamTutar = 0;

        for (SepetUrun sepetUrun : sepetUrunler) {

            Urun urun = sepetUrun.getUrun();

            if (urun.getStok() < sepetUrun.getAdet()) {
                throw new RuntimeException("Yetersiz stok: " + urun.getAd());
            }

        
            urun.setStok(urun.getStok() - sepetUrun.getAdet());
            urunRepository.save(urun);

            
            SiparisUrun siparisUrun = new SiparisUrun();
            siparisUrun.setSiparis(kaydedilenSiparis);
            siparisUrun.setUrun(urun);
            siparisUrun.setAdet(sepetUrun.getAdet());
            siparisUrun.setFiyat(urun.getFiyat());

            siparisUrunRepository.save(siparisUrun);

            toplamTutar += urun.getFiyat() * sepetUrun.getAdet();
        }

        kaydedilenSiparis.setToplamTutar(toplamTutar);
        siparisRepository.save(kaydedilenSiparis);

        sepetUrunRepository.deleteAll(sepetUrunler);

        return kaydedilenSiparis;
    }

    @Transactional
    public Siparis siparisIptal(Long siparisId) {

        Siparis siparis = siparisRepository.findById(siparisId)
                .orElseThrow(() -> new RuntimeException("Sipariş bulunamadı"));

        if (siparis.getDurum() == SiparisDurum.IPTAL_EDILDI) {
            throw new RuntimeException("Sipariş zaten iptal edilmiş");
        }

        List<SiparisUrun> siparisUrunler =
                siparisUrunRepository.findBySiparisId(siparisId);

        
        for (SiparisUrun su : siparisUrunler) {
            Urun urun = su.getUrun();
            urun.setStok(urun.getStok() + su.getAdet());
            urunRepository.save(urun);
        }

        siparis.setDurum(SiparisDurum.IPTAL_EDILDI);
        return siparisRepository.save(siparis);
    }

   
    public Siparis siparisGetir(Long id) {
        return siparisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sipariş bulunamadı"));
    }

    public List<Siparis> tumSiparisler() {
        return siparisRepository.findAll();
    }
}
