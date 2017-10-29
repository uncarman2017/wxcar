package com.kxcar.wxcar.util.web;

/**
 * Created by uncar_000 on 2016-12-31.
 */
public class CookieConstantTable
{
    // cookie的有效期默认为30天
    public final static int COOKIE_MAX_AGE = 60 * 60 * 24 * 10; //单位:秒
    //cookie加密时的额外的salt
    public final static String salt = "www.kxcar.cn";
    //自动登录的Cookie名
    public final static String RememberMe = "anonymous";
}
