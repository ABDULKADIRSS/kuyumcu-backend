package com.kuyumcu.kuyumcu_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuyumcu.kuyumcu_backend.entity.Kullanici;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class KullaniciControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void kullaniciEkle() throws Exception {
        Kullanici k = new Kullanici();
        k.setAd("Ali");
        k.setEmail("ali@mail.com");

        mockMvc.perform(post("/api/kullanicilar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(k)))
                .andExpect(status().isOk());
    }
}
