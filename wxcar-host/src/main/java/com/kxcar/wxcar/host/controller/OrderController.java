package com.kxcar.wxcar.host.controller;

import com.kxcar.wxcar.business.model.*;
import com.kxcar.wxcar.business.service.IOrderService;
import com.kxcar.wxcar.business.service.IUserService;
import com.kxcar.wxcar.host.entity.OrderInquiryRequestModel;
import com.kxcar.wxcar.util.config.Tuple;
import com.kxcar.wxcar.util.date.DateHelper;
import com.kxcar.wxcar.util.text.LogHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.*;


/**
 * Created by uncar_000 on 20161210.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    private final static String userSessionName = "User";

    /**
     * 取消订单
     */
    @RequestMapping(value = "/cancel/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public ExecResult cancelOrder(@PathVariable("orderId") long orderId) {
        Tuple tuple = orderService.cancelOrder(orderId);
        ExecResult result = new ExecResult();
        result.result = (boolean) tuple._1().get();
        result.resultMsg = (String) tuple._2().get();

        return result;
    }

    /**
     * 抢单
     */
    @RequestMapping(value = "/catch", method = RequestMethod.GET)
    @ResponseBody
    public ExecResult catchOrder(@RequestParam("orderId") long orderId, HttpSession session) {
        ExecResult result = new ExecResult();
        UserModel user = (UserModel) session.getAttribute(userSessionName);
        if (user == null) {
            result.result = true;
            result.resultMsg = "用户会话信息失效,请重新登录";
            return result;
        }
        Tuple tuple = orderService.catchOrder(user.userId, orderId);
        result.result = (boolean) tuple._1().get();
        result.resultMsg = (String) tuple._2().get();

        return result;
    }

    /**
     * 完成订单
     */
    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    @ResponseBody
    public ExecResult completeOrder(@RequestParam("orderId") long orderId) {
        Tuple tuple = orderService.completeOrder(orderId);
        ExecResult result = new ExecResult();
        result.result = (boolean) tuple._1().get();
        result.resultMsg = (String) tuple._2().get();

        return result;
    }

    /**
     * 改派订单,进行中订单恢复成新建状态
     */
    @RequestMapping(value = "/delegate", method = RequestMethod.GET)
    @ResponseBody
    public ExecResult delegateOrder(@RequestParam("orderId") long orderId) {
        Tuple tuple = orderService.delegateOrder(orderId);
        ExecResult result = new ExecResult();
        result.result = (boolean) tuple._1().get();
        result.resultMsg = (String) tuple._2().get();

        return result;
    }

    /**
     * 使订单过期
     */
    @RequestMapping(value = "/expire", method = RequestMethod.GET)
    @ResponseBody
    public ExecResult expireOrder(@RequestParam("orderId") long orderId) {
        Tuple tuple = orderService.expireOrder(orderId);
        ExecResult result = new ExecResult();
        result.result = (boolean) tuple._1().get();
        result.resultMsg = (String) tuple._2().get();

        return result;
    }

    /**
     * 创建订单
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ExecResult createOrder(@RequestBody OrderModel newOrder) {
        ExecResult result = new ExecResult();

        Tuple tuple = orderService.createOrder(newOrder);
        result.result = (long) tuple._1().get() > 0;
        result.resultMsg = (String) tuple._2().get();

        return result;
    }

    /**
     * 更新订单
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ExecResult updateOrder(@RequestBody OrderModel order) {
        ExecResult result = new ExecResult();

        Tuple tuple = orderService.updateOrder(order);
        result.result = (boolean) tuple._1().get();
        result.resultMsg = (String) tuple._2().get();

        return result;
    }

    /**
     * 获取我的订单列表
     */
    @RequestMapping(value = "/mylist", method = RequestMethod.GET)
    @ResponseBody
    public ExecResult getMyOrderList(@RequestParam("status") OrderStatus orderStatus,
                                     @RequestParam("beginDate") String beginDate,
                                     @RequestParam("endDate") String endDate,
                                     @RequestParam("pageNo") int pageNo, HttpSession session) {
        ExecResult result = new ExecResult();
        result.result = true;
        result.resultMsg = "获取我的订单列表";


        if (session == null || session.getAttribute("User") == null) {
            result.resultMsg = "用户会话信息丢失,请重新登录";
            result.result = false;
            return result;
        }

        UserModel user = (UserModel) session.getAttribute("User");
        List<OrderModel> orderList = orderService.getMyOrderListByDate(user.mobile, beginDate, endDate, orderStatus, pageNo);
        result.responseData = orderList;

        return result;
    }

    /**
     * 获取订单明细
     */
    @RequestMapping(value = "/detail/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public ExecResult getOrderDetail(@PathVariable long orderId) {
        ExecResult result = new ExecResult();
        result.result = true;
        result.resultMsg = "获取订单明细成功";

        OrderModel orderModel = orderService.getOrderDetail(orderId);
        result.responseData = orderModel;
        return result;
    }

    /**
     * 获取新单列表
     */
    @RequestMapping(value = "/newlist", method = RequestMethod.GET)
    @ResponseBody
    public ExecResult getNewOrderList(@RequestParam("date") String date, @RequestParam("pageNo") int pageNo) {
        Date beginDate = Date.valueOf(date);
        List<OrderModel> orderList = orderService.getSubmittedOrderListByDate(beginDate, pageNo);

        ExecResult result = new ExecResult();
        result.responseData = orderList;
        result.result = orderList != null;
        result.resultMsg = orderList != null ? "获取新进订单列表成功" : "获取新进订单列表失败";
        return result;
    }


    /**
     * 后台查询订单
     */
    @RequestMapping(value = "/inquire", method = RequestMethod.POST)
    @ResponseBody
    public ExecResult inquireOrderList(@RequestBody OrderInquiryRequestModel request) {
        //ModelAndView mv = new ModelAndView();

//        mv.setViewName("orderlist");
        List<OrderModel> orderList = orderService.inquireOrderList(request.orderId,
                request.driverMobile,
                request.customerMobile,
                Date.valueOf(request.beginDate),
                Date.valueOf(request.endDate), request.pageNo);
//        mv.addObject("OrderList",orderList);

//        return mv;

        ExecResult result = new ExecResult();
        result.responseData = orderList;
        result.result = orderList != null;
        result.resultMsg = orderList != null ? "查询订单列表成功" : "查询订单列表失败";
        return result;
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ExecResult uploadOrderFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        ExecResult execResult = new ExecResult();
        execResult.result = true;
        execResult.resultMsg = "上传文件成功";

        //  String path = request.getSession().getServletContext().getRealPath("upload");
        try {

//            String content = new String( file.getBytes(),"UTF-8");
//            String[] items = content.split("\r\n");
//
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(),"UTF-8"));
            String line;

            //导入订单记录,CSV格式,首行为字段列表
            //字段顺序:乘客手机,用车时间,产品备注,产品类型,车辆档次,价格,出发地点,到达地点,到达地址详情,车辆牌照
            StringBuilder errNumberList = new StringBuilder();
            int lineNumber = 0;
            while((line = reader.readLine()) != null){
                if(++ lineNumber == 1) continue; //忽略第一行
                String[] fields = line.split(",");
                OrderModel newOrder = new OrderModel();
                newOrder.driverName = "";
                newOrder.driverMobile = "";
                newOrder.customerMobile = fields[0];
                newOrder.useDateTime = fields[1];
                newOrder.remark = fields[2];
                newOrder.productType = ProductType.findByStringValue(fields[3]);
                newOrder.vehicleLevel = VehicleLevel.findByStringValue(fields[4]);
                newOrder.price = Double.parseDouble(fields[5]);
                newOrder.departLocation = fields[6];
                newOrder.arriveLocation = fields[7];
                newOrder.addressDetail = fields[8];
                newOrder.carLiscence = fields[9];
                newOrder.orderStatus = OrderStatus.Submitted;
                Tuple tuple = orderService.createOrder(newOrder);
                if((long)tuple._1().get() <= 0)errNumberList.append(lineNumber + ",");

            }
            reader.close();
            if(! errNumberList.toString().isEmpty()) execResult.resultMsg = "出错行:" + errNumberList.toString();
//            String path = "C:\\upload";
//            String fileName = file.getOriginalFilename();
//            File targetFile = new File(path, fileName);
//            if (targetFile.exists()) {
//                targetFile.delete();
//            }
//            //targetFile.createNewFile();
//            targetFile.mkdirs();
//
//            file.transferTo(targetFile);
        } catch (Exception e) {
            LogHelper.error(e);
            execResult.result = false;
            execResult.resultMsg = "写入磁盘文件异常";
        }


        return execResult;
    }

}
