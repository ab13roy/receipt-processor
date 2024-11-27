package com.fetch_rewards.receipt_processor.repository;

import com.fetch_rewards.receipt_processor.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, String> {
}