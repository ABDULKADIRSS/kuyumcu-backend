package com.kuyumcu.kuyumcu_backend.controller;

import org.junit.jupiter.api.Tag;
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
@Tag("integration")
class SiparisControllerIT {

    @Autowired
    private MockMvc mockMvc;

    

    @Test
    void tumSiparisler_basarili() throws Exception {
        mockMvc.perform(get("/api/siparisler"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }


    @Test
    void siparisGetir_bulunamadi_hata() throws Exception {
        mockMvc.perform(get("/api/siparisler/99999"))
                .andExpect(status().isNotFound());
    }

   

    @Test
    void siparisOlustur_sepetYok_hata() throws Exception {
        mockMvc.perform(post("/api/siparisler")
                .param("sepetId", "99999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    

    @Test
    void siparisIptal_bulunamadi_hata() throws Exception {
        mockMvc.perform(post("/api/siparisler/99999/iptal"))
                .andExpect(status().isNotFound());
    }

    

    @Test
    void siparisler_yanlisEndpoint_404() throws Exception {
        mockMvc.perform(get("/api/siparis"))
                .andExpect(status().isNotFound());
    }
}
