package com.kuyumcu.kuyumcu_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuyumcu.kuyumcu_backend.entity.Kategori;
import com.kuyumcu.kuyumcu_backend.entity.Sepet;
import com.kuyumcu.kuyumcu_backend.entity.Urun;
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
class SepetControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    

    @Test
    void sepetOlustur_basarili() throws Exception {
        mockMvc.perform(post("/api/sepetler"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

   

    @Test
    void sepeteUrunEkle_basarili() throws Exception {
        
        Kategori kategori = new Kategori();
        kategori.setAd("Altın");

        String kategoriJson = mockMvc.perform(post("/api/kategoriler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(kategori)))
                .andReturn().getResponse().getContentAsString();

        Kategori kaydedilenKategori = objectMapper.readValue(kategoriJson, Kategori.class);

        
        Urun urun = new Urun();
        urun.setAd("Altın Yüzük");
        urun.setGram(2.5);
        urun.setFiyat(1000.0);
        urun.setStok(10);
        urun.setKategori(kaydedilenKategori);

        String urunJson = mockMvc.perform(post("/api/urunler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(urun)))
                .andReturn().getResponse().getContentAsString();

        Urun kaydedilenUrun = objectMapper.readValue(urunJson, Urun.class);

       
        String sepetJson = mockMvc.perform(post("/api/sepetler"))
                .andReturn().getResponse().getContentAsString();

        Sepet sepet = objectMapper.readValue(sepetJson, Sepet.class);

       
        mockMvc.perform(post("/api/sepetler/" + sepet.getId() + "/urun-ekle")
                .param("urunId", kaydedilenUrun.getId().toString())
                .param("adet", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.adet").value(2));
    }


    @Test
    void sepetUrunleri_basarili() throws Exception {
        String sepetJson = mockMvc.perform(post("/api/sepetler"))
                .andReturn().getResponse().getContentAsString();

        Sepet sepet = objectMapper.readValue(sepetJson, Sepet.class);

        mockMvc.perform(get("/api/sepetler/" + sepet.getId() + "/urunler"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

 

    @Test
    void sepetBosalt_basarili() throws Exception {
        String sepetJson = mockMvc.perform(post("/api/sepetler"))
                .andReturn().getResponse().getContentAsString();

        Sepet sepet = objectMapper.readValue(sepetJson, Sepet.class);

        mockMvc.perform(delete("/api/sepetler/" + sepet.getId()))
                .andExpect(status().isNoContent());
    }

    

    @Test
    void sepeteUrunEkle_adetSifir_hata() throws Exception {
        mockMvc.perform(post("/api/sepetler/1/urun-ekle")
                .param("urunId", "1")
                .param("adet", "0"))
                .andExpect(status().isBadRequest());
    }

    

    @Test
    void sepeteUrunEkle_sepetYok_hata() throws Exception {
        mockMvc.perform(post("/api/sepetler/9999/urun-ekle")
                .param("urunId", "1")
                .param("adet", "1"))
                .andExpect(status().isBadRequest());
    }
}
