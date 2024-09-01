package com.whale.shopquanao.service.service;

import com.whale.shopquanao.dto.mapper.UserMapper;
import com.whale.shopquanao.dto.request.UserRequest;
import com.whale.shopquanao.dto.response.UserResponse;
import com.whale.shopquanao.entity.User;
import com.whale.shopquanao.repository.UserRepository;
import com.whale.shopquanao.service.iservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
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
        User user = userMapper.toUser(userRequest);
        user = userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(Integer id, UserRequest userRequest) {
        User user = userMapper.toUser(id, userRequest);
        user = userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}

