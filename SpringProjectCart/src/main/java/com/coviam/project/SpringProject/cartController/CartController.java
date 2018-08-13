package com.coviam.project.SpringProject.cartController;
import com.coviam.project.SpringProject.cartDTO.CartDTO;
import com.coviam.project.SpringProject.cartService.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    @Qualifier("com.coviam.project.SpringProject.cartService.Impl.CartServiceImpl")
    CartService cartService;


    @RequestMapping(method = RequestMethod.GET,value = "/allcartitems")
    public ResponseEntity<List<CartDTO>> getAllCartDetails(){
        List<CartDTO> cartDTOList=cartService.getAllCartDetails();
        return new ResponseEntity<List<CartDTO>>(cartDTOList,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/addtocart")
    public ResponseEntity<String> addtoCart(@RequestBody CartDTO cartDTO){
        String addStatus="Added Successfully to cart";
        CartDTO cartDTOadd=cartService.addToCart(cartDTO);
        return new ResponseEntity<String>(cartDTOadd.getCartid(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/updatetocart/{cartid}/{quantity}")
    public ResponseEntity<String> updateToCart(@PathVariable String cartid,@PathVariable int quantity){
        String updatedStatus="Updated Successfully";
        String updatedNotStatus="Updated Successfully";
        if(cartService.exists(cartid)){

            if(cartService.updateCart(cartid,quantity)) {
                return new ResponseEntity<String>(updatedNotStatus, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<String>(updatedNotStatus, HttpStatus.OK);
            }
        }
        return null;
    }



    @RequestMapping(method = RequestMethod.DELETE,value = "/removefromcart/{cartid}")
    public ResponseEntity<String> deleteFromCart(@PathVariable String cartid){
        String deletedStatus="deleted Successfully from cart";
        String deletedNotStatus="not deleted successfully from cart";
        if(cartService.removeFromCart(cartid)!=false){
            return new ResponseEntity<String>(deletedStatus,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>(deletedNotStatus,HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/removeallfromcart/{userid}")
    public ResponseEntity<String> deleteAllfromCart(@PathVariable String userid){
        String deletedStatus="deleted Successfully from cart";
        String deletedNotStatus="not deleted successfully from cart";
//        if(cartService.removeAllFromCart(userid)){
//            return new ResponseEntity<String>(deletedStatus,HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<String>(deletedNotStatus,HttpStatus.OK);
//        }
        cartService.removeAllFromCart(userid);
        return new ResponseEntity<String>(deletedStatus,HttpStatus.OK);

    }
}
