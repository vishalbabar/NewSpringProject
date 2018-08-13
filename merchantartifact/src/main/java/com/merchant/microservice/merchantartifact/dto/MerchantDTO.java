package com.merchant.microservice.merchantartifact.dto;

public class MerchantDTO {

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

    public double getMerchantRating() {
        return merchantRating;
    }

    public void setMerchantRating(double merchantRating) {
        this.merchantRating = merchantRating;
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


    @Override
    public String toString() {
        return "MerchantDTO{" +
                "merchantId='" + merchantId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", location='" + location + '\'' +
                ", merchantRating=" + merchantRating +
                ", productCount=" + productCount +
                ", allStock=" + allStock +
                ", soldQuantities=" + soldQuantities +
                '}';
    }
}
