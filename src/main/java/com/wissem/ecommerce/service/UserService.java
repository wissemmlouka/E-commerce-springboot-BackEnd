package com.wissem.ecommerce.service;


import com.wissem.ecommerce.dao.RoleDAO;
import com.wissem.ecommerce.dao.UserDAO;
import com.wissem.ecommerce.entity.Role;
import com.wissem.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerNewUser(User user) {

        Role role = roleDAO.findById("User").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setUserPassword(getEncoderPassword(user.getUserPassword()));

        return userDAO.save(user);
    }

    public void initRolesAndUser() {
        Role adminRole = Role.builder()
                .roleName("Admin")
                .roleDescription("Admin role")
                .build();
        roleDAO.save(adminRole);

        Role userRole = Role.builder()
                .roleName("User")
                .roleDescription("Default role")
                .build();
        roleDAO.save(userRole);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        User adminUser = User.builder()
                .userFirstName("admin")
                .userLastName("admin")
                .userName("admin123")
                .userPassword(getEncoderPassword("admin@pass"))
                .roles(adminRoles)
                .build();
        userDAO.save(adminUser);

    }

    public String getEncoderPassword(String password) {
        return passwordEncoder.encode(password);
    }


}
