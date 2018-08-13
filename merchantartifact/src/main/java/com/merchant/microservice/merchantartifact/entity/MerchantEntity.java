package com.merchant.microservice.merchantartifact.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.annotation.Documented;

@Entity
public class MerchantEntity {
    @Id
    private String merchantId;
    private String merchantName;
    private String location;
    private double merchantRating;
    private int productCount;
    private int allStock;
    private int soldQuantities;


    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getAllStock() {
        return allStock;
    }

    public void setAllStock(int allStock) {
        this.allStock = allStock;
    }

    public int getSoldQuantities() {
        return soldQuantities;
    }

    public void setSoldQuantities(int soldQuantities) {
        this.soldQuantities = soldQuantities;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getMerchantRating() {
        return merchantRating;
    }

    public void setMerchantRating(double merchantRating) {
        this.merchantRating = merchantRating;
    }

    @Override
    public String toString() {
        return "MerchantEntity{" +
                "merchantId='" + merchantId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", location='" + location + '\'' +
                ", rating=" + merchantRating +
                ", productCount=" + productCount +
                ", allStock=" + allStock +
                ", soldQuantities=" + soldQuantities +
                '}';
    }
}
