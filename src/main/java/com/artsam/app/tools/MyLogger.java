package com.artsam.app.tools;

import com.artsam.app.App;

import java.io.IOException;
import java.util.logging.*;

public class MyLogger {

    private static final String LOGGER_TAG = "MyLog";
    private static final String DEF_LOG_FILE_NAME = "log.xml";

    private String className;
    private String logFileName = DEF_LOG_FILE_NAME;
    private boolean append = true;
    private boolean showInConsole = true;
    private FileHandler fh = null;
    private Logger logger = Logger.getLogger(LOGGER_TAG);

    public MyLogger(String className) {
        this.className = className;
    }

    public MyLogger(String className, String logFileName) {
        try {
            if (logFileName.length() < 1 || !logFileName.contains(".xml")) {
                throw new IllegalArgumentException();
            } else {
                this.className = className;
                this.logFileName = logFileName;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public MyLogger(String className, boolean append, boolean showInConsole) {
        this.className = className;
        this.append = append;
        this.showInConsole = showInConsole;
    }

    public boolean isAppend() {
        return append;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    public boolean isShowInConsole() {
        return showInConsole;
    }

    public void setShowInConsole(boolean showInConsole) {
        this.showInConsole = showInConsole;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void log(Level level, String methodName) {
        log(level, methodName, null, null);
    }

    public void log(Level level, String methodName, String msg) {
        log(level, methodName, msg, null);
    }

    /**
     * log Method enable to log all exceptions to a file
     * and display user message on demand
     *
     * @param level
     * @param msg
     * @param ex
     */
    public void log(Level level, String methodName, String msg, Exception ex) {
        if (msg==null) {
            msg = "Ok";
        }
        try {
            fh = new FileHandler(logFileName, append);
            LogRecord lr = new LogRecord(level, msg);
            lr.setSourceClassName(className);
            lr.setSourceMethodName(methodName);
            fh.setFormatter(new MyFormatter(lr, level));
            logger.addHandler(fh);
            logger.setUseParentHandlers(showInConsole); // To remove the console handler, use false
            logger.log(level, msg, ex);
        } catch (IOException | SecurityException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        } finally {
            if (fh != null) fh.close();
        }
    }
}
