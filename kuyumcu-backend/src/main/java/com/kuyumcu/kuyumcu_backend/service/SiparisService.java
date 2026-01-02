package com.kuyumcu.kuyumcu_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

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

    public Siparis siparisOlustur(Long sepetId) {

        Sepet sepet = sepetRepository.findById(sepetId)
                .orElseThrow(() -> new RuntimeException("Sepet bulunamadı"));

        List<SepetUrun> sepetUrunler = sepetUrunRepository.findBySepetId(sepetId);

        if (sepetUrunler.isEmpty()) {
            throw new RuntimeException("Sepet boş");
        }

        Siparis siparis = new Siparis();
        siparis.setOlusturmaTarihi(LocalDateTime.now());

        Siparis kaydedilenSiparis = siparisRepository.save(siparis);

        double toplamTutar = 0;

        for (SepetUrun sepetUrun : sepetUrunler) {
            Urun urun = sepetUrun.getUrun();

            if (urun.getStok() < sepetUrun.getAdet()) {
                throw new RuntimeException("Yetersiz stok: " + urun.getAd());
            }

            // stok düş
            urun.setStok(urun.getStok() - sepetUrun.getAdet());
            urunRepository.save(urun);

            // sipariş ürünü oluştur
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

        // sepeti boşalt
        sepetUrunRepository.deleteAll(sepetUrunler);

        return kaydedilenSiparis;
    }

    public Siparis siparisGetir(Long id) {
        return siparisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sipariş bulunamadı"));
    }

    public List<Siparis> tumSiparisler() {
        return siparisRepository.findAll();
    }
}
