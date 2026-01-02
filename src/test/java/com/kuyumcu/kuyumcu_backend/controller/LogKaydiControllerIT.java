package com.kuyumcu.kuyumcu_backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LogKaydiControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    void logEkle_ve_listele_basarili() throws Exception {
        mockMvc.perform(post("/api/loglar")
                .param("islem", "TEST")
                .param("aciklama", "JUnit test"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/loglar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
