package com.wissem.ecommerce.jwt.dao;


import com.wissem.ecommerce.jwt.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role,String> {
}
