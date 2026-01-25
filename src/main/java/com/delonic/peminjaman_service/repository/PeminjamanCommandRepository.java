package com.delonic.peminjaman_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.delonic.peminjaman_service.model.PeminjamanCommand;

@Repository
public interface PeminjamanCommandRepository extends JpaRepository<PeminjamanCommand, Long> {
}
