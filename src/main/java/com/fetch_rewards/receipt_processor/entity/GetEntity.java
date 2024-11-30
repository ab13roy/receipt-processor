package com.fetch_rewards.receipt_processor.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetEntity {
    @JsonProperty("points")
    private Integer points;

    public GetEntity(){

    }

    public GetEntity(int points) {
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "GetEntity{" +
                "points=" + String.valueOf(points) +
                '}';
    }
}
