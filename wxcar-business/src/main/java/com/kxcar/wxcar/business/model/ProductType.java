package com.kxcar.wxcar.business.model;

/**
 * Created by jiaweiyu on 2016/12/30.
 */
public enum ProductType
{
    JieJi(0),
    SongJi(1);

    private final int value;

    ProductType(int value) {
        this.value = value;
    }

    /**
     * Get the integer value of this enum value, as defined in the Baiji IDL.
     */
    public int getValue() {
        return value;
    }

    public String getStringValue(){
        String result = "";
        switch (value){
            case 0:
                result = "国内接机";
                break;
            case 1:
                result = "国内送机";
                break;
        }

        return result;
    }
    /**
     * Get the integer value of this enum value, as defined in the Baiji IDL.
     */
    public static ProductType findByValue(int value) {
        switch (value) {
            case 0:
                return JieJi;
            case 1:
                return SongJi;
            default:
                return null;
        }
    }

    public static ProductType findByStringValue(String value) {
        switch (value.toLowerCase()) {
            case "国内接机":
                return JieJi;
            case "国内送机":
                return SongJi;
            default:
                return null;
        }
    }
}
