package com.delonicgia.buku_service.service;

import org.springframework.stereotype.Service;

import com.delonicgia.buku_service.model.Buku;
import com.delonicgia.buku_service.repository.write.BukuWriteRepository;

@Service
public class BukuCommandService {
    private final BukuWriteRepository writeRepo;

    public BukuCommandService(BukuWriteRepository writeRepo) {
        this.writeRepo = writeRepo;
    }

    public Buku tambahBuku(Buku buku) {
        return writeRepo.save(buku);
    }
}
