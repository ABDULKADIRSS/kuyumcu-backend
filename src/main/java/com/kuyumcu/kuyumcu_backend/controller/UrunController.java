package com.kuyumcu.kuyumcu_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kuyumcu.kuyumcu_backend.entity.Urun;
import com.kuyumcu.kuyumcu_backend.service.UrunService;

import lombok.RequiredArgsConstructor;


import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/urunler")
@RequiredArgsConstructor
public class UrunController {

  private final UrunService urunService;


    
    @PostMapping
    public ResponseEntity<Urun> urunEkle(@RequestBody Urun urun) {
        Urun kaydedilenUrun = urunService.urunEkle(urun);
        return ResponseEntity.ok(kaydedilenUrun);
    }

  
    @GetMapping
    public ResponseEntity<List<Urun>> tumUrunleriGetir() {
        return ResponseEntity.ok(urunService.tumUrunler());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Urun> urunGetir(@PathVariable Long id) {
        return ResponseEntity.ok(urunService.urunGetir(id));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> urunSil(@PathVariable Long id) {
        urunService.urunSil(id);
        return ResponseEntity.noContent().build();
    }
}
