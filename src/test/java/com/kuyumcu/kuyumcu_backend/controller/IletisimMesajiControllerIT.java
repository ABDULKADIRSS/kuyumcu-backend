package com.kuyumcu.kuyumcu_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuyumcu.kuyumcu_backend.entity.IletisimMesaji;
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
class IletisimMesajiControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void mesajGonder_ve_listele() throws Exception {
        IletisimMesaji mesaj = new IletisimMesaji();
        mesaj.setEmail("test@mail.com");
        mesaj.setMesaj("Merhaba");

        mockMvc.perform(post("/api/iletisim")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mesaj)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/iletisim"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
