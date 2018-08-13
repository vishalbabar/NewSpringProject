package com.coviam.project.SpringProject.service;

import com.coviam.project.SpringProject.cartDTO.CartDTO;
import com.coviam.project.SpringProject.cartEntity.CartEntity;
import com.coviam.project.SpringProject.cartRepository.CartRepository;
import com.coviam.project.SpringProject.cartService.Impl.CartServiceImpl;
import javafx.beans.binding.When;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class CartServiceImplTest {

    @InjectMocks
    CartServiceImpl cartServiceImpl;

    @Mock
    CartRepository cartRepositoryImpl;

    @Test
    public void findAllSuccess(){
        List<CartEntity> cartEntities=new ArrayList<>();
        CartEntity cartEntity=new CartEntity();
        cartEntity.setCartid("cart1");
        cartEntity.setProductid("prod1");
        cartEntity.setMerchantid("merch1");
        cartEntity.setQuantity(2);
        cartEntity.setUserid("user1");

cartEntities.add(cartEntity);
        when(this.cartRepositoryImpl.findAll()).thenReturn(cartEntities);
        List<CartDTO> cartDTOList=cartServiceImpl.getAllCartDetails();
        assertEquals(cartDTOList.size(),cartEntities.size());


    }



}
