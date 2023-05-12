package com.wissem.ecommerce.entity;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetails {
    private String orderId;
    private String currency;
    private int amount;
    private String Key_id;
}
