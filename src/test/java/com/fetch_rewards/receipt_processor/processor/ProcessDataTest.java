package com.fetch_rewards.receipt_processor.processor;

import com.fetch_rewards.receipt_processor.entity.Receipt;
import com.fetch_rewards.receipt_processor.service.UtilsForTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProcessDataTest extends UtilsForTests {


    @InjectMocks
    ProcessData processData;

    @BeforeEach
    public void _init_(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testReadReceiptShouldReturnValidReceipt() throws Exception {

        Receipt receipt = createReceiptForTarget();
        processData.readReceipt(receipt);
        assertNotNull(receipt.getReceiptId());
        assertEquals("target", receipt.getRetailer());
        assertEquals("2024-10-21", receipt.getPurchaseDate());
        assertEquals("13:01", receipt.getPurchaseTime());
        assertEquals(5, receipt.getItems().size());
    }

}
