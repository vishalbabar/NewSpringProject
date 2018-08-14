package com.merchant.microservice.merchantartifact.service;

import com.merchant.microservice.merchantartifact.dto.MerchantDTO;
import com.merchant.microservice.merchantartifact.dto.MerchantProduct;
import com.merchant.microservice.merchantartifact.entity.MerchantEntity;
import com.merchant.microservice.merchantartifact.entity.WrapperMerchantEntity;


import java.util.List;
import java.util.Map;

public interface MerchantService {

    List<MerchantDTO> getAllMerchants();
    MerchantDTO getMerchant(String merchantId);
    public MerchantEntity addMerchant(MerchantDTO merchantDTO);
    public void deleteMerchant(String id );
    public  List<MerchantDTO> findByLocation(String location);

    public  MerchantEntity updateMerchantStock(String merchantId, int quantity);
    public Map<String, Double> rankMerchants(List<String> merchantIdList, List<Double> priceList , List<Double> productReviewList);
    public List<MerchantProduct> rankMerchantsagain(List<String> merchantIdList, List<Double> priceList , List<Double> productReviewList);

    public  List<MerchantProduct> rankMerchantsagainagain(List<WrapperMerchantEntity> wrapperMerchantEntityList);
}
