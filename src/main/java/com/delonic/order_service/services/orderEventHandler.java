package com.delonic.order_service.services;



import com.delonic.order_service.model.orderCommand;
import com.delonic.order_service.model.orderQuery;
import com.delonic.order_service.repository.orderQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class orderEventHandler {

    private final orderQueryRepository orderQueryRepository;

    @KafkaListener(topics = "order", groupId = "order-query-group")
    public void consume(orderCommand event) {
        if (event == null || event.getId() == null || event.getEventType() == null) {
            log.warn("⚠ Event tidak valid, diabaikan.");
            return;
        }

        switch (event.getEventType()) {
            case CREATED:
            case UPDATED:
                // Simpan/update ke MongoDB
                orderQuery orderDoc = new orderQuery(event);
                orderQueryRepository.save(orderDoc);
                log.info("✅ Order tersimpan/terupdate di MongoDB, ID: {}", event.getId());
                break;

            case DELETED:
                orderQueryRepository.deleteById(event.getId());
                log.info("🗑 Order dihapus dari MongoDB, ID: {}", event.getId());
                break;

            default:
                log.warn("⚠ Event tidak dikenali: {}", event.getEventType());
        }
    }
}