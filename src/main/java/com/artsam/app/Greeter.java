package com.artsam.app;

import com.artsam.app.tools.MyBundleControl;
import org.apache.log4j.Logger;

import java.time.Clock;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

class Greeter {

    static final Logger logger = Logger.getLogger(Greeter.class);

    // period position numbers
    static final int DEFAULT = -1;
    static final int MORNING = 0;
    static final int DAY = 1;
    static final int EVENING = 2;
    static final int NIGHT = 3;

    // period start timestamps
    private static final LocalTime SIX = LocalTime.parse("06:00:00");
    private static final LocalTime NINE = LocalTime.parse("09:00:00");
    private static final LocalTime SEVENTEEN = LocalTime.parse("19:00:00");
    private static final LocalTime TWENTY_THREE = LocalTime.parse("23:00:00");

    // if U will add a period, its name has to be added into this array
    // in position witch match a period pos number;
    // actually, this array has to be saved in res file (like R.id... in android)
    private static final String[] periodNames = new String[]{"app.good_morning",
            "app.good_day", "app.good_evening", "app.good_night", "app.default"};

    void greet() {
        LocalTime nowUtcTime = LocalTime.now(Clock.systemUTC());
        logger.info("greet() Timestamp is: " + nowUtcTime.toString());

        int period = determineInterval(nowUtcTime);

        String greetingText = getGreetingSentence(period);

        System.out.println(greetingText);
        logger.info("greet() Process finished correctly");
    }

    int determineInterval(LocalTime nowUtcTime) {
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
        logger.info("determineInterval() period is:" + period);
        return period;
    }

    String getGreetingSentence(int period) {
//        Locale.setDefault(new Locale("en", "US")); // if You want to try eng lang
//        Locale.setDefault(new Locale("ru", "UA")); // if You want to try rus lang
        String greetingText;
        ResourceBundle bundle = ResourceBundle.getBundle(
                "bundle", Locale.getDefault(), new MyBundleControl());

        greetingText = bundle.getString(periodNames[period]);
        logger.info("getGreetingSentence() greeting text is: " + greetingText);
        return greetingText;
    }
}
