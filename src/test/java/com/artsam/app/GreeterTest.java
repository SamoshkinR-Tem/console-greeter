package com.artsam.app;

import com.artsam.app.tools.MyLogger;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;

public class GreeterTest {

    private int period;

    public GreeterTest() {
    }

    @Before
    public void executedBeforeEach() {
        Greeter greeter = new Greeter();
        LocalTime nowUtcTime = LocalTime.now(Clock.systemUTC());
        period = greeter.determineInterval(nowUtcTime);
    }

    @Test
    public void determineInterval() {
        try {
            testDetermination(Greeter.NIGHT, LocalTime.parse("03:00:00"));
            testDetermination(Greeter.MORNING, LocalTime.parse("06:00:00"));
            testDetermination(Greeter.MORNING, LocalTime.parse("07:00:00"));
            testDetermination(Greeter.DAY, LocalTime.parse("09:00:00"));
            testDetermination(Greeter.DAY, LocalTime.parse("11:00:00"));
            testDetermination(Greeter.EVENING, LocalTime.parse("19:00:00"));
            testDetermination(Greeter.EVENING, LocalTime.parse("20:00:00"));
            testDetermination(Greeter.NIGHT, LocalTime.parse("23:00:00"));
            testDetermination(Greeter.NIGHT, LocalTime.parse("23:30:00"));
            testDetermination(Greeter.DEFAULT, LocalTime.parse("24:00:00"));
        } catch (DateTimeParseException e){
            e.printStackTrace();
        }
    }

    private void testDetermination(int expected, LocalTime time) {
        assertEquals(expected,
                new Greeter().determineInterval(time));
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