package com.wissem.ecommerce.service;


import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.wissem.ecommerce.config.JwtRequestFilter;
import com.wissem.ecommerce.dao.CartDao;
import com.wissem.ecommerce.dao.OrderDetailsDAO;
import com.wissem.ecommerce.dao.ProductDao;
import com.wissem.ecommerce.dao.UserDAO;
import com.wissem.ecommerce.entity.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    private static final String ORDER_PLACED = "Placed";
    private static final String KEY = "rzp_test_0JsPFvUnym2Qhv";
    private static final String SECRET_KEY = "40aThIrhCURQZO4KexQ85UhE";
    private static final String CURRENCY = "USD";


    @Autowired
    private OrderDetailsDAO orderDetailsDAO;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CartDao cartDao;

    public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout) {
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
            if (!isSingleProductCheckout) {
                List<Cart> carts = cartDao.findByUser(user);
                carts.stream().forEach(x -> cartDao.deleteById(x.getCartId())
                );
            }
            orderDetailsDAO.save(orderDetail);
        }

    }

    public List<OrderDetail> getOrderDetails() {
        String currenUser = JwtRequestFilter.CURRENT_USER;
        User user = userDAO.findById(currenUser).get();
        return orderDetailsDAO.findByUser(user);
    }

    public List<OrderDetail> getAllOrderDetails(String status) {
        if (status.equals("all")) {
            return orderDetailsDAO.findAll();

        } else {
            return orderDetailsDAO.findByOrderStatusContainingIgnoreCase(status);
        }
    }

    public OrderDetail orderDelivered(int orderId) {
        OrderDetail order = orderDetailsDAO.findById(orderId).get();
        if (order != null) {
            order.setOrderStatus("Delivered");
            return orderDetailsDAO.save(order);

        }
        return null;
    }

    public TransactionDetails createTransaction(Double amount) throws RazorpayException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount", amount * 100);
        jsonObject.put("currency", CURRENCY);

        RazorpayClient razorpayClient = new RazorpayClient(KEY, SECRET_KEY);
        Order order = razorpayClient.orders.create(jsonObject);
        return prepareTransactionDetails(order);
    }

    public TransactionDetails prepareTransactionDetails(Order order) {
        String orderId = order.get("id");
        String currency = order.get("currency");
        int amount = order.get("amount");
        TransactionDetails transactionDetails =
                TransactionDetails.builder()
                        .orderId(orderId)
                        .currency(currency)
                        .amount(amount)
                        .Key_id(KEY)
                        .build();
        return transactionDetails;
    }
}
