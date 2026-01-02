package com.kuyumcu.kuyumcu_backend.controller;

import com.kuyumcu.kuyumcu_backend.entity.IletisimMesaji;
import com.kuyumcu.kuyumcu_backend.service.IletisimMesajiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/iletisim")
@RequiredArgsConstructor
public class IletisimMesajiController {

    private final IletisimMesajiService iletisimMesajiService;

    @PostMapping
    public ResponseEntity<IletisimMesaji> mesajGonder(
            @RequestBody IletisimMesaji mesaj) {

        return ResponseEntity.ok(
                iletisimMesajiService.mesajGonder(mesaj)
        );
    }

    @GetMapping
    public ResponseEntity<List<IletisimMesaji>> tumMesajlar() {
        return ResponseEntity.ok(
                iletisimMesajiService.tumMesajlar()
        );
    }
}
