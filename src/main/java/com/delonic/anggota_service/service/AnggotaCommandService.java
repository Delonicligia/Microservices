package com.delonic.anggota_service.service;

import org.springframework.stereotype.Service;
import com.delonic.anggota_service.model.AnggotaCommand;
import com.delonic.anggota_service.repository.AnggotaCommandRepository;
import org.springframework.kafka.core.KafkaTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnggotaCommandService {

    private final AnggotaCommandRepository anggotaRepository;
    private final KafkaTemplate<Long, Object> kafkaTemplate;
    private static final String TOPIC = "anggota-events";

    public AnggotaCommand createAnggota(AnggotaCommand anggota) {
        AnggotaCommand saved = anggotaRepository.save(anggota);
        saved.setEventType(AnggotaCommand.EventType.CREATED);
        publishEventToKafka(saved);
        return saved;
    }

    public AnggotaCommand updateAnggota(Long id, AnggotaCommand anggota) {
        AnggotaCommand existing = anggotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anggota tidak ditemukan dengan id: " + id));

        existing.setNim(anggota.getNim());
        existing.setNama(anggota.getNama());
        existing.setAlamat(anggota.getAlamat());
        existing.setJenis_kelamin(anggota.getJenis_kelamin());

        AnggotaCommand updated = anggotaRepository.save(existing);

        updated.setEventType(AnggotaCommand.EventType.UPDATED);

        publishEventToKafka(updated);

        return updated;
    }

    public void deleteAnggota(Long id) {
        anggotaRepository.deleteById(id);

        AnggotaCommand deleted = new AnggotaCommand();
        deleted.setId(id);
        deleted.setEventType(AnggotaCommand.EventType.DELETED);
        publishEventToKafka(deleted);

    }

    private void publishEventToKafka(AnggotaCommand event) {
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
