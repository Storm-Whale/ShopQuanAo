package com.whale.shopquanao.service.service;

import com.whale.shopquanao.dto.mapper.UserMapper;
import com.whale.shopquanao.dto.request.UserRequest;
import com.whale.shopquanao.dto.response.UserResponse;
import com.whale.shopquanao.entity.Role;
import com.whale.shopquanao.entity.User;
import com.whale.shopquanao.entity.UserRole;
import com.whale.shopquanao.entity.UserRoleId;
import com.whale.shopquanao.exception.DataNotFoundException;
import com.whale.shopquanao.repository.RoleRepository;
import com.whale.shopquanao.repository.UserRepository;
import com.whale.shopquanao.repository.UserRoleRepository;
import com.whale.shopquanao.service.iservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(userMapper.toUserResponse(user)));
        return users;
    }

    @Override
    public UserResponse getUserById(Integer id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserResponse storeUser(UserRequest userRequest) {
        Role role = findRoleById(userRequest.getIdRole());
        User savedUser = saveUserWithRole(null, userRequest, role);
        saveUserRole(savedUser, role);
        return getUserResponse(savedUser.getId());
    }

    @Override
    public UserResponse updateUser(Integer id, UserRequest userRequest) {
        Role role = findRoleById(userRequest.getIdRole());
        User updatedUser = saveUserWithRole(id, userRequest, role);
        saveUserRole(updatedUser, role);
        return getUserResponse(updatedUser.getId());
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + id));
        Set<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.forEach(role -> {
                UserRoleId userRoleId = UserRoleId.builder()
                        .roleId(role.getId())
                        .userId(user.getId())
                        .build();
                userRoleRepository.findById(userRoleId)
                        .ifPresent(userRoleRepository::delete);
            });
        }
        userRepository.deleteById(id);
    }


    private Role findRoleById(Integer roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new DataNotFoundException("Role không tồn tại"));
    }

    private User saveUserWithRole(Integer id, UserRequest userRequest, Role role) {
        User user, lastUpdated;
        if (id == null) {
            user = userMapper.toUser(userRequest);
        } else {
            user = userRepository.findById(id)
                    .orElseThrow(() -> new DataNotFoundException("User không tồn tại"));
            user.setUsername(userRequest.getUsername());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user.setFullName(userRequest.getFullName());
            user.setPhoneNumber(userRequest.getPhoneNumber());
            user.setAddress(userRequest.getAddress());
        }
        user.setRoles(new HashSet<>(List.of(role)));
        return userRepository.save(user);
    }


    private void saveUserRole(User user, Role role) {
        UserRoleId userRoleId = UserRoleId.builder()
                .userId(user.getId()).roleId(role.getId()).build();
        UserRole userRole = UserRole.builder()
                .id(userRoleId).user(user).role(role).build();
        userRoleRepository.save(userRole);
    }

    private UserResponse getUserResponse(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User không tồn tại"));
        return userMapper.toUserResponse(user);
    }
}

