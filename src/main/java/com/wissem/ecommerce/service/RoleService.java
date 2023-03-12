package com.wissem.ecommerce.jwt.service;


import com.wissem.ecommerce.jwt.dao.RoleDAO;
import com.wissem.ecommerce.jwt.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDAO roleDAO;
    public Role createNewRole(Role role){
      return roleDAO.save(role);
    }

}
