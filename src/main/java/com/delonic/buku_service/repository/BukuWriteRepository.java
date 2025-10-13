package com.delonic.buku_service.repository;

import com.delonic.buku_service.model.Buku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BukuWriteRepository extends JpaRepository<Buku, Long> {
}

