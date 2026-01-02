    package com.kuyumcu.kuyumcu_backend.entity;
    import jakarta.persistence.Entity;
    import jakarta.persistence.EnumType;
    import jakarta.persistence.Enumerated;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.Table;
    import lombok.Data;

    @Entity
    @Table(name = "siparis_urunler")
    @Data
    public class SiparisUrun {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private Siparis siparis;

        @ManyToOne
        private Urun urun;

        private Integer adet;
        private Double fiyat;
    }

