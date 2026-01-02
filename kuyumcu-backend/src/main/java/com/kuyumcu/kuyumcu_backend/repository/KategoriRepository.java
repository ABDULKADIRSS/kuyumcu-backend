package com.kuyumcu.kuyumcu_backend.repository;



import com.kuyumcu.kuyumcu_backend.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategoriRepository extends JpaRepository<Kategori, Long> {
}
