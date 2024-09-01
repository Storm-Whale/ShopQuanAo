package com.whale.shopquanao.service.service;

import com.whale.shopquanao.dto.mapper.SizeMapper;
import com.whale.shopquanao.dto.request.SizeRequest;
import com.whale.shopquanao.dto.response.SizeResponse;
import com.whale.shopquanao.entity.Size;
import com.whale.shopquanao.exception.DataNotFoundException;
import com.whale.shopquanao.repository.SizeRepository;
import com.whale.shopquanao.service.iservice.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;
    private final SizeMapper sizeMapper;

    @Override
    public List<SizeResponse> getAllSize() {
        try {
            return sizeRepository.findAll().stream()
                    .map(sizeMapper::toSizeResponse)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving sizes from the database", e);
        }
    }

    @Override
    public SizeResponse getSizeById(Integer id) {
        try {
            return sizeRepository.findById(id)
                    .map(sizeMapper::toSizeResponse)
                    .orElseThrow(() -> new DataNotFoundException("Size not found with id: " + id));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving size from the database", e);
        }
    }

    @Override
    public SizeResponse storeSize(SizeRequest sizeRequest) {
        try {
            if (sizeRepository.existsBySizeName(sizeRequest.getSizeName())) {
                throw new DuplicateKeyException("A size with the name '" + sizeRequest.getSizeName() + "' already exists.");
            }

            Size size = SizeMapper.toSize(sizeRequest);
            size = sizeRepository.save(size);

            return sizeMapper.toSizeResponse(size);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation while storing size", e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error storing size in the database", e);
        }
    }

    @Override
    public SizeResponse updateSize(int id, SizeRequest sizeRequest) {
        try {
            if (!sizeRepository.existsById(id)) {
                throw new DataNotFoundException("Size not found with id: " + id);
            }

            Size size = SizeMapper.toSize(id, sizeRequest);
            size = sizeRepository.save(size);

            return sizeMapper.toSizeResponse(size);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation while updating size", e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating size in the database", e);
        }
    }

    @Override
    public void deleteSize(int id) {
        try {
            if (!sizeRepository.existsById(id)) {
                throw new DataNotFoundException("Size not found with id: " + id);
            }
            sizeRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting size from the database", e);
        }
    }
}
