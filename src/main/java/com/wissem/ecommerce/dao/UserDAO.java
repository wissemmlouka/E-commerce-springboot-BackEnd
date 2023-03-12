package com.wissem.ecommerce.jwt.dao;


import com.wissem.ecommerce.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User,String> {


}
