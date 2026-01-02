package com.kuyumcu.kuyumcu_backend.service;



import com.kuyumcu.kuyumcu_backend.entity.Kategori;
import com.kuyumcu.kuyumcu_backend.repository.KategoriRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kuyumcu.kuyumcu_backend.repository.KategoriRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KategoriService {

    private final KategoriRepository kategoriRepository;

    /**
     * Yeni kategori ekler
     */
    public Kategori kategoriEkle(Kategori kategori) {
        if (kategori.getAd() == null || kategori.getAd().isBlank()) {
            throw new IllegalArgumentException("Kategori adı boş olamaz");
        }
        return kategoriRepository.save(kategori);
    }

    /**
     * Tüm kategorileri getirir
     */
    public List<Kategori> tumKategoriler() {
        return kategoriRepository.findAll();
    }

    /**
     * ID ile kategori getirir
     */
    public Kategori kategoriGetir(Long id) {
        return kategoriRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));
    }

    /**
     * Kategori siler
     */
    public void kategoriSil(Long id) {
        Kategori kategori = kategoriGetir(id);
        kategoriRepository.delete(kategori);
    }
}
