package com.delonic.peminjaman_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.delonic.peminjaman_service.model.PeminjamanQuery;
import com.delonic.peminjaman_service.repository.PeminjamanQueryRepository;
import com.delonic.peminjaman_service.vo.Anggota;
import com.delonic.peminjaman_service.vo.Buku;
import com.delonic.peminjaman_service.vo.ResponseTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PeminjamanQueryService {
    private final PeminjamanQueryRepository PeminjamanRepository;
    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;
    
    // Read all
    public List<PeminjamanQuery> getAllPeminjamans() {
        return PeminjamanRepository.findAll();
    }

    // Read by ID
    public Optional<PeminjamanQuery> getPeminjamanById(Long id) {
        return PeminjamanRepository.findById(id);
    }
    
     // Get Peminjaman with Buku & Anggota
    public ResponseTemplate getPeminjamanWithDetailsById(Long id) {
        PeminjamanQuery peminjaman = PeminjamanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Peminjaman dengan id " + id + " tidak ditemukan"));

        // Ambil service BUKU
        List<ServiceInstance> bukuInstances = discoveryClient.getInstances("BUKU-SERVICE");
        if (bukuInstances.isEmpty()) {
            throw new RuntimeException("Service BUKU tidak ditemukan");
        }
        String bukuUrl = bukuInstances.get(0).getUri().toString() + "/api/buku/" + peminjaman.getBukuId();
        Buku buku = restTemplate.getForObject(bukuUrl, Buku.class);

        // Ambil service ANGGOTA
        List<ServiceInstance> anggotaInstances = discoveryClient.getInstances("ANGGOTA-SERVICE");
        if (anggotaInstances.isEmpty()) {
            throw new RuntimeException("Service ANGGOTA tidak ditemukan");
        }
        String anggotaUrl = anggotaInstances.get(0).getUri().toString() + "/api/anggota/" + peminjaman.getAnggotaId();
        Anggota anggota = restTemplate.getForObject(anggotaUrl, Anggota.class);

        // Build response
        ResponseTemplate vo = new ResponseTemplate();
        vo.setPeminjaman(peminjaman);
        vo.setBuku(buku);
        vo.setAnggota(anggota);

        return vo;
    }
}
