package com.artsam.app;

import com.artsam.app.tools.MyLogger;

import java.util.logging.Level;

/**
 * Good ?, world!
 */
public class App {

    public static final MyLogger logger = MyLogger.getInstanceOf();

    public static void main(String[] args) {
        logger.setLevel(Level.INFO);
        logger.setAppend(true);
        logger.setShowInConsole(false);
        logger.log("Job Started");
        new Greeter().greet();
    }
}
