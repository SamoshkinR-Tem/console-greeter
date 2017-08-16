package com.artsam.app;

import org.apache.log4j.Logger;

/**
 * Good ?, world!
 */
public class App {

    static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("main(): application started");
        new Greeter().greet();
    }
}
