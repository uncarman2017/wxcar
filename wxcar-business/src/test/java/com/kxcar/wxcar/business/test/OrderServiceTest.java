package com.kxcar.wxcar.business.test;

import com.kxcar.wxcar.business.model.*;
import com.kxcar.wxcar.business.service.IOrderService;
import com.kxcar.wxcar.util.config.Tuple;
import com.kxcar.wxcar.util.date.DateHelper;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by uncar_000 on 2017-01-01.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderServiceTest extends BaseUnitTest
{
    @Autowired
    private IOrderService service;

    @Test
    public void test01CreateOrder() throws Exception{
        OrderModel orderModel = new OrderModel();
        orderModel.driverName = "庄傻";
        orderModel.customerMobile = "13122325566";
        orderModel.driverMobile = "15022221111";
        orderModel.useDateTime = DateHelper.format(new Date(),"yyyy-MM-dd HH:mm");
        orderModel.remark = "测试订单";
        orderModel.productType = ProductType.JieJi;
        orderModel.vehicleLevel = VehicleLevel.Business;
        orderModel.price = 220;
        orderModel.departLocation = "上海市闵行区都市路500号";
        orderModel.arriveLocation = "上海市长宁区仙霞西路666号";
        orderModel.addressDetail = "仙霞西路666号百联西郊商务广场";
        orderModel.carLiscence = "沪B23678";
        Tuple result = service.createOrder(orderModel);
        long orderId = (long)result._1().get();
        assertTrue(orderId > 0);
    }


    @Test
    public void test02CatchOrder() throws Exception{
        Tuple result = service.catchOrder(1,2);
        assertTrue((boolean)result._1().get());
    }


    @Test
    public void test03CompleteOrder() throws Exception{
        Tuple result = service.completeOrder(2);
        assertTrue((boolean)result._1().get());
    }


    @Test
    public void test04CancelOrder() throws Exception{
        Tuple result = service.cancelOrder(2);
        assertTrue((boolean)result._1().get());
    }


    @Test
    public void test05ExpireOrder() throws Exception{
        Tuple result = service.expireOrder(2);
        assertTrue((boolean)result._1().get());
    }

    @Test
    public void test06GetOrderDetail() throws Exception{
        OrderModel orderModel = service.getOrderDetail(2);
        assertNotNull(orderModel);
    }

    @Test
    public void test07UpdateOrder() throws Exception{
        OrderModel orderModel = service.getOrderDetail(2);
        orderModel.addressDetail = "测试用的订单";
//        orderModel.orderStatus = OrderStatus.Submitted;
        Tuple result = service.updateOrder(orderModel);
        assertTrue((boolean)result._1().get());
    }

    @Test
    public void test08GetMyOrderListByDate() throws Exception{
        List<OrderModel> orderList = service.getMyOrderListByDate("13023228090", "2017-01-01","2017-12-31", OrderStatus.All,1);
        assertTrue(orderList != null && ! orderList.isEmpty());
    }

    @Test
    public void test09GetSubmittedOrderListByDate() throws Exception{
        Date date = DateHelper.parse("2016-12-11","yyyy-MM-dd");
        List<OrderModel> orderList = service.getSubmittedOrderListByDate(date,1);
        assertTrue(orderList != null && ! orderList.isEmpty());

    }

    @Test
    public void test10InquireOrderList() throws Exception{
        Date beginDate = DateHelper.parse("2016-12-11","yyyy-MM-dd");
        Date endDate = DateHelper.parse("2016-12-11","yyyy-MM-dd");

        List<OrderModel> orderList = service.inquireOrderList(0,"15022221111","",beginDate,endDate,1);
        assertTrue(orderList != null && ! orderList.isEmpty());

        endDate = DateHelper.parse("2017-12-31","yyyy-MM-dd");
        orderList = service.inquireOrderList(0,"","",beginDate,endDate,1);
        assertTrue(orderList != null && ! orderList.isEmpty());

    }


}
