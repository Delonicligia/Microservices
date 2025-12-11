package com.delonic.order_service.vo;
import com.delonic.order_service.model.orderQuery;

public class ResponseTemplate {
    private orderQuery order;
    private produk produk;
    private pelanggan pelanggan;

    // Constructor kosong
    public ResponseTemplate() {
    }

    // Constructor dengan isi
    public ResponseTemplate(orderQuery order, produk produk, pelanggan pelanggan) {
        this.order = order;
        this.produk = produk;
        this.pelanggan = pelanggan;
    }

    // Getter dan Setter
    public orderQuery getOrder() {
        return order;
    }

    public void setOrder(orderQuery order) {
        this.order = order;
    }

    public produk getProduk() {
        return produk;
    }

    public void setProduk(produk produk) {
        this.produk = produk;
    }

    public pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }
}