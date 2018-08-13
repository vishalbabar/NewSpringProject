package com.coviam.coms.coviam.project.service;

import com.coviam.coms.coviam.project.entity.ProductEntity;
import com.coviam.coms.coviam.project.productDTO.ProductDTO;
//import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public interface ProductService {
    public void deleteAll();
    public List<ProductDTO> getAll();

    public List<ProductDTO> searchByName(String id, Pageable pageable);


    List<ProductDTO> searchById(String id);
    List<ProductDTO> searchByStartingName(String productName);
}
