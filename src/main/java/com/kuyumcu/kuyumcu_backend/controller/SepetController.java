package com.kuyumcu.kuyumcu_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kuyumcu.kuyumcu_backend.entity.Sepet;
import com.kuyumcu.kuyumcu_backend.entity.SepetUrun;
import com.kuyumcu.kuyumcu_backend.service.SepetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sepetler")
@RequiredArgsConstructor
public class SepetController {

    private final SepetService sepetService;

    @PostMapping
    public ResponseEntity<Sepet> sepetOlustur() {
        return ResponseEntity.ok(sepetService.sepetOlustur());
    }

    @PostMapping("/{sepetId}/urun-ekle")
    public ResponseEntity<SepetUrun> sepeteUrunEkle(
            @PathVariable Long sepetId,
            @RequestParam Long urunId,
            @RequestParam int adet) {

        return ResponseEntity.ok(
                sepetService.sepeteUrunEkle(sepetId, urunId, adet)
        );
    }

    @GetMapping("/{sepetId}/urunler")
    public ResponseEntity<List<SepetUrun>> sepetUrunleri(@PathVariable Long sepetId) {
        return ResponseEntity.ok(sepetService.sepetUrunleri(sepetId));
    }

    @DeleteMapping("/{sepetId}")
    public ResponseEntity<Void> sepetBosalt(@PathVariable Long sepetId) {
        sepetService.sepetBosalt(sepetId);
        return ResponseEntity.noContent().build();
    }
}
