package com.delonic.peminjaman_service.vo;

public class Anggota {
    private Long id;
    private String nama;
    private String email;
    private String alamat;
    private String telepon;

    public Anggota() {
    }

    public Anggota(Long id, String nama, String email, String alamat, String telepon) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.telepon = telepon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}
