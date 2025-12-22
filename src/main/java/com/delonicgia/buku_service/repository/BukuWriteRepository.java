package com.delonicgia.buku_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delonicgia.buku_service.model.Buku;

public interface BukuWriteRepository extends JpaRepository<Buku, Long> {
}

