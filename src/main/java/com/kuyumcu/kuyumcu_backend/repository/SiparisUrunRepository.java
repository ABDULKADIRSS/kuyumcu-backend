package com.kuyumcu.kuyumcu_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kuyumcu.kuyumcu_backend.entity.SiparisUrun;

public interface SiparisUrunRepository extends JpaRepository<SiparisUrun, Long> {

    List<SiparisUrun> findBySiparisId(Long siparisId);
}
