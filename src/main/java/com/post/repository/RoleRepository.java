package com.post.repository;

import com.post.base.BaseRepository;
import com.post.model.ERole;
import com.post.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends BaseRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
