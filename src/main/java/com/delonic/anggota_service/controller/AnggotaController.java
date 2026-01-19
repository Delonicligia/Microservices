package com.delonic.anggota_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.delonic.anggota_service.model.Anggota;
import com.delonic.anggota_service.service.AnggotaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/anggota")
public class AnggotaController {

    @Autowired
    private AnggotaService anggotaService;

    // GET semua anggota
    @GetMapping
    public List<Anggota> getAllAnggota() {
        log.info("Berhasil mengambil semua anggota");
        return anggotaService.getAllAnggota();
    }

    // GET anggota by ID
    @GetMapping("/{id}")
    public ResponseEntity<Anggota> getAnggotaById(@PathVariable Long id) {
        log.info("Berhasil mendapatkan anggota berdasarkan id", getAnggotaById(id));
        Anggota anggota = anggotaService.getAnggotaById(id);
        return anggota != null ? ResponseEntity.ok(anggota) : ResponseEntity.notFound().build();
    }

    // POST buat anggota baru
    @PostMapping
    public Anggota createAnggota(@RequestBody Anggota anggota) {
        log.info("Create Anggota : {}", anggota.getNama());
        return anggotaService.createAnggota(anggota);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Anggota> updateAnggota(@PathVariable Long id, @RequestBody Anggota AnggotaDetails){
        log.info("Mengupdate anggota dengan id: {}", id);
        return ResponseEntity.ok(anggotaService.updateAnggota(id, AnggotaDetails));
    }

    // DELETE anggota by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnggota(@PathVariable Long id) {
        log.info("Berhasil menghapus anggota");
        anggotaService.deleteAnggota(id);
        return ResponseEntity.ok().build();
    }
}
