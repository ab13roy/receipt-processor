package com.fetch_rewards.receipt_processor.service;

import com.fetch_rewards.receipt_processor.entity.Receipt;
import com.fetch_rewards.receipt_processor.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptServicesImpl implements ReceiptServices {

    @Autowired
    ReceiptRepository receiptRepository;

    public Receipt getReceiptById(String receiptId) {
        Optional<Receipt> receipt = receiptRepository.findById(receiptId);

        return receipt.orElse(null);
    }

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public void addReceipt(Receipt receipt) {
        receiptRepository.save(receipt);
    }

    public void deleteReceipt(String receiptId) {
        receiptRepository.deleteById(receiptId);
    }

}
