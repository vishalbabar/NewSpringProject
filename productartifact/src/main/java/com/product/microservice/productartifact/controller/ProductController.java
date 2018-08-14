package com.product.microservice.productartifact.controller;


import com.product.microservice.productartifact.dto.MerchantDTO;
import com.product.microservice.productartifact.dto.MerchantProductWrapper;
import com.product.microservice.productartifact.dto.ProductDTO;
import com.product.microservice.productartifact.entity.MerchantEntity;
import com.product.microservice.productartifact.entity.ProductEntity;
import com.product.microservice.productartifact.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @RequestMapping(method = RequestMethod.GET, value = "/getmerchantentity/{productId}")
    public ResponseEntity<List<MerchantEntity>> getMerchantEntityFromProductId(@PathVariable String productId){
        List<MerchantEntity> merchantEntityList = productService.getMerchantEntityFromProductId(productId);
        return new ResponseEntity<List<MerchantEntity>>(merchantEntityList,HttpStatus.OK);
    }



//
    @RequestMapping(method = RequestMethod.GET, value = "/getmerchantwrapperlist/{productId}")
    public ResponseEntity<List<MerchantProductWrapper>> getMerchantwrapperFromProductService(@PathVariable String productId){
        RestTemplate restTemplate = new RestTemplate();
        List<MerchantEntity> merchantEntityList = productService.getMerchantEntityFromProductId(productId);
        List<MerchantProductWrapper> merchantProductWrapperList = new ArrayList<>();
        String urlPost = "http://localhost:8081/merchant/getMerchantRanks";
        merchantProductWrapperList = restTemplate.postForObject(urlPost,merchantEntityList,List.class);
        return new ResponseEntity<List<MerchantProductWrapper>>(merchantProductWrapperList,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getmerchantidlist/{productId}")
    public ResponseEntity<List<String>> getMerchantIdList(@PathVariable String productId){
        List<String> merchantIdList = productService.getMerchantIdList(productId);
        return new ResponseEntity<List<String>>(merchantIdList,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getmerchantpricelist/{productId}")
    public ResponseEntity<List<Double>> getMerchantPriceList(@PathVariable String productId){
        List<Double> merchantPriceList = productService.getMerchantPriceList(productId);
        return new ResponseEntity<List<Double>>(merchantPriceList,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getmerchantreviewlist/{productId}")
    public ResponseEntity<List<Double>> getMerchantReviewList(@PathVariable String productId){

        List<Double> merchantReviewList = productService.getMerchantReviewList(productId);
        return new ResponseEntity<List<Double>>(merchantReviewList,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getimagepath/{productId}")
    public ResponseEntity<String> getImagePath(@PathVariable  String productId){
        return  new ResponseEntity<String>(productService.getImagePath(productId),HttpStatus.OK);
    }




}
