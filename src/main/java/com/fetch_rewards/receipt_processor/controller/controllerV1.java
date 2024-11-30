package com.fetch_rewards.receipt_processor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fetch_rewards.receipt_processor.Exception.CustomException;
import com.fetch_rewards.receipt_processor.Exception.NotFoundException;
import com.fetch_rewards.receipt_processor.entity.GetEntity;
import com.fetch_rewards.receipt_processor.entity.PostEntity;
import com.fetch_rewards.receipt_processor.entity.Receipt;
import com.fetch_rewards.receipt_processor.processor.ProcessData;
import com.fetch_rewards.receipt_processor.service.PointsServicesImpl;
import com.fetch_rewards.receipt_processor.service.ProductServicesImpl;
import com.fetch_rewards.receipt_processor.service.ReceiptServicesImpl;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/receipts")
public class controllerV1 {

    static final Logger logger = LoggerFactory.getLogger(controllerV1.class);

    @Autowired
    ProcessData processData;

    @Autowired
    PointsServicesImpl pointsServices;

    @Autowired
    ProductServicesImpl productServices;

    @Autowired
    ReceiptServicesImpl receiptServices;

    @ApiResponses({
            @ApiResponse(responseCode = "202", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PostEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Unable to process request",
                    content = @Content)
    })
    @PostMapping("/process")
    public ResponseEntity<PostEntity> processReceipts(@RequestBody Receipt givenReceipt) throws JsonProcessingException, CustomException {
        MDC.clear();
        MDC.put("context", UUID.randomUUID().toString());

        logger.info("Method Post Received request to parse {} at {}", givenReceipt, LocalDate.now());

        processData.readReceipt(givenReceipt);

        receiptServices.addReceipt(givenReceipt);

        return new ResponseEntity<>(new PostEntity(givenReceipt.getReceiptId()), HttpStatus.ACCEPTED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "202", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Unable to process request", content = @Content)
    })
    @GetMapping("/{id}/points")
    public ResponseEntity<GetEntity> getPoints(@PathVariable("id") String receiptId ) throws NotFoundException {
        MDC.clear();
        MDC.put("context", UUID.randomUUID().toString());

        logger.info("Method Get Received request to parse {} at {}", receiptId, LocalDate.now());

        Integer finalPoints = pointsServices.calculatePointsForReceipt(receiptId);

        return new ResponseEntity<>(new GetEntity(finalPoints), HttpStatus.OK);
    }

    @ExceptionHandler({JsonProcessingException.class, CustomException.class})
    public ResponseEntity<String> parseException(HttpServletRequest req, Exception ex) {
        logger.info("{} could not serve request {}", req.getRequestURI(), ex.getMessage());
        return new ResponseEntity<>("Unable to process request", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> parseException2(HttpServletRequest req, Exception ex) {
        logger.info("{} could find receipt {} for request {}", req.getRequestURI(), req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE), ex.getMessage());
        return new ResponseEntity<>("Unable to find receipt", HttpStatus.NOT_FOUND);
    }

}
