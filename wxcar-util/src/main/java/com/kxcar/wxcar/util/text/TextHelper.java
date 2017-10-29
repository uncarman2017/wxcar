package com.kxcar.wxcar.util.text;

/**
 * Created by jiaweiyu on 2016/11/1.
 */
public class TextHelper
{
    public static boolean isNullOrEmpty(String txt){
        return txt == null || txt.isEmpty();
    }

    public static boolean isNullOrWhiteSpace(String txt){
        return txt == null || txt.indexOf(' ',0) > 0;
    }
}
