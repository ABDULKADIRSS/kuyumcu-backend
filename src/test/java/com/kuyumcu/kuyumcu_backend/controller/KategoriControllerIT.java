package com.kuyumcu.kuyumcu_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuyumcu.kuyumcu_backend.entity.Kategori;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class KategoriControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void kategoriEkle_basarili() throws Exception {
        Kategori kategori = new Kategori();
        kategori.setAd("Pırlanta");

        mockMvc.perform(post("/api/kategoriler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(kategori)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ad").value("Pırlanta"));
    }

    @Test
    void kategoriEkle_bosAd_hata() throws Exception {
        Kategori kategori = new Kategori();
        kategori.setAd("");

        mockMvc.perform(post("/api/kategoriler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(kategori)))
                .andExpect(status().isBadRequest());
    }

    

    @Test
    void tumKategoriler_basarili() throws Exception {
        mockMvc.perform(get("/api/kategoriler"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void tumKategoriler_yanlisEndpoint_hata() throws Exception {
        mockMvc.perform(get("/api/kategori")) // yanlış endpoint
                .andExpect(status().isNotFound());
    }

 

    @Test
    void kategoriGetir_basarili() throws Exception {
       
        Kategori kategori = new Kategori();
        kategori.setAd("Altın");

        String response = mockMvc.perform(post("/api/kategoriler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(kategori)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Kategori kaydedilen = objectMapper.readValue(response, Kategori.class);

        mockMvc.perform(get("/api/kategoriler/" + kaydedilen.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ad").value("Altın"));
    }

    @Test
    void kategoriGetir_bulunamadi_hata() throws Exception {
        mockMvc.perform(get("/api/kategoriler/99999"))
               .andExpect(status().isNotFound());

    }

    

    @Test
    void kategoriSil_basarili() throws Exception {
        Kategori kategori = new Kategori();
        kategori.setAd("Gümüş");

        String response = mockMvc.perform(post("/api/kategoriler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(kategori)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Kategori kaydedilen = objectMapper.readValue(response, Kategori.class);

        mockMvc.perform(delete("/api/kategoriler/" + kaydedilen.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void kategoriSil_bulunamadi() throws Exception {
        long olmayanId = 9999L;

        mockMvc.perform(delete("/api/kategoriler/" + olmayanId))
                .andExpect(status().isNotFound());
    }

}
