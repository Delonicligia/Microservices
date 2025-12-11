package com.delonic.order_service.model;


import com.delonic.order_service.EventType; // Impor enum dari file terpisah
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders_command")
public class orderCommand implements Serializable {

    @Id
    private String id;

    private String produkId;
    private Long pelangganId;
    private Integer jumlah;
    
    // REKOMENDASI: Gunakan tipe data tanggal yang tepat
    private LocalDateTime tanggal; 
    
    private String status;
    private Double total;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime processedAt;

    // REKOMENDASI: Abaikan field ini dari database command
    @Transient 
    private EventType eventType;
}
