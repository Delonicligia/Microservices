package com.delonic.uts_delonic.controller;

import com.delonic.uts_delonic.dto.peminjamanDto;
import com.delonic.uts_delonic.model.Pinjaman;
import com.delonic.uts_delonic.service.PinjamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pinjaman")
public class PinjamanController {

    @Autowired
    private PinjamanService pinjamanService;

    // CREATE
    @PostMapping
    public Pinjaman createPinjaman(@RequestBody Pinjaman pinjaman) {
        return pinjamanService.create(pinjaman);
    }


      @PostMapping("/dto")
    public Pinjaman createPinjamanFromDto(@RequestBody peminjamanDto dto) {
        return pinjamanService.createDto(dto);
    }

    // READ ALL
    @GetMapping
    public List<Pinjaman> getAllPinjaman() {
        return pinjamanService.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Pinjaman getPinjamanById(@PathVariable Long id) {
        return pinjamanService.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Pinjaman updatePinjaman(@PathVariable Long id, @RequestBody Pinjaman pinjaman) {
        return pinjamanService.update(id, pinjaman);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deletePinjaman(@PathVariable Long id) {
        pinjamanService.delete(id);
    }
}
