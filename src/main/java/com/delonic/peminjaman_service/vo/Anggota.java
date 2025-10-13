package com.delonic.peminjaman_service.vo;

public class Anggota {
    private Long id;
    private String tanggal_pinjam;
    private String tanggal_kembali;
    private Long anggotaId;
    private Long bukuId;

    public Anggota() {
    }

    public Anggota(Long id, String tanggal_pinjam, String tanggal_kembali, Long anggotaId, Long bukuId) {
        this.id = id;
        this.tanggal_pinjam = tanggal_pinjam;
        this.tanggal_kembali = tanggal_kembali;
        this.anggotaId = anggotaId;
        this.bukuId = bukuId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTanggal_pinjam() {
        return tanggal_pinjam;
    }

    public void setTanggal_pinjam(String tanggal_pinjam) {
        this.tanggal_pinjam = tanggal_pinjam;
    }

    public String getTanggal_kembali() {
        return tanggal_kembali;
    }

    public void setTanggal_kembali(String tanggal_kembali) {
        this.tanggal_kembali = tanggal_kembali;
    }

    public Long getAnggotaId() {
        return anggotaId;
    }

    public void setAnggotaId(Long anggotaId) {
        this.anggotaId = anggotaId;
    }

    public Long getBukuId() {
        return bukuId;
    }

    public void setBukuId(Long bukuId) {
        this.bukuId = bukuId;
    }
}
