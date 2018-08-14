package com.product.microservice.productartifact.dto;

import com.product.microservice.productartifact.entity.MerchantEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDTO {

    private String productId;
    private String name;
    private String description;
    private String imagepath;
    private String type;

    private Map<String, String> attributes = new HashMap<String, String>();

    private  List<MerchantDTO> merchantEntityList;


    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public List<MerchantDTO> getMerchantEntityList() {
        return merchantEntityList;
    }

    public void setMerchantEntityList(List<MerchantDTO> merchantEntityList) {
        this.merchantEntityList = merchantEntityList;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", attributes=" + attributes +
                ", merchantEntityList=" + merchantEntityList +
                '}';
    }
}
