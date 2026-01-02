package com.kuyumcu.kuyumcu_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kuyumcu.kuyumcu_backend.entity.SepetUrun;

public interface SepetUrunRepository extends JpaRepository<SepetUrun, Long> {

    List<SepetUrun> findBySepetId(Long sepetId);
}
