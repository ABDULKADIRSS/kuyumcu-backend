    package com.kuyumcu.kuyumcu_backend.controller;

    import java.util.List;

    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import com.kuyumcu.kuyumcu_backend.entity.Kullanici;
    import com.kuyumcu.kuyumcu_backend.service.KullaniciService;

    import lombok.RequiredArgsConstructor;

    @RestController
    @RequestMapping("/api/kullanicilar")
    @RequiredArgsConstructor
    public class KullaniciController {

        private final KullaniciService kullaniciService;

        @PostMapping
        public ResponseEntity<Kullanici> kullaniciEkle(@RequestBody Kullanici kullanici) {
            return ResponseEntity.ok(kullaniciService.kullaniciEkle(kullanici));
        }

        @GetMapping
        public ResponseEntity<List<Kullanici>> tumKullanicilar() {
            return ResponseEntity.ok(kullaniciService.tumKullanicilar());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Kullanici> kullaniciGetir(@PathVariable Long id) {
            return ResponseEntity.ok(kullaniciService.kullaniciGetir(id));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> kullaniciSil(@PathVariable Long id) {
            kullaniciService.kullaniciSil(id);
            return ResponseEntity.noContent().build();
        }
    }
