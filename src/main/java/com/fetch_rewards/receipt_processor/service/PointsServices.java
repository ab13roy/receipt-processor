package com.fetch_rewards.receipt_processor.service;

import com.fetch_rewards.receipt_processor.entity.Product;

import java.util.List;

public interface PointsServices {
    double calculatePointsForReceipt(String receiptId);

    int calculatePointsForName(String retailerName);

    int checkIfRoundDollarAmount(double amount);

    int checkIfMultipleOf25(double amount);

    int countOfItemsOnReceipt(List<Product> productList);

    int checkIfShortDescLengthIsMultipleOf3(Product product);

    int checkIfDateOfPurchaseIsOdd(String purchaseDate);

    int checkIfPurchaseTimeIsBetween2And4Pm(String purchaseTime);

}
