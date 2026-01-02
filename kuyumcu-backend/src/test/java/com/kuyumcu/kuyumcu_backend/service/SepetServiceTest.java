package com.kuyumcu.kuyumcu_backend.service;

import com.kuyumcu.kuyumcu_backend.entity.Sepet;
import com.kuyumcu.kuyumcu_backend.repository.SepetRepository;
import com.kuyumcu.kuyumcu_backend.repository.SepetUrunRepository;
import com.kuyumcu.kuyumcu_backend.repository.UrunRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SepetServiceTest {

    @Mock
    SepetRepository sepetRepository;
    @Mock
    SepetUrunRepository sepetUrunRepository;
    @Mock
    UrunRepository urunRepository;

    @InjectMocks
    SepetService sepetService;

    @Test
    void sepetOlustur_basarili() {
        when(sepetRepository.save(any())).thenReturn(new Sepet());

        Sepet sepet = sepetService.sepetOlustur();

        assertNotNull(sepet);
    }
}
