package com.delonic.order_service;


/**
 * Mendefinisikan jenis-jenis event yang bisa terjadi pada sebuah order.
 * Digunakan untuk komunikasi via Kafka antara command dan query side.
 */
public enum EventType {
    /**
     * Event yang dipicu saat sebuah order baru berhasil dibuat.
     */
    CREATED,

    /**
     * Event yang dipicu saat data sebuah order berhasil diperbarui.
     */
    UPDATED,

    /**
     * Event yang dipicu saat sebuah order berhasil dihapus.
     */
    DELETED
}
