package com.kxcar.wxcar.business.service;

import com.kxcar.wxcar.business.model.AdminModel;
import com.kxcar.wxcar.business.model.DriverModel;
import com.kxcar.wxcar.business.model.OrderModel;
import com.kxcar.wxcar.db.model.DriverPO;
import com.kxcar.wxcar.util.config.Tuple;

import java.util.List;

/**
 * Created by uncar_000 on 20161217.
 */
public interface IUserService
{
    Tuple registerDriver(DriverModel newDriver);

    DriverModel validateDriver(String mobile, String pwd);

    DriverModel getDriver(String mobile);

    Tuple updateDriver(DriverModel driver);

    Tuple registerAdmin(AdminModel newAdmin);

    AdminModel validateAdmin(String mobile,String pwd);

    AdminModel getAdmin(String mobile);

    Tuple updateAdmin(AdminModel admin);

    List<DriverModel> getAllDrivers(int pageNo);

}