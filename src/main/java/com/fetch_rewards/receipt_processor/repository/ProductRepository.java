package com.fetch_rewards.receipt_processor.repository;

import com.fetch_rewards.receipt_processor.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}