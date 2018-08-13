package com.merchant.microservice.merchantartifact.repository;

import com.merchant.microservice.merchantartifact.entity.MerchantEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends CrudRepository<MerchantEntity,String> {
        public List<MerchantEntity> findByLocation(String location);
}
