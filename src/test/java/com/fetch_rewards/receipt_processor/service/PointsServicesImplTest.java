package com.fetch_rewards.receipt_processor.service;

import com.fetch_rewards.receipt_processor.Exception.NotFoundException;
import com.fetch_rewards.receipt_processor.entity.Product;
import com.fetch_rewards.receipt_processor.entity.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PointsServicesImplTest extends UtilsForTests {

    @Mock
    ReceiptServicesImpl receiptServices;

    @InjectMocks
    PointsServicesImpl pointsServices;

    @BeforeEach
    public void _init_() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculatePointsForReceipt() throws NotFoundException {

        Receipt receipt = createReceiptForTarget();
        when(receiptServices.getReceiptById(any())).thenReturn(receipt);

        assertEquals(28, pointsServices.calculatePointsForReceipt("123"));
    }

    @Test
    public void testCalculatePointsForReceiptTest2() throws NotFoundException {
        Receipt receipt = createReceiptForMM();
        when(receiptServices.getReceiptById(any())).thenReturn(receipt);

        assertEquals(109, pointsServices.calculatePointsForReceipt("123"));
    }

    @Test
    public void testCalculatePointsForName() {
        assertEquals(6, pointsServices.calculatePointsForName("target"));
    }

    @Test
    public void testCalculatePointsForNameTest2() {
        assertEquals(14, pointsServices.calculatePointsForName("M&M Corner Market"));
    }

    @Test
    public void testCalculatePointsForNameTest3() {
        assertEquals(0, pointsServices.calculatePointsForName(""));
    }

    @Test
    public void testCalculatePointsForNameTest4() {
        assertEquals(0, pointsServices.calculatePointsForName("---"));
    }

    @Test
    public void testCalculatePointsForNameTest5() {
        assertEquals(6, pointsServices.calculatePointsForName("TARget"));
    }

    @Test
    public void testCheckIfRoundDollarAmountReturns50() {
        assertEquals(50, pointsServices.checkIfRoundDollarAmount(9.00));
    }

    @Test
    public void testCheckIfRoundDollarAmountReturns0() {
        assertEquals(0, pointsServices.checkIfRoundDollarAmount(9.01));
    }

    @Test
    public void testCheckIfMultipleOf25Returns25() {
        assertEquals(25, pointsServices.checkIfMultipleOf25(9.00));
    }

    @Test
    public void testCheckIfMultipleOf25Returns0() {
        assertEquals(0, pointsServices.checkIfMultipleOf25(9.05));
    }

    @Test
    public void testCountOfItemsOnReceiptShouldReturn0() {
        assertEquals(0, pointsServices.countOfItemsOnReceipt(List.of()));
    }

    @Test
    public void testCountOfItemsOnReceiptShouldReturn25() {
        assertEquals(25, pointsServices.countOfItemsOnReceipt(List.of(new Product(), new Product(), new Product(), new Product(), new Product(), new Product(), new Product(), new Product(), new Product(), new Product())));
    }

    @Test
    public void  testCheckIfShortDescLengthIsMultipleOf3Returns0() {
        Product product = new Product(new Receipt(), "Gatorade", 2.25);
        assertEquals(0, pointsServices.checkIfShortDescLengthIsMultipleOf3(product));
    }

    @Test
    public void  testCheckIfShortDescLengthIsMultipleOf3Returns245() {
        Product product = new Product(new Receipt(), "Emils Cheese Pizza", 12.25);
        assertEquals(3, pointsServices.checkIfShortDescLengthIsMultipleOf3(product));
    }

    @Test
    public void testCheckIfDateOfPurchaseIsOddReturns0() {
        assertEquals(0, pointsServices.checkIfDateOfPurchaseIsOdd("2024-10-20"));
    }

    @Test
    public void testCheckIfDateOfPurchaseIsOddReturns6() {
        assertEquals(6, pointsServices.checkIfDateOfPurchaseIsOdd("2024-06-21"));
    }

    @Test
    public void testCheckIfPurchaseTimeIsBetween2And4PmReturns0() {
        assertEquals(0, pointsServices.checkIfPurchaseTimeIsBetween2And4Pm("13:58"));
    }

    @Test
    public void testCheckIfPurchaseTimeIsBetween2And4PmReturns0ForAt14Hrs() {
        assertEquals(0, pointsServices.checkIfPurchaseTimeIsBetween2And4Pm("14:00"));
    }

    @Test
    public void testCheckIfPurchaseTimeIsBetween2And4PmReturns0After16Hrs() {
        assertEquals(0, pointsServices.checkIfPurchaseTimeIsBetween2And4Pm("16:00"));
    }

    @Test
    public void testCheckIfPurchaseTimeIsBetween2And4PmReturns10() {
        assertEquals(10, pointsServices.checkIfPurchaseTimeIsBetween2And4Pm("14:59"));
    }

    @Test
    public void testCheckIfPurchaseTimeIsBetween2And4PmReturns10Before16Hrs() {
        assertEquals(10, pointsServices.checkIfPurchaseTimeIsBetween2And4Pm("15:59"));
    }
}
