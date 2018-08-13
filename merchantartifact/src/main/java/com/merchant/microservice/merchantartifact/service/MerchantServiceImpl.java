package com.merchant.microservice.merchantartifact.service;

import com.merchant.microservice.merchantartifact.dto.MerchantDTO;
import com.merchant.microservice.merchantartifact.entity.MerchantEntity;
import com.merchant.microservice.merchantartifact.repository.MerchantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    public MerchantRepository merchantRepository;

    @Override
    public List<MerchantDTO> getAllMerchants(){
        List<MerchantDTO> merchantDTOList = new ArrayList<>();
         merchantRepository.findAll().forEach(merchantEntity -> {
             MerchantDTO merchantDTO = new MerchantDTO();
             BeanUtils.copyProperties(merchantEntity,merchantDTO);
             merchantDTOList.add(merchantDTO);
         });
         return merchantDTOList;
    }

    private MerchantEntity getMerchantEntity(String merchantId){
        MerchantEntity merchantEntity = merchantRepository.findById(merchantId).get();
        return merchantEntity;
    }

    @Override
    public MerchantDTO getMerchant(String merchantId){
        MerchantDTO merchantDTO = new MerchantDTO();
        MerchantEntity merchantEntity = getMerchantEntity(merchantId);
        BeanUtils.copyProperties(merchantEntity,merchantDTO);
        return merchantDTO;
    }

    @Override
    public MerchantEntity addMerchant(MerchantDTO merchantDTO) {
        MerchantEntity merchantEntity = new MerchantEntity();
        BeanUtils.copyProperties(merchantDTO,merchantEntity);
        return merchantRepository.save(merchantEntity);
    }

    @Override
    public void deleteMerchant(String id) {
         merchantRepository.deleteById(id);
    }

    @Override
    public List<MerchantDTO> findByLocation(String location) {
        List<MerchantDTO> merchantDTOList = new ArrayList<>();
        merchantRepository.findByLocation(location ).forEach(merchantEntity -> {
            MerchantDTO merchantDTO = new MerchantDTO();
            BeanUtils.copyProperties(merchantEntity,merchantDTO);
            merchantDTOList.add(merchantDTO);
        });
        return  merchantDTOList;
    }



    private List<Double> convertToNormalisedList(List<Double> unNormalisedList){
        double total = unNormalisedList.stream().mapToDouble(Double::doubleValue).sum();
        unNormalisedList.replaceAll(item -> item/total);
        return unNormalisedList;
    }
    @Override
    public Map<String, Double> rankMerchants(List<String> merchantIdList, List<Double> priceList , List<Double> productReviewList){

        List<Double> numberOfProd = new ArrayList<>();
        List<Double> soldProd =new ArrayList<>();
        List<Double> stockofProd = new ArrayList<>();
        List<Double> merchantRating =new ArrayList<>();

        merchantIdList.forEach(merchantId ->{
            MerchantEntity merchantEntity = getMerchantEntity(merchantId);
            numberOfProd.add((double) merchantEntity.getProductCount());
            soldProd.add((double) merchantEntity.getSoldQuantities());
            stockofProd.add((double) merchantEntity.getAllStock());
            merchantRating.add(merchantEntity.getMerchantRating());
        });


//        double priceTotal = priceList.stream().mapToDouble(Double::doubleValue).sum();
//        priceList.replaceAll(price-> price/priceTotal);
        List<Double> priceListNormalised = convertToNormalisedList(priceList);
        List<Double> productReviewListNormalised = convertToNormalisedList(productReviewList);
        List<Double> numberOfProdNormalised = convertToNormalisedList(numberOfProd);
        List<Double> soldProdNormalised =convertToNormalisedList(soldProd);
        List<Double> stockofProdNormalised = convertToNormalisedList(stockofProd);
        List<Double> merchantRatingNormalised =convertToNormalisedList(merchantRating);

        Map<String,Double> merchantIdRank = new HashMap<String,Double>();
        List<Double> rankMerchant= new ArrayList<>();
        for (int i=0;i<merchantIdList.size() ;i++){
            rankMerchant.add(i,priceListNormalised.get(i) +
                    productReviewListNormalised.get(i) +
                    numberOfProdNormalised.get(i) +
                    soldProdNormalised.get(i) +
                    stockofProdNormalised.get(i)+
                    merchantRatingNormalised.get(i)
            );
            merchantIdRank.put(merchantIdList.get(i),rankMerchant.get(i));
        }
        return merchantIdRank;
    }

        @Override
    public MerchantEntity updateMerchantStock(String merchantId , int quantity){
        MerchantEntity merchantEntity = getMerchantEntity(merchantId);
        merchantEntity.setSoldQuantities(quantity+merchantEntity.getSoldQuantities());
        merchantEntity.setAllStock(merchantEntity.getAllStock()-quantity);
        return merchantRepository.save(merchantEntity);
    }



}
