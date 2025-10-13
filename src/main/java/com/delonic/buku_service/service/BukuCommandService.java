package com.delonic.buku_service.service;

import com.delonic.buku_service.model.Buku;
import com.delonic.buku_service.repository.write.BukuWriteRepository;
import org.springframework.stereotype.Service;

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
