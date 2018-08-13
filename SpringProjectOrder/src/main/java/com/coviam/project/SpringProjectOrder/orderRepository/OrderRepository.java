package com.coviam.project.SpringProjectOrder.orderRepository;

import com.coviam.project.SpringProjectOrder.orderEntity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity,String> {

}
