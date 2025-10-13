package com.delonic.anggota_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.delonic.anggota_service.model.Anggota;
import com.delonic.anggota_service.service.AnggotaService;

@RestController
@RequestMapping("/api/anggota")
public class AnggotaController {

    @Autowired
    private AnggotaService anggotaService;

    // GET semua anggota
    @GetMapping
    public List<Anggota> getAllAnggota() {
        return anggotaService.getAllAnggota();
    }

    // GET anggota by ID
    @GetMapping("/{id}")
    public ResponseEntity<Anggota> getAnggotaById(@PathVariable Long id) {
        Anggota anggota = anggotaService.getAnggotaById(id);
        return anggota != null ? ResponseEntity.ok(anggota) : ResponseEntity.notFound().build();
    }

    // POST buat anggota baru
    @PostMapping
    public Anggota createAnggota(@RequestBody Anggota anggota) {
        return anggotaService.createAnggota(anggota);
    }

    // DELETE anggota by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnggota(@PathVariable Long id) {
        anggotaService.deleteAnggota(id);
        return ResponseEntity.ok().build();
    }
}
