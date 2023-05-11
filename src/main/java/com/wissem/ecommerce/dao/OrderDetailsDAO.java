package com.wissem.ecommerce.dao;


import com.wissem.ecommerce.entity.OrderDetail;
import com.wissem.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface OrderDetailsDAO extends JpaRepository<OrderDetail,Integer> {
public List<OrderDetail> findByUser(User user);

public List<OrderDetail> findByOrderStatusContainingIgnoreCase(String status);

}
