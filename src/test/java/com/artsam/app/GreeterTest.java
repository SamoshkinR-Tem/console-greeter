package com.artsam.app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GreeterTest {

    private int period;

    public GreeterTest() {
    }

    @Before
    public void executedBeforeEach() {
        Greeter greeter = new Greeter();
        period = greeter.determineInterval();
    }

    @Test
    public void determineInterval() {
        switch (period) {
            case Greeter.MORNING:
                assertEquals(0, period);
                break;
            case Greeter.DAY:
                assertEquals(1, period);
                break;
            case Greeter.EVENING:
                assertEquals(2, period);
                break;
            case Greeter.NIGHT:
                assertEquals(3, period);
                break;
            case Greeter.DEFAULT:
                assertEquals(-1, period);
                break;
        }
    }


    @Test
    public void getRightSentence() {
        String greetText = new Greeter().getRightSentence(period);
        switch (period) {
            case Greeter.MORNING:
                assertEquals("Доброе утро, Мир!", greetText);
                break;
            case Greeter.DAY:
                assertEquals("Добрый день, Мир!", greetText);
                break;
            case Greeter.EVENING:
                assertEquals("Добрый вечер, Мир!", greetText);
                break;
            case Greeter.NIGHT:
                assertEquals("Доброй ночи, Мир!", greetText);
                break;
            case Greeter.DEFAULT:
                assertEquals("Вы с какой планеты?", greetText);
                break;
        }
    }
}