package com.artsam.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GreeterTest {

    public GreeterTest() {
    }

    @Test
    public void determineInterval() {
        Greeter greeter = new Greeter();
        int period = greeter.determineInterval();
        assertEquals(1, period);
    }

    @Test
    public void getRightSentence() {
        String greetText = new Greeter().getRightSentence(1);
        assertEquals("Добрый день, Мир!", greetText);
    }
}