package com.kuyumcu.kuyumcu_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuyumcu.kuyumcu_backend.entity.Kategori;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class KategoriControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void kategoriEkle_ve_listele() throws Exception {
        Kategori kategori = new Kategori();
        kategori.setAd("Pırlanta");

        mockMvc.perform(post("/api/kategoriler")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(kategori)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/kategoriler"))
                .andExpect(status().isOk());
    }
}
