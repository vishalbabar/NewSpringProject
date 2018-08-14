package com.merchant.microservice.merchantartifact.service;

import com.merchant.microservice.merchantartifact.dto.MerchantDTO;
import com.merchant.microservice.merchantartifact.dto.MerchantProduct;
import com.merchant.microservice.merchantartifact.entity.MerchantEntity;
import com.merchant.microservice.merchantartifact.entity.WrapperMerchantEntity;
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
        List<Double> normalisedList = new ArrayList<>();
        unNormalisedList.forEach(item ->{
            normalisedList.add(item/total);
        });
//        unNormalisedList.replaceAll(item -> item/total);
//        return unNormalisedList;
        return normalisedList;
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
            rankMerchant.add(i,- priceListNormalised.get(i) +
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
    public List<MerchantProduct> rankMerchantsagain(List<String> merchantIdList, List<Double> priceList , List<Double> productReviewList){

        int lengthoflist = merchantIdList.size();
        if(lengthoflist==0){
            List<MerchantProduct> merchantProductList =null;
            return merchantProductList;
        }
        List<Double> numberOfProd = new ArrayList<>();
        List<Double> soldProd =new ArrayList<>();
        List<Double> stockofProd = new ArrayList<>();
        List<Double> merchantRating =new ArrayList<>();
        List<String> merchantLocation = new ArrayList<>();
        List<String> merchantname = new ArrayList<>();

        merchantIdList.forEach(merchantId ->{
            MerchantEntity merchantEntity = getMerchantEntity(merchantId);
            numberOfProd.add((double) merchantEntity.getProductCount());
            soldProd.add((double) merchantEntity.getSoldQuantities());
            stockofProd.add((double) merchantEntity.getAllStock());
            merchantRating.add(merchantEntity.getMerchantRating());
            merchantLocation.add(merchantEntity.getLocation());
            merchantLocation.add(merchantEntity.getMerchantName());
        });


//        double priceTotal = priceList.stream().mapToDouble(Double::doubleValue).sum();
//        priceList.replaceAll(price-> price/priceTotal);
        List<Double> priceListNormalised = convertToNormalisedList(priceList);
        List<Double> productReviewListNormalised = convertToNormalisedList(productReviewList);
        List<Double> numberOfProdNormalised = convertToNormalisedList(numberOfProd);
        List<Double> soldProdNormalised =convertToNormalisedList(soldProd);
        List<Double> stockofProdNormalised = convertToNormalisedList(stockofProd);
        List<Double> merchantRatingNormalised =convertToNormalisedList(merchantRating);

        List<Double> rankMerchant= new ArrayList<>();
        for (int i=0;i<lengthoflist ;i++){
            rankMerchant.add(i,- priceListNormalised.get(i) +
                    productReviewListNormalised.get(i) +
                    numberOfProdNormalised.get(i) +
                    soldProdNormalised.get(i) +
                    stockofProdNormalised.get(i)+
                    merchantRatingNormalised.get(i)
            );

        }
        List<MerchantProduct> merchantProductList = new ArrayList<>();

        for(int i=0;i<lengthoflist;i++){
            int largest = 0;
            for(int j=0;j<lengthoflist-i;j++){
                if(rankMerchant.get(j)>rankMerchant.get(largest)){
                    largest = j;
                }
                MerchantProduct merchantProduct = new MerchantProduct();
                merchantProduct.setLocation(merchantLocation.get(largest));
                merchantLocation.remove(largest);
                merchantProduct.setMerchantName(merchantname.get(largest));
                merchantLocation.remove(largest);
                merchantProduct.setMerchantId(merchantIdList.get(largest));
                merchantLocation.remove(largest);
                merchantProduct.setPrice(priceList.get(largest));
                priceList.remove(largest);
                merchantProductList.add(merchantProduct);
            }

        }
        return merchantProductList;

    }

    @Override
    public List<MerchantProduct> rankMerchantsagainagain(List<WrapperMerchantEntity> wrapperMerchantEntityList){
        int lengthoflist = wrapperMerchantEntityList.size();
        if(lengthoflist==0){
            List<MerchantProduct> merchantProductList =null;
            return merchantProductList;
        }
        List<Double> priceList = new ArrayList<>();
        List<Double> productReviewList =new ArrayList<>();
        List<Double> numberOfProd = new ArrayList<>();
        List<Double> soldProd =new ArrayList<>();
        List<Double> stockofProd = new ArrayList<>();
        List<Double> merchantRating =new ArrayList<>();
        List<String> merchantLocation = new ArrayList<>();
        List<String> merchantname = new ArrayList<>();
        List<String> merchantIdList = new ArrayList<>();

        wrapperMerchantEntityList.forEach(wrapperMerchantEntity ->{
            MerchantEntity merchantEntity = getMerchantEntity(wrapperMerchantEntity.getMerchantId());
            priceList.add(wrapperMerchantEntity.getPrice());
            productReviewList.add(wrapperMerchantEntity.getProductRating());
            numberOfProd.add((double) merchantEntity.getProductCount());
            soldProd.add((double) merchantEntity.getSoldQuantities());
            stockofProd.add((double) merchantEntity.getAllStock());
            merchantRating.add(merchantEntity.getMerchantRating());
            merchantLocation.add(merchantEntity.getLocation());
            merchantname.add(merchantEntity.getMerchantName());
            merchantIdList.add(merchantEntity.getMerchantId());
        });


//        double priceTotal = priceList.stream().mapToDouble(Double::doubleValue).sum();
//        priceList.replaceAll(price-> price/priceTotal);
        List<Double> priceListNormalised = convertToNormalisedList(priceList);
        List<Double> productReviewListNormalised = convertToNormalisedList(productReviewList);
        List<Double> numberOfProdNormalised = convertToNormalisedList(numberOfProd);
        List<Double> soldProdNormalised =convertToNormalisedList(soldProd);
        List<Double> stockofProdNormalised = convertToNormalisedList(stockofProd);
        List<Double> merchantRatingNormalised =convertToNormalisedList(merchantRating);

        List<Double> rankMerchant= new ArrayList<>();
        for (int i=0;i<lengthoflist ;i++){
            rankMerchant.add(i,- priceListNormalised.get(i) +
                    productReviewListNormalised.get(i) +
                    numberOfProdNormalised.get(i) +
                    soldProdNormalised.get(i) +
                    stockofProdNormalised.get(i)+
                    merchantRatingNormalised.get(i)
            );

        }
        List<MerchantProduct> merchantProductList = new ArrayList<>();

        for(int i=0;i<lengthoflist;i++){
            int largest = 0;
            for(int j=0;j<lengthoflist-i;j++) {
                if (rankMerchant.get(j) > rankMerchant.get(largest)) {
                    largest = j;
                }
            }
                MerchantProduct merchantProduct = new MerchantProduct();
                merchantProduct.setLocation(merchantLocation.get(largest));
                merchantLocation.remove(largest);
                merchantProduct.setMerchantName(merchantname.get(largest));
                merchantname.remove(largest);
                merchantProduct.setMerchantId(merchantIdList.get(largest));
                merchantIdList.remove(largest);
                merchantProduct.setPrice(priceList.get(largest));
                priceList.remove(largest);
                merchantProduct.setReview(productReviewList.get(largest));
                productReviewList.remove(largest);
                merchantProductList.add(merchantProduct);


        }
        return merchantProductList;
    }


        @Override
    public MerchantEntity updateMerchantStock(String merchantId , int quantity){
        MerchantEntity merchantEntity = getMerchantEntity(merchantId);
        merchantEntity.setSoldQuantities(quantity+merchantEntity.getSoldQuantities());
        merchantEntity.setAllStock(merchantEntity.getAllStock()-quantity);
        return merchantRepository.save(merchantEntity);
    }



}
