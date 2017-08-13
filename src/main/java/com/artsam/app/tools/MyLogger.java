package com.artsam.app.tools;

import java.io.IOException;
import java.util.logging.*;

public class MyLogger {

    private static final String LOGGER_TAG = "MyLog";
    private static final String DEF_LOG_FILE_NAME = "log.xml";
    private static final int CLASS_NAME = 0;
    private static final int METHOD_NAME = 1;
    private static final int DEPTH = 4;

    private static MyLogger instanceOf;

    private boolean append = true;
    private boolean showInConsole = true;
    private String logFileName = DEF_LOG_FILE_NAME;
    private Level level = Level.INFO;
    private FileHandler fh = null;
    private Logger logger = Logger.getLogger(LOGGER_TAG);

    private MyLogger() {
    }

    public static MyLogger getInstanceOf() {
        if (instanceOf == null) {
            instanceOf = new MyLogger();
        }
        return instanceOf;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        try {
            if (logFileName.length() < 1 && !logFileName.contains(".xml")) {
                throw new IllegalArgumentException();
            } else {
                this.logFileName = logFileName;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void log() {
        log(level, null, null);
    }

    public void log(String msg) {
        log(level, msg, null);
    }

    public void log(Level level, String msg) {
        log(level, msg, null);
    }

    /**
     * log Method enable to log all exceptions to a file
     * and display user message on demand
     *
     * @param level
     * @param msg
     * @param ex
     */
    public void log(Level level, String msg, Exception ex) {
        if (msg == null) {
            msg = "Ok";
        }
        try {
            String[] classNameAndMethod = getCallerClassNameAndMethod();
            fh = new FileHandler(logFileName, append);
            LogRecord lr = new LogRecord(level, msg);
            lr.setSourceClassName(classNameAndMethod[CLASS_NAME]);
            lr.setSourceMethodName(classNameAndMethod[METHOD_NAME]);
            fh.setFormatter(new MyFormatter(lr, level));
            logger.addHandler(fh);
            logger.setUseParentHandlers(showInConsole); // To remove the console handler, use false
            logger.log(level, msg, ex);
        } catch (IOException | SecurityException e) {
            logger.log(Level.SEVERE, null, e);
        } finally {
            if (fh != null) fh.close();
        }
    }

    public static String[] getCallerClassNameAndMethod() {
        String[] data = new String[2];
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        StackTraceElement ste = stElements[DEPTH];
        data[CLASS_NAME] = ste.getClassName();
        data[METHOD_NAME] = ste.getMethodName();
        return data;
    }
}
