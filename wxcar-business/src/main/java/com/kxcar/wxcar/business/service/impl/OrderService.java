package com.kxcar.wxcar.business.service.impl;

import com.kxcar.wxcar.business.model.*;
import com.kxcar.wxcar.business.service.IOrderService;
import com.kxcar.wxcar.business.translator.CommonTranslator;
import com.kxcar.wxcar.db.SqlSessionFactoryUtil;
import com.kxcar.wxcar.db.dao.DriverPOMapper;
import com.kxcar.wxcar.db.dao.OrderPOMapper;
import com.kxcar.wxcar.db.model.DriverPO;
import com.kxcar.wxcar.db.model.OrderPO;
import com.kxcar.wxcar.util.config.Tuple;
import com.kxcar.wxcar.util.date.CalendarHelper;
import com.kxcar.wxcar.util.date.DateHelper;
import com.kxcar.wxcar.util.text.LogHelper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static com.kxcar.wxcar.util.config.Tuple.of;

/**
 * Created by uncar_000 on 20161210.
 */
@Service
public class OrderService implements IOrderService {

    private final static int pageSize = 3;

    public OrderService() {

    }

    /*
    * 创建订单
    * */
    @Override
    public Tuple createOrder(OrderModel newOrder) {
        SqlSession sqlSession = null;
        String msg = "创建订单成功";
        long orderId = 0;

        try {
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            OrderPOMapper orderMapper = sqlSession.getMapper(OrderPOMapper.class);
            OrderPO orderPO = new OrderPO();
            orderPO.setDrivername(newOrder.driverName);
            orderPO.setCustomermobile(newOrder.customerMobile);
            orderPO.setDrivermobile(newOrder.driverMobile);
            orderPO.setUsetime(DateHelper.parse(newOrder.useDateTime,"yyyy-MM-dd HH:mm"));
            orderPO.setRemark(newOrder.remark);
            orderPO.setProducttype(newOrder.productType.getStringValue());
            orderPO.setVehiclelevel(newOrder.vehicleLevel.getStringValue());
            orderPO.setPrice(newOrder.price);
            orderPO.setDepartlocation(newOrder.departLocation);
            orderPO.setArrivelocation(newOrder.arriveLocation);
            orderPO.setAddressdetail(newOrder.addressDetail);
            orderPO.setCarliscence(newOrder.carLiscence);
            orderPO.setOrderstatus(OrderStatus.Submitted.getStringValue());
            orderPO.setDatachangeCreatetime(new Date());
            orderPO.setDatachangeLasttime(new Date());
            msg = orderMapper.insert(orderPO) > 0 ? "创建订单成功" : "创建订单失败";
            orderId = orderPO.getOrderid();
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            msg = "创建订单异常";
            LogHelper.error(e);

        } finally {
            if (sqlSession != null) sqlSession.close();
        }

        return Tuple.<Long, String>of(orderId, msg);
    }


    /*
    * 抢单
    * */
    @Override
    public Tuple catchOrder(int driverId, long orderId) {
        SqlSession sqlSession = null;
        String msg = "抢单成功";
        boolean result = true;

        try {
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            OrderPOMapper orderMapper = sqlSession.getMapper(OrderPOMapper.class);
            DriverPOMapper driverMapper = sqlSession.getMapper(DriverPOMapper.class);

            DriverPO driverPO = driverMapper.selectByPrimaryKey(driverId);
            if (driverPO == null) {
                if (sqlSession != null) sqlSession.close();
                return Tuple.<Boolean, String>of(false, "找不到司机记录");
            }
            if (driverPO.getDriverStatus() == 0) {
                if (sqlSession != null) sqlSession.close();
                return Tuple.<Boolean, String>of(false, "司机状态无效");
            }

            OrderPO orderPO = orderMapper.selectByPrimaryKey(orderId);
            if (orderPO.getOrderstatus().equalsIgnoreCase(OrderStatus.Pending.getStringValue())) {
                if (sqlSession != null) sqlSession.close();
                return Tuple.<Boolean, String>of(false, "订单已被其他司机抢走了");
            }

            result = orderMapper.catchOrder(driverPO, orderId) > 0;

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            msg = "抢单异常,请稍候重试";
            LogHelper.error(e);
            result = false;

        } finally {
            if (sqlSession != null) sqlSession.close();
        }


        return Tuple.<Boolean, String>of(result, msg);
    }

