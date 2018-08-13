package com.coviam.coms.coviam.project.productDTO;

import org.apache.solr.client.solrj.beans.Field;

public class ProductDTO {

    private String productid;

    private String productname;

    private String producttitle;

    private String producttype;

    private String imagelocation;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProducttitle() {
        return producttitle;
    }

    public void setProducttitle(String producttitle) {
        producttitle = producttitle;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getImagelocation() {
        return imagelocation;
    }

    public void setImagelocation(String imagelocation) {
        this.imagelocation = imagelocation;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductDTO{");
        sb.append("productid='").append(productid).append('\'');
        sb.append(", productname='").append(productname).append('\'');
        sb.append(", Producttitle='").append(producttitle).append('\'');
        sb.append(", producttype='").append(producttype).append('\'');
        sb.append(", imagelocation='").append(imagelocation).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
