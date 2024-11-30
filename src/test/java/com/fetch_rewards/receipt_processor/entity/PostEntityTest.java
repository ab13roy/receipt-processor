package com.fetch_rewards.receipt_processor.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PostEntityTest {

    @Test
    public void testPostEntityMethods() {
        PostEntity postEntity = new PostEntity();
        postEntity.setUid("123");

        assertNotNull(postEntity);
        assertEquals("123", postEntity.getUid());
        assertNotEquals(0, postEntity.toString().length());
    }

}
