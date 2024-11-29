package com.fetch_rewards.receipt_processor.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fetch_rewards.receipt_processor.Exception.CustomException;
import com.fetch_rewards.receipt_processor.Exception.NotFoundException;
import com.fetch_rewards.receipt_processor.entity.Receipt;
import com.fetch_rewards.receipt_processor.processor.ProcessData;
import com.fetch_rewards.receipt_processor.service.PointsServicesImpl;
import com.fetch_rewards.receipt_processor.service.ProductServicesImpl;
import com.fetch_rewards.receipt_processor.service.ReceiptServicesImpl;
import com.fetch_rewards.receipt_processor.service.UtilsForTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class controllerV1Test extends UtilsForTests {

    @Mock
    ProcessData processData;

    @Mock
    PointsServicesImpl pointsServices;

    @Mock
    ProductServicesImpl productServices;

    @Mock
    ReceiptServicesImpl receiptServices;

    @InjectMocks
    controllerV1 controller;

    @BeforeEach
    public void _init_() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessReceiptsFromTarget() throws Exception {
        Receipt receipt = createReceiptForTarget();

        when(processData.readReceipt(any())).thenReturn(receipt);
        doNothing().when(receiptServices).addReceipt(any());

        ResponseEntity<String> result = controller.processReceipts(UtilsForTests.targetJson);

        assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void testGetPointsShouldReturnSomething() throws NotFoundException {
        when(pointsServices.calculatePointsForReceipt(any())).thenReturn(10d);

        ResponseEntity<Double> result = controller.getPoints("123");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(10d, result.getBody());
    }

    @Test
    public void testProcessReceiptsShouldThrowExceptionAndReturnBadRequest() {
        assertThrows( JsonParseException.class, () -> {
            Receipt receipt = createReceiptForTarget();

            when(processData.readReceipt(any())).thenThrow(JsonParseException.class);

            ResponseEntity<String> result = controller.processReceipts(UtilsForTests.targetJson.substring(0, 20));
            assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        });
    }

    @Test
    public void testParseException() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServerName("www.something.com");
        request.setRequestURI("/receipts/process");
        request.setContentType("application/json");
        request.setContent(UtilsForTests.targetJson.substring(0, 20).getBytes());

        Exception ex = new CustomException("Unable to parse json");

        assertEquals(HttpStatus.BAD_REQUEST, controller.parseException(request, ex).getStatusCode());
    }

    @Test
    public void testParseException2() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServerName("www.something.com");
        request.setRequestURI("/1-2-3/points");
        request.setContentType("application/json");

        Exception ex = new NotFoundException("Could not find requested receipt");

        assertEquals(HttpStatus.NOT_FOUND, controller.parseException2(request, ex).getStatusCode());
    }
}
