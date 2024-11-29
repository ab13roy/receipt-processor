package com.fetch_rewards.receipt_processor.utils;

import com.fetch_rewards.receipt_processor.Exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidationUtil {

    static final Logger logger = LoggerFactory.getLogger(ValidationUtil.class);

    static final String format = "yyyy-MM-dd";

    public static int getPointsForRetailerName(String retailerName) {
        StringBuilder sb = new StringBuilder();
        for(char c: retailerName.toCharArray()) {
            if((c >= 'a' && c <='z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                sb.append(c);
            }
        }
        return sb.length();
    }

    public static void validatePurchaseDate(String purchaseDate) throws CustomException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        try {
           LocalDate dat = LocalDate.parse(purchaseDate);
        }
        catch (DateTimeParseException parseException) {
            logger.debug(parseException.getMessage());
            logger.debug(parseException.toString());
            logger.error("{} for {}", parseException.getMessage(), purchaseDate);
            throw new CustomException("Unable to parse Date");
        }
    }

    public static void validatePurchaseTime(String purchaseTime) throws CustomException {
        String[] time = purchaseTime.split(":");
        int hour = Integer.parseInt(time[0]), mins = Integer.parseInt(time[1]);
        if((hour < 0 || hour > 23) || (mins < 0 || mins > 59) ) {
            throw new CustomException("Unable to parse Time of purchase");
        }
    }

    public static void validateCost(double amount) throws CustomException {
        if(amount <= 0d) {
            throw new CustomException("Unable to parse Cost");
        }
    }

}
