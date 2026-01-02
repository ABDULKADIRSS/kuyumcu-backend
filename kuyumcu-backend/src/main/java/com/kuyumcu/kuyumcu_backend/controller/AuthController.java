package com.kuyumcu.kuyumcu_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kuyumcu.kuyumcu_backend.dto.LoginRequest;
import com.kuyumcu.kuyumcu_backend.entity.Kullanici;
import com.kuyumcu.kuyumcu_backend.service.KullaniciService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final KullaniciService kullaniciService;

    @PostMapping("/login")
    public ResponseEntity<Kullanici> login(@RequestBody LoginRequest request) {
        Kullanici kullanici =
                kullaniciService.girisYap(request.getEmail(), request.getSifre());
        return ResponseEntity.ok(kullanici);
    }
}
