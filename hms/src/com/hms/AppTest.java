package com.hms;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
    @Test
    public void test() {
        String data = "testname" +
            "\ntestDoB" +
            "\ntestuser" +
            "\ntestpass";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        assertEquals(2, 1+1);
    }
}
