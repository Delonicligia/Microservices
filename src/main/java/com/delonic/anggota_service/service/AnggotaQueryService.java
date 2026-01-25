package com.delonic.anggota_service.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.delonic.anggota_service.model.AnggotaQuery;
import com.delonic.anggota_service.repository.AnggotaQueryRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnggotaQueryService {

    private final AnggotaQueryRepository anggotaRepository;

    public List<AnggotaQuery> getAllAnggota() {
        return anggotaRepository.findAll();
    }

    public AnggotaQuery getAnggotaById(Long id) {
        return anggotaRepository.findById(id).orElse(null);
    }
    
}
