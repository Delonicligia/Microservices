package com.delonic.pengembalian_service.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delonic.pengembalian_service.model.Pengembalian;
import com.delonic.pengembalian_service.service.PengembalianService;
import com.delonic.pengembalian_service.vo.ResponseTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/pengembalian")
public class PengembalianController {
    @Autowired
    private PengembalianService pengembalianService;

    // GET semua pengembalian
    @GetMapping
    public List<Pengembalian> getPengembalians(){
        log.info("Berhasil mengambil semua pengembalian");
        return pengembalianService.getAllPengembalians();
    }

    // GET pengembalian by ID
    @GetMapping("/{id}")
    public ResponseEntity<Pengembalian> getPengembalianById(@PathVariable Long id) {
        log.info("Berhasil mendapatkan pengembalian berdasarkan id");
        Pengembalian pengembalian = pengembalianService.getPengembalianById(id);
        return pengembalian != null ? ResponseEntity.ok(pengembalian): ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/detail")
public ResponseEntity<List<ResponseTemplate>> getPengembalianDetail(@PathVariable Long id) {
    log.info("Berhasil mendapatkan detail pengembalian berdasarkan id");
    List<ResponseTemplate> response = pengembalianService.getPengembalianWithDetailById(id);
    return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
}

// POST buat pengembalian baru
    @PostMapping
    public Pengembalian createPengembalian(@RequestBody Pengembalian pengembalian) throws ParseException{
        log.info("Create Pengembalian : {}", pengembalian.getPeminjamanId());
        return pengembalianService.createPengembalian(pengembalian);
    }

    // DELETE pengembalian by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePengembalian(@PathVariable Long id){
        log.info("Berhasil menghapus pengembalian");
        pengembalianService.deletePengembalian(id);
        return ResponseEntity.ok().build();
    }
}