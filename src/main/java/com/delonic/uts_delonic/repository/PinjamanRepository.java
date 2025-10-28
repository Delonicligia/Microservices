package com.delonic.uts_delonic.repository;

import com.delonic.uts_delonic.model.Pinjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinjamanRepository extends JpaRepository<Pinjaman, Long> {
}


