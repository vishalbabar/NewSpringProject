package com.coviam.project.SpringProject.cartRepository;

import com.coviam.project.SpringProject.cartEntity.CartEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CartRepository extends CrudRepository<CartEntity,String> {

    public List<CartEntity> findByUserid(String userid);
    public void deleteByUserid(String userid);

}
