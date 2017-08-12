package com.artsam.app;

import com.artsam.app.tools.MyLogger;

import java.util.logging.Level;

/**
 * Good ?, world!
 */
public class App {

    public static MyLogger logger = new MyLogger(App.class.getName());

    public static void main(String[] args) {
        logger.setAppend(false);
        logger.setShowInConsole(false);
        logger.log(Level.INFO, "main()");
        new Greeter().greet();
    }
}
