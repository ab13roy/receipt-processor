package com.fetch_rewards.receipt_processor.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetch_rewards.receipt_processor.Exception.CustomException;
import com.fetch_rewards.receipt_processor.entity.Product;
import com.fetch_rewards.receipt_processor.entity.Receipt;
import com.fetch_rewards.receipt_processor.utils.ValidationUtil;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProcessData {

    public Receipt readReceipt(String givenReceipt) throws JsonProcessingException, CustomException {
        ObjectMapper mapper = new ObjectMapper();
        Receipt createReceipt = mapper.readValue(givenReceipt, Receipt.class);
        ValidationUtil.validatePurchaseDate(createReceipt.getPurchaseDate());
        ValidationUtil.validatePurchaseTime(createReceipt.getPurchaseTime());
        ValidationUtil.validateCost(createReceipt.getTotal());
        for(Product p: createReceipt.getItems()) {
            ValidationUtil.validateCost(p.getCost());
        }
        createReceipt.setReceiptId(UUID.randomUUID().toString());
        return createReceipt;
    }

}
