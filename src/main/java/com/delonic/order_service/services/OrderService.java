package com.delonic.order_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.delonic.order_service.model.Order;
import com.delonic.order_service.repository.OrderRepository;
import com.delonic.order_service.vo.pelanggan;
import com.delonic.order_service.vo.produk;
import com.delonic.order_service.vo.ResponseTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Ambil semua order
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Ambil order by ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<ResponseTemplate> getAllOrdersWithDetails() {
    List<Order> orders = orderRepository.findAll();
    List<ResponseTemplate> responseList = new ArrayList<>();

    for (Order order : orders) {
        produk produk = restTemplate.getForObject(
                "http://localhost:8081/api/produk/" + order.getProdukId(),
                produk.class
        );

        pelanggan pelanggan = restTemplate.getForObject(
                "http://localhost:8082/api/pelanggan/" + order.getPelangganId(),
                pelanggan.class
        );

        ResponseTemplate vo = new ResponseTemplate();
        vo.setOrder(order);
        vo.setProduk(produk);
        vo.setPelanggan(pelanggan);

        responseList.add(vo);
    }

    return responseList;
}


    // Ambil order beserta produk & pelanggan
    public ResponseTemplate getOrderWithDetails(Long id) {
        Order order = getOrderById(id);
        if (order == null) return null;

        produk produk = restTemplate.getForObject(
                "http://localhost:8081/api/produk/" + order.getProdukId(),
                produk.class
        );

        pelanggan pelanggan = restTemplate.getForObject(
                "http://localhost:8082/api/pelanggan/" + order.getPelangganId(),
                pelanggan.class
        );

        ResponseTemplate vo = new ResponseTemplate();
        vo.setOrder(order);
        vo.setProduk(produk);
        vo.setPelanggan(pelanggan);

        return vo;
    }

    // Buat order baru
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // Hapus order
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
