package com.kxcar.wxcar.db.model;

import java.util.Date;

public class OrderPO {
    private Long orderid;

    private String drivername;

    private String customermobile;

    private String drivermobile;

    private Date usetime;

    private String remark;

    private String producttype;

    private String vehiclelevel;

    private Double price;

    private String departlocation;

    private String arrivelocation;

    private String addressdetail;

    private String carliscence;

    private String orderstatus;

    private Date datachangeCreatetime;

    private Date datachangeLasttime;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername == null ? null : drivername.trim();
    }

    public String getCustomermobile() {
        return customermobile;
    }

    public void setCustomermobile(String customermobile) {
        this.customermobile = customermobile == null ? null : customermobile.trim();
    }

    public String getDrivermobile() {
        return drivermobile;
    }

    public void setDrivermobile(String drivermobile) {
        this.drivermobile = drivermobile == null ? null : drivermobile.trim();
    }

    public Date getUsetime() {
        return usetime;
    }

    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype == null ? null : producttype.trim();
    }

    public String getVehiclelevel() {
        return vehiclelevel;
    }

    public void setVehiclelevel(String vehiclelevel) {
        this.vehiclelevel = vehiclelevel == null ? null : vehiclelevel.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDepartlocation() {
        return departlocation;
    }

    public void setDepartlocation(String departlocation) {
        this.departlocation = departlocation == null ? null : departlocation.trim();
    }

    public String getArrivelocation() {
        return arrivelocation;
    }

    public void setArrivelocation(String arrivelocation) {
        this.arrivelocation = arrivelocation == null ? null : arrivelocation.trim();
    }

    public String getAddressdetail() {
        return addressdetail;
    }

    public void setAddressdetail(String addressdetail) {
        this.addressdetail = addressdetail == null ? null : addressdetail.trim();
    }

    public String getCarliscence() {
        return carliscence;
    }

    public void setCarliscence(String carliscence) {
        this.carliscence = carliscence == null ? null : carliscence.trim();
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus == null ? null : orderstatus.trim();
    }

    public Date getDatachangeCreatetime() {
        return datachangeCreatetime;
    }

    public void setDatachangeCreatetime(Date datachangeCreatetime) {
        this.datachangeCreatetime = datachangeCreatetime;
    }

    public Date getDatachangeLasttime() {
        return datachangeLasttime;
    }

    public void setDatachangeLasttime(Date datachangeLasttime) {
        this.datachangeLasttime = datachangeLasttime;
    }
}