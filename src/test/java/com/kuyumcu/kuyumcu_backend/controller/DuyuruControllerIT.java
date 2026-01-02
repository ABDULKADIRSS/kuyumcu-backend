package com.kuyumcu.kuyumcu_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuyumcu.kuyumcu_backend.entity.Duyuru;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DuyuruControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void duyuruEkle_ve_aktifleriGetir() throws Exception {
        Duyuru d = new Duyuru();
        d.setBaslik("Bakım");
        d.setAktif(true);

        mockMvc.perform(post("/api/duyurular")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(d)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/duyurular/aktif"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
