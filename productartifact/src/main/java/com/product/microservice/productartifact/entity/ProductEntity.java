package com.product.microservice.productartifact.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection ="product")
public class ProductEntity {

    @Id
    private String productId;
    private String name;
    private String description;
    private String type;
    private String rating;
    private Map<String, String> attributes = new HashMap<String, String>();

    private List<MerchantEntity> merchantEntityList ;

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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<MerchantEntity> getMerchantEntityList() {
        return merchantEntityList;
    }

    public void setMerchantEntityList(List<MerchantEntity> merchantEntityList) {
        this.merchantEntityList = merchantEntityList;
    }


    @Override
    public String toString() {
        return "ProductEntity{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", rating='" + rating + '\'' +
                ", merchantEntityList=" + merchantEntityList +
                '}';
    }
}
