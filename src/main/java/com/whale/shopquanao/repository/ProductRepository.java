package com.whale.shopquanao.repository;

import com.whale.shopquanao.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByProductName(String name);
}
