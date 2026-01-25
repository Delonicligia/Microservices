package com.delonic.anggota_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.delonic.anggota_service.model.AnggotaCommand;
import com.delonic.anggota_service.model.AnggotaQuery;
import com.delonic.anggota_service.repository.AnggotaQueryRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnggotaEventHandler {

    private final AnggotaQueryRepository anggotaQueryRepository;


    @KafkaListener(topics = "anggota-events", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(AnggotaCommand event) {
        if(event == null || event.getId() == null || event.getEventType() == null){
            log.warn("Menerima Event yang tidak valid karena id atau eventype tidak ditemukan");
            return;
        }

        AnggotaCommand.EventType eventType = event.getEventType();
        Long AnggotaId = event.getId();

        switch(eventType) {
            case CREATED:
            case UPDATED:
                AnggotaQuery AnggotaDocument = new AnggotaQuery(event);
                anggotaQueryRepository.save(AnggotaDocument);
                log.info("Data anggota berhasil disimpan/diupdate di database dengan id {}", AnggotaId);
                break;
            case DELETED:
                anggotaQueryRepository.deleteById(AnggotaId);
                log.info("Data anggota berhasil dihapus dari database dengan id {}", AnggotaId);
                break;
            default:
                log.warn("Event tidak dikenali: {}", eventType);
                break;    
        }
    }
}