package com.delonic.peminjaman_service.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.delonic.peminjaman_service.model.PeminjamanCommand;
import com.delonic.peminjaman_service.model.PeminjamanQuery;
import com.delonic.peminjaman_service.repository.PeminjamanQueryRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class PeminjamanEventHandler {
    private final PeminjamanQueryRepository peminjamanQueryRepository;


    @KafkaListener(topics = "peminjaman-events", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(PeminjamanCommand event) {
        if(event == null || event.getId() == null || event.getEventType() == null){
            log.warn("Menerima Event yang tidak valid karena id atau eventype tidak ditemukan");
            return;
        }

        PeminjamanCommand.EventType eventType = event.getEventType();
        Long PeminjamanId = event.getId();

        switch(eventType) {
            case CREATED:
            case UPDATED:
                PeminjamanQuery AnggotaDocument = new PeminjamanQuery(event);
                peminjamanQueryRepository.save(AnggotaDocument);
                log.info("Data peminjaman berhasil disimpan/diupdate di database dengan id {}", PeminjamanId);
                break;
            case DELETED:
                peminjamanQueryRepository.deleteById(PeminjamanId);
                log.info("Data peminjaman berhasil dihapus dari database dengan id {}", PeminjamanId);
                break;
            default:
                log.warn("Event tidak dikenali: {}", eventType);
                break;    
        }
    }
}
