package com.kuyumcu.kuyumcu_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kuyumcu.kuyumcu_backend.entity.Sepet;
import com.kuyumcu.kuyumcu_backend.entity.SepetUrun;
import com.kuyumcu.kuyumcu_backend.entity.Urun;
import com.kuyumcu.kuyumcu_backend.repository.SepetRepository;
import com.kuyumcu.kuyumcu_backend.repository.SepetUrunRepository;
import com.kuyumcu.kuyumcu_backend.repository.UrunRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SepetService {

    private final SepetRepository sepetRepository;
    private final SepetUrunRepository sepetUrunRepository;
    private final UrunRepository urunRepository;

    public Sepet sepetOlustur() {
        return sepetRepository.save(new Sepet());
    }

    public SepetUrun sepeteUrunEkle(Long sepetId, Long urunId, int adet) {
        if (adet <= 0) {
            throw new IllegalArgumentException("Adet 0'dan büyük olmalı");
        }

        Sepet sepet = sepetRepository.findById(sepetId)
                .orElseThrow(() -> new RuntimeException("Sepet bulunamadı"));

        Urun urun = urunRepository.findById(urunId)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

        SepetUrun sepetUrun = new SepetUrun();
        sepetUrun.setSepet(sepet);
        sepetUrun.setUrun(urun);
        sepetUrun.setAdet(adet);

        return sepetUrunRepository.save(sepetUrun);
    }

    public List<SepetUrun> sepetUrunleri(Long sepetId) {
        return sepetUrunRepository.findBySepetId(sepetId);
    }

    public void sepetBosalt(Long sepetId) {
        List<SepetUrun> urunler = sepetUrunRepository.findBySepetId(sepetId);
        sepetUrunRepository.deleteAll(urunler);
    }
}
