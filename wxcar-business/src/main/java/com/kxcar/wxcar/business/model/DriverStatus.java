package com.kxcar.wxcar.business.model;

/**
 * Created by uncar_000 on 2016-12-31.
 */
public enum DriverStatus
{
    Disable(0),
    Enable(1);

    private final int value;

    DriverStatus(int value) {
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
                result = "disable";
                break;
            case 1:
                result = "enable";
                break;

        }

        return result;
    }

    /**
     * Get the integer value of this enum value, as defined in the Baiji IDL.
     */
    public static DriverStatus findByValue(int value) {
        switch (value) {
            case 0:
                return Disable;
            case 1:
                return Enable;
            default:
                return null;
        }
    }

    public static DriverStatus findByStringValue(String value) {

        switch (value.toLowerCase()) {
            case "disable":
                return DriverStatus.Disable;
            case "enable":
                return DriverStatus.Enable;
            default:
                return null;
        }//switch

    }
}
