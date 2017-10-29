package com.kxcar.wxcar.business.service.impl;


import com.kxcar.wxcar.business.model.*;
import com.kxcar.wxcar.business.service.IUserService;
import com.kxcar.wxcar.business.translator.CommonTranslator;
import com.kxcar.wxcar.db.SqlSessionFactoryUtil;
import com.kxcar.wxcar.db.dao.AdminPOMapper;
import com.kxcar.wxcar.db.dao.DriverPOMapper;
import com.kxcar.wxcar.db.dao.OrderPOMapper;
import com.kxcar.wxcar.db.model.AdminPO;
import com.kxcar.wxcar.db.model.DriverPO;
import com.kxcar.wxcar.db.model.OrderPO;
import com.kxcar.wxcar.util.config.Tuple;
import com.kxcar.wxcar.util.text.LogHelper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uncar_000 on 20161210.
 */
@Service
public class UserService implements IUserService
{
    public UserService(){

    }

    //region 司机操作

    /*
    * 注册新司机
    * */
    @Override
    public Tuple registerDriver(DriverModel newDriver) {

        SqlSession sqlSession = null;
        String msg = "注册司机成功";
        int driverId = 0;

        try{
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            DriverPOMapper driverMapper = sqlSession.getMapper(DriverPOMapper.class);
            DriverPO po = new DriverPO();
            po.setName(newDriver.name);
            po.setCarliscence(newDriver.carLiscence);
            po.setMobile(newDriver.mobile);
            po.setPwd(newDriver.pwd);
            po.setRemark(newDriver.remark);
            po.setDriverType(newDriver.driverType.getValue());
            po.setDriverStatus(DriverStatus.Enable.getValue());
            msg = driverMapper.insert(po) > 0 ? "注册司机成功" : "注册司机失败";
            driverId = po.getDriverid();
            sqlSession.commit();
        }
        catch (Exception e){
            sqlSession.rollback();
            msg = "注册司机异常";
            LogHelper.error(e);
        }
        finally {
            if(sqlSession != null)sqlSession.close();
        }

        return Tuple.<Integer,String>of(driverId,msg);
    }

    /*
    * 验证司机身份
    * */
    @Override
    public DriverModel validateDriver(String mobile, String pwd) {
        SqlSession sqlSession = null;
        DriverModel driverModel = new DriverModel();

        try{
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            DriverPOMapper driverMapper = sqlSession.getMapper(DriverPOMapper.class);
            DriverPO driverPO = driverMapper.getDriverByPwd(mobile,pwd);
            if(driverPO != null) {
                driverModel.driverId = driverPO.getDriverid();
                driverModel.carLiscence = driverPO.getCarliscence();
                driverModel.driverStatus = DriverStatus.findByValue(driverPO.getDriverStatus());
                driverModel.driverType = DriverType.findByValue(driverPO.getDriverType());
                driverModel.mobile = driverPO.getMobile();
                driverModel.name = driverPO.getName();
                driverModel.pwd = driverPO.getPwd();
                driverModel.remark = driverPO.getRemark();
                driverModel.msg = "司机身份验证通过";
                driverModel.result = true;
            }
            else {
                driverModel.msg = "司机身份验证失败";
                driverModel.result = false;
            }
        }
        catch (Exception e){
            LogHelper.error(e);
        }
        finally {
            if(sqlSession != null)sqlSession.close();
        }

        return driverModel;

    }

    /**
     * 获取司机信息详情
     */
    @Override
    public DriverModel getDriver(String mobile) {

        SqlSession sqlSession = null;
        DriverModel driverModel = new DriverModel();

        try{
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            DriverPOMapper driverMapper = sqlSession.getMapper(DriverPOMapper.class);
            DriverPO driverPO = driverMapper.getDriver(mobile);
            if(driverPO != null) {
                driverModel.driverId = driverPO.getDriverid();
                driverModel.carLiscence = driverPO.getCarliscence();
                driverModel.driverStatus = DriverStatus.findByValue(driverPO.getDriverStatus());
                driverModel.driverType = DriverType.findByValue(driverPO.getDriverType());
                driverModel.mobile = driverPO.getMobile();
                driverModel.name = driverPO.getName();
                driverModel.pwd = driverPO.getPwd();
                driverModel.remark = driverPO.getRemark().isEmpty() ? "这位司机没有留下任何信息" : driverPO.getRemark();
                driverModel.result = true;
                driverModel.msg = "获取司机信息明细成功";
            }
            else{
                driverModel.result = false;
                driverModel.msg = "获取司机信息明细失败";
            }

        }
        catch (Exception e){
            LogHelper.error(e);
        }
        finally {
            if(sqlSession != null)sqlSession.close();
        }

        return driverModel;
    }

