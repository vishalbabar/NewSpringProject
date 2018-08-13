package com.coviam.coms.coviam.project.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;


//@EnableSolrRepositories("com.coviam.project.SpringProjectSearch1.repository")
@SolrDocument(solrCoreName = "search")
public class ProductEntity {

    @Field
    private String productid;
    @Field
    private String productname;
    @Field
    private String producttitle;
    @Field
    private String producttype;
    @Field
    private String imagelocation;

    public ProductEntity() {
    }

    public ProductEntity(String productid, String productname, String producttitle,String producttype,String imagelocation){
        this.productid = productid;
        this.productname = productname;
        this.producttitle = producttitle;
        this.producttype=producttype;
        this.imagelocation=imagelocation;
    }

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
        final StringBuilder sb = new StringBuilder("ProductEntity{");
        sb.append("productid='").append(productid).append('\'');
        sb.append(", productname='").append(productname).append('\'');
        sb.append(", Producttitle='").append(producttitle).append('\'');
        sb.append(", producttype='").append(producttype).append('\'');
        sb.append(", imagelocation='").append(imagelocation).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

