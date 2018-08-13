package com.coviam.project.SpringProjectOrder.orderDTO;

import java.util.Date;

public class OrderDTO {

    private String orderid;
    private String userid;
    private String merchantid;
    private String productid;
    private int quantity;
    private String addressid;
    private Date dateofpurchase;
    private int orderprice;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public Date getDateofpurchase() {
        return dateofpurchase;
    }

    public void setDateofpurchase(Date dateofpurchase) {
        this.dateofpurchase = dateofpurchase;
    }

    public int getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(int orderprice) {
        this.orderprice = orderprice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderDTO{");
        sb.append("orderid='").append(orderid).append('\'');
        sb.append(", userid='").append(userid).append('\'');
        sb.append(", merchantid='").append(merchantid).append('\'');
        sb.append(", productid='").append(productid).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", addressid='").append(addressid).append('\'');
        sb.append(", dateofpurchase=").append(dateofpurchase);
        sb.append(", orderprice=").append(orderprice);
        sb.append('}');
        return sb.toString();
    }
}
