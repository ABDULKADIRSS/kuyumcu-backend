package com.kuyumcu.kuyumcu_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kuyumcu.kuyumcu_backend.entity.Kategori;
import com.kuyumcu.kuyumcu_backend.service.KategoriService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/kategoriler")
@RequiredArgsConstructor
public class KategoriController {

    private final KategoriService kategoriService;

    @PostMapping
    public ResponseEntity<Kategori> kategoriEkle(@RequestBody Kategori kategori) {
        return ResponseEntity.ok(kategoriService.kategoriEkle(kategori));
    }

    @GetMapping
    public ResponseEntity<List<Kategori>> tumKategoriler() {
        return ResponseEntity.ok(kategoriService.tumKategoriler());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kategori> kategoriGetir(@PathVariable Long id) {
        return ResponseEntity.ok(kategoriService.kategoriGetir(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> kategoriSil(@PathVariable Long id) {
        kategoriService.kategoriSil(id);
        return ResponseEntity.noContent().build();
    }
}
