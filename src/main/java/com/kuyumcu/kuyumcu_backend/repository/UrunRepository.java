package com.kuyumcu.kuyumcu_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kuyumcu.kuyumcu_backend.entity.Urun;

public interface UrunRepository extends JpaRepository<Urun, Long> {
}
