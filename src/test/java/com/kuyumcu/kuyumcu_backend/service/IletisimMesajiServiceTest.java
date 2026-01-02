package com.kuyumcu.kuyumcu_backend.service;

import com.kuyumcu.kuyumcu_backend.entity.IletisimMesaji;
import com.kuyumcu.kuyumcu_backend.repository.IletisimMesajiRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IletisimMesajiServiceTest {

    @Mock
    IletisimMesajiRepository repository;

    @InjectMocks
    IletisimMesajiService service;

    @Test
    void mesajGonder_basarili() {
        when(repository.save(any())).thenReturn(new IletisimMesaji());

        IletisimMesaji mesaj = service.mesajGonder(new IletisimMesaji());

        assertNotNull(mesaj);
    }
}
