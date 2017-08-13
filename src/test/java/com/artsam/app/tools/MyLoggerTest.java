package com.artsam.app.tools;

import org.junit.Before;
import org.junit.Test;

import java.util.logging.Level;

public class MyLoggerTest {

    MyLogger ml;
    String logFileName = "logTestTmp.xml";

    @Before
    public void createInst() {
        ml = MyLogger.getInstanceOf();
        ml.setLogFileName(logFileName);
        ml.setAppend(false);
    }

    @Test
    public void log1() throws Exception {
        ml.log(Level.SEVERE, "test SEVERE", null);
    }

    @Test
    public void log2() throws Exception {
        ml.log(Level.CONFIG, "test CONFIG", null);
    }

    @Test
    public void log3() throws Exception {
        ml.log(Level.FINE, "test FINE", null);
    }

    @Test
    public void log4() throws Exception {
        ml.log(Level.FINER, "test FINER", null);
    }

    @Test
    public void log5() throws Exception {
        ml.log(Level.FINEST, "test FINEST", null);
    }

    @Test
    public void log6() throws Exception {
        ml.log(Level.ALL, "test ALL", null);
    }

    @Test
    public void log7() throws Exception {
        ml.log(Level.OFF, "test OFF", null);
    }

    @Test
    public void log8() throws Exception {
        ml.log(Level.WARNING, "test WARNING", null);
    }

    @Test
    public void log9() throws Exception {
        ml.log(Level.INFO, "test INFO", null);
    }
}