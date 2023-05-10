package com.wissem.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderInput {
    private String fullName;
    private String fullAddress;
    private String contactNumber;
    private String alternateNumber;

    private List<OrderProductQuantity> orderProductQuantities;

}
