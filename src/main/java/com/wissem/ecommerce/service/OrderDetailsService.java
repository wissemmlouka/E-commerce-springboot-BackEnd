package com.wissem.ecommerce.service;


import com.wissem.ecommerce.config.JwtRequestFilter;
import com.wissem.ecommerce.dao.OrderDetailsDAO;
import com.wissem.ecommerce.dao.ProductDao;
import com.wissem.ecommerce.dao.UserDAO;
import com.wissem.ecommerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    private static final String ORDER_PLACED = "placed";
    @Autowired
    private OrderDetailsDAO orderDetailsDAO;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDAO userDAO;


    public void placeOrder(OrderInput orderInput) {
        List<OrderProductQuantity> orderProductQuantities = orderInput.getOrderProductQuantities();
        for (OrderProductQuantity o : orderProductQuantities) {

            Product product = productDao.findById(o.getProductId()).get();
            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user = userDAO.findById(currentUser).get();
            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateNumber(),
                    ORDER_PLACED,
                    product.getProductDiscountedPrice() * o.getQuantity(),
                    product,
                    user

            );

             orderDetailsDAO.save(orderDetail);
        }

    }
}
