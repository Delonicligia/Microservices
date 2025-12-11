package com.delonic.order_service.services;


import com.delonic.order_service.model.orderQuery;
import com.delonic.order_service.repository.orderQueryRepository;
import com.delonic.order_service.vo.pelanggan;
import com.delonic.order_service.vo.produk;
import com.delonic.order_service.vo.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class orderQueryService {

    private final orderQueryRepository orderQueryRepository;
    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    @Autowired
    public orderQueryService(orderQueryRepository orderQueryRepository, DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.orderQueryRepository = orderQueryRepository;
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    // GET ALL
    public List<orderQuery> getAllOrders() {
        return orderQueryRepository.findAll();
    }

    // GET BY ID
    public Optional<orderQuery> getOrderById(String id) {
        return orderQueryRepository.findById(id);
    }

    // GET ORDER + produk + pelanggan
    public ResponseTemplate getOrderWithDetails(String id) {
        Optional<orderQuery> optionalOrder = orderQueryRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            return null; // Bisa juga lempar exception
        }

        orderQuery order = optionalOrder.get();

        // Ambil service produk
        List<ServiceInstance> produkInstances = discoveryClient.getInstances("PRODUK-SERVICE");
        if (produkInstances.isEmpty()) {
            throw new RuntimeException("Service PRODUK tidak ditemukan");
        }
        String produkUrl = produkInstances.get(0).getUri().toString() + "/api/produk/" + order.getProdukId();
        produk produk = restTemplate.getForObject(produkUrl, produk.class);

        // Ambil service pelanggan
        List<ServiceInstance> pelangganInstances = discoveryClient.getInstances("pelanggan-SERVICE");
        if (pelangganInstances.isEmpty()) {
            throw new RuntimeException("Service pelanggan tidak ditemukan");
        }
        String pelangganUrl = pelangganInstances.get(0).getUri().toString() + "/api/pelanggan/" + order.getPelangganId();
        pelanggan pelanggan = restTemplate.getForObject(pelangganUrl, pelanggan.class);

        // Build response
        ResponseTemplate vo = new ResponseTemplate();
        vo.setOrder(order);
        vo.setProduk(produk);
        vo.setPelanggan(pelanggan);

        return vo;
    }
}