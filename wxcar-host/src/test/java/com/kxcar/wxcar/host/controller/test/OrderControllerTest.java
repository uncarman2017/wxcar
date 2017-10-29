package com.kxcar.wxcar.host.controller.test;

import com.kxcar.wxcar.business.model.*;
import com.kxcar.wxcar.business.service.IOrderService;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.kxcar.wxcar.host.controller.OrderController;
import com.kxcar.wxcar.host.entity.OrderInquiryRequestModel;
import com.kxcar.wxcar.util.config.Tuple;
import com.kxcar.wxcar.util.date.DateHelper;
import com.kxcar.wxcar.util.serializer.JsonHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


/**
 * Created by jiaweiyu on 2016/12/12.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderControllerTest extends AbstractContextControllerTest {
    @Test
    public void test01CreateOrder() throws Exception {
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
        String requestStr = JsonHelper.serialize(orderModel);

        try {
            MvcResult result = mockMvc.perform(post("/order/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(requestStr))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            String responseStr = result.getResponse().getContentAsString();
            ExecResult execResult = JsonHelper.deserialize(responseStr, ExecResult.class);
            assertTrue(execResult.result);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }

    }

    @Test
    public void test02CatchOrder() throws Exception {
        try {
            MvcResult result = mockMvc.perform(get("/order/catch")
                    .accept(MediaType.APPLICATION_JSON)
                    .param("orderId", "2")
                    .param("mobile", "13023228090"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            String responseStr = result.getResponse().getContentAsString();
            ExecResult execResult = JsonHelper.deserialize(responseStr, ExecResult.class);
            assertTrue(execResult.result);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }

    }


    @Test
    public void test03CompleteOrder() throws Exception {
        try {
            MvcResult result = mockMvc.perform(get("/order/complete")
                    .accept(MediaType.APPLICATION_JSON)
                    .param("orderId", "2"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            String responseStr = result.getResponse().getContentAsString();
            ExecResult execResult = JsonHelper.deserialize(responseStr, ExecResult.class);
            assertTrue(execResult.result);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }


    @Test
    public void test04CancelOrder() throws Exception {
        try {
            MvcResult result = mockMvc.perform(get("/order/cancel/2")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            String responseStr = result.getResponse().getContentAsString();
            ExecResult execResult = JsonHelper.deserialize(responseStr, ExecResult.class);
            assertTrue(execResult.result);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void test05ExpireOrder() throws Exception {
        try {
            MvcResult result = mockMvc.perform(get("/order/expire")
                    .accept(MediaType.APPLICATION_JSON)
                    .param("orderId", "2"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            String responseStr = result.getResponse().getContentAsString();
            ExecResult execResult = JsonHelper.deserialize(responseStr, ExecResult.class);
            assertTrue(execResult.result);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }


    @Test
    public void test06GetOrderDetail() throws Exception {
        try {
            MvcResult result = mockMvc.perform(get("/order/detail/2")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("orderdetail"))
                    .andExpect(model().attributeExists("orderDetail"))
                    .andReturn();


        } catch (Exception e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void test07UpdateOrder() throws Exception {
        OrderModel orderModel = new OrderModel();
        orderModel.orderId = 2;
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
        orderModel.orderStatus = OrderStatus.Submitted;

        String requestStr = JsonHelper.serialize(orderModel);

        try {
            MvcResult result = mockMvc.perform(post("/order/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(requestStr))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            String responseStr = result.getResponse().getContentAsString();
            ExecResult execResult = JsonHelper.deserialize(responseStr, ExecResult.class);
            assertTrue(execResult.result);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }

 //    @RequestParam("status") OrderStatus orderStatus,
//    @RequestParam("range") DateRangeType dateRange,
//    @RequestParam("pageNo") int pageNo
    @Test
    public void test08GetMyOrderList() throws Exception {
        try {
            UserModel user = new UserModel();
            user.mobile = "15022221111";
            user.isAdmin = false;

            MvcResult result = mockMvc.perform(get("/order/mylist")
                    .accept(MediaType.APPLICATION_JSON)
                    .param("status",OrderStatus.All.getStringValue())
                    .param("range",DateRangeType.All.getStringValue())
                    .param("pageNo","1")
                    .sessionAttr("User",user))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("orderlist"))
                    .andExpect(model().attributeExists("OrderList"))
                    .andReturn();



        } catch (Exception e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void test09GetNewOrderList() throws Exception {
        String date = "2016-12-11";

        try {
            MvcResult result = mockMvc.perform(get("/order/newlist")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .param("date", date)
                    .param("pageNo", "1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("orderlist"))
                    .andExpect(model().attributeExists("OrderList"))
                    .andReturn();



        } catch (Exception e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }

    }

    @Test
    public void test10InquireOrderList() throws Exception {


        try {
            OrderInquiryRequestModel model = new OrderInquiryRequestModel();
            model.beginDate = "2016-12-11";
            model.endDate = "2016-12-11";
            model.customerMobile = "";
            model.driverMobile = "";
            model.orderId = 0;
            model.pageNo = 1;
            String requestStr = JsonHelper.serialize(model);

            MvcResult result = mockMvc.perform(post("/order/inquire")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(requestStr))
                    .andDo(print())
                    .andExpect(status().isOk())
//                    .andExpect(view().name("orderlist"))
//                    .andExpect(model().attributeExists("OrderList"))
                    .andReturn();

            String responseStr = result.getResponse().getContentAsString();
            ExecResult execResult = JsonHelper.deserialize(responseStr, ExecResult.class);
            assertTrue(execResult.result);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }

}
