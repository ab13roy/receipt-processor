package com.fetch_rewards.receipt_processor.processor;

import com.fetch_rewards.receipt_processor.entity.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProcessDataTest {


    @InjectMocks
    ProcessData processData;

    @BeforeEach
    public void _init_(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testReadReceiptShouldReturnValidReceipt() throws Exception {

        Path file = Path.of("src/test/resources").resolve("example1");

        Receipt receipt = processData.readReceipt(Files.readString(file));
        assertNotNull(receipt.getReceiptId());
        assertEquals("Target", receipt.getRetailer());
        assertEquals("2022-01-01", receipt.getPurchaseDate());
        assertEquals("13:01", receipt.getPurchaseTime());
        assertEquals(5, receipt.getItems().size());
    }

}
