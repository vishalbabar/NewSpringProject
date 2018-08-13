package com.coviam.project.SpringProjectOrder.orderService;

import com.coviam.project.SpringProjectOrder.orderDTO.OrderDTO;
import org.hibernate.criterion.Order;

import java.util.List;

public interface OrderService {
    public List<OrderDTO> allorderitems();
    public OrderDTO addToCart(OrderDTO cartDTO);


}
