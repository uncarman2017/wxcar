package com.kxcar.wxcar.util.text;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by jiaweiyu on 2016/12/20.
 */
public class LogHelper
{
    //log4j定义了8个级别的log（除去OFF和ALL，可以说分为6个级别），优先级从高到低依次为：OFF、FATAL、ERROR、WARN、INFO、DEBUG、TRACE、 ALL。
    //如果将log level设置在某一个级别上，那么比此级别优先级高的log都能打印出来。
    // 例如，如果设置优先级为WARN，那么OFF、FATAL、ERROR、WARN 4个级别的log能正常输出，而INFO、DEBUG、TRACE、 ALL级别的log则会被忽略。从我们实验的结果可以看出，log4j默认的优先级为ERROR或者WARN（实际上是ERROR）。
    private static Logger logger = LogManager.getLogger("wxcar");


    public static void error(Exception e){
//        logger.entry();
        logger.error(e);
    }

    public static  void error(String errMsg){
        logger.error(errMsg);
    }


    public static void info(String msg){
        logger.info(msg);
    }


    public static void warn(String msg){
        logger.warn(msg);
    }
}
