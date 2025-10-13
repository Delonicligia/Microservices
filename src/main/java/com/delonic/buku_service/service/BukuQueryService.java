// service/BukuQueryService.java
package com.delonic.buku_service.service;

import com.delonic.buku_service.model.Buku;
import com.delonic.buku_service.repository.read.BukuReadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BukuQueryService {
    private final BukuReadRepository readRepo;

    public BukuQueryService(BukuReadRepository readRepo) {
        this.readRepo = readRepo;
    }

    public List<Buku> semuaBuku() {
        return readRepo.findAll();
    }
}
