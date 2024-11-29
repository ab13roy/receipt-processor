package com.fetch_rewards.receipt_processor.service;

import com.fetch_rewards.receipt_processor.entity.Product;
import com.fetch_rewards.receipt_processor.entity.Receipt;

import java.util.List;

public class UtilsForTests {

    public static final String targetJson = "{\n" +
            "  \"retailer\": \"Target\",\n" +
            "  \"purchaseDate\": \"2022-01-01\",\n" +
            "  \"purchaseTime\": \"13:01\",\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"shortDescription\": \"Mountain Dew 12PK\",\n" +
            "      \"price\": \"6.49\"\n" +
            "    },{\n" +
            "      \"shortDescription\": \"Emils Cheese Pizza\",\n" +
            "      \"price\": \"12.25\"\n" +
            "    },{\n" +
            "      \"shortDescription\": \"Knorr Creamy Chicken\",\n" +
            "      \"price\": \"1.26\"\n" +
            "    },{\n" +
            "      \"shortDescription\": \"Doritos Nacho Cheese\",\n" +
            "      \"price\": \"3.35\"\n" +
            "    },{\n" +
            "      \"shortDescription\": \"   Klarbrunn 12-PK 12 FL OZ  \",\n" +
            "      \"price\": \"12.00\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"total\": \"35.35\"\n" +
            "}";

    public Receipt createReceiptForTarget() {
        Receipt receipt = new Receipt();
        receipt.setReceiptId("123");
        receipt.setRetailer("target");
        receipt.setPurchaseDate("2024-10-21");
        receipt.setPurchaseTime("13:01");
        receipt.setTotal(35.35);
        receipt.setItems(List.of(new Product(receipt, "Mountain Dew 12PK",6.49),
                new Product(receipt, "Emils Cheese Pizza",12.25),
                new Product(receipt, "Knorr Creamy Chicken",1.26),
                new Product(receipt, "Doritos Nacho Cheese", 3.35),
                new Product(receipt, "   Klarbrunn 12-PK 12 FL OZ  ", 12.00)));
        return receipt;
    }

    public  Receipt createReceiptForMM() {
        Receipt receipt = new Receipt();
        receipt.setReceiptId("123");
        receipt.setRetailer("M&M Corner Market");
        receipt.setPurchaseDate("2022-03-20");
        receipt.setPurchaseTime("14:33");
        receipt.setTotal(9.00);
        receipt.setItems(List.of(new Product(receipt, "Gatorade", 2.25),
                new Product(receipt, "Gatorade", 2.25),
                new Product(receipt, "Gatorade", 2.25),
                new Product(receipt, "Gatorade", 2.25)));
        return receipt;
    }
}
