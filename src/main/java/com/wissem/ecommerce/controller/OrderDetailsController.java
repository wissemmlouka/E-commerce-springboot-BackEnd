package com.wissem.ecommerce.controller;


import com.wissem.ecommerce.entity.OrderInput;
import com.wissem.ecommerce.service.OrderDetailsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
