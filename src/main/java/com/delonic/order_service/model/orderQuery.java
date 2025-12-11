package com.delonic.order_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders_query")
public class orderQuery implements Serializable {
    @Id
    private String id;

    private String produkId;
    private Long pelangganId;
    private Integer jumlah;
    private LocalDateTime tanggal;
    private String status;
    private Double total;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    /**
     * Constructor ini sangat berguna untuk Kafka Listener.
     * Saat menerima event BukuCommand, kita bisa langsung membuat
     * objek BukuQuery dari event tersebut untuk disimpan ke MongoDB.
     */
    public orderQuery(orderCommand command) {
        this.id = command.getId();
        this.produkId = command.getProdukId();
        this.pelangganId = command.getPelangganId();
        this.jumlah = command.getJumlah();
        this.tanggal = command.getTanggal();
        this.status = command.getStatus();
        this.total = command.getTotal();
        this.createdAt = command.getCreatedAt();
        this.processedAt = command.getProcessedAt();        
    }
}