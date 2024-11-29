package com.fetch_rewards.receipt_processor.utils;

import com.fetch_rewards.receipt_processor.Exception.CustomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationUtilTest {

    @Test
    public void testGetPointsForRetailerNameShouldReturnNonZero() {
        assertEquals(8, ValidationUtil.getPointsForRetailerName("xxxx0123"));
    }

    @Test
    public void testValidatePurchaseDateShouldNotThrowExceptionForValidDates() {
        assertDoesNotThrow( () -> ValidationUtil.validatePurchaseDate("2024-10-10"));
    }

    @Test
    public void testValidatePurchaseDateShouldThrowException() {
        assertThrows(CustomException.class, () -> ValidationUtil.validatePurchaseDate("2024-1651-10"));
    }

    @Test
    public void testValidPurchaseTimeShouldNotThrowException() {
        assertDoesNotThrow(() -> ValidationUtil.validatePurchaseTime("00:00"));
        assertDoesNotThrow(() -> ValidationUtil.validatePurchaseTime("23:59"));
        assertDoesNotThrow(() -> ValidationUtil.validatePurchaseTime("12:59"));
    }

    @Test
    public void testValidPurchaseTimeShouldThrowException() {
        assertThrows(CustomException.class, () -> ValidationUtil.validatePurchaseTime("12:415"));
        assertThrows(CustomException.class, () -> ValidationUtil.validatePurchaseTime("123:41"));
    }

}
