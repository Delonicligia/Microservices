package com.delonic.peminjaman_service.service;

import org.springframework.stereotype.Service;
import com.delonic.peminjaman_service.model.PeminjamanCommand;
import com.delonic.peminjaman_service.repository.PeminjamanCommandRepository;
import org.springframework.kafka.core.KafkaTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class PeminjamanCommandService {

    private final PeminjamanCommandRepository PeminjamanRepository;
    private final KafkaTemplate<Long, Object> kafkaTemplate;
    private static final String TOPIC = "peminjaman-events";
    

    // Create
    public PeminjamanCommand savePeminjaman(PeminjamanCommand peminjaman) {
        PeminjamanCommand saved = PeminjamanRepository.save(peminjaman);
        saved.setEventType(PeminjamanCommand.EventType.CREATED);
        publishEventToKafka(saved);
        return saved;
    }

    // Update
    public PeminjamanCommand updatePeminjaman(Long id, PeminjamanCommand peminjaman) {
        PeminjamanCommand existing = PeminjamanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Peminjaman tidak ditemukan dengan id: " + id));

            existing.setBukuId(peminjaman.getBukuId());
            existing.setAnggotaId(peminjaman.getAnggotaId());
            existing.setTanggal_pinjam(peminjaman.getTanggal_pinjam());
            existing.setTanggal_kembali(peminjaman.getTanggal_kembali());

            PeminjamanCommand updated = PeminjamanRepository.save(existing);

            updated.setEventType(PeminjamanCommand.EventType.UPDATED);

            publishEventToKafka(updated);

            return updated;
        
    }

    // Delete
    public void deletePeminjaman(Long id) {
        PeminjamanRepository.deleteById(id);

        PeminjamanCommand deleted = new PeminjamanCommand();
        deleted.setId(id);
        deleted.setEventType(PeminjamanCommand.EventType.DELETED);
        publishEventToKafka(deleted);
    }

   

    private void publishEventToKafka(PeminjamanCommand event) {
        try {
            kafkaTemplate.send(TOPIC, event.getId(), event).whenComplete((result, ex)-> {
                if(ex == null){
                    log.info("Berhasil mengirim event {} dengan id {} ke partisi {} offset {}", 
                        event.getEventType(),
                        event.getId(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset()
                    );
                }else{
                    log.error("Gagal mempublish event {} dengan id {}", event.getEventType(), event.getId(), ex);
                }
            });
        } catch (Exception e) {
            log.error("gagal membaca event", e);
        }
    }
}