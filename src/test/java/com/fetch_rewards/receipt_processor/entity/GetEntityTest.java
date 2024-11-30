package com.fetch_rewards.receipt_processor.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetEntityTest {

    @Test
    public void testGetEntityMethods() {
        GetEntity getEntity = new GetEntity();

        getEntity.setPoints(100);

        assertEquals(100, getEntity.getPoints());
        assertNotNull(getEntity.toString());
        assertNotEquals(0, getEntity.toString().length());
    }

}
