package com.kxcar.wxcar.business.service;

import com.kxcar.wxcar.business.model.DateRangeType;
import com.kxcar.wxcar.business.model.OrderDetailModel;
import com.kxcar.wxcar.business.model.OrderModel;
import com.kxcar.wxcar.business.model.OrderStatus;
import com.kxcar.wxcar.util.config.Tuple;

import java.util.Date;
import java.util.List;

/**
 * Created by uncar_000 on 20161217.
 */
public interface IOrderService
{
    Tuple catchOrder(int driverId, long orderId);

    Tuple cancelOrder(long orderId);

    Tuple expireOrder(long orderId);

    Tuple completeOrder(long orderId);

    Tuple delegateOrder(long orderId);

    Tuple createOrder(OrderModel newOrder);

    Tuple updateOrder(OrderModel order);

    List<OrderModel> getSubmittedOrderListByDate(Date today,int pageNo);

    OrderModel getOrderDetail(long orderId);

    List<OrderModel> inquireOrderList(long orderId, String driverMobile, String customerMobile,Date beginDate,Date endDate,int pageNo);

    List<OrderModel> getMyOrderListByDate(String driverMobile, String beginDate,String endDate, OrderStatus orderStatus,int pageNo);


}
