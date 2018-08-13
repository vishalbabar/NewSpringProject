package com.merchant.microservice.merchantartifact;

import com.merchant.microservice.merchantartifact.dto.MerchantDTO;
import com.merchant.microservice.merchantartifact.entity.MerchantEntity;
import com.merchant.microservice.merchantartifact.repository.MerchantRepository;
import com.merchant.microservice.merchantartifact.service.MerchantServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MerchantServiceTestImpl {

    @InjectMocks
    MerchantServiceImpl merchantServiceImpl;

    @Mock
    MerchantRepository merchantRepository;

    @Test
    public void getMerchant_success(){    //failed test unit
        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity.setMerchantId("merchant2");
        merchantEntity.setMerchantName("merchantname1");
        merchantEntity.setAllStock(40);
        merchantEntity.setSoldQuantities(30);
        merchantEntity.setLocation("location1");
        merchantEntity.setMerchantRating(4.0);
        merchantEntity.setProductCount(5);
        when(this.merchantRepository.findById("merchant2")).thenReturn(Optional.of(merchantEntity));
        MerchantDTO merchantDTO1 = merchantServiceImpl.getMerchant("merchant2");
        MerchantEntity merchantEntity1 = new MerchantEntity();
        BeanUtils.copyProperties(merchantDTO1,merchantEntity1);
        assertEquals(merchantEntity1.getAllStock(), 40, 0);
    }



    @Before
    public  void setup(){
        MockitoAnnotations.initMocks(this);
    }
}
