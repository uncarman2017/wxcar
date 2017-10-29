package com.kxcar.wxcar.host.controller.test;

import com.kxcar.wxcar.business.model.*;
import com.kxcar.wxcar.util.serializer.JsonHelper;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jiaweiyu on 2016/12/12.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest extends  AbstractContextControllerTest
{
    @Test
    public void test01GetAdmin() throws Exception{

        try {
            MvcResult result = mockMvc.perform(get("/admin/get/13023228090")
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
    public void test02GetAllDrivers() throws Exception{

        try {
            MvcResult result = mockMvc.perform(get("/driver/getall")
                    .accept(MediaType.APPLICATION_JSON)
                    .param("pageNo","1"))
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
    public void test03GetDriver() throws Exception{
        try {
            MvcResult result = mockMvc.perform(get("/driver/get/15022221111")
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
    public void test04RegisterAdmin() throws Exception{
        AdminModel adminModel = new AdminModel();
        adminModel.mobile = "13388992356";
        adminModel.pwd = "redmaple";
        String requestStr = JsonHelper.serialize(adminModel);

        try {
            MvcResult result = mockMvc.perform(post("/admin/register")
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
    public void test05RegisterDriver() throws Exception{
        DriverModel driverModel = new DriverModel();
        driverModel.name = "庄傻";
        driverModel.carLiscence = "沪B23678";
        Random random = new Random();
        driverModel.mobile = "1390" + String.valueOf(random.nextInt(1000));
        driverModel.pwd = "redmaple";
        driverModel.remark = "我是一个自营司机";
        driverModel.driverType = DriverType.Internal;
        driverModel.driverStatus = DriverStatus.Enable;
        String requestStr = JsonHelper.serialize(driverModel);

        try{
            MvcResult result = mockMvc.perform(post("/driver/register")
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
    public void test06UpdateAdmin() throws  Exception{
        AdminModel adminModel = new AdminModel();
        adminModel.adminId = 3;
        adminModel.mobile = "13388992356";
        adminModel.pwd = "redmaple";
        String requestStr = JsonHelper.serialize(adminModel);

        try{
            MvcResult result = mockMvc.perform(post("/admin/update")
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
    public void test07UpdateDriver() throws Exception{
        DriverModel driverModel = new DriverModel();
        driverModel.driverId = 7;
        driverModel.name = "庄二傻";
        driverModel.carLiscence = "沪B23678";
        driverModel.mobile = "13522321167";
        driverModel.pwd = "redmaple";
        driverModel.remark = "我是一个外围司机";
        driverModel.driverType = DriverType.External;
        driverModel.driverStatus = DriverStatus.Enable;
        String requestStr = JsonHelper.serialize(driverModel);


        try{

            MvcResult result = mockMvc.perform(post("/driver/update")
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










}
