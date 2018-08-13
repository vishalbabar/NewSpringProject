package com.coviam.project.SpringProjectOrder.orderService;

import com.coviam.project.SpringProjectOrder.orderDTO.OrderDTO;
import com.coviam.project.SpringProjectOrder.orderEntity.OrderEntity;
import com.coviam.project.SpringProjectOrder.orderRepository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("com.coviam.project.SpringProjectOrder.orderService.OrderServiceImpl")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Override
    public List<OrderDTO> allorderitems() {


        List<OrderEntity> orderEntityList = new ArrayList<>();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        Iterable<OrderEntity> orderEntities = orderRepository.findAll();
        orderEntities.forEach(orderEntityList::add);

        for (OrderEntity orderEntity1 : orderEntityList) {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderEntity1, orderDTO);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }


    @Override
    public OrderDTO addToCart(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderDTO, orderEntity);
        OrderEntity orderEntityadd = orderRepository.save(orderEntity);
        OrderDTO orderDTOadd = new OrderDTO();
        BeanUtils.copyProperties(orderEntityadd, orderDTOadd);
        return orderDTOadd;
    }
}
