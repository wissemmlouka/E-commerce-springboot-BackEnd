package com.wissem.ecommerce.service;


import com.wissem.ecommerce.dao.ProductDao;
import com.wissem.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;


    public Product addNewProduct(Product product) {
       return productDao.save(product);
    }
}
