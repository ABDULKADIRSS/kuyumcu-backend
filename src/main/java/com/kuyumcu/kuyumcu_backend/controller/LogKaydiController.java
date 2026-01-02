package com.kuyumcu.kuyumcu_backend.controller;

import com.kuyumcu.kuyumcu_backend.entity.LogKaydi;
import com.kuyumcu.kuyumcu_backend.service.LogKaydiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loglar")
@RequiredArgsConstructor
public class LogKaydiController {

    private final LogKaydiService logKaydiService;

    @PostMapping
    public ResponseEntity<LogKaydi> logEkle(
            @RequestParam String islem,
            @RequestParam String aciklama) {

        return ResponseEntity.ok(
                logKaydiService.logEkle(islem, aciklama)
        );
    }

    @GetMapping
    public ResponseEntity<List<LogKaydi>> tumLoglar() {
        return ResponseEntity.ok(logKaydiService.tumLoglar());
    }
}
