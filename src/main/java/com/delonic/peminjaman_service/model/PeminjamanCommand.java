package com.delonic.peminjaman_service.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "peminjaman-command")
public class PeminjamanCommand implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tanggal_pinjam;
    private String tanggal_kembali;
    private Long anggotaId;
    private Long bukuId;

    @Transient
    private EventType eventType;

    public enum EventType{
        CREATED,
        UPDATED,
        DELETED
    }
}
