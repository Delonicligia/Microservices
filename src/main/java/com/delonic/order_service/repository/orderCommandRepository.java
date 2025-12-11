package com.delonic.order_service.repository;


import com.delonic.order_service.model.orderCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderCommandRepository extends JpaRepository<orderCommand, String> {

}
