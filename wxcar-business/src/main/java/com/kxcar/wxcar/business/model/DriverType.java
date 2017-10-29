package com.kxcar.wxcar.business.model;

/**
 * Created by uncar_000 on 2016-12-31.
 */
public enum DriverType
{
    Internal(0),  //自营司机
    External(1);    //外围司机

    private final int value;

    DriverType(int value) {
        this.value = value;
    }

    /**
     * Get the integer value of this enum value, as defined in the Baiji IDL.
     */
    public int getValue() {
        return value;
    }

    public String getStringValue() {
        String result = "";
        switch (value) {
            case 0:
                result = "internal";
                break;
            case 1:
                result = "external";
                break;

        }

        return result;
    }

    /**
     * Get the integer value of this enum value, as defined in the Baiji IDL.
     */
    public static DriverType findByValue(int value) {
        switch (value) {
            case 0:
                return Internal;
            case 1:
                return External;
            default:
                return null;
        }
    }

    public static DriverType findByStringValue(String value) {

        switch (value.toLowerCase()) {
            case "internal":
                return DriverType.Internal;
            case "external":
                return DriverType.External;
            default:
                return null;
        }//switch

    }
}
