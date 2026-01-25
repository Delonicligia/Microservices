package com.delonic.peminjaman_service.vo;

import com.delonic.peminjaman_service.model.PeminjamanQuery;

public class ResponseTemplate {
    private Anggota anggota;
    private Buku buku;
    private PeminjamanQuery peminjaman;

    // Constructor kosong
    public ResponseTemplate() {
    }

    // Constructor dengan isi
    public ResponseTemplate(Anggota anggota, Buku buku, PeminjamanQuery peminjaman) {
        this.anggota = anggota;
        this.buku = buku;
        this.peminjaman = peminjaman;
    }

    // Getter dan Setter
    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public PeminjamanQuery getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(PeminjamanQuery peminjaman) {
        this.peminjaman = peminjaman;
    }
}
