package com.wissem.ecommerce.service;


import com.wissem.ecommerce.config.JwtRequestFilter;
import com.wissem.ecommerce.dao.CartDao;
import com.wissem.ecommerce.dao.ProductDao;
import com.wissem.ecommerce.dao.UserDAO;
import com.wissem.ecommerce.entity.Cart;
import com.wissem.ecommerce.entity.Product;
import com.wissem.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDAO userDAO;

    public Cart addToCart(int productId) {

        Product product = productDao.findById(productId).get();
        String userName = JwtRequestFilter.CURRENT_USER;
        User user = null;
        if (userName != null) {
            user = userDAO.findById(userName).get();
        }
        List<Cart> carts=cartDao.findByUser(user);
       List<Cart> filteredList= carts.stream().filter(x->x.getProduct().getProductId()==productId).collect(Collectors.toList());
        if (product != null && user != null && filteredList.size()<=0) {
            Cart cart = Cart.builder()
                    .product(product)
                    .user(user)
                    .build();
            return cartDao.save(cart);
        }
        return null;

    }

    public List<Cart> getCartDetails(){
        String userName = JwtRequestFilter.CURRENT_USER;
        User user =userDAO.findById(userName).get();
        return cartDao.findByUser(user);
    }

    public void deleteCartItem(int cartId){
        cartDao.deleteById(cartId);
    }
}
