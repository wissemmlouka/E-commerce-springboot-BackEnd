package com.wissem.ecommerce.dao;


import com.wissem.ecommerce.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface OrderDetailsDAO extends JpaRepository<OrderDetail,Integer> {
}
