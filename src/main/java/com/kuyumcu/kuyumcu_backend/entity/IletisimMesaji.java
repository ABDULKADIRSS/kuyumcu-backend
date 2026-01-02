package com.kuyumcu.kuyumcu_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "iletisim_mesajlari")
@Data
public class IletisimMesaji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String mesaj;
}
