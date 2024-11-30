package com.fetch_rewards.receipt_processor.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fetch_rewards.receipt_processor.Exception.CustomException;
import com.fetch_rewards.receipt_processor.entity.Product;
import com.fetch_rewards.receipt_processor.entity.Receipt;
import com.fetch_rewards.receipt_processor.utils.ValidationUtil;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProcessData {

    public void readReceipt(Receipt givenReceipt) throws JsonProcessingException, CustomException {

        ValidationUtil.validatePurchaseDate(givenReceipt.getPurchaseDate());
        ValidationUtil.validatePurchaseTime(givenReceipt.getPurchaseTime());
        ValidationUtil.validateCost(givenReceipt.getTotal());
        for(Product p: givenReceipt.getItems()) {
            ValidationUtil.validateCost(p.getCost());
        }
        givenReceipt.setReceiptId(UUID.randomUUID().toString());
    }

}
