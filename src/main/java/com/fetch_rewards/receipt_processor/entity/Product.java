package com.fetch_rewards.receipt_processor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name ="receiptId")
    private Receipt receipt;

    @JsonProperty("shortDescription")
    private String shortDescription;

    @JsonProperty("price")
    private double cost;

    public Product() {

    }

    public Product(Receipt receipt, String name, double cost) {
        this.receipt = receipt;
        this.shortDescription = name;
        this.cost = cost;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "receiptId='" + receipt.getReceiptId() + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", cost=" + cost +
                '}';
    }
}
