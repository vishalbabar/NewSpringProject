package com.coviam.project.SpringProject.cartDTO;

public class CartDTO {
    private String cartid;
    private String userid;
    private String productid;
    private String merchantid;
    private int quantity;


    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CartEntity{");
        sb.append("cartid='").append(cartid).append('\'');
        sb.append(", userid='").append(userid).append('\'');
        sb.append(", productid='").append(productid).append('\'');
        sb.append(", merchantid='").append(merchantid).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }

}
