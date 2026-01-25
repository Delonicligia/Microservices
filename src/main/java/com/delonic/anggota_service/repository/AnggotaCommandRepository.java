package com.delonic.anggota_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.delonic.anggota_service.model.AnggotaCommand;

@Repository
public interface AnggotaCommandRepository extends JpaRepository<AnggotaCommand, Long> {
    
}
