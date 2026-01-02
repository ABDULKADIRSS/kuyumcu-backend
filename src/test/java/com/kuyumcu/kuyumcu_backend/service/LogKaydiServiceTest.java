package com.kuyumcu.kuyumcu_backend.service;

import com.kuyumcu.kuyumcu_backend.entity.LogKaydi;
import com.kuyumcu.kuyumcu_backend.repository.LogKaydiRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogKaydiServiceTest {

    @Mock
    private LogKaydiRepository logKaydiRepository;

    @InjectMocks
    private LogKaydiService logKaydiService;

    @Test
    void logEkle_basarili() {
        when(logKaydiRepository.save(any())).thenReturn(new LogKaydi());

        LogKaydi log = logKaydiService.logEkle("LOGIN", "Kullanıcı giriş yaptı");

        assertNotNull(log);
    }
}
