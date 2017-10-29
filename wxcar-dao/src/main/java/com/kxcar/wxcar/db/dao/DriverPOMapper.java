package com.kxcar.wxcar.db.dao;

import com.kxcar.wxcar.db.model.DriverPO;

import java.util.List;

import com.kxcar.wxcar.db.model.DriverPOCondition;
import org.apache.ibatis.annotations.Param;

public interface DriverPOMapper {
    int countByExample(DriverPOCondition example);

    int deleteByExample(DriverPOCondition example);

    int deleteByPrimaryKey(@Param("driverid") Integer driverid);

    int insert(DriverPO record);

    int insertSelective(DriverPO record);

    List<DriverPO> selectByExample(DriverPOCondition example);

    DriverPO selectByPrimaryKey(Integer driverid);

    int updateByExampleSelective(DriverPO record, DriverPOCondition example);

    int updateByExample(DriverPO record,DriverPOCondition example);

    int updateByPrimaryKeySelective(DriverPO record);

    int updateByPrimaryKey(DriverPO record);

    //region 扩展方法
    /**
     * 根据手机号获取管理员信息
     * */
    DriverPO getDriver(@Param("mobile") String mobile);

    /**
    * 登录验证身份用
     */
    DriverPO getDriverByPwd(@Param("mobile") String mobile, @Param("pwd") String pwd);

    /**
     * 获取全部司机
     * */
    List<DriverPO> getAllDrivers(@Param("pageNo") int pageNo);
    //endregion

}