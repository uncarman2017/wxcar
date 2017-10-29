package com.kxcar.wxcar.business.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by uncar_000 on 20161210.
 */
public class OrderModel extends BaseModel
{
    public long orderId;

    public String driverName;

    public String customerMobile;

    public String driverMobile;

    public String useDateTime;

    public String remark;

    public ProductType productType; //0-国内接机  1-国内送机
//
    public VehicleLevel vehicleLevel; //0-经济型 1-舒适型 2-商务型

    public double price;

    public String departLocation;

    public String arriveLocation;

    public String addressDetail;

    public String carLiscence;

    public OrderStatus orderStatus;

    public String orderStatusName;

}
