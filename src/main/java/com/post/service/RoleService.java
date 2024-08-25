package com.post.service;

import com.post.base.BaseService;
import com.post.model.Role;
import com.post.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RoleService extends BaseService<Role,Integer> {
/*
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void save(List<Role> roles)
    {
        roleRepository.saveAll(roles);
    }

 */
}
