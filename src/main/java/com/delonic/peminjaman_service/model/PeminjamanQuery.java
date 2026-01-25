package com.delonic.peminjaman_service.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "peminjaman-query")
public class PeminjamanQuery implements Serializable{

    @Id
    private Long id;

    private String tanggal_pinjam;
    private String tanggal_kembali;
    private Long anggotaId;
    private Long bukuId;

    public PeminjamanQuery(PeminjamanCommand command){
        this.id = command.getId();
        this.tanggal_pinjam = command.getTanggal_pinjam();
        this.tanggal_kembali = command.getTanggal_kembali();
        this.anggotaId = command.getAnggotaId();
        this.bukuId = command.getBukuId();
    }
}
