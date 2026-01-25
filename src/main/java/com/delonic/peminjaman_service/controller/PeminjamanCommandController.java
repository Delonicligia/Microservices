package com.delonic.peminjaman_service.controller;

import com.delonic.peminjaman_service.model.PeminjamanCommand;
import com.delonic.peminjaman_service.service.PeminjamanCommandService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/peminjaman/command")
public class PeminjamanCommandController {

    private final PeminjamanCommandService peminjamanService;

    // Create peminjaman
    @PostMapping
    public PeminjamanCommand createPeminjaman(@RequestBody PeminjamanCommand peminjaman) {
        return peminjamanService.savePeminjaman(peminjaman);
    }

    // Update peminjaman
    @PutMapping("/{id}")
    public PeminjamanCommand updatePeminjaman(@PathVariable Long id, @RequestBody PeminjamanCommand peminjamanDetails) {
        return peminjamanService.updatePeminjaman(id, peminjamanDetails);
    }

    // Delete peminjaman
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePeminjaman(@PathVariable Long id) {
        peminjamanService.deletePeminjaman(id);
        return ResponseEntity.ok("peminjaman berhasil di hapus");
    }
}
