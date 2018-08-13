package com.merchant.microservice.merchantartifact.controller;


import com.merchant.microservice.merchantartifact.dto.MerchantDTO;
import com.merchant.microservice.merchantartifact.entity.MerchantEntity;
import com.merchant.microservice.merchantartifact.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @RequestMapping(method = RequestMethod.GET, value = "/get/{merchantId}")
    public ResponseEntity<MerchantDTO> getMerchant(@PathVariable("merchantId") String merchantId) {
        MerchantDTO merchantDTO =  merchantService.getMerchant(merchantId);
        return new ResponseEntity<MerchantDTO>(merchantDTO, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getallmerchants")
    public ResponseEntity<List<MerchantDTO>> getAllMerchant( ) {

        List<MerchantDTO> merchantDTOList =  merchantService.getAllMerchants();
        return new ResponseEntity<List<MerchantDTO>>(merchantDTOList,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addMerchant")
    public ResponseEntity<String> addMerchant(@RequestBody MerchantDTO merchantDTO){
        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity = merchantService.addMerchant(merchantDTO);
        return new ResponseEntity<String>(merchantEntity.getMerchantId(), HttpStatus.CREATED);
    }


//  todo::  deletemerchant code
//
    @RequestMapping(method = RequestMethod.PUT, value = "/getMerchantRanks")
    public ResponseEntity<Map<String, Double>> rankMerchants(@RequestBody List<String> merchantIdList,
                                                             @RequestBody List<Double> priceList ,
                                                             @RequestBody List<Double> productReviewList
                                                            ){
        Map<String,Double> merchantIdRank = merchantService.rankMerchants(merchantIdList,priceList,productReviewList);
        return new ResponseEntity<Map<String, Double>>(merchantIdRank,HttpStatus.OK);
    }

//    @RequestMapping(method = RequestMethod.PUT, value = "/getMerchantRanks/{merchantIdList}")
//    public ResponseEntity<Map<String, Double>> rankMerchants(@PathVariable List<String> merchantIdList){
////        Map<String,Double> merchantIdRank = merchantService.rankMerchants(merchantIdList,priceList,productReviewList);
////        return new ResponseEntity<Map<String, Double>>(merchantIdRank,HttpStatus.OK);
//        return null;
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "/getMerchantRanks/")
//    public ResponseEntity<Map<String, Double>> rankMerchants(@RequestBody List<String> merchantIdList){
////        Map<String,Double> merchantIdRank = merchantService.rankMerchants(merchantIdList,priceList,productReviewList);
////        return new ResponseEntity<Map<String, Double>>(merchantIdRank,HttpStatus.OK);
//        return null;
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/getMerchantRankstest")
    public ResponseEntity<Map<String, Double>> rankMerchantsdata( ){
        List<String > merchantIdList = new ArrayList<>();
        List<Double > priceList = new ArrayList<>();
        List<Double > productReviewList = new ArrayList<>();
        merchantIdList.add("merchant1");
        merchantIdList.add("merchant2");
        priceList.add(100.0);
        priceList.add(110.0);
        productReviewList.add(4.0);
        productReviewList.add(4.5);


        return  rankMerchants(merchantIdList,priceList,productReviewList);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/updateMerchantQuantity/{merchantId}/{quantity}")
    public ResponseEntity<String> updateMerchantQuantity(@PathVariable String merchantId , @PathVariable int quantity){
        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity = merchantService.updateMerchantStock(merchantId,quantity);
        return new ResponseEntity<String>(merchantEntity.getMerchantId(), HttpStatus.CREATED);
    }

}
