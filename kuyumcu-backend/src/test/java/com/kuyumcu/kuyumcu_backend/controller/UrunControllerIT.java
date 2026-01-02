package com.kuyumcu.kuyumcu_backend.controller;

import com.kuyumcu.kuyumcu_backend.entity.Urun;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UrunControllerIT {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void urunEkle_ve_listele_basarili() throws Exception {
                // 1️⃣ Ürün oluştur
                Urun urun = new Urun();
                urun.setAd("Altın Kolye");
                urun.setFiyat(15000.0);
                urun.setStok(5);

                // 2️⃣ POST /api/urunler
                mockMvc.perform(post("/api/urunler")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(urun)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.ad").value("Altın Kolye"));

                // 3️⃣ GET /api/urunler
                mockMvc.perform(get("/api/urunler"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].ad").value("Altın Kolye"));
        }

        @Test
        void urunGetir_id_ile_basarili() throws Exception {
                Urun urun = new Urun();
                urun.setAd("Altın Bilezik");
                urun.setFiyat(20000.0);
                urun.setStok(3);

                String response = mockMvc.perform(post("/api/urunler")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(urun)))
                                .andReturn()
                                .getResponse()
                                .getContentAsString();

                Urun kaydedilen = objectMapper.readValue(response, Urun.class);

                mockMvc.perform(get("/api/urunler/" + kaydedilen.getId()))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.ad").value("Altın Bilezik"));
        }

        @Test
        void urunSil_basarili() throws Exception {
                Urun urun = new Urun();
                urun.setAd("Altın Küpe");
                urun.setFiyat(8000.0);
                urun.setStok(2);

                String response = mockMvc.perform(post("/api/urunler")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(urun)))
                                .andReturn()
                                .getResponse()
                                .getContentAsString();

                Urun kaydedilen = objectMapper.readValue(response, Urun.class);

                mockMvc.perform(delete("/api/urunler/" + kaydedilen.getId()))
                                .andExpect(status().isNoContent());
        }
}