    /*
    * 更新司机详情
    * */
    public Tuple updateDriver(DriverModel driver){
        SqlSession sqlSession = null;
        String msg = "更新司机成功";
        boolean result = true;
        try{
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            DriverPOMapper driverMapper = sqlSession.getMapper(DriverPOMapper.class);
            DriverPO driverPO = new DriverPO();
            driverPO.setDriverid(driver.driverId);
            driverPO.setName(driver.name);
            driverPO.setCarliscence(driver.carLiscence);
            driverPO.setMobile(driver.mobile);
            driverPO.setPwd(driver.pwd);
            driverPO.setRemark(driver.remark);
            driverPO.setDriverType(driver.driverType.getValue());
            driverPO.setDriverStatus(driver.driverStatus.getValue());
            msg = driverMapper.updateByPrimaryKeySelective(driverPO) > 0 ? "更新司机成功" : "更新司机失败";

            sqlSession.commit();
        }
        catch (Exception e){
            sqlSession.rollback();
            msg = "更新司机异常";
            result = false;
            LogHelper.error(e);
        }
        finally {
            if(sqlSession != null)sqlSession.close();
        }

        return Tuple.<Boolean,String>of(result,msg);
    }

    //endregion

    //region 管理员操作

    @Override
    public Tuple registerAdmin(AdminModel newAdmin) {

        SqlSession sqlSession = null;
        String msg = "注册司机成功";
        int adminId = 0;

        try{
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            AdminPOMapper adminMapper = sqlSession.getMapper(AdminPOMapper.class);
            AdminPO po = new AdminPO();
            po.setMobile(newAdmin.mobile);
            po.setPwd(newAdmin.pwd);
            msg = adminMapper.insert(po) > 0 ? "注册管理员成功" : "注册管理员失败";
            adminId = po.getAdminid();
            sqlSession.commit();
        }
        catch (Exception e){
            sqlSession.rollback();
            msg = "注册管理员异常";
            LogHelper.error(e);
        }
        finally {
            if(sqlSession != null)sqlSession.close();
        }

        return Tuple.<Integer,String>of(adminId,msg);
    }

    @Override
    public AdminModel validateAdmin(String mobile, String pwd) {
        SqlSession sqlSession = null;
        AdminModel adminModel = new AdminModel();

        try{
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            AdminPOMapper adminMapper = sqlSession.getMapper(AdminPOMapper.class);
            AdminPO adminPO = adminMapper.getAdminByPwd(mobile,pwd);
            if(adminPO != null) {
                adminModel.adminId = adminPO.getAdminid();
                adminModel.mobile = adminPO.getMobile();
                adminModel.pwd = adminPO.getPwd();
                adminModel.msg = "管理员身份验证通过";
                adminModel.result = true;
            }
            else {
                adminModel.msg = "管理员身份验证失败";
                adminModel.result = false;
            }
        }
        catch (Exception e){
            LogHelper.error(e);
        }
        finally {
            if(sqlSession != null)sqlSession.close();
        }

        return adminModel;
    }

    @Override
    public AdminModel getAdmin(String mobile) {
        SqlSession sqlSession = null;
        AdminModel adminModel = new AdminModel();

        try{
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            AdminPOMapper adminMapper = sqlSession.getMapper(AdminPOMapper.class);
            AdminPO adminPO = adminMapper.getAdmin(mobile);
            if(adminPO != null) {
                adminModel.adminId = adminPO.getAdminid();
                adminModel.mobile = adminPO.getMobile();
                adminModel.pwd = adminPO.getPwd();
                adminModel.result = true;
                adminModel.msg = "获取管理员信息明细成功";
            }
            else{
                adminModel.result = false;
                adminModel.msg = "获取管理员信息明细失败";
            }

        }
        catch (Exception e){
            LogHelper.error(e);
        }
        finally {
            if(sqlSession != null)sqlSession.close();
        }

        return adminModel;
    }

    @Override
    public Tuple updateAdmin(AdminModel admin) {
        SqlSession sqlSession = null;
        String msg = "更新管理员成功";
        boolean result = true;
        try{
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            AdminPOMapper adminMapper = sqlSession.getMapper(AdminPOMapper.class);
            AdminPO adminPO = new AdminPO();
            adminPO.setAdminid(admin.adminId);
            adminPO.setMobile(admin.mobile);
            adminPO.setPwd(admin.pwd);
            msg = adminMapper.updateByPrimaryKey(adminPO) > 0 ? "更新管理员成功" : "更新管理员失败";

            sqlSession.commit();
        }
        catch (Exception e){
            sqlSession.rollback();
            msg = "更新管理员异常";
            result = false;
            LogHelper.error(e);
        }
        finally {
            if(sqlSession != null)sqlSession.close();
        }

        return Tuple.<Boolean,String>of(result,msg);
    }

    @Override
    public List<DriverModel> getAllDrivers(int pageNo) {
        SqlSession sqlSession = null;
        List<DriverModel> result = new ArrayList<>();

        try{
            sqlSession = SqlSessionFactoryUtil.getInstance().openSession();
            DriverPOMapper driverMapper = sqlSession.getMapper(DriverPOMapper.class);
            List<DriverPO> driverList = driverMapper.getAllDrivers(pageNo);
            for (DriverPO driverPO : driverList) {
                DriverModel orderModel = CommonTranslator.toDriverModel(driverPO);
                result.add(orderModel);
            }

        }
        catch (Exception e){
            LogHelper.error(e);
        }
        finally {
            if(sqlSession != null)sqlSession.close();
        }

        return result;
    }

    //endregion
}
