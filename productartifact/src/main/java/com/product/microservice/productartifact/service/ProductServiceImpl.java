package com.product.microservice.productartifact.service;

import com.product.microservice.productartifact.dto.MerchantDTO;
import com.product.microservice.productartifact.dto.ProductDTO;
import com.product.microservice.productartifact.entity.MerchantEntity;
import com.product.microservice.productartifact.entity.ProductEntity;
import com.product.microservice.productartifact.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private  ProductRepository productRepository;

    @Override
    public ProductDTO getProduct(String productId){
        ProductDTO productDTO = new ProductDTO();
        ProductEntity productEntity = getProductEntity(productId);
        BeanUtils.copyProperties(productEntity,productDTO);
        return productDTO;
    }

    private ProductEntity getProductEntity(String productId){
        ProductEntity productEntity = productRepository.findById(productId).get();
        return productEntity;
    }


    @Override
    public ProductEntity saveProduct(ProductDTO productDTO){
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDTO,productEntity);
        return productRepository.save(productEntity);

    }

    @Override
    public ProductEntity updateMerchantQuantity(String productId , String merchantId , int quantity){
        ProductEntity productEntity = new ProductEntity();
        productEntity = getProductEntity(productId);
        productEntity.getMerchantEntityList().forEach(merchantEntity -> {
                if(merchantEntity.getMerchantId().equals(merchantId)){
                    merchantEntity.setSoldQuantity(merchantEntity.getSoldQuantity()+quantity);
                    merchantEntity.setStock(merchantEntity.getStock()-quantity);
                }
        });
        return productRepository.save(productEntity);

    }

    @Override
    public ProductDTO addMerchant(String productId, MerchantDTO merchantDTO){
        ProductEntity productEntity = new ProductEntity();
        ProductDTO productDTO =new ProductDTO();
        productEntity = getProductEntity(productId);
        MerchantEntity merchantEntity = new MerchantEntity();
        BeanUtils.copyProperties(merchantDTO,merchantEntity);
        productEntity.getMerchantEntityList().add(merchantEntity);
        ProductEntity productEntity1 =  productRepository.save(productEntity);
        BeanUtils.copyProperties(productEntity1,productDTO);
        return productDTO;
    }

    @Override
    public List<ProductDTO> getAllByCategory(String producttype){
        List<ProductDTO> productDTOList = new ArrayList<>();
        productRepository.findByType(producttype).forEach(productEntity -> {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(productEntity,productDTO);
            productDTOList.add(productDTO);
        });
        return productDTOList;
    }

}
