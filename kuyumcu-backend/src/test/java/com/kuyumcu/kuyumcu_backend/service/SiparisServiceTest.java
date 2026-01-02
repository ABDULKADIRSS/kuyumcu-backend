package com.kuyumcu.kuyumcu_backend.service;

import com.kuyumcu.kuyumcu_backend.entity.Sepet;
import com.kuyumcu.kuyumcu_backend.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SiparisServiceTest {

    @Mock
    SiparisRepository siparisRepository;
    @Mock
    SiparisUrunRepository siparisUrunRepository;
    @Mock
    SepetRepository sepetRepository;
    @Mock
    SepetUrunRepository sepetUrunRepository;
    @Mock
    UrunRepository urunRepository;

    @InjectMocks
    SiparisService siparisService;

    @Test
    void sepetBos_ise_hata() {
        when(sepetRepository.findById(1L))
                .thenReturn(Optional.of(new Sepet()));
        when(sepetUrunRepository.findBySepetId(1L))
                .thenReturn(List.of());

        assertThrows(RuntimeException.class,
                () -> siparisService.siparisOlustur(1L));
    }
}
