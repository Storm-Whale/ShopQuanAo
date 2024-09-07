package com.whale.shopquanao.repository;

import com.whale.shopquanao.entity.UserRole;
import com.whale.shopquanao.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
