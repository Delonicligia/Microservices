package com.delonic.anggota_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.delonic.anggota_service.model.AnggotaQuery;
import com.delonic.anggota_service.service.AnggotaQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/anggota/query")
public class AnggotaQueryController {

    private final AnggotaQueryService anggotaQuery;

    // GET semua anggota
    @GetMapping
    public ResponseEntity<List<AnggotaQuery>> getAllAnggota() {
        return ResponseEntity.ok(anggotaQuery.getAllAnggota());
    }

    // GET anggota by ID
    @GetMapping("/{id}")
    public ResponseEntity<AnggotaQuery> getAnggotaById(@PathVariable Long id) {
        return ResponseEntity.ok(anggotaQuery.getAnggotaById(id));
    }

}
