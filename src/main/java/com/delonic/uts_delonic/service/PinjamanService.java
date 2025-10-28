package com.delonic.uts_delonic.service;

import com.delonic.uts_delonic.dto.peminjamanDto;
import com.delonic.uts_delonic.model.Pinjaman;
import com.delonic.uts_delonic.repository.PinjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinjamanService {

    @Autowired
    private PinjamanRepository pinjamanRepository;


    public Pinjaman create(Pinjaman pinjaman) {
        return pinjamanRepository.save(pinjaman);
    }

    
    public List<Pinjaman> getAll() {
        return pinjamanRepository.findAll();
    }

    
    public Pinjaman getById(Long id) {
        return pinjamanRepository.findById(id).orElse(null);
    }

    
    public Pinjaman update(Long id, Pinjaman pinjamanBaru) {
        Pinjaman pinjaman = pinjamanRepository.findById(id).orElse(null);

        pinjaman.setKdTransaksi(pinjamanBaru.getKdTransaksi());
        pinjaman.setNasabah(pinjamanBaru.getNasabah());
        pinjaman.setJumlahPinjaman(pinjamanBaru.getJumlahPinjaman());
        pinjaman.setBunga(pinjamanBaru.getBunga());
        pinjaman.setLamaPinjaman(pinjamanBaru.getLamaPinjaman());
        pinjaman.setAngsuranPerbulan(pinjamanBaru.getAngsuranPerbulan());
        pinjaman.setTotalPinjaman(pinjamanBaru.getTotalPinjaman());

        return pinjamanRepository.save(pinjaman);
    }

    
    public void delete(Long id) {
        pinjamanRepository.deleteById(id);
    }

      public Pinjaman createDto(peminjamanDto dto) {
        Pinjaman pinjaman = dto.toEntity();
        return pinjamanRepository.save(pinjaman);
    }
}
