// service/BukuQueryService.java
package com.delonicgia.buku_service.service;

import org.springframework.stereotype.Service;

import com.delonicgia.buku_service.model.Buku;
import com.delonicgia.buku_service.repository.read.BukuReadRepository;

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
