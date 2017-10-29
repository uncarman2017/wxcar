package com.kxcar.wxcar.business.model;

/**
 * Created by jiaweiyu on 2016/12/30.
 */
public enum OrderStatus {
    All(-1),
    Submitted(0),
    Pending(1),
    Completed(2),
    Cancelled(3),
    Expired(4);

    private final int value;

    OrderStatus(int value) {
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
            case -1:
                result = "All";
                break;
            case 0:
                result = "Submitted";
                break;
            case 1:
                result = "Pending";
                break;
            case 2:
                result = "Completed";
                break;
            case 3:
                result = "Cancelled";
                break;
            case 4:
                result = "Expired";
                break;
        }

        return result;
    }

    public String getCNValue(){
        String result = "";
        switch (value) {
            case -1:
                result = "全部";
                break;
            case 0:
                result = "新建";
                break;
            case 1:
                result = "进行中";
                break;
            case 2:
                result = "完成";
                break;
            case 3:
                result = "取消";
                break;
            case 4:
                result = "过期";
                break;
        }

        return result;
    }

    /**
     * Get the integer value of this enum value, as defined in the Baiji IDL.
     */
    public static OrderStatus findByValue(int value) {
        switch (value) {
            case -1:
                return All;
            case 0:
                return Submitted;
            case 1:
                return Pending;
            case 2:
                return Completed;
            case 3:
                return Cancelled;
            case 4:
                return Expired;
            default:
                return null;
        }
    }

    public static OrderStatus findByStringValue(String value) {

        switch (value.toLowerCase()) {
            case "submitted":
                return OrderStatus.Submitted;
            case "pending":
                return OrderStatus.Pending;
            case "completed":
                return OrderStatus.Completed;
            case "cancelled":
                return OrderStatus.Cancelled;
            case "expired":
                return OrderStatus.Expired;
            default:
                return null;
        }//switch

    }

}
