package com.coviam.project.SpringProject.cartService.Impl;

import com.coviam.project.SpringProject.cartDTO.CartDTO;
import com.coviam.project.SpringProject.cartEntity.CartEntity;
import com.coviam.project.SpringProject.cartRepository.CartRepository;
import com.coviam.project.SpringProject.cartService.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("com.coviam.project.SpringProject.cartService.Impl.CartServiceImpl")
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public List<CartDTO> getAllCartDetails() {

        List<CartEntity> cartEntityList = new ArrayList<>();
        List<CartDTO> cartDTOList = new ArrayList<>();
        Iterable<CartEntity> cartEntities = cartRepository.findAll();
        if(cartEntities!=null) {
            cartEntities.forEach(cartEntityList::add);
        }

        for (CartEntity cartEntity1 : cartEntityList) {
            CartDTO cartDTO = new CartDTO();
            BeanUtils.copyProperties(cartEntity1, cartDTO);
            cartDTOList.add(cartDTO);
        }
        return cartDTOList;
    }

    @Override
    public CartDTO addToCart(CartDTO cartDTO) {
        CartEntity cartEntity = new CartEntity();
        BeanUtils.copyProperties(cartDTO, cartEntity);
        CartEntity cartEntityadd = cartRepository.save(cartEntity);
        CartDTO cartDTOadd = new CartDTO();
        BeanUtils.copyProperties(cartEntityadd, cartDTOadd);
        return cartDTOadd;
    }

    @Override
    public Boolean updateCart(String cartid,int quantity) {
        CartDTO cartDTO = new CartDTO();
        List<CartEntity> cartEntityList = new ArrayList<>();
        CartEntity cartEntity = cartRepository.findOne(cartid);
        cartEntity.setQuantity(quantity);
        CartEntity cartEntityupdate = cartRepository.save(cartEntity);
        if((cartEntityupdate.getQuantity()==cartEntity.getQuantity())&&(cartEntityupdate.getCartid()==cartEntity.getCartid())){
            return true;
        }
        else {
            return false;
        }
    }



    @Override
    public Boolean removeFromCart(String cartid) {

        CartEntity cartEntity = cartRepository.findOne(cartid);
        if (cartEntity != null) {
            cartRepository.delete(cartEntity);
            return true;
        }
        return false;

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void removeAllFromCart(String userid) {
//          List<CartEntity> cartEntityList=cartRepository.findByUserId(userid);
//          if(cartEntityList==null) {
//              return false;
//          }
//          cartRepository.delete(cartEntityList);
//return true;

//  if (cartRepository.deleteByUserId(userid)){
//      return true;
//  }
//  return false;
//    }
        cartRepository.deleteByUserid(userid);

    }

    public Boolean exists(String userid) {

        return cartRepository.exists(userid);

    }
}
