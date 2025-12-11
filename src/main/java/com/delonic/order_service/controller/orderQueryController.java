package com.delonic.order_service.controller;


import com.delonic.order_service.model.orderQuery;
import com.delonic.order_service.services.orderQueryService;
import com.delonic.order_service.vo.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/query/order")
public class orderQueryController {

    @Autowired
    private orderQueryService orderQueryService;

    // GET ALL
    @GetMapping
    public List<orderQuery> getAllOrders() {
        return orderQueryService.getAllOrders();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Optional<orderQuery> getOrderById(@PathVariable String id) {
        return orderQueryService.getOrderById(id);
    }

    // GET ORDER + PRODUCT + PELANGGAN
    @GetMapping("/{id}/details")
    public ResponseTemplate getOrderWithDetails(@PathVariable String  id) {
        return orderQueryService.getOrderWithDetails(id);
    }
}