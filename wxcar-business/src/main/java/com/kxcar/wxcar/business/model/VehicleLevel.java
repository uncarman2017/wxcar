package com.kxcar.wxcar.business.model;

/**
 * Created by jiaweiyu on 2016/12/30.
 */
public enum VehicleLevel
{
    Economy(0),
    Comfort(1),
    Business(2);

    private final int value;

    VehicleLevel(int value) {
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
                result = "经济型";
                break;
            case 1:
                result = "舒适型";
                break;
            case 2:
                result = "商务型";
                break;
        }

        return result;
    }
    /**
     * Get the integer value of this enum value, as defined in the Baiji IDL.
     */
    public static VehicleLevel findByValue(int value) {
        switch (value) {
            case 0:
                return Economy;
            case 1:
                return Comfort;
            case 2:
                return Business;
            default:
                return null;
        }
    }

    public static VehicleLevel findByStringValue(String value) {
        switch (value.toLowerCase()) {
            case "经济型":
                return Economy;
            case "舒适型":
                return Comfort;
            case "商务型":
                return Business;
            default:
                return null;
        }
    }
}
