package com.example.finalprojectapi.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class AppLogManager {

    public static String  requestInfo (String Url) {
        return "[Request To "+ Url +"]\n";
    }
    public static String responseInfo(String Url) {
        return "[Response From "+ Url +"]\n";
    }

    private static String getCallerAsString(int level) {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        String rawFQN = stElements[level+1].toString().split("\\(")[0];

//        System.out.println(rawFQN);

        return rawFQN.substring(0, StringUtils.lastIndexOf(rawFQN, "."));
    }

    private static String[] parseCaller(String str, Object x) {
        return new String[]{
                str
            ,   MessageFormat.format("{0}:{1} - {2}", StringUtils.substringAfterLast(str, ".") + ".java", Thread.currentThread().getStackTrace()[4].getLineNumber(), String.valueOf(x))
        };
    }

    private static String[] parseCaller(Object x) {
        return parseCaller(getCallerAsString(3), String.valueOf(x));
    }

    public static void debug(Object x) {
        String[] parsed = parseCaller(String.valueOf(x));
        debug(parsed[0], parsed[1]);
    }

    public static void error(Object x) {
        String[] parsed = parseCaller(String.valueOf(x));
        error(parsed[0], parsed[1]);
    }

    public static void info(Object x) {
        String[] parsed = parseCaller(String.valueOf(x));
        info(parsed[0], parsed[1]);
    }

    public static void error(Throwable ex) {
        String[] parsed = parseCaller(ex.getMessage());
        error(parsed[0], ex.getMessage(), ex);
    }


    public static void debug(String category, Object x) {
        Logger logger = getLogger(category);
        debug(logger, String.valueOf(x));
    }

    private static void debug(Logger logger, Object x) {
        logger.debug("####################################################################################################");
        logger.debug(String.valueOf(x));
        logger.debug("####################################################################################################");
    }


    private static void error(Logger logger, Object x) {
        error(logger, String.valueOf(x), null);
    }

    private static void error(Logger logger, Object x, Throwable ex) {
        logger.error("####################################################################################################");
        logger.error(String.valueOf(x));
        logger.error("####################################################################################################");

        if(ex != null) {
            ex.printStackTrace();
        }
    }

    public static void info(Class<?> caller, Object x) {
        String[] parsed = parseCaller(String.valueOf(x));
        info(caller.getName(), parsed[1]);
    }

    public static void info(String category, Object x) {
        Logger logger = LoggerFactory.getLogger(category);
        info(logger, String.valueOf(x));
    }

    public static void error(String category, Object x) {
        error(category, String.valueOf(x), null);
    }

    public static void error(String category, Object x, Throwable ex) {
        Logger logger = LoggerFactory.getLogger(category);
        error(logger, String.valueOf(x), ex);
    }

    private static void info(Logger logger, Object x) {
        logger.info(String.valueOf(x));
    }

    public static Logger getLogger(String category) {
        if(StringUtils.isBlank(category)) return getRootLogger();
        return LoggerFactory.getLogger(category);
    }

    public static Logger getRootLogger() {
        return LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    }

    public static void main(String[] args) {
        info("test");
    }

};