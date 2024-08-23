package com.whale.shopquanao.service.iservice;

import com.whale.shopquanao.dto.request.SizeRequest;
import com.whale.shopquanao.dto.response.SizeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SizeService {
    List<SizeResponse> getAllSize();

    SizeResponse getSizeById(Integer id);

    SizeResponse storeSize(SizeRequest sizeRequest);

    SizeResponse updateSize(int id, SizeRequest sizeRequest);

    void deleteSize(int id);
}
