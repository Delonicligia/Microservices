package com.delonic.uts_delonic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Pinjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String kdTransaksi;
    private String nasabah;
    private double jumlahPinjaman;
    private double bunga;  
    private int lamaPinjaman;
    private double angsuranPerbulan;
    private double totalPinjaman;
    private double hitungBunga;
}
