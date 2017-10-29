package com.kxcar.wxcar.util.config;

/**
 * Created by jiaweiyu on 2016/10/26.
 */
public class AppConfig
{
    private String fxConfigServiceUrl;
    private String appId = "";
    private String subEnv = "";
    private String redisUrl = "";
    private String redisClusterName = "";
    private String loggingServerIP = "";
    private String loggingServerPort = "";

    public String getFxConfigServiceUrl() {return fxConfigServiceUrl;};

    public void setFxConfigServiceUrl(String value) {fxConfigServiceUrl = value;};

    public String getAppId() {return appId;};

    public void setAppId(String value) {appId = value;};

    public String getSubEnv() {return subEnv;};

    public void setSubEnv(String value) {subEnv = value;};


    public String getRedisUrl() {return redisUrl;};

    public void setRedisUrl(String value){redisUrl = value;}

    public String getRedisClusterName() {return redisClusterName;};

    public void setRedisClusterName(String value){redisClusterName = value;};

    public String getLoggingServerIP(){return loggingServerIP;};

    public void setLoggingServerIP(String value){loggingServerIP = value;};

    public String getLoggingServerPort(){return loggingServerPort;};

    public void setLoggingServerPort(String value){loggingServerPort = value;};



}
