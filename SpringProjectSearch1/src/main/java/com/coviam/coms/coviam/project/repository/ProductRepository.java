package com.coviam.coms.coviam.project.repository;

import com.coviam.coms.coviam.project.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;


@Repository
public interface ProductRepository extends SolrCrudRepository<ProductEntity,String> {


    public ProductEntity findById(String id);

    public List<ProductEntity> findByProductname(String productname, Pageable pageable);

    //public List<ProductEntity> findByNameStartingWith(String productName);


}
