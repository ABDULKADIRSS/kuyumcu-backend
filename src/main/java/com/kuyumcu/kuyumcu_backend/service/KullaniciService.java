package com.kuyumcu.kuyumcu_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kuyumcu.kuyumcu_backend.entity.Kullanici;
import com.kuyumcu.kuyumcu_backend.repository.KullaniciRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KullaniciService {

    private final KullaniciRepository kullaniciRepository;

    public Kullanici girisYap(String email, String sifre) {
        return kullaniciRepository
                .findByEmailAndSifre(email, sifre)
                .orElseThrow(() -> new RuntimeException("Email veya şifre hatalı"));
    }


    public Kullanici kullaniciEkle(Kullanici kullanici) {
        if (kullanici.getEmail() == null || kullanici.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email boş olamaz");
        }
        return kullaniciRepository.save(kullanici);
    }

    public List<Kullanici> tumKullanicilar() {
        return kullaniciRepository.findAll();
    }

    public Kullanici kullaniciGetir(Long id) {
        return kullaniciRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
    }

    public void kullaniciSil(Long id) {
        Kullanici kullanici = kullaniciGetir(id);
        kullaniciRepository.delete(kullanici);
    }
}
