package com.whale.shopquanao.repository;

import com.whale.shopquanao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    boolean existsByRoleName(String roleName);
}
