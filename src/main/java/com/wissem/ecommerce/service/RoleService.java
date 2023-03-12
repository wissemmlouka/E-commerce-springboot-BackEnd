package com.wissem.ecommerce.service;


import com.wissem.ecommerce.dao.RoleDAO;
import com.wissem.ecommerce.entity.Role;
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
