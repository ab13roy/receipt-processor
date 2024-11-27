package com.fetch_rewards.receipt_processor.service;

import com.fetch_rewards.receipt_processor.entity.Product;

import java.util.List;

public class PointsServicesImpl implements PointsServices{
    @Override
    public double calculatePointsForReceipt(String receiptId) {
        // calculate total points here
        return 0;
    }

    @Override
    public int calculatePointsForName(String retailerName) {
        // 1 point for every alphanumeric character in the name
        return 0;
    }

    @Override
    public int checkIfRoundDollarAmount(double amount) {
        // 50 points if the total is a round amount with no cents
        return 0;
    }

    @Override
    public int checkIfMultipleOf25(double amount) {
        // 25 points if the total is a multiple of 0.25
        return 0;
    }

    @Override
    public int countOfItemsOnReceipt(List<Product> productList) {
        // 5 points for every 2 items on the receipt
        return 0;
    }

    @Override
    public int checkIfShortDescLengthIsMultipleOf3(Product product) {
        // if the length is multiple of 3, multiply cost by 0.2 and round to the next integer
        return 0;
    }

    @Override
    public int checkIfDateOfPurchaseIsOdd(String purchaseDate) {
        // 6 points if the date is odd
        return 0;
    }

    @Override
    public int checkIfPurchaseTimeIsBetween2And4Pm(String purchaseTime) {
        // 10 points if the purchase is between 14:00 and 16:00 hrs
        return 0;
    }
}
