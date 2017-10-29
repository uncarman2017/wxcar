package com.kxcar.wxcar.host.controller.test;

import com.kxcar.wxcar.business.model.ExecResult;
import com.kxcar.wxcar.business.model.UserModel;
import com.kxcar.wxcar.host.controller.HomeController;
import com.kxcar.wxcar.host.controller.OrderController;
import com.kxcar.wxcar.util.serializer.JsonHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by uncar_000 on 2016-12-18.
 */

public class HomeControllerTest  extends  AbstractContextControllerTest
{
    //    @Test
//    public void testHelloWorld() throws Exception {
//        // 1. controller mvc test
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/demo/hello"))
//                .andExpect(MockMvcResultMatchers.handler().handlerType(OrderController.class))
//                .andExpect(MockMvcResultMatchers.handler().methodName("helloWorld"))
//                .andExpect(MockMvcResultMatchers.view().name("demo/hello"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("msg"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        Assert.assertNotNull(result.getModelAndView().getModel().get("msg"));
//
//        // 2. demo service stub test
//        String stub = "except result";
////        Mockito.when(orderService.demoShow()).thenReturn(stub);
////        Assert.assertEquals(stub, orderService.demoShow());
////        Mockito.verify(orderService).demoShow();
//    }

    @Test
    public void testIndex() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(handler().handlerType(HomeController.class))
                .andExpect(handler().methodName("index"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertNotNull(result.getModelAndView().getViewName());
    }

    @Test
    public void testLogin() throws Exception{
        UserModel admin = new UserModel();
        admin.isAdmin = true;
        admin.mobile = "admin";
        admin.password = "admin";
        String requestStr = JsonHelper.serialize(admin);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                                            .characterEncoding("UTF-8")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(requestStr)
                                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(handler().handlerType(HomeController.class))
                //.andExpect(MockMvcResultMatchers.handler().methodName("login"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)) //验证响应contentType
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String responseStr = result.getResponse().getContentAsString();
        ExecResult execResult = JsonHelper.deserialize(responseStr, ExecResult.class);
        assertTrue(execResult.result);

        UserModel driver = new UserModel();
        driver.isAdmin = false;
        driver.mobile = "15022221111";
        driver.password = "redmaple";
        requestStr = JsonHelper.serialize(admin);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestStr)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(handler().handlerType(HomeController.class))
                //.andExpect(handler().methodName("login"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)) //验证响应contentType
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        responseStr = result.getResponse().getContentAsString();
        execResult = JsonHelper.deserialize(responseStr, ExecResult.class);
        assertTrue(execResult.result);


    }

}
