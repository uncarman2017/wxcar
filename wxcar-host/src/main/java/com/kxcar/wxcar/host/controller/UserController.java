package com.kxcar.wxcar.host.controller;

import com.kxcar.wxcar.business.model.AdminModel;
import com.kxcar.wxcar.business.model.DriverModel;
import com.kxcar.wxcar.business.model.ExecResult;
import com.kxcar.wxcar.business.model.UserModel;
import com.kxcar.wxcar.business.service.IOrderService;
import com.kxcar.wxcar.business.service.IUserService;
import com.kxcar.wxcar.util.config.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by uncar_000 on 20161210.
 */
@Controller
public class UserController
{
    @Autowired
    private IUserService userService;

    @PostMapping(value = "/driver/register")
    @ResponseBody
    public ExecResult registerDriver(@RequestBody DriverModel newDriver) {
        ExecResult result = new ExecResult();

        Tuple tuple = userService.registerDriver(newDriver);
        result.result = (int)tuple._1().get() > 0;
        result.resultMsg = (String)tuple._2().get();
        return result;
    }

    @GetMapping(value = "/driver/get/{mobile}")
    //@RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public ExecResult getDriver(@PathVariable("mobile") String mobile) {
        ExecResult result = new ExecResult();

        DriverModel driverModel = userService.getDriver(mobile);
        result.result = driverModel.result;
        result.resultMsg = driverModel.msg;
        result.responseData = driverModel;
        return result;
    }


    @GetMapping(value = "/driver/getall")
    @ResponseBody
    public ExecResult getAllDrivers(@RequestParam("pageNo") int pageNo) {
        ExecResult result = new ExecResult();

        List<DriverModel> driverList = userService.getAllDrivers(pageNo);
        result.result = true;
        result.resultMsg = "";
        result.responseData = driverList;
        return result;
    }


    @PostMapping(value = "/driver/update")
    @ResponseBody
    public ExecResult updateDriver(@RequestBody DriverModel newDriver) {
        ExecResult result = new ExecResult();

        Tuple tuple = userService.updateDriver(newDriver);
        result.result = (boolean)tuple._1().get();
        result.resultMsg = (String)tuple._2().get();
        return result;
    }


    @PostMapping(value = "/admin/register")
    @ResponseBody
    public ExecResult registerAdmin(@RequestBody AdminModel newAdmin) {
        ExecResult result = new ExecResult();

        Tuple tuple = userService.registerAdmin(newAdmin);
        result.result = (int)tuple._1().get() > 0;
        result.resultMsg = (String)tuple._2().get();
        return result;
    }

    @GetMapping(value = "/admin/get/{mobile}")
    //@RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public ExecResult getAdmin(@PathVariable("mobile") String mobile) {
        ExecResult result = new ExecResult();

        AdminModel adminModel = userService.getAdmin(mobile);
        result.result = adminModel.result;
        result.resultMsg = adminModel.msg;
        result.responseData = adminModel;
        return result;
    }


    @PostMapping(value = "/admin/update")
    @ResponseBody
    public ExecResult updateAdmin(@RequestBody AdminModel newAdmin) {
        ExecResult result = new ExecResult();

        Tuple tuple = userService.updateAdmin(newAdmin);
        result.result = (boolean)tuple._1().get();
        result.resultMsg = (String)tuple._2().get();
        return result;
    }





}
