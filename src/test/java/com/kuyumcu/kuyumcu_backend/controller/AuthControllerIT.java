package com.kuyumcu.kuyumcu_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuyumcu.kuyumcu_backend.dto.LoginRequest;
import com.kuyumcu.kuyumcu_backend.entity.Kullanici;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void register_basarili() throws Exception {
        Kullanici k = new Kullanici();
        k.setAd("Ali");
        k.setEmail("ali@mail.com");
        k.setSifre("1234");

        mockMvc.perform(post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(k)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("ali@mail.com"));
    }

   
    @Test
    void login_basarili() throws Exception {
        Kullanici k = new Kullanici();
        k.setAd("Veli");
        k.setEmail("veli@mail.com");
        k.setSifre("1234");

        mockMvc.perform(post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(k)))
                .andExpect(status().isOk());

        LoginRequest request = new LoginRequest();
        request.setEmail("veli@mail.com");
        request.setSifre("1234");

       
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

   
    @Test
    void login_hataliSifre_hata() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setEmail("olmayan@mail.com");
        request.setSifre("yanlis");

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }
}
