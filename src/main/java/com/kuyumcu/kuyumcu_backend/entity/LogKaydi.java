package com.kuyumcu.kuyumcu_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_kayitlari")
@Data
public class LogKaydi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String islem;
    private String aciklama;

    private LocalDateTime tarih = LocalDateTime.now();
}
