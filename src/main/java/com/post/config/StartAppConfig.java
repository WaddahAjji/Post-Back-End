package com.post.config;

import com.post.model.ERole;
import com.post.model.Role;
import com.post.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StartAppConfig implements CommandLineRunner {
    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        //insert roles
        if(roleService.findAll().isEmpty()){
            List<Role> roles= new ArrayList<Role>(Arrays.asList(new Role(ERole.ROLE_ADMIN),new Role(ERole.ROLE_USER)));
            roleService.insert(roles);
        }



    }
}
