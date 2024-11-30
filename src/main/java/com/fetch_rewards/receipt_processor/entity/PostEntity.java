package com.fetch_rewards.receipt_processor.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostEntity {
    @JsonProperty("id")
    private String uid;

    public PostEntity(){

    }

    public PostEntity(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "uid='" + uid + '\'' +
                '}';
    }
}
