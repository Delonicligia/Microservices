package com.delonic.anggota_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.delonic.anggota_service.model.AnggotaQuery;

@Repository
public interface AnggotaQueryRepository extends JpaRepository<AnggotaQuery, Long> {
    
}
