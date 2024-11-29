package com.fetch_rewards.receipt_processor.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void testReceiptGetterAndSetter() {
        Receipt receipt = new Receipt();
        receipt.setReceiptId("123");

        Product product = new Product();
        product.setReceipt(receipt);

        assertEquals(receipt.getReceiptId(), product.getReceipt().getReceiptId());
    }

}
