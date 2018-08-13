package com.coviam.coms.coviam.project.service.Impl;

import com.coviam.coms.coviam.project.entity.ProductEntity;
import com.coviam.coms.coviam.project.productDTO.ProductDTO;
import com.coviam.coms.coviam.project.repository.ProductRepository;
import com.coviam.coms.coviam.project.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("com.coviam.coms.coviam.project.service.Impl.ProductServiceImpl")
public class ProductServiceImpl implements ProductService {

    @Autowired
     ProductRepository productRepository;

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }



    @Override
    public List<ProductDTO> getAll() {
        List<ProductEntity> productEntityList=new ArrayList<ProductEntity>();
        List<ProductDTO> productDTOS=new ArrayList<>();
        Iterable<ProductEntity> productIterable=productRepository.findAll();
        if(productIterable!=null) {
            productIterable.forEach(productEntityList::add);

        }
        for (ProductEntity product:productEntityList) {
            ProductDTO employeeDTO=new ProductDTO();
            BeanUtils.copyProperties(product,employeeDTO);
            productDTOS.add(employeeDTO);
        }

        return productDTOS;
    }

    @Override
    public List<ProductDTO> searchById(String id) {

       // ProductEntity productEntity=productRepository
       ProductEntity productEntity=productRepository.findById(id);
       ProductDTO productDTO=new ProductDTO();
       BeanUtils.copyProperties(productEntity,productDTO);
       List<ProductDTO> productDTOS=new ArrayList<>();
       productDTOS.add(productDTO);
       return productDTOS;
    }

    public List<ProductDTO> searchByName(String productname,Pageable pageable){

        List<ProductEntity> productEntity=productRepository.findByProductname(productname,pageable);
        ProductDTO productDTO=new ProductDTO();
        List<ProductDTO> productDTOS=new ArrayList<>();
        BeanUtils.copyProperties(productEntity,productDTO);
        productDTOS.add(productDTO);
        return  productDTOS;


    }

    public List<ProductDTO> searchByStartingName(String productname){

        ProductEntity productEntity=productRepository.findOne(productname);
        ProductDTO productDTO=new ProductDTO();
        List<ProductDTO> productDTOS=new ArrayList<>();
        BeanUtils.copyProperties(productEntity,productDTO);
        productDTOS.add(productDTO);
        return productDTOS;

    }
}
