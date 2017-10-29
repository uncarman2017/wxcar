package com.kxcar.wxcar.business.model;

/**
 * Created by jiaweiyu on 2016/12/30.
 */
public enum DateRangeType
{
    All(-1),
    Today(0),
    RecentThreeDays(1),
    RecentOneWeek(2),
    RecentOneMonth(3);

    private final int value;

    DateRangeType(int value) {
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
                result = "today";
                break;
            case 1:
                result = "recentThreeDays";
                break;
            case 2:
                result = "recentOneWeek";
                break;
            case 3:
                result = "recentOneMonth";
                break;
        }

        return result;
    }
    /**
     * Get the integer value of this enum value, as defined in the Baiji IDL.
     */
    public static DateRangeType findByValue(int value) {
        switch (value) {
            case -1:
                return All;
            case 0:
                return Today;
            case 1:
                return RecentThreeDays;
            case 2:
                return RecentOneWeek;
            case 3:
                return RecentOneMonth;
            default:
                return null;
        }
    }

    public static DateRangeType findByStringValue(String value) {
        switch (value.toLowerCase()) {
            case "today":
                return Today;
            case "recentthreedays":
                return RecentThreeDays;
            case "recentoneweek":
                return RecentOneWeek;
            case "recentonemonth":
                return RecentOneMonth;
            default:
                return null;
        }
    }
}
