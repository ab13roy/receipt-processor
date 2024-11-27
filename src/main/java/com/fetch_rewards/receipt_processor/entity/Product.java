package com.fetch_rewards.receipt_processor.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    @JsonProperty("shortDescription")
    private String shortDescription;

    @JsonProperty("price")
    private double cost;

    public Product() {

    }

    public Product(String name, double cost) {
        this.shortDescription = name;
        this.cost = cost;
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
        return "Items{" +
                "shortDescription='" + shortDescription + '\'' +
                ", cost=" + cost +
                '}';
    }
}
