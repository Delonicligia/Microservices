package com.delonic.uts_delonic.dto;

import com.delonic.uts_delonic.model.Pinjaman;

public class peminjamanDto {
    private String kdTransaksi;
    private String nasabah;
    private double bunga;  
    private double jumlahPinjaman;
    private double lamaPinjaman;

    public String getKdTransaksi() {
        return kdTransaksi;
    }

    public void setKdTransaksi(String kdTransaksi) {
        this.kdTransaksi = kdTransaksi;
    }

    public String getNasabah() {
        return nasabah;
    }

    public void setNasabah(String nasabah) {
        this.nasabah = nasabah;
    }

    public double getBunga() {
        return bunga;
    }

    public void setBunga(double bunga) {
        this.bunga = bunga;
    }

    public double getJumlahPinjaman() {
        return jumlahPinjaman;
    }

    public void setJumlahPinjaman(double jumlahPinjaman) {
        this.jumlahPinjaman = jumlahPinjaman;
    }

    public double getLamaPinjaman() {
        return lamaPinjaman;
    }

    public void setLamaPinjaman(double lamaPinjaman) {
        this.lamaPinjaman = lamaPinjaman;
    }

    // Perhitungan
    public double getHitungBunga() {
        return jumlahPinjaman * (bunga / 100);
    }

    public double getAngsuranPerbulan() {
        return (jumlahPinjaman + getHitungBunga()) / lamaPinjaman;
    }

    
    public Pinjaman toEntity() {
        Pinjaman pinjaman = new Pinjaman();
        pinjaman.setKdTransaksi(this.kdTransaksi);
        pinjaman.setNasabah(this.nasabah);
        pinjaman.setBunga(this.bunga);
        pinjaman.setJumlahPinjaman(this.jumlahPinjaman);
        pinjaman.setLamaPinjaman((int) this.lamaPinjaman);
        pinjaman.setHitungBunga(getHitungBunga());
        pinjaman.setAngsuranPerbulan(getAngsuranPerbulan());
        pinjaman.setTotalPinjaman(this.jumlahPinjaman + getHitungBunga());
        return pinjaman;
    }
}
