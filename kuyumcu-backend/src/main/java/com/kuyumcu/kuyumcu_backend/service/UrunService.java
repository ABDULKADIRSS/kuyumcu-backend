package com.kuyumcu.kuyumcu_backend.service;




import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.kuyumcu.kuyumcu_backend.repository.UrunRepository;
import com.kuyumcu.kuyumcu_backend.entity.Urun;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UrunService {

    private final UrunRepository urunRepository;

    /**
     * Yeni ürün ekler
     */
    public Urun urunEkle(Urun urun) {
        if (urun.getStok() < 0) {
            throw new IllegalArgumentException("Stok negatif olamaz");
        }
        if (urun.getFiyat() <= 0) {
            throw new IllegalArgumentException("Fiyat sıfırdan büyük olmalı");
        }
        return urunRepository.save(urun);
    }

    /**
     * Tüm ürünleri getirir
     */
    public List<Urun> tumUrunler() {
        return urunRepository.findAll();
    }

    /**
     * ID ile ürün getirir
     */
    public Urun urunGetir(Long id) {
        return urunRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));
    }

    /**
     * Stok düşürme (sipariş sırasında kullanılır)
     */
    public void stokDusur(Long urunId, int adet) {
        Urun urun = urunGetir(urunId);

        if (adet <= 0) {
            throw new IllegalArgumentException("Adet 0'dan büyük olmalı");
        }

        if (urun.getStok() < adet) {
            throw new RuntimeException("Yetersiz stok");
        }

        urun.setStok(urun.getStok() - adet);
        urunRepository.save(urun);
    }

    /**
     * Ürün silme
     */
    public void urunSil(Long id) {
        Urun urun = urunGetir(id);
        urunRepository.delete(urun);
    }
}

