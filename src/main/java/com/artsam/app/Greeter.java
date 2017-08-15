package com.artsam.app;

import com.artsam.app.tools.MyBundleControl;

import java.time.Clock;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

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
    private static final LocalTime MIDNIGHT = LocalTime.parse("00:00:00");


    public void greet() {
        App.logger.log();
        LocalTime nowUtcTime = LocalTime.now(Clock.systemUTC());
        int period = determineInterval(nowUtcTime);
        App.logger.log("current period: " + period);
        String greetingText = getRightSentence(period);
        App.logger.log("text: " + greetingText + " just shown");
        displayValue(greetingText);
    }

    public int determineInterval(LocalTime nowUtcTime) {
        int period = DEFAULT;
        if (nowUtcTime.equals(SIX) ||
                nowUtcTime.isAfter(SIX) &&
                        nowUtcTime.isBefore(NINE)) {
            period = MORNING;
        } else if (nowUtcTime.equals(NINE) ||
                nowUtcTime.isAfter(NINE) &&
                        nowUtcTime.isBefore(SEVENTEEN)) {
            period = DAY;
        } else if (nowUtcTime.equals(SEVENTEEN) ||
                nowUtcTime.isAfter(SEVENTEEN) &&
                        nowUtcTime.isBefore(TWENTY_THREE)) {
            period = EVENING;
        } else if (nowUtcTime.equals(TWENTY_THREE) ||
                (nowUtcTime.isAfter(TWENTY_THREE)) ||
                nowUtcTime.isBefore(SIX)) {
            period = NIGHT;
        }
        return period;
    }

    public String getRightSentence(int period) {
        App.logger.log();
//        Locale.setDefault(new Locale("en", "US")); // if You want to try eng lang
        ResourceBundle bundle = ResourceBundle.getBundle(
                "bundle", Locale.getDefault(), new MyBundleControl());
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
        App.logger.log("Job done!");
    }
}
