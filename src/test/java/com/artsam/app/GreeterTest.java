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

    @Test
    public void determineInterval() {
        try {
            testDet(Greeter.NIGHT, LocalTime.parse("03:00:00"));
            testDet(Greeter.MORNING, LocalTime.parse("06:00:00"));
            testDet(Greeter.MORNING, LocalTime.parse("07:00:00"));
            testDet(Greeter.DAY, LocalTime.parse("09:00:00"));
            testDet(Greeter.DAY, LocalTime.parse("11:00:00"));
            testDet(Greeter.EVENING, LocalTime.parse("19:00:00"));
            testDet(Greeter.EVENING, LocalTime.parse("20:00:00"));
            testDet(Greeter.NIGHT, LocalTime.parse("23:00:00"));
            testDet(Greeter.NIGHT, LocalTime.parse("23:30:00"));
            testDet(Greeter.DEFAULT, LocalTime.parse("24:00:00"));
        } catch (DateTimeParseException e){
            e.printStackTrace();
        }
    }

    private void testDet(int expected, LocalTime time) {
        assertEquals(expected,
                new Greeter().determineInterval(time));
    }

    @Test
    public void getGreetingSentence() {
        testGetGS(new Locale("en", "US"),
                Greeter.DAY, "Good day, World!"); // for eng lang

        testGetGS(new Locale("ru", "RU"),
                Greeter.NIGHT, "Доброй ночи, Мир!"); // for rus lang
    }

    private void testGetGS(Locale locale, int period, String expected) {
        try {
            Locale.setDefault(locale);
            assertEquals(expected,
                    new Greeter().getGreetingSentence(period));
        } catch (Exception e) {
            MyLogger.getInstanceOf().log(Level.WARNING,
                    "MissingResourceException", e);
        }
    }
}