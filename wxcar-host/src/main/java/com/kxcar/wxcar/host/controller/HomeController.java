package com.kxcar.wxcar.host.controller;

import com.kxcar.wxcar.business.model.AdminModel;
import com.kxcar.wxcar.business.model.DriverModel;
import com.kxcar.wxcar.business.model.ExecResult;
import com.kxcar.wxcar.business.model.UserModel;
import com.kxcar.wxcar.business.service.IUserService;
import com.kxcar.wxcar.util.date.DateHelper;
import com.kxcar.wxcar.util.text.TextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiaweiyu on 2016/12/12.
 */
@Controller
public class HomeController {
    @Autowired
    private IUserService userService;

    private final static String userSessionName = "User";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model, HttpSession session) {
//        Object mobile = session.getAttribute("mobile");
//        if(mobile == null || String.valueOf(mobile).isEmpty()) return "login";
//
//        String date = DateHelper.format(new Date(),"yyyy-MM-dd");
//        return "redirect:/order/list?date=" + date;

        return "index";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ExecResult login(@RequestBody UserModel user, HttpSession session) {
        ExecResult result = new ExecResult();

        if (user.isAdmin) {
            AdminModel adminModel = userService.validateAdmin(user.mobile, user.password);
            result.resultMsg = adminModel.msg;
            result.result = adminModel.result;
            result.responseData = adminModel;
            user.userId = adminModel.adminId;
        } else {
            DriverModel driverModel = userService.validateDriver(user.mobile, user.password);
            result.resultMsg = driverModel.msg;
            result.result = driverModel.result;
            result.responseData = driverModel;
            user.userId = driverModel.driverId;
        }
        if (result.result) {
            user.password = "";
            session.setAttribute(userSessionName, user);
            session.setMaxInactiveInterval(60 * 60);  //单位秒,会话有效时间1小时
        }

        return result;
    }


    @RequestMapping(value = "/{viewName}", method = RequestMethod.GET)
    public ModelAndView showPage(@PathVariable String viewName, HttpSession session) {
        String vName = viewName.toLowerCase();
        String viewNameCN = "";
        switch (vName) {
            case "index":
                viewNameCN = "首页";
                break;
            case "login":
                viewNameCN = "登录";
                break;
            case "admin_createorder":
                viewNameCN = "下单";
                break;
            case "admin_driverlist":
                viewNameCN = "司机名册";
                break;
            case "admin_orderlist":
                viewNameCN = "订单查询";
                break;
            case "admin_orderdetail":
                viewNameCN = "订单详情";
                break;
            case "orderlist":
                viewNameCN = "司机抢单";
                break;
            case "myorderlist":
                viewNameCN = "我的订单";
                break;
        }

        ModelAndView mv = new ModelAndView();

        if (vName.equals("login") || vName.equals("help") || vName.equals("index")) {
            mv.addObject("viewNameCN", viewNameCN);
            mv.setViewName(vName);
            return mv;
        }

        UserModel user = (UserModel) session.getAttribute(userSessionName);
        if (user == null) {
            mv.addObject("viewNameCN", "登录");
            mv.setViewName("login");
            return mv;
        }

        if (viewName.toLowerCase().indexOf("admin") >= 0 && !user.isAdmin) {
            mv.setViewName("error");
            mv.addObject("viewNameCN", viewNameCN);
            mv.addObject("roleName", "司机");
            return mv;
        } else if (viewName.toLowerCase().indexOf("admin") < 0 && user.isAdmin) {
            mv.setViewName("error");
            mv.addObject("viewNameCN", viewNameCN);
            mv.addObject("roleName", "管理员");
            return mv;
        }

        mv.addObject("viewNameCN", viewNameCN);
        mv.setViewName(viewName);

        return mv;
    }

    @RequestMapping(value = "/showError/{msgType}", method = RequestMethod.GET)
    public ModelAndView showError(@PathVariable("msgType") String msgType){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("showError");
        String errorMsg = "";
        switch (msgType){
            case "FileUploadError":
                errorMsg = "文件上传失败";
                break;
        }
        mv.addObject("errorMsg",errorMsg);
        return mv;
    }
}
