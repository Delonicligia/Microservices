package com.delonic.order_service.repository;

import com.delonic.order_service.model.orderQuery;
import org.springframework.data.mongodb.repository.MongoRepository; // PERBAIKAN: Import MongoRepository
import org.springframework.stereotype.Repository;

@Repository
public interface orderQueryRepository extends MongoRepository<orderQuery, String>  {
    
}
