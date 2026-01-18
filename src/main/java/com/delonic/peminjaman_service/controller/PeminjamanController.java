package com.delonic.peminjaman_service.controller;

import com.delonic.peminjaman_service.model.Peminjaman;
import com.delonic.peminjaman_service.service.PeminjamanService;
import com.delonic.peminjaman_service.vo.ResponseTemplate;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/peminjaman")
public class PeminjamanController {

    @Autowired
    private PeminjamanService peminjamanService;

    // Get all peminjaman
    @GetMapping
    public List<Peminjaman> getAllPeminjaman() {
        log.info("Berhasil mengambil semua peminjaman");
        return peminjamanService.getAllPeminjamans();
    }
    

    // Get peminjaman by ID
    @GetMapping("/{id}")
    public ResponseEntity<Peminjaman> getPeminjamanById(@PathVariable Long id) {
        log.info("Berhasil mendapatkan peminjaman berdasarkan id");
        return peminjamanService.getPeminjamanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get peminjaman + buku + anggota
    @GetMapping("/{id}/details")
    public ResponseTemplate getPeminjamanWithDetails(@PathVariable Long id) {
        log.info("Berhasil mendapatkan detail peminjaman berdasarkan id");
        return peminjamanService.getPeminjamanWithDetailsById(id);
    }

    // Create peminjaman
    @PostMapping
    public Peminjaman createPeminjaman(@RequestBody Peminjaman peminjaman) {
        log.info("Create Peminjaman : {}", peminjaman.getAnggotaId());
        return peminjamanService.savePeminjaman(peminjaman);
    }

    // Update peminjaman
    @PutMapping("/{id}")
    public Peminjaman updatePeminjaman(@PathVariable Long id, @RequestBody Peminjaman peminjamanDetails) {
        log.info("Mengupdate peminjaman dengan id: {}", id);
        return peminjamanService.updatePeminjaman(id, peminjamanDetails);
    }

    // Delete peminjaman
    @DeleteMapping("/{id}")
    public String deletePeminjaman(@PathVariable Long id) {
        log.info("Berhasil menghapus peminjaman");
        return peminjamanService.deletePeminjaman(id);
    }
}
