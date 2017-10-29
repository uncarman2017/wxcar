package com.kxcar.wxcar.db.dao;

import com.kxcar.wxcar.db.model.AdminPO;
import com.kxcar.wxcar.db.model.AdminPOExample;

import java.util.List;


import org.apache.ibatis.annotations.Param;

public interface AdminPOMapper {
    int countByExample(AdminPOExample example);

    int deleteByExample(AdminPOExample example);

    int deleteByPrimaryKey(@Param("adminid") Integer adminid);

    int insert(AdminPO record);

    int insertSelective(AdminPO record);

    List<AdminPO> selectByExample(AdminPOExample example);

    AdminPO selectByPrimaryKey(@Param("adminid") Integer adminid);

    int updateByExampleSelective(AdminPO record, AdminPOExample example);

    int updateByExample(AdminPO record,AdminPOExample example);

    int updateByPrimaryKeySelective(AdminPO record);

    int updateByPrimaryKey(AdminPO record);

    //region 扩展方法

    /**
     * 根据手机号获取管理员信息
     */
    AdminPO getAdmin(@Param("mobile") String mobile);

    /**
     * 管理员登录验证身份用
     */
    AdminPO getAdminByPwd(@Param("mobile") String mobile, @Param("pwd") String pwd);

    //endregion

}