package com.fetch_rewards.receipt_processor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fetch_rewards.receipt_processor.processor.ProcessData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipts")
public class controllerV1 {

    @Autowired
    ProcessData processData;

    @PostMapping("/process")
    public String processReceipts(@RequestBody String givenReceipt) throws JsonProcessingException {

        return processData.readReceipt(givenReceipt).getReceiptId();
    }

}
