package com.htjy.log;

import org.apache.log4j.Logger;

/**
 * Created By willz
 * Date : 2020/10/10
 * Time: 16:06
 */
public class LogTest {

    private static Logger logger = Logger.getLogger(LogTest.class);
    public static void main(String[] args) {
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");
    }
}
