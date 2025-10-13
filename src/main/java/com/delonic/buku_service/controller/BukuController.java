package com.delonic.buku_service.controller;


import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.delonic.buku_service.model.Buku;
import com.delonic.buku_service.service.BukuCommandService;
import com.delonic.buku_service.service.BukuQueryService;

@RestController
@RequestMapping("/api/buku")
public class BukuController {

    private final BukuCommandService commandService;
    private final BukuQueryService queryService;

    public BukuController(BukuCommandService commandService, BukuQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    // GET semua buku
    @GetMapping
    public List<Buku> getAllBuku() {
        return queryService.semuaBuku();
    }

    // GET buku by ID
    @GetMapping("/{id}")
    public ResponseEntity<Buku> getBukuById(@PathVariable Long id) {
        return queryService.getBukuById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST buat buku baru
    @PostMapping
    public Buku createBuku(@RequestBody Buku buku) {
        return commandService.tambahBuku(buku);
    }

    // DELETE buku by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBuku(@PathVariable Long id) {
        commandService.hapusBuku(id);
        return ResponseEntity.ok().build();
    }
}