    /*
    * 完成订单
    * */
    @Override
    public Tuple completeOrder(long orderId) {
        SqlSession sqlSession = null;
        String msg = "完成订单成功";
        boolean result = true;

        try {
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            OrderPOMapper orderMapper = sqlSession.getMapper(OrderPOMapper.class);
            result = orderMapper.completeOrder(orderId) > 0;

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            msg = "完成订单异常";
            LogHelper.error(e);
            result = false;

        } finally {
            if (sqlSession != null) sqlSession.close();
        }

        return Tuple.<Boolean, String>of(result, msg);

    }


    /*
   * 改派订单
   * */
    @Override
    public Tuple delegateOrder(long orderId) {
        SqlSession sqlSession = null;
        String msg = "改派订单成功";
        boolean result = true;

        try {
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            OrderPOMapper orderMapper = sqlSession.getMapper(OrderPOMapper.class);
            OrderPO orderPO = orderMapper.selectByPrimaryKey(orderId);
            if(! orderPO.getOrderstatus().equalsIgnoreCase("pending")){
                return Tuple.of(false,"只有状态为进行中的订单可以改派");

            }
            Date date = DateHelper.addHoursTimestamp(new Date(),-1);
            if(! orderPO.getUsetime().after(date)){
                return Tuple.of(false, "用车时间为一小时后的订单才可以改派");
            }


            result = orderMapper.delegateOrder(orderId) > 0;

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            msg = "改派订单异常";
            LogHelper.error(e);
            result = false;

        } finally {
            if (sqlSession != null) sqlSession.close();
        }

        return Tuple.<Boolean, String>of(result, msg);

    }

    /*
    * 取消订单
    * */
    @Override
    public Tuple cancelOrder(long orderId) {
        SqlSession sqlSession = null;
        String msg = "取消订单成功";
        boolean result = true;

        try {
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            OrderPOMapper orderMapper = sqlSession.getMapper(OrderPOMapper.class);
            result = orderMapper.cancelOrder(orderId) > 0;

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            msg = "取消订单异常";
            LogHelper.error(e);
            result = false;

        } finally {
            if (sqlSession != null) sqlSession.close();
        }

        return Tuple.<Boolean, String>of(result, msg);

    }

    @Override
    public Tuple expireOrder(long orderId) {
        SqlSession sqlSession = null;
        String msg = "订单状态更新为过期";
        boolean result = true;

        try {
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            OrderPOMapper orderMapper = sqlSession.getMapper(OrderPOMapper.class);
            result = orderMapper.expireOrder(orderId) > 0;

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            msg = "订单状态更新为过期异常";
            LogHelper.error(e);
            result = false;

        } finally {
            if (sqlSession != null) sqlSession.close();
        }

        return Tuple.<Boolean, String>of(result, msg);
    }


    /*
    * 更新订单
    * */
    @Override
    public Tuple updateOrder(OrderModel order) {
        SqlSession sqlSession = null;
        String msg = "更新订单成功";
        boolean result = true;

        try {
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            OrderPOMapper orderMapper = sqlSession.getMapper(OrderPOMapper.class);
            OrderPO orderPO = new OrderPO();
            orderPO.setOrderid(order.orderId);
            orderPO.setDrivername(order.driverName);
            orderPO.setCustomermobile(order.customerMobile);
            orderPO.setDrivermobile(order.driverMobile);
            orderPO.setUsetime(DateHelper.parse(order.useDateTime,"yyyy-MM-dd HH:mm"));
            orderPO.setRemark(order.remark);
            orderPO.setProducttype(order.productType.getStringValue());
            orderPO.setVehiclelevel(order.vehicleLevel.getStringValue());
            orderPO.setPrice(order.price);
            orderPO.setDepartlocation(order.departLocation);
            orderPO.setArrivelocation(order.arriveLocation);
            orderPO.setAddressdetail(order.addressDetail);
            orderPO.setCarliscence(order.carLiscence);
            orderPO.setOrderstatus(order.orderStatus.getStringValue());
            orderPO.setDatachangeCreatetime(new Date());
            orderPO.setDatachangeLasttime(new Date());

            result = orderMapper.updateByPrimaryKey(orderPO) > 0;

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            msg = "更新订单异常";
            LogHelper.error(e);
            result = false;

        } finally {
            if (sqlSession != null) sqlSession.close();
        }

        return Tuple.<Boolean, String>of(result, msg);
    }


