package com.whale.shopquanao.controller;

import com.whale.shopquanao.dto.request.CategoryRequest;
import com.whale.shopquanao.dto.request.RoleRequest;
import com.whale.shopquanao.dto.response.CategoryResponse;
import com.whale.shopquanao.dto.response.RoleResponse;
import com.whale.shopquanao.entity.Role;
import com.whale.shopquanao.service.iservice.CategoryService;
import com.whale.shopquanao.service.iservice.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/index")
    public ResponseEntity<List<RoleResponse>> index() {
        List<RoleResponse> roles = roleService.getAllRole();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<RoleResponse> findById(@PathVariable("id") Integer id) {
        RoleResponse role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @PostMapping("/store")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> store(@Valid @RequestBody RoleRequest roleRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }
        RoleResponse roleResponse = roleService.storeRole(roleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Integer id,
            @Valid @RequestBody  RoleRequest roleRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }
        RoleResponse roleResponse = roleService.updateRole(id, roleRequest);
        return ResponseEntity.ok(roleResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Delete category successfully with id: " + id);
    }

    // Phương thức riêng để xử lý lỗi ràng buộc
    private ResponseEntity<List<String>> handleValidationErrors(BindingResult bindingResult) {
        List<String> errors = bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }
}
