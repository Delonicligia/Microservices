package com.delonic.peminjaman_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delonic.peminjaman_service.model.PeminjamanQuery;
import com.delonic.peminjaman_service.service.PeminjamanQueryService;
import com.delonic.peminjaman_service.vo.ResponseTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/peminjaman/query")
public class PeminjamanQueryController {
    private final PeminjamanQueryService peminjamanService;
    // Get all peminjaman
    @GetMapping
    public List<PeminjamanQuery> getAllPeminjaman() {
        return peminjamanService.getAllPeminjamans();
    }
    
    
    // Get peminjaman by ID
    @GetMapping("/{id}")
    public ResponseEntity<PeminjamanQuery> getPeminjamanById(@PathVariable Long id) {
        return peminjamanService.getPeminjamanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get peminjaman + buku + anggota
    @GetMapping("/{id}/details")
    public ResponseTemplate getPeminjamanWithDetails(@PathVariable Long id) {
        return peminjamanService.getPeminjamanWithDetailsById(id);
    }
}
