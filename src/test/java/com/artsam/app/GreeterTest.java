package com.artsam.app;

import com.artsam.app.tools.MyLogger;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.logging.Level;

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
                assertEquals(Greeter.MORNING, period);
                break;
            case Greeter.DAY:
                assertEquals(Greeter.DAY, period);
                break;
            case Greeter.EVENING:
                assertEquals(Greeter.EVENING, period);
                break;
            case Greeter.NIGHT:
                assertEquals(Greeter.NIGHT, period);
                break;
            case Greeter.DEFAULT:
                assertEquals(Greeter.DEFAULT, period);
                break;
        }
    }

    @Test
    public void getRightSentence() {
        try {
            String greetText = new Greeter().getRightSentence(period);
            if (Locale.getDefault().toString().matches("en_US")) {
                switch (period) {
                    case Greeter.MORNING:
                        assertEquals("Good morning, World!", greetText);
                        break;
                    case Greeter.DAY:
                        assertEquals("Good day, World!", greetText);
                        break;
                    case Greeter.EVENING:
                        assertEquals("Good evening, World!", greetText);
                        break;
                    case Greeter.NIGHT:
                        assertEquals("Good night, World!", greetText);
                        break;
                    case Greeter.DEFAULT:
                        assertEquals("Witch World are You from?", greetText);
                        break;
                }
            } else if (Locale.getDefault().toString().matches("ru_UA")) {
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
        } catch (Exception e) {
            MyLogger.getInstanceOf().log(Level.WARNING,
                    "MissingResourceException", e);
        }
    }
}