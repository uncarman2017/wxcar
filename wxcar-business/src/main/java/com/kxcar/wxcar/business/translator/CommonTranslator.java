package com.kxcar.wxcar.business.translator;

import com.kxcar.wxcar.business.model.*;
import com.kxcar.wxcar.db.model.DriverPO;
import com.kxcar.wxcar.db.model.OrderPO;
import com.kxcar.wxcar.util.date.DateHelper;

/**
 * Created by jiaweiyu on 2016/12/30.
 */
public class CommonTranslator
{
    public static OrderModel toOrderModel(OrderPO orderPO){
        OrderModel orderModel = new OrderModel();
        orderModel.orderId = orderPO.getOrderid();
        orderModel.addressDetail = orderPO.getAddressdetail();
        orderModel.arriveLocation = orderPO.getArrivelocation();
        orderModel.carLiscence = orderPO.getCarliscence();
        orderModel.customerMobile = orderPO.getCustomermobile();
        orderModel.departLocation = orderPO.getArrivelocation();
        orderModel.driverMobile = orderPO.getDrivermobile();
        orderModel.driverName = orderPO.getDrivername();
        orderModel.orderStatus = OrderStatus.findByStringValue(orderPO.getOrderstatus());
        orderModel.orderStatusName = orderModel.orderStatus.getCNValue();
        orderModel.addressDetail = orderPO.getAddressdetail();
        orderModel.price = orderPO.getPrice();
        orderModel.productType = ProductType.findByStringValue(orderPO.getProducttype());
        orderModel.remark = orderPO.getRemark();
        orderModel.useDateTime = DateHelper.format(orderPO.getUsetime(),"yyyy-MM-dd HH:mm");
        orderModel.vehicleLevel = VehicleLevel.findByStringValue(orderPO.getVehiclelevel());

        return orderModel;
    }

    public static OrderDetailModel toOrderDetailDetailModel(OrderPO orderPO){
        OrderDetailModel orderDetailModel = new OrderDetailModel();
        orderDetailModel.orderId = orderPO.getOrderid();
        orderDetailModel.addressDetail = orderPO.getAddressdetail();
        orderDetailModel.arriveLocation = orderPO.getArrivelocation();
        orderDetailModel.carLiscence = orderPO.getCarliscence();
        orderDetailModel.customerMobile = orderPO.getCustomermobile();
        orderDetailModel.departLocation = orderPO.getArrivelocation();
        orderDetailModel.driverMobile = orderPO.getDrivermobile();
        orderDetailModel.driverName = orderPO.getDrivername();
        orderDetailModel.orderStatus = OrderStatus.findByStringValue(orderPO.getOrderstatus());
        orderDetailModel.orderStatusName = orderDetailModel.orderStatus.getCNValue();
        orderDetailModel.addressDetail = orderPO.getAddressdetail();
        orderDetailModel.price = orderPO.getPrice();
        orderDetailModel.productType = ProductType.findByStringValue(orderPO.getProducttype());
        orderDetailModel.remark = orderPO.getRemark();
        orderDetailModel.useDateTime = DateHelper.format(orderPO.getUsetime(),"yyyy-MM-dd HH:mm");
        orderDetailModel.vehicleLevel = VehicleLevel.findByStringValue(orderPO.getVehiclelevel());

        return orderDetailModel;
    }

    public static DriverModel toDriverModel(DriverPO driverPO) {
        DriverModel model = new DriverModel();
        model.carLiscence = driverPO.getCarliscence();
        model.driverId = driverPO.getDriverid();
        model.driverStatus = DriverStatus.findByValue(driverPO.getDriverStatus());
        model.driverType = DriverType.findByValue(driverPO.getDriverType());
        model.mobile = driverPO.getMobile();
        model.name = driverPO.getName();
        model.remark = driverPO.getRemark();
        return model;
    }
}
