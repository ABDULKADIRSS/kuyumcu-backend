package com.kuyumcu.kuyumcu_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuyumcu.kuyumcu_backend.entity.Kullanici;
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
class KullaniciControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void kullaniciEkle_basarili() throws Exception {
        Kullanici k = new Kullanici();
        k.setAd("Ali");
        k.setEmail("ali@mail.com");
        k.setSifre("1234");

        mockMvc.perform(post("/api/kullanicilar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(k)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("ali@mail.com"));
    }

    

    @Test
    void kullaniciEkle_emailBos_hata() throws Exception {
        Kullanici k = new Kullanici();
        k.setAd("Ali");
        k.setEmail("");

        mockMvc.perform(post("/api/kullanicilar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(k)))
                .andExpect(status().isBadRequest());

    }

   

    @Test
    void tumKullanicilar_basarili() throws Exception {
        mockMvc.perform(get("/api/kullanicilar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }


    @Test
    void kullaniciGetir_basarili() throws Exception {
        
        Kullanici k = new Kullanici();
        k.setAd("Veli");
        k.setEmail("veli@mail.com");
        k.setSifre("1234");

        String response = mockMvc.perform(post("/api/kullanicilar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(k)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Kullanici kaydedilen = objectMapper.readValue(response, Kullanici.class);

        mockMvc.perform(get("/api/kullanicilar/" + kaydedilen.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("veli@mail.com"));
    }

   

    @Test
    void kullaniciGetir_bulunamadi_hata() throws Exception {
        mockMvc.perform(get("/api/kullanicilar/99999"))
                .andExpect(status().isNotFound());

    }


    @Test
    void kullaniciSil_basarili() throws Exception {
        Kullanici k = new Kullanici();
        k.setAd("Ayşe");
        k.setEmail("ayse@mail.com");
        k.setSifre("1234");

        String response = mockMvc.perform(post("/api/kullanicilar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(k)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Kullanici kaydedilen = objectMapper.readValue(response, Kullanici.class);

        mockMvc.perform(delete("/api/kullanicilar/" + kaydedilen.getId()))
                .andExpect(status().isNoContent());
    }

   

    @Test
    void kullanicilar_yanlisEndpoint_404() throws Exception {
        mockMvc.perform(get("/api/kullanici"))
                .andExpect(status().isNotFound());
    }
}
