package com.fetch_rewards.receipt_processor.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetch_rewards.receipt_processor.entity.Receipt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProcessData {

    public Receipt readReceipt(String givenReceipt) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Receipt createReceipt = mapper.readValue(givenReceipt, Receipt.class);
        createReceipt.setReceiptId(UUID.randomUUID().toString());
        return createReceipt;
    }

}
