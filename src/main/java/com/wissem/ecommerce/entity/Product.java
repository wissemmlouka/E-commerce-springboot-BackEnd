package com.wissem.ecommerce.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private String productName;
    @Column(length = 2000)
    private String productDescription;
    private Double productDiscountedPrice;
    private Double productActualPrice;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_images",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "productId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "image_id",
                    referencedColumnName = "imageId"
            )
    )
    private Set<ImageModel> productImages;

}
