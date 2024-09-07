package com.whale.shopquanao.service.service;

import com.whale.shopquanao.dto.mapper.RoleMapper;
import com.whale.shopquanao.dto.request.RoleRequest;
import com.whale.shopquanao.dto.response.RoleResponse;
import com.whale.shopquanao.entity.Role;
import com.whale.shopquanao.exception.DataNotFoundException;
import com.whale.shopquanao.repository.RoleRepository;
import com.whale.shopquanao.service.iservice.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleResponse> getAllRole() {
        try {
            return roleRepository.findAll().stream()
                    .map(roleMapper::toRoleResponse)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving role from the database", e);
        }
    }

    @Override
    public RoleResponse getRoleById(Integer id) {
        try {
            return roleRepository.findById(id)
                    .map(roleMapper::toRoleResponse)
                    .orElseThrow(() -> new DataNotFoundException("Role not found with id: " + id));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving role from the database", e);
        }
    }

    @Override
    public RoleResponse storeRole(RoleRequest roleRequest) {
        try {
            if (roleRepository.existsByRoleName(roleRequest.getRoleName())) {
                throw new DuplicateKeyException("A role with the name '" + roleRequest.getRoleName() + "' already exists.");
            }
            Role role = roleMapper.toRole(roleRequest);
            Role saveRole = roleRepository.save(role);
            return roleMapper.toRoleResponse(saveRole);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation while storing role", e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error storing role in the database", e);
        }
    }

    @Override
    public RoleResponse updateRole(Integer id, RoleRequest roleRequest) {
        try {
            if (!roleRepository.existsById(id)) {
                throw new DataNotFoundException("Role not found with id: " + id);
            }
            Role role = roleMapper.toRole(id, roleRequest);
            Role updateRole = roleRepository.save(role);
            return roleMapper.toRoleResponse(updateRole);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation while updating role", e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating role in the database", e);
        }
    }

    @Override
    public void deleteRole(Integer id) {
        try {
            if (!roleRepository.existsById(id)) {
                throw new DataNotFoundException("Role not found with id: " + id);
            }
            roleRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting role from the database", e);
        }
    }
}
