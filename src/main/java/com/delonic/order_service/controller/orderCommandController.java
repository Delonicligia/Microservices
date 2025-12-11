package com.delonic.order_service.controller;


import com.delonic.order_service.model.orderCommand;
import com.delonic.order_service.services.orderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/command/order")
public class orderCommandController {

    @Autowired
    private orderCommandService orderCommandService;

    // CREATE
    @PostMapping
    public orderCommand createOrder(@RequestBody orderCommand order) {
        return orderCommandService.createOrder(order);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Optional<orderCommand> updateOrder(@PathVariable String id, @RequestBody orderCommand orderDetails) {
        return orderCommandService.updateOrder(id, orderDetails);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable String id) {
        boolean deleted = orderCommandService.deleteOrder(id);
        return deleted ? "Order with id " + id + " deleted!" : "Order not found!";
    }
}