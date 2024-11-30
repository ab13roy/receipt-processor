package com.fetch_rewards.receipt_processor.service;

import com.fetch_rewards.receipt_processor.Exception.NotFoundException;
import com.fetch_rewards.receipt_processor.entity.Product;
import com.fetch_rewards.receipt_processor.entity.Receipt;
import com.fetch_rewards.receipt_processor.utils.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PointsServicesImpl implements PointsServices{

    @Autowired
    ReceiptServicesImpl receiptServices;

    @Autowired
    ProductServicesImpl productServices;

    static final String regex = "^[a-zA-Z0-9]+$";

    @Override
    public Integer calculatePointsForReceipt(String receiptId) throws NotFoundException {
        Receipt receipt = receiptServices.getReceiptById(receiptId);
        if(receipt == null) {
            throw new NotFoundException("Could not find requested receipt");
        }
        Integer total = Integer.valueOf(0);
        total += calculatePointsForName(receipt.getRetailer());
        total += checkIfRoundDollarAmount(receipt.getTotal());
        total += checkIfMultipleOf25(receipt.getTotal());
        total += countOfItemsOnReceipt(receipt.getItems());

        for(Product p : receipt.getItems()) {
            total += checkIfShortDescLengthIsMultipleOf3(p);
        }

        total += checkIfDateOfPurchaseIsOdd(receipt.getPurchaseDate());
        total += checkIfPurchaseTimeIsBetween2And4Pm(receipt.getPurchaseTime());

        return total;
    }

    @Override
    public int calculatePointsForName(String retailerName) {
        // 1 point for every alphanumeric character in the name
        if(retailerName.matches(regex)) {
            return retailerName.length();
        }
        return ValidationUtil.getPointsForRetailerName(retailerName);
    }

    @Override
    public int checkIfRoundDollarAmount(double amount) {
        // 50 points if the total is a round amount with no cents
        return (amount / (int)amount) == 1d? 50: 0;
    }

    @Override
    public int checkIfMultipleOf25(double amount) {
        // 25 points if the total is a multiple of 0.25
        return ((amount *100) % 25) == 0d? 25: 0;
    }

    @Override
    public int countOfItemsOnReceipt(List<Product> productList) {
        // 5 points for every 2 items on the receipt
        return (productList.size()/2) * 5;
    }

    @Override
    public int checkIfShortDescLengthIsMultipleOf3(Product product) {
        // if the length is multiple of 3, multiply cost by 0.2 and round to the next integer
        StringBuilder sb = new StringBuilder(StringUtils.trim(product.getShortDescription()));
        return (sb.length() % 3) == 0? (int) Math.ceil(product.getCost() * 0.2) : 0;
    }

    @Override
    public int checkIfDateOfPurchaseIsOdd(String purchaseDate) {
        // 6 points if the date is odd yyyy-mm-dd
        String[] date = purchaseDate.split("-");
        return Integer.parseInt(date[2]) % 2 == 1? 6 : 0;
    }

    @Override
    public int checkIfPurchaseTimeIsBetween2And4Pm(String purchaseTime) {
        // 10 points if the purchase is after 14:00 and before 16:00 hrs
        String[] time = purchaseTime.split(":");
        int hours = Integer.parseInt(time[0]);
        int mins = Integer.parseInt(time[1]);
        if((hours >= 14 && hours <16) && (mins >= 1 && mins < 60)) {
            return 10;
        }
        return 0;
    }
}
