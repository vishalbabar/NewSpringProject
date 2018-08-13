package com.product.microservice.productartifact.repository;

import com.product.microservice.productartifact.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity,String> {
    List<ProductEntity> findByName(final String productName);
    List<ProductEntity> findByType(final String type);
}
