package com.kuyumcu.kuyumcu_backend.service;

import com.kuyumcu.kuyumcu_backend.entity.IletisimMesaji;
import com.kuyumcu.kuyumcu_backend.repository.IletisimMesajiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IletisimMesajiService {

    private final IletisimMesajiRepository iletisimMesajiRepository;

    public IletisimMesaji mesajGonder(IletisimMesaji mesaj) {
        return iletisimMesajiRepository.save(mesaj);
    }

    public List<IletisimMesaji> tumMesajlar() {
        return iletisimMesajiRepository.findAll();
    }
}