    /**
     * 获取某天和次日的新单列表
     */
    public List<OrderModel> getSubmittedOrderListByDate(Date today,int pageNo) {
        SqlSession sqlSession = null;
        List<OrderModel> result = new ArrayList<>();

        try {
            Date beginDate = DateHelper.parse(DateHelper.format(today, "yyyy-MM-dd") + " 00:00:00");
            Date endDate = DateHelper.addDaysOfDate(beginDate,1); //
            endDate = DateHelper.parse(DateHelper.format(endDate, "yyyy-MM-dd") + " 23:59:59");
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            OrderPOMapper orderMapper = sqlSession.getMapper(OrderPOMapper.class);
            List<OrderPO> orderList = orderMapper.getSubmittedOrderListByDate(beginDate, endDate, pageNo);
            for (OrderPO orderPO : orderList) {
                OrderModel orderModel = CommonTranslator.toOrderModel(orderPO);
                result.add(orderModel);
            }

        } catch (Exception e) {
            LogHelper.error(e);

        } finally {
            if (sqlSession != null) sqlSession.close();
        }

        return result;
    }

    /*
    * 后台查询订单列表
    * */
    @Override
    public List<OrderModel> inquireOrderList(long orderId, String driverMobile, String customerMobile, Date beginDate, Date endDate,
                                             int pageNo) {
        SqlSession sqlSession = null;
        List<OrderModel> result = new ArrayList<>();

        try {
            Date beginDate2 = DateHelper.parse(DateHelper.format(beginDate, "yyyy-MM-dd") + " 00:00:00");
            Date endDate2 = DateHelper.parse(DateHelper.format(endDate, "yyyy-MM-dd") + " 23:59:59");
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            OrderPOMapper orderMapper = sqlSession.getMapper(OrderPOMapper.class);
            List<OrderPO> orderList = orderMapper.inquireOrderList(orderId, driverMobile, customerMobile, beginDate2, endDate2,pageNo);
            for (OrderPO orderPO : orderList) {
                OrderModel orderModel = CommonTranslator.toOrderModel(orderPO);
                result.add(orderModel);
            }

        } catch (Exception e) {
            LogHelper.error(e);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }

        return result;
    }

    /**
     * 获取我的订单
     */
    @Override
    public List<OrderModel> getMyOrderListByDate(String driverMobile, String beginDate, String endDate, OrderStatus orderStatus,int pageNo) {
        SqlSession sqlSession = null;
        List<OrderModel> result = new ArrayList<>();

        try {
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            OrderPOMapper orderMapper = sqlSession.getMapper(OrderPOMapper.class);
            Date beginDate2 = DateHelper.parse(beginDate,"yyyy-MM-dd");
            Date endDate2 = DateHelper.parse(endDate + " 23:59:59","yyyy-MM-dd HH:mm:ss");


            List<OrderPO> orderList = orderMapper.getMyOrderListByDate(driverMobile, beginDate2, endDate2, orderStatus.getStringValue(),pageNo);
            for (OrderPO orderPO : orderList) {
                OrderModel orderModel = CommonTranslator.toOrderModel(orderPO);
                result.add(orderModel);
            }

        } catch (Exception e) {
            LogHelper.error(e);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }

        return result;
    }


    /*
    * 获取订单明细
    * */
    @Override
    public OrderModel getOrderDetail(long orderId) {
        SqlSession sqlSession = null;
        OrderModel orderModel = new OrderDetailModel();

        try {
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            OrderPOMapper orderMapper = sqlSession.getMapper(OrderPOMapper.class);
            OrderPO orderPO = orderMapper.selectByPrimaryKey(orderId);
            orderModel = CommonTranslator.toOrderModel(orderPO);
            orderModel.useDateTime = orderModel.useDateTime.replace(' ','T');

        } catch (Exception e) {
            LogHelper.error(e);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }

        return orderModel;
    }

}
