package com.coviam.project.SpringProject.cartService;

import com.coviam.project.SpringProject.cartDTO.CartDTO;

import java.util.List;

public interface CartService {
    public List<CartDTO> getAllCartDetails();
    public CartDTO addToCart(CartDTO cartDTO);
    public Boolean updateCart(String cartid,int quantity);
    public Boolean removeFromCart(String cartid);
    public void removeAllFromCart(String userid);
    public Boolean exists(String userid);
}
