package com.whale.shopquanao.service.iservice;

import com.whale.shopquanao.dto.request.RoleRequest;
import com.whale.shopquanao.dto.response.RoleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    List<RoleResponse> getAllRole();

    RoleResponse getRoleById(Integer id);

    RoleResponse storeRole(RoleRequest roleRequest);

    RoleResponse updateRole(Integer id, RoleRequest roleRequest);

    void deleteRole(Integer id);
}
