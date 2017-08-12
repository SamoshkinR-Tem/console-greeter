package com.artsam.app.tools;

import sun.util.logging.LoggingSupport;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class MyFormatter extends SimpleFormatter {
    private final Date dat = new Date();
    private static final String format = LoggingSupport.getSimpleFormat();
    private String level;
    private LogRecord logRecord;

    public MyFormatter(LogRecord logRecord, Level level) {
        this.logRecord = logRecord;
        this.level = level.toString();
    }

    @Override
    public synchronized String format(LogRecord var1) {
        dat.setTime(logRecord.getMillis());
        String var2;
        if (logRecord.getSourceClassName() != null) {
            var2 = logRecord.getSourceClassName();
            if (logRecord.getSourceMethodName() != null) {
                var2 = var2 + " " + logRecord.getSourceMethodName();
            }
        } else {
            var2 = logRecord.getLoggerName();
        }

        String var3 = this.formatMessage(logRecord);
        String var4 = "";
        if (logRecord.getThrown() != null) {
            StringWriter var5 = new StringWriter();
            PrintWriter var6 = new PrintWriter(var5);
            var6.println();
            logRecord.getThrown().printStackTrace(var6);
            var6.close();
            var4 = var5.toString();
        }

        return String.format(format, this.dat, var2,
                logRecord.getLoggerName(),
                level,
                var3, var4);
    }
}
