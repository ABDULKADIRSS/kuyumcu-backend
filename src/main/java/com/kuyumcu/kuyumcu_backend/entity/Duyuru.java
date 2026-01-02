package com.kuyumcu.kuyumcu_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "duyurular")
@Data
public class Duyuru {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String baslik;
    private boolean aktif;
}
