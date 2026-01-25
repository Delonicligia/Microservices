package com.delonic.anggota_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "anggota-query")
public class AnggotaQuery implements Serializable{
    @Id
    private Long id;

    private String nim;
    private String nama;
    private String alamat;
    private String jenis_kelamin;
    
    public AnggotaQuery(AnggotaCommand command){
        this.id = command.getId();
        this.nim = command.getNim();
        this.nama = command.getNama();
        this.alamat = command.getAlamat();
        this.jenis_kelamin = command.getJenis_kelamin();
    }
}
