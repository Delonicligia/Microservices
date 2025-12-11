package com.delonic.order_service.services;


import com.delonic.order_service.model.orderCommand;
import com.delonic.order_service.repository.orderCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.delonic.order_service.EventType;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class orderCommandService {

    private final orderCommandRepository orderCommandRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public orderCommandService(orderCommandRepository orderCommandRepository,
            KafkaTemplate<String, Object> kafkaTemplate) {
        this.orderCommandRepository = orderCommandRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    // CREATE
    public orderCommand createOrder(orderCommand order) {
        order.setId(UUID.randomUUID().toString());
        order.setCreatedAt(LocalDateTime.now());
        orderCommand savedOrder = orderCommandRepository.save(order);

        // Atur eventType DI DALAM objek
        savedOrder.setEventType(EventType.CREATED);
        // Kirim objek yang sudah lengkap
        kafkaTemplate.send("orders", savedOrder.getId(), savedOrder);

        return savedOrder;
    }

    // UPDATE
    public Optional<orderCommand> updateOrder(String id, orderCommand newData) {
        Optional<orderCommand> existing = orderCommandRepository.findById(id);
        if (existing.isPresent()) {
            orderCommand order = existing.get();
            // ... (kode untuk copy data dari newData ke order)
            orderCommand updated = orderCommandRepository.save(order);

            updated.setEventType(EventType.UPDATED);
            kafkaTemplate.send("orders", updated.getId(), updated);
            return Optional.of(updated);
        }
        return Optional.empty();
    }

    // DELETE
    public boolean deleteOrder(String id) {
        Optional<orderCommand> existing = orderCommandRepository.findById(id);
        if (existing.isPresent()) {
            orderCommand orderToDelete = existing.get();
            orderCommandRepository.deleteById(id);

            orderToDelete.setEventType(EventType.DELETED);
            kafkaTemplate.send("orders", orderToDelete.getId(), orderToDelete);
            return true;
        }
        return false;
    }
}