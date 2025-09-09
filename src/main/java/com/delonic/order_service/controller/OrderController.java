package com.delonic.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.delonic.order_service.model.Order;
import com.delonic.order_service.services.OrderService;
import com.delonic.order_service.vo.ResponseTemplate;

@RestController
@RequestMapping("/api/orders") // pakai plural biar konsisten
public class OrderController {

    @Autowired
    private OrderService orderService;

    // GET semua order biasa
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // GET semua order dengan detail produk & pelanggan
    @GetMapping("/details")
    public ResponseEntity<List<ResponseTemplate>> getAllOrderDetails() {
        List<ResponseTemplate> response = orderService.getAllOrdersWithDetails();
        return ResponseEntity.ok(response);
    }

    // GET 1 order by id
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    // GET 1 order dengan detail produk & pelanggan
    @GetMapping("/{id}/details")
    public ResponseEntity<ResponseTemplate> getOrderWithDetails(@PathVariable Long id) {
        ResponseTemplate response = orderService.getOrderWithDetails(id);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    // POST buat order baru
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    // DELETE order
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
