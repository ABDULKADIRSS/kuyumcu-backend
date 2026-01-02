package com.kuyumcu.kuyumcu_backend.service;

import com.kuyumcu.kuyumcu_backend.entity.LogKaydi;
import com.kuyumcu.kuyumcu_backend.repository.LogKaydiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogKaydiService {

    private final LogKaydiRepository logKaydiRepository;

    public LogKaydi logEkle(String islem, String aciklama) {
        LogKaydi log = new LogKaydi();
        log.setIslem(islem);
        log.setAciklama(aciklama);
        log.setTarih(LocalDateTime.now());
        return logKaydiRepository.save(log);
    }

    public List<LogKaydi> tumLoglar() {
        return logKaydiRepository.findAll();
    }
}
