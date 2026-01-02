package com.kuyumcu.kuyumcu_backend.service;

import com.kuyumcu.kuyumcu_backend.entity.Duyuru;
import com.kuyumcu.kuyumcu_backend.repository.DuyuruRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DuyuruService {

    private final DuyuruRepository duyuruRepository;

    public Duyuru duyuruEkle(Duyuru duyuru) {
        return duyuruRepository.save(duyuru);
    }

    public List<Duyuru> aktifDuyurular() {
        return duyuruRepository.findByAktifTrue();
    }

    public List<Duyuru> tumDuyurular() {
        return duyuruRepository.findAll();
    }
}
