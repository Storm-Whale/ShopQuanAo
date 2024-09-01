package com.whale.shopquanao.controller;

import com.whale.shopquanao.dto.request.UserRequest;
import com.whale.shopquanao.service.iservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/index")
    public ResponseEntity<?> index () {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/find_by_id/{id}")
    public ResponseEntity<?> findById (@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/store")
    public ResponseEntity<?> store (@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.storeUser(userRequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update (@PathVariable(name = "id") Integer id, @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.updateUser(id, userRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable(name = "id") Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("Delete user successfully with id: " + id, HttpStatus.OK);
    }
}
