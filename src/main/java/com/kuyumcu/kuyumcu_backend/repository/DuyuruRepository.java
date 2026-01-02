package com.kuyumcu.kuyumcu_backend.repository;

import com.kuyumcu.kuyumcu_backend.entity.Duyuru;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DuyuruRepository extends JpaRepository<Duyuru, Long> {
    List<Duyuru> findByAktifTrue();
}
