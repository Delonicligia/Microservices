package com.delonic.anggota_service.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.delonic.anggota_service.model.Anggota;
import com.delonic.anggota_service.repository.AnggotaRepository;

@Service
public class AnggotaService {

    @Autowired
    private AnggotaRepository anggotaRepository;

    public List<Anggota> getAllAnggota() {
        return anggotaRepository.findAll();
    }

    public Anggota getAnggotaById(Long id) {
        return anggotaRepository.findById(id).orElse(null);
    }

    public Anggota createAnggota(Anggota anggota) {
        // pastikan id kosong agar Hibernate tau ini entity baru
        anggota.setId(null);
        return anggotaRepository.save(anggota);
    }

    public Anggota updateAnggota(Long id, Anggota dataBaru) {
        Anggota existing = anggotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anggota tidak ditemukan dengan id: " + id));

        // update field yang perlu
        existing.setNama(dataBaru.getNama());
        existing.setAlamat(dataBaru.getAlamat());
        // tambahkan field lain sesuai model Anggota kamu

        return anggotaRepository.save(existing);
    }

    public void deleteAnggota(Long id) {
        anggotaRepository.deleteById(id);
    }
}
