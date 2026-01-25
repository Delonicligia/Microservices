package com.delonic.anggota_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.delonic.anggota_service.model.AnggotaCommand;
import com.delonic.anggota_service.service.AnggotaCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/anggota/command")
public class AnggotaCommandController {

    private final AnggotaCommandService anggotaService;

    // POST buat anggota baru
    @PostMapping
    public ResponseEntity<String> createAnggota(@RequestBody AnggotaCommand anggota) {
        anggotaService.createAnggota(anggota);
        return ResponseEntity.ok("Berhasil Create Anggota");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAnggota(@PathVariable Long id, @RequestBody AnggotaCommand anggota) {
        anggotaService.updateAnggota(id, anggota);
        return ResponseEntity.ok("Berhasil Mengupdate anggota");
    }

    // DELETE anggota by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnggota(@PathVariable Long id) {
        anggotaService.deleteAnggota(id);
        return ResponseEntity.ok("berhasil hapus anggota");
    }
}
