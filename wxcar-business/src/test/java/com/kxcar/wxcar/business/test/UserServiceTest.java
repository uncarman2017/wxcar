package com.kxcar.wxcar.business.test;

import com.kxcar.wxcar.business.model.AdminModel;
import com.kxcar.wxcar.business.model.DriverModel;
import com.kxcar.wxcar.business.model.DriverStatus;
import com.kxcar.wxcar.business.model.DriverType;
import com.kxcar.wxcar.business.service.IUserService;
import com.kxcar.wxcar.util.config.Tuple;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by jiaweiyu on 2016/12/19.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest extends BaseUnitTest
        //extends AbstractTransactionalJUnit4SpringContextTests
{
    @Autowired
    private IUserService service;

    @Test
    public void testGetDriver() throws Exception {
        DriverModel driverModel = service.getDriver("15022221111");
        assertNotNull(driverModel);
    }

    @Test
    public void testRegisterDriver() throws Exception {
        DriverModel driverModel = new DriverModel();
        driverModel.name = "庄傻";
        driverModel.carLiscence = "沪B23678";
        driverModel.mobile = "13022321167";
        driverModel.pwd = "redmaple";
        driverModel.remark = "我是一个自营司机";
        driverModel.driverType = DriverType.Internal;
        driverModel.driverStatus = DriverStatus.Enable;
        Tuple result = service.registerDriver(driverModel);
        assertTrue((int) result._1().get() > 0);
    }


    @Test
    public void testUpdateDriver() throws Exception {

        DriverModel driverModel = new DriverModel();
        driverModel.driverId = 7;
        driverModel.name = "庄二傻";
        driverModel.carLiscence = "沪B23678";
        driverModel.mobile = "13522321167";
        driverModel.pwd = "redmaple";
        driverModel.remark = "我是一个外围司机";
        driverModel.driverType = DriverType.External;
        driverModel.driverStatus = DriverStatus.Enable;
        Tuple result = service.updateDriver(driverModel);
        assertTrue((boolean) result._1().get());

    }

    @Test
    public void testValidateDriver() throws Exception {

        DriverModel driverModel = service.validateDriver("13023228090", "redmaple");
        assertTrue(driverModel.result);
    }


    @Test
    public void testGetAdmin() throws Exception {
        AdminModel adminModel = service.getAdmin("13023228090");
        assertNotNull(adminModel);
    }

    @Test
    public void testRegisterAdmin() throws Exception {
        AdminModel adminModel = new AdminModel();
        adminModel.mobile = "13388992356";
        adminModel.pwd = "redmaple";
        Tuple result = service.registerAdmin(adminModel);
        assertTrue((int)result._1().get() > 0);
    }


    @Test
    public void testUpdateAdmin() throws Exception {
        AdminModel adminModel = new AdminModel();
        adminModel.adminId = 3;
        adminModel.mobile = "13388992356";
        adminModel.pwd = "redmaple";
        Tuple result = service.updateAdmin(adminModel);
        assertTrue((boolean) result._1().get());

    }

    @Test
    public void testValidateAdmin() throws Exception{
        AdminModel adminModel = service.validateAdmin("13023228090","redmaple");
        assertTrue(adminModel.result);
    }



}
