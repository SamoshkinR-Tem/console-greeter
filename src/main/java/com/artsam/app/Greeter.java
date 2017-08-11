package com.artsam.app;

import com.artsam.app.tools.MyBundleControl;

import java.time.Clock;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

class Greeter {

    public static final int DEFAULT = -1;
    public static final int MORNING = 0;
    public static final int DAY = 1;
    public static final int EVENING = 2;
    public static final int NIGHT = 3;

    private static final LocalTime SIX = LocalTime.parse("06:00:00");
    private static final LocalTime NINE = LocalTime.parse("09:00:00");
    private static final LocalTime SEVENTEEN = LocalTime.parse("19:00:00");
    private static final LocalTime TWENTY_THREE = LocalTime.parse("23:00:00");

    private static Logger log = Logger.getLogger(App.class.getName());

    public void greet() {
        int period = determineInterval();

        String greetingText = getRightSentence(period);

        displayValue(greetingText);
    }

    public int determineInterval() {
        LocalTime nowUtcTime = LocalTime.now(Clock.systemUTC());
        if (nowUtcTime.equals(SIX) ||
                nowUtcTime.isAfter(SIX) &&
                        nowUtcTime.isBefore(NINE)) {
            return MORNING;
        } else if (nowUtcTime.equals(NINE) ||
                nowUtcTime.isAfter(NINE) &&
                        nowUtcTime.isBefore(SEVENTEEN)) {
            return DAY;
        } else if (nowUtcTime.equals(SEVENTEEN) ||
                nowUtcTime.isAfter(SEVENTEEN) &&
                        nowUtcTime.isBefore(TWENTY_THREE)) {
            return EVENING;
        } else if (nowUtcTime.equals(TWENTY_THREE) ||
                nowUtcTime.isAfter(TWENTY_THREE) &&
                        nowUtcTime.isBefore(SIX)) {
            return NIGHT;
        }
        return DEFAULT;
    }

    public String getRightSentence(int period) {
        // new Locale("en", "US")   if You want english language
        ResourceBundle bundle = ResourceBundle.getBundle(
                "Bundle", Locale.getDefault(), new MyBundleControl());
        switch (period) {
            case MORNING:
                return bundle.getString("app.good_morning");
            case DAY:
                return bundle.getString("app.good_day");
            case EVENING:
                return bundle.getString("app.good_evening");
            case NIGHT:
                return bundle.getString("app.good_night");
            default:
                return bundle.getString("app.default");
        }
    }

    public void displayValue(String message) {
        System.out.println(message);
    }
}
