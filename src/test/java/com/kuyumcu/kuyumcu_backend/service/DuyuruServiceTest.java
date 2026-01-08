package com.kuyumcu.kuyumcu_backend.service;

import com.kuyumcu.kuyumcu_backend.entity.Duyuru;
import com.kuyumcu.kuyumcu_backend.repository.DuyuruRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DuyuruServiceTest {

    @Mock
    DuyuruRepository duyuruRepository;

    @InjectMocks
    DuyuruService duyuruService;

    @Test
    @Tag("unit")
    void duyuruEkle_basarili() {
        Duyuru d = new Duyuru();
        d.setBaslik("Test");
        d.setAktif(true);

        when(duyuruRepository.save(any())).thenReturn(d);

        Duyuru sonuc = duyuruService.duyuruEkle(d);

        assertEquals("Test", sonuc.getBaslik());
    }
}
