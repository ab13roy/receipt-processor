package com.fetch_rewards.receipt_processor.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    private String receiptId;

    @JsonProperty("shortDescription")
    private String shortDescription;

    @JsonProperty("price")
    private double cost;

    public Product() {

    }

    public Product(String receiptId, String name, double cost) {
        this.receiptId = receiptId;
        this.shortDescription = name;
        this.cost = cost;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
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
                "receiptId='" + receiptId + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", cost=" + cost +
                '}';
    }
}
