package com.product.microservice.productartifact.service;

import com.product.microservice.productartifact.dto.MerchantDTO;
import com.product.microservice.productartifact.dto.ProductDTO;
import com.product.microservice.productartifact.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    public ProductDTO getProduct(String productId);
    public ProductEntity saveProduct(ProductDTO productDTO);
    public ProductEntity updateMerchantQuantity(String productId , String merchantId , int quantity);
    public List<ProductDTO> getAllByCategory(String producttype);
    public ProductDTO addMerchant(String productId, MerchantDTO merchantDTO);
    public List<ProductDTO> getAllProducts();
    public double getpriceFromProdMerchantId(String productId, String merchantId);
    public String getMerchantName(String productId, String merchantId);
}
