package com.kxcar.wxcar.db.dao;

import com.kxcar.wxcar.db.model.DriverPO;
import com.kxcar.wxcar.db.model.OrderPO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.kxcar.wxcar.db.model.OrderPOCondition;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrderPOMapper {
    int countByExample(OrderPOCondition example);

    int deleteByExample(OrderPOCondition example);

    int deleteByPrimaryKey(@Param("orderid") Long orderid);

    int insert(OrderPO record);

    int insertSelective(OrderPO record);

    List<OrderPO> selectByExample(OrderPOCondition example);

    OrderPO selectByPrimaryKey(@Param("orderid") Long orderid);

    int updateByExampleSelective(OrderPO record,OrderPOCondition example);

    int updateByExample(OrderPO record, OrderPOCondition example);

    int updateByPrimaryKeySelective(OrderPO record);

    int updateByPrimaryKey(OrderPO record);

    //region 扩展方法

    /**
     * 后台订单查询
     * */
    List<OrderPO> inquireOrderList(@Param("orderId")long orderId,
                                   @Param("driverMobile") String driverMobile,@Param("customerMobile") String customerMobile,
                                   @Param("beginTime") Date beginTime, @Param("endTime") Date endTime,
                                   @Param("pageNo") int pageNo);

    /**
     * 根据日期段获取订单列表
    */
    //@Select(value = "SELECT * from order WHERE UseTime between #{beginTime} AND #{endTime}")
    List<OrderPO> getOrderListByDate(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime,@Param("pageNo") int pageNo);

    List<OrderPO> getSubmittedOrderListByDate(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime,@Param("pageNo") int pageNo);

    /**
     * 根据日期段和手机号获取司机自己的订单
     * */
    List<OrderPO> getMyOrderListByDate(@Param("mobile") String mobile,
                                       @Param("beginTime") Date beginTime, @Param("endTime") Date endTime, @Param("orderStatus") String orderStatus,
                                       @Param("pageNo") int pageNo);

    /**
     * 刷新订单状态为进行中
     * */
    int catchOrder(@Param("driver") DriverPO driver, @Param("orderID") long orderID);

    /**
     * 刷新订单状态为已取消
    * */
    int cancelOrder(@Param("orderID") long orderID);

    /**
     * 刷新订单状态为已完成
     * */
    int completeOrder(@Param("orderID") long orderID);

    /**
     * 刷新订单状态为新建
     * */
    int delegateOrder(@Param("orderID") long orderID);

    /**
     * 刷新订单状态为已过期
     * */
    int expireOrder(@Param("orderID") long orderID);


    //endregion


}