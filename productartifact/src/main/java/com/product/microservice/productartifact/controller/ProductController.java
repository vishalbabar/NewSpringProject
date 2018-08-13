package com.product.microservice.productartifact.controller;


import com.product.microservice.productartifact.dto.MerchantDTO;
import com.product.microservice.productartifact.dto.ProductDTO;
import com.product.microservice.productartifact.entity.ProductEntity;
import com.product.microservice.productartifact.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(method = RequestMethod.GET, value = "/get/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("productId") String productId) {
        ProductDTO productDTO =  productService.getProduct(productId);
//
////       todo:: if merchant microservice is working
//        List<String> merchantIdList = new ArrayList<>();
//        List<Double> priceList = new ArrayList<>();
//        List<Double> productReviewList = new ArrayList<>();
//
//        productDTO.getMerchantEntityList().forEach(merchant ->{
//            merchantIdList.add(merchant.getMerchantId());
//            priceList.add(merchant.getPrice());
//            productReviewList.add(merchant.getProductRating());
//        } );
//
//        // todo::call merchant microservice to display results.



        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO){
        ProductEntity productEntity = new ProductEntity();
        productEntity = productService.saveProduct(productDTO);
        return new ResponseEntity<String>(productEntity.getProductId(), HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateQuantity/{productId}/{merchantId}/{quantity}")
    public ResponseEntity<String> updateMerchantQuantity(@PathVariable String productId ,@PathVariable String merchantId , @PathVariable int quantity){
        ProductEntity productEntity = new ProductEntity();
        productEntity = productService.updateMerchantQuantity(productId,merchantId,quantity);

        //update merchant service quantity

        return new ResponseEntity<String>(productEntity.getProductId(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/addmerchant/{productId}")
    public ResponseEntity<ProductDTO> addMerchantInProduct(@PathVariable String productId,@RequestBody MerchantDTO merchantDTO){
        ProductDTO productDTO = productService.addMerchant(productId,merchantDTO);
        return new ResponseEntity<ProductDTO>(productDTO,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getbycategory/{type}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String type){
        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList = productService.getAllByCategory(type);
        return new ResponseEntity<List<ProductDTO>>(productDTOList, HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/getAllProducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList = productService.getAllProducts();
        return new ResponseEntity<List<ProductDTO>>(productDTOList, HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/getmerchantprice/{productId}/{merchantId}")
    public ResponseEntity<Double> getpriceFromProdMerchantId(@PathVariable String productId, @PathVariable String merchantId){
        Double price = productService.getpriceFromProdMerchantId(productId,merchantId);
        return new ResponseEntity<Double>(price,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getmerchantname/{productId}/{merchantId}")
    public ResponseEntity<String> getMerchantName(@PathVariable String productId, @PathVariable String merchantId){
        String merchantName = productService.getMerchantName(productId,merchantId);
        return new ResponseEntity<String>(merchantName,HttpStatus.OK);
    }







}
