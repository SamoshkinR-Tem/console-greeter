package com.artsam.app.tools;

import org.junit.Before;
import org.junit.Test;

import java.util.logging.Level;

import static org.junit.Assert.*;

public class LogToFileTest {

    MyLogger ltf;
    String logFileName;
    boolean append = false;

    @Before
    public void createInst() {
        if (logFileName == null || logFileName.matches("") && ltf == null) {
            logFileName = "logTestTmp.xml";
            ltf = new MyLogger(LogToFileTest.class.getName(),logFileName);
            ltf.setAppend(false);
        }
    }

    @Test
    public void logToFile1Param() {
        MyLogger ltf = new MyLogger(LogToFileTest.class.getName());
        ltf.setAppend(append);
        assertEquals(ltf.isAppend(), append);
    }

    @Test
    public void logToFile2Params() {
        assertEquals(ltf.getLogFileName(), logFileName);
        assertEquals(ltf.isAppend(), append);
    }

    @Test
    public void log1() throws Exception {
        ltf.log(Level.SEVERE, "test SEVERE", null);
    }

    @Test
    public void log2() throws Exception {
        ltf.log(Level.CONFIG, "test CONFIG", null);
    }

    @Test
    public void log3() throws Exception {
        ltf.log(Level.FINE, "test FINE", null);
    }

    @Test
    public void log4() throws Exception {
        ltf.log(Level.FINER, "test FINER", null);
    }

    @Test
    public void log5() throws Exception {
        ltf.log(Level.FINEST, "test FINEST", null);
    }

    @Test
    public void log6() throws Exception {
        ltf.log(Level.ALL, "test ALL", null);
    }

    @Test
    public void log7() throws Exception {
        ltf.log(Level.OFF, "test OFF", null);
    }
}