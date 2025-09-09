package com.delonic.order_service.vo;
import com.delonic.order_service.model.Order;

public class ResponseTemplate {
    private Order order;
    private produk produk;
    private pelanggan pelanggan;

    // Constructor kosong
    public ResponseTemplate() {
    }

    // Constructor dengan isi
    public ResponseTemplate(Order order, produk produk, pelanggan pelanggan) {
        this.order = order;
        this.produk = produk;
        this.pelanggan = pelanggan;
    }

    // Getter dan Setter
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
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