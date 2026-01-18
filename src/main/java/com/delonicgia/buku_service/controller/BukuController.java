package com.delonicgia.buku_service.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.delonicgia.buku_service.model.Buku;
import com.delonicgia.buku_service.service.BukuService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/buku")
public class BukuController {

    @Autowired
    private BukuService bukuService;

    // GET semua buku
    @GetMapping
    public List<Buku> getAllBuku() {
        log.info("Berhasil mengambil semua buku");
        return bukuService.getAllBuku();
    }

    // GET buku by ID
    @GetMapping("/{id}")
    public ResponseEntity<Buku> getBukuById(@PathVariable Long id) {
        log.info("Berhasil mendapatkan buku berdasarkan id");
        Buku buku = bukuService.getBukuById(id);
        return (buku != null)
                ? ResponseEntity.ok(buku)
                : ResponseEntity.notFound().build();
    }

    // POST buat buku baru
    @PostMapping
    public Buku createBuku(@RequestBody Buku buku) {
        log.info("Create Buku : {}", buku.getJudul());
        return bukuService.createBuku(buku);
    }

    // PUT / UPDATE buku
    @PutMapping("/{id}")
    public ResponseEntity<Buku> updateBuku(@PathVariable Long id, @RequestBody Buku dataBaru) {
        log.info("Berhasil mengupdate buku");
        try {
            Buku updated = bukuService.updateBuku(id, dataBaru);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE buku by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBuku(@PathVariable Long id) {
        log.info("Berhasil menghapus buku");
        bukuService.deleteBuku(id);
        return ResponseEntity.ok().build();
    }
}