package com.kuyumcu.kuyumcu_backend.controller;

import com.kuyumcu.kuyumcu_backend.entity.Duyuru;
import com.kuyumcu.kuyumcu_backend.service.DuyuruService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duyurular")
@RequiredArgsConstructor
public class DuyuruController {

    private final DuyuruService duyuruService;

    @PostMapping
    public ResponseEntity<Duyuru> duyuruEkle(@RequestBody Duyuru duyuru) {
        return ResponseEntity.ok(duyuruService.duyuruEkle(duyuru));
    }

    @GetMapping("/aktif")
    public ResponseEntity<List<Duyuru>> aktifDuyurular() {
        return ResponseEntity.ok(duyuruService.aktifDuyurular());
    }

    @GetMapping
    public ResponseEntity<List<Duyuru>> tumDuyurular() {
        return ResponseEntity.ok(duyuruService.tumDuyurular());
    }
}
