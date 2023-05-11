package com.wissem.ecommerce.service;


import com.wissem.ecommerce.config.JwtRequestFilter;
import com.wissem.ecommerce.dao.CartDao;
import com.wissem.ecommerce.dao.ProductDao;
import com.wissem.ecommerce.dao.UserDAO;
import com.wissem.ecommerce.entity.Cart;
import com.wissem.ecommerce.entity.Product;
import com.wissem.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CartDao cartDao;


    public Product addNewProduct(Product product) {
        return productDao.save(product);
    }

    public List<Product> getAllProducts(int pageNumber, String searchKey) {
        Pageable pageable = PageRequest.of(pageNumber, 2);
        if (searchKey.equals("")) {
            return productDao.findAll(pageable);
        } else {
            return productDao.findAllByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey, pageable);
        }
    }


    public void deleteProduct(int productId) {
        productDao.deleteById(productId);
    }

    public Product getProductById(int productId) {
        return productDao.findById(productId).get();
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, int productId) {
        if (isSingleProductCheckout && productId != 0) {
            List<Product> list = new ArrayList<>();
            Product product = productDao.findById(productId).get();
            list.add(product);
            return list;
        } else {
            String userName = JwtRequestFilter.CURRENT_USER;
            User user = userDAO.findById(userName).get();
            List<Cart> carts = cartDao.findByUser(user);

            return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());
        }
    }

}


