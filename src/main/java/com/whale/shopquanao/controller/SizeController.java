package com.whale.shopquanao.controller;

import com.whale.shopquanao.dto.request.SizeRequest;
import com.whale.shopquanao.dto.response.SizeResponse;
import com.whale.shopquanao.service.iservice.SizeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/size")
@RequiredArgsConstructor
public class SizeController {

    private final SizeService sizeService;

    @GetMapping(value = "/index")
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(sizeService.getAllSize(), HttpStatus.OK);
    }

    @GetMapping(value = "/find_by_id/{id}")
    public ResponseEntity<?> index(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(sizeService.getSizeById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/store")
    public ResponseEntity<?> store(@Valid @RequestBody SizeRequest sizeRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        SizeResponse sizeResponse = sizeService.storeSize(sizeRequest);
        return new ResponseEntity<>(sizeResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable(name = "id") Integer id, @Valid @RequestBody SizeRequest sizeRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        SizeResponse sizeResponse = sizeService.updateSize(id, sizeRequest);
        return new ResponseEntity<>(sizeResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        sizeService.deleteSize(id);
        return new ResponseEntity<>("Delete size successfully with id: " + id, HttpStatus.OK);
    }
}
