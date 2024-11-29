package com.fetch_rewards.receipt_processor.entity;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReceiptTest {

    @Test
    public void testReceiptToString() {
        Receipt dummy = new Receipt();
        dummy.setReceiptId("123");

        Receipt receipt = new Receipt("target", "2024-10-21", "13:01" ,
                List.of(new Product(dummy, "Mountain Dew 12PK",6.49),
                new Product(dummy, "Emils Cheese Pizza",12.25),
                new Product(dummy, "Knorr Creamy Chicken",1.26),
                new Product(dummy, "Doritos Nacho Cheese", 3.35),
                new Product(dummy, "   Klarbrunn 12-PK 12 FL OZ  ", 12.00)),
                35.35);

        assertNotNull(receipt.toString());
        assertNotEquals(0, receipt.toString().length());
    }

}
