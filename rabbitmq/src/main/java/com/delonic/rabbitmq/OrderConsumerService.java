package com.delonic.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderConsumerService {

    private final OrderRepository orderRepository;

    // GET MAIL SERVICE 
    private final EmailService emailService;

    public OrderConsumerService(OrderRepository orderRepository, EmailService emailService) {
        this.orderRepository = orderRepository;
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    @Transactional
    public void receiveOrder(@Payload Order order) {
        try {
            System.out.println("Order received from RabbitMQ: " + order);

            // Update status order
            order.setStatus(Order.OrderStatus.PROCESSING);
            orderRepository.save(order);

            // Simulasi proses bisnis
            processOrder(order);

            // Update status setelah selesai diproses
            order.setStatus(Order.OrderStatus.COMPLETED);
            order.setProcessedAt(java.time.LocalDateTime.now());
            orderRepository.save(order);

            System.out.println("Order processed successfully: " + order.getId());

        } catch (Exception e) {
            System.err.println("Error processing order: " + order.getId() + ", Error: " + e.getMessage());

            // Update status jika gagal
            order.setStatus(Order.OrderStatus.FAILED);
            orderRepository.save(order);

            // Bisa ditambahkan logic untuk retry atau dead letter queue
            throw new RuntimeException("Failed to process order", e);
        }
    }


    // CODE UNTUK MEMPROSES DATA DAN MENGIRIMKAN KE EMAIL
    @Async
    private void processOrder(Order order) {
        System.out.println("Processing order: " + order.getId());

        // Kirim email setelah delay (misalnya delay 2000 detik atau 33 menit)
        try {
            Thread.sleep(5000); // 2000 detik = 2.000.000 ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Kirim email setelah delay
        emailService.sendOrderConfirmation(order.getCustomerEmail(), order.getId().toString());
        System.out.println("Order processing completed: " + order.getId());
    }
}
