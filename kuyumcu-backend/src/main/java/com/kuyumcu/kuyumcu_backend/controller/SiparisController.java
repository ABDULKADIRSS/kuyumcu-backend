package com.kuyumcu.kuyumcu_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kuyumcu.kuyumcu_backend.entity.Siparis;
import com.kuyumcu.kuyumcu_backend.service.SiparisService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/siparisler")
@RequiredArgsConstructor
public class SiparisController {

    private final SiparisService siparisService;

    @PostMapping
    public ResponseEntity<Siparis> siparisOlustur(@RequestParam Long sepetId) {
        return ResponseEntity.ok(siparisService.siparisOlustur(sepetId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Siparis> siparisGetir(@PathVariable Long id) {
        return ResponseEntity.ok(siparisService.siparisGetir(id));
    }

    @GetMapping
    public ResponseEntity<List<Siparis>> tumSiparisler() {
        return ResponseEntity.ok(siparisService.tumSiparisler());
    }
}
