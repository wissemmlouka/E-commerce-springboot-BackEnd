package com.wissem.ecommerce.controller;


import com.wissem.ecommerce.entity.OrderDetail;
import com.wissem.ecommerce.entity.OrderInput;
import com.wissem.ecommerce.service.OrderDetailsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }
    @PreAuthorize("hasRole('User')")
    @PostMapping({"/placeOrder/{isSingleProductCheckout}"})
    public void placeOrder(@RequestBody OrderInput orderInput,
                           @PathVariable(name = "isSingleProductCheckout")boolean isSingleProductCheckout ){
       orderDetailsService.placeOrder(orderInput,isSingleProductCheckout);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getOrderDetails"})
    public List<OrderDetail> getOrderDetails(){
      return orderDetailsService.getOrderDetails();
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getAllOrderDetails/{status}"})
    public List<OrderDetail> getAllOrdersDetails(@PathVariable(name = "status")String status){
        return orderDetailsService.getAllOrderDetails(status);
    }


    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/orderDelivered/{orderId}"})
    public OrderDetail orderDelivered(@PathVariable(name = "orderId")int orderId){
        return orderDetailsService.orderDelivered(orderId);
    }
}
