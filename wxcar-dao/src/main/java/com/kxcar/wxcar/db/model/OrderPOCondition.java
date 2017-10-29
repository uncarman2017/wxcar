package com.kxcar.wxcar.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderPOCondition {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderPOCondition() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOrderidIsNull() {
            addCriterion("OrderID is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("OrderID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(Long value) {
            addCriterion("OrderID =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(Long value) {
            addCriterion("OrderID <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(Long value) {
            addCriterion("OrderID >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(Long value) {
            addCriterion("OrderID >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(Long value) {
            addCriterion("OrderID <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(Long value) {
            addCriterion("OrderID <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<Long> values) {
            addCriterion("OrderID in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<Long> values) {
            addCriterion("OrderID not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(Long value1, Long value2) {
            addCriterion("OrderID between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(Long value1, Long value2) {
            addCriterion("OrderID not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andDrivernameIsNull() {
            addCriterion("DriverName is null");
            return (Criteria) this;
        }

        public Criteria andDrivernameIsNotNull() {
            addCriterion("DriverName is not null");
            return (Criteria) this;
        }

        public Criteria andDrivernameEqualTo(String value) {
            addCriterion("DriverName =", value, "drivername");
            return (Criteria) this;
        }

        public Criteria andDrivernameNotEqualTo(String value) {
            addCriterion("DriverName <>", value, "drivername");
            return (Criteria) this;
        }

        public Criteria andDrivernameGreaterThan(String value) {
            addCriterion("DriverName >", value, "drivername");
            return (Criteria) this;
        }

        public Criteria andDrivernameGreaterThanOrEqualTo(String value) {
            addCriterion("DriverName >=", value, "drivername");
            return (Criteria) this;
        }

        public Criteria andDrivernameLessThan(String value) {
            addCriterion("DriverName <", value, "drivername");
            return (Criteria) this;
        }

        public Criteria andDrivernameLessThanOrEqualTo(String value) {
            addCriterion("DriverName <=", value, "drivername");
            return (Criteria) this;
        }

        public Criteria andDrivernameLike(String value) {
            addCriterion("DriverName like", value, "drivername");
            return (Criteria) this;
        }

        public Criteria andDrivernameNotLike(String value) {
            addCriterion("DriverName not like", value, "drivername");
            return (Criteria) this;
        }

        public Criteria andDrivernameIn(List<String> values) {
            addCriterion("DriverName in", values, "drivername");
            return (Criteria) this;
        }

        public Criteria andDrivernameNotIn(List<String> values) {
            addCriterion("DriverName not in", values, "drivername");
            return (Criteria) this;
        }

        public Criteria andDrivernameBetween(String value1, String value2) {
            addCriterion("DriverName between", value1, value2, "drivername");
            return (Criteria) this;
        }

        public Criteria andDrivernameNotBetween(String value1, String value2) {
            addCriterion("DriverName not between", value1, value2, "drivername");
            return (Criteria) this;
        }

        public Criteria andCustomermobileIsNull() {
            addCriterion("CustomerMobile is null");
            return (Criteria) this;
        }

        public Criteria andCustomermobileIsNotNull() {
            addCriterion("CustomerMobile is not null");
            return (Criteria) this;
        }

        public Criteria andCustomermobileEqualTo(String value) {
            addCriterion("CustomerMobile =", value, "customermobile");
            return (Criteria) this;
        }

        public Criteria andCustomermobileNotEqualTo(String value) {
            addCriterion("CustomerMobile <>", value, "customermobile");
            return (Criteria) this;
        }

        public Criteria andCustomermobileGreaterThan(String value) {
            addCriterion("CustomerMobile >", value, "customermobile");
            return (Criteria) this;
        }

        public Criteria andCustomermobileGreaterThanOrEqualTo(String value) {
            addCriterion("CustomerMobile >=", value, "customermobile");
            return (Criteria) this;
        }

        public Criteria andCustomermobileLessThan(String value) {
            addCriterion("CustomerMobile <", value, "customermobile");
            return (Criteria) this;
        }

        public Criteria andCustomermobileLessThanOrEqualTo(String value) {
            addCriterion("CustomerMobile <=", value, "customermobile");
            return (Criteria) this;
        }

        public Criteria andCustomermobileLike(String value) {
            addCriterion("CustomerMobile like", value, "customermobile");
            return (Criteria) this;
        }

        public Criteria andCustomermobileNotLike(String value) {
            addCriterion("CustomerMobile not like", value, "customermobile");
            return (Criteria) this;
        }

        public Criteria andCustomermobileIn(List<String> values) {
            addCriterion("CustomerMobile in", values, "customermobile");
            return (Criteria) this;
        }

        public Criteria andCustomermobileNotIn(List<String> values) {
            addCriterion("CustomerMobile not in", values, "customermobile");
            return (Criteria) this;
        }

        public Criteria andCustomermobileBetween(String value1, String value2) {
            addCriterion("CustomerMobile between", value1, value2, "customermobile");
            return (Criteria) this;
        }

        public Criteria andCustomermobileNotBetween(String value1, String value2) {
            addCriterion("CustomerMobile not between", value1, value2, "customermobile");
            return (Criteria) this;
        }

        public Criteria andDrivermobileIsNull() {
            addCriterion("DriverMobile is null");
            return (Criteria) this;
        }

        public Criteria andDrivermobileIsNotNull() {
            addCriterion("DriverMobile is not null");
            return (Criteria) this;
        }

        public Criteria andDrivermobileEqualTo(String value) {
            addCriterion("DriverMobile =", value, "drivermobile");
            return (Criteria) this;
        }

        public Criteria andDrivermobileNotEqualTo(String value) {
            addCriterion("DriverMobile <>", value, "drivermobile");
            return (Criteria) this;
        }

        public Criteria andDrivermobileGreaterThan(String value) {
            addCriterion("DriverMobile >", value, "drivermobile");
            return (Criteria) this;
        }

        public Criteria andDrivermobileGreaterThanOrEqualTo(String value) {
            addCriterion("DriverMobile >=", value, "drivermobile");
            return (Criteria) this;
        }

        public Criteria andDrivermobileLessThan(String value) {
            addCriterion("DriverMobile <", value, "drivermobile");
            return (Criteria) this;
        }

        public Criteria andDrivermobileLessThanOrEqualTo(String value) {
            addCriterion("DriverMobile <=", value, "drivermobile");
            return (Criteria) this;
        }

        public Criteria andDrivermobileLike(String value) {
            addCriterion("DriverMobile like", value, "drivermobile");
            return (Criteria) this;
        }

        public Criteria andDrivermobileNotLike(String value) {
            addCriterion("DriverMobile not like", value, "drivermobile");
            return (Criteria) this;
        }

        public Criteria andDrivermobileIn(List<String> values) {
            addCriterion("DriverMobile in", values, "drivermobile");
            return (Criteria) this;
        }

        public Criteria andDrivermobileNotIn(List<String> values) {
            addCriterion("DriverMobile not in", values, "drivermobile");
            return (Criteria) this;
        }

        public Criteria andDrivermobileBetween(String value1, String value2) {
            addCriterion("DriverMobile between", value1, value2, "drivermobile");
            return (Criteria) this;
        }

        public Criteria andDrivermobileNotBetween(String value1, String value2) {
            addCriterion("DriverMobile not between", value1, value2, "drivermobile");
            return (Criteria) this;
        }

        public Criteria andUsetimeIsNull() {
            addCriterion("UseTime is null");
            return (Criteria) this;
        }

        public Criteria andUsetimeIsNotNull() {
            addCriterion("UseTime is not null");
            return (Criteria) this;
        }

        public Criteria andUsetimeEqualTo(Date value) {
            addCriterion("UseTime =", value, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeNotEqualTo(Date value) {
            addCriterion("UseTime <>", value, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeGreaterThan(Date value) {
            addCriterion("UseTime >", value, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UseTime >=", value, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeLessThan(Date value) {
            addCriterion("UseTime <", value, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeLessThanOrEqualTo(Date value) {
            addCriterion("UseTime <=", value, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeIn(List<Date> values) {
            addCriterion("UseTime in", values, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeNotIn(List<Date> values) {
            addCriterion("UseTime not in", values, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeBetween(Date value1, Date value2) {
            addCriterion("UseTime between", value1, value2, "usetime");
            return (Criteria) this;
        }

        public Criteria andUsetimeNotBetween(Date value1, Date value2) {
            addCriterion("UseTime not between", value1, value2, "usetime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("Remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("Remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("Remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("Remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("Remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("Remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("Remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("Remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("Remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("Remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("Remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("Remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("Remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("Remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andProducttypeIsNull() {
            addCriterion("ProductType is null");
            return (Criteria) this;
        }

        public Criteria andProducttypeIsNotNull() {
            addCriterion("ProductType is not null");
            return (Criteria) this;
        }

        public Criteria andProducttypeEqualTo(String value) {
            addCriterion("ProductType =", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotEqualTo(String value) {
            addCriterion("ProductType <>", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeGreaterThan(String value) {
            addCriterion("ProductType >", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeGreaterThanOrEqualTo(String value) {
            addCriterion("ProductType >=", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeLessThan(String value) {
            addCriterion("ProductType <", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeLessThanOrEqualTo(String value) {
            addCriterion("ProductType <=", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeLike(String value) {
            addCriterion("ProductType like", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotLike(String value) {
            addCriterion("ProductType not like", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeIn(List<String> values) {
            addCriterion("ProductType in", values, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotIn(List<String> values) {
            addCriterion("ProductType not in", values, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeBetween(String value1, String value2) {
            addCriterion("ProductType between", value1, value2, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotBetween(String value1, String value2) {
            addCriterion("ProductType not between", value1, value2, "producttype");
            return (Criteria) this;
        }

        public Criteria andVehiclelevelIsNull() {
            addCriterion("VehicleLevel is null");
            return (Criteria) this;
        }

        public Criteria andVehiclelevelIsNotNull() {
            addCriterion("VehicleLevel is not null");
            return (Criteria) this;
        }

        public Criteria andVehiclelevelEqualTo(String value) {
            addCriterion("VehicleLevel =", value, "vehiclelevel");
            return (Criteria) this;
        }

        public Criteria andVehiclelevelNotEqualTo(String value) {
            addCriterion("VehicleLevel <>", value, "vehiclelevel");
            return (Criteria) this;
        }

        public Criteria andVehiclelevelGreaterThan(String value) {
            addCriterion("VehicleLevel >", value, "vehiclelevel");
            return (Criteria) this;
        }

        public Criteria andVehiclelevelGreaterThanOrEqualTo(String value) {
            addCriterion("VehicleLevel >=", value, "vehiclelevel");
            return (Criteria) this;
        }

        public Criteria andVehiclelevelLessThan(String value) {
            addCriterion("VehicleLevel <", value, "vehiclelevel");
            return (Criteria) this;
        }

        public Criteria andVehiclelevelLessThanOrEqualTo(String value) {
            addCriterion("VehicleLevel <=", value, "vehiclelevel");
            return (Criteria) this;
        }

        public Criteria andVehiclelevelLike(String value) {
            addCriterion("VehicleLevel like", value, "vehiclelevel");
            return (Criteria) this;
        }

        public Criteria andVehiclelevelNotLike(String value) {
            addCriterion("VehicleLevel not like", value, "vehiclelevel");
            return (Criteria) this;
        }

        public Criteria andVehiclelevelIn(List<String> values) {
            addCriterion("VehicleLevel in", values, "vehiclelevel");
            return (Criteria) this;
        }

        public Criteria andVehiclelevelNotIn(List<String> values) {
            addCriterion("VehicleLevel not in", values, "vehiclelevel");
            return (Criteria) this;
        }

        public Criteria andVehiclelevelBetween(String value1, String value2) {
            addCriterion("VehicleLevel between", value1, value2, "vehiclelevel");
            return (Criteria) this;
        }

        public Criteria andVehiclelevelNotBetween(String value1, String value2) {
            addCriterion("VehicleLevel not between", value1, value2, "vehiclelevel");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("Price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("Price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Double value) {
            addCriterion("Price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Double value) {
            addCriterion("Price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Double value) {
            addCriterion("Price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("Price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Double value) {
            addCriterion("Price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Double value) {
            addCriterion("Price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Double> values) {
            addCriterion("Price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Double> values) {
            addCriterion("Price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Double value1, Double value2) {
            addCriterion("Price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Double value1, Double value2) {
            addCriterion("Price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andDepartlocationIsNull() {
            addCriterion("DepartLocation is null");
            return (Criteria) this;
        }

        public Criteria andDepartlocationIsNotNull() {
            addCriterion("DepartLocation is not null");
            return (Criteria) this;
        }

        public Criteria andDepartlocationEqualTo(String value) {
            addCriterion("DepartLocation =", value, "departlocation");
            return (Criteria) this;
        }

        public Criteria andDepartlocationNotEqualTo(String value) {
            addCriterion("DepartLocation <>", value, "departlocation");
            return (Criteria) this;
        }

        public Criteria andDepartlocationGreaterThan(String value) {
            addCriterion("DepartLocation >", value, "departlocation");
            return (Criteria) this;
        }

        public Criteria andDepartlocationGreaterThanOrEqualTo(String value) {
            addCriterion("DepartLocation >=", value, "departlocation");
            return (Criteria) this;
        }

        public Criteria andDepartlocationLessThan(String value) {
            addCriterion("DepartLocation <", value, "departlocation");
            return (Criteria) this;
        }

        public Criteria andDepartlocationLessThanOrEqualTo(String value) {
            addCriterion("DepartLocation <=", value, "departlocation");
            return (Criteria) this;
        }

        public Criteria andDepartlocationLike(String value) {
            addCriterion("DepartLocation like", value, "departlocation");
            return (Criteria) this;
        }

        public Criteria andDepartlocationNotLike(String value) {
            addCriterion("DepartLocation not like", value, "departlocation");
            return (Criteria) this;
        }

        public Criteria andDepartlocationIn(List<String> values) {
            addCriterion("DepartLocation in", values, "departlocation");
            return (Criteria) this;
        }

        public Criteria andDepartlocationNotIn(List<String> values) {
            addCriterion("DepartLocation not in", values, "departlocation");
            return (Criteria) this;
        }

        public Criteria andDepartlocationBetween(String value1, String value2) {
            addCriterion("DepartLocation between", value1, value2, "departlocation");
            return (Criteria) this;
        }

        public Criteria andDepartlocationNotBetween(String value1, String value2) {
            addCriterion("DepartLocation not between", value1, value2, "departlocation");
            return (Criteria) this;
        }

        public Criteria andArrivelocationIsNull() {
            addCriterion("ArriveLocation is null");
            return (Criteria) this;
        }

        public Criteria andArrivelocationIsNotNull() {
            addCriterion("ArriveLocation is not null");
            return (Criteria) this;
        }

        public Criteria andArrivelocationEqualTo(String value) {
            addCriterion("ArriveLocation =", value, "arrivelocation");
            return (Criteria) this;
        }

        public Criteria andArrivelocationNotEqualTo(String value) {
            addCriterion("ArriveLocation <>", value, "arrivelocation");
            return (Criteria) this;
        }

        public Criteria andArrivelocationGreaterThan(String value) {
            addCriterion("ArriveLocation >", value, "arrivelocation");
            return (Criteria) this;
        }

        public Criteria andArrivelocationGreaterThanOrEqualTo(String value) {
            addCriterion("ArriveLocation >=", value, "arrivelocation");
            return (Criteria) this;
        }

        public Criteria andArrivelocationLessThan(String value) {
            addCriterion("ArriveLocation <", value, "arrivelocation");
            return (Criteria) this;
        }

        public Criteria andArrivelocationLessThanOrEqualTo(String value) {
            addCriterion("ArriveLocation <=", value, "arrivelocation");
            return (Criteria) this;
        }

        public Criteria andArrivelocationLike(String value) {
            addCriterion("ArriveLocation like", value, "arrivelocation");
            return (Criteria) this;
        }

        public Criteria andArrivelocationNotLike(String value) {
            addCriterion("ArriveLocation not like", value, "arrivelocation");
            return (Criteria) this;
        }

        public Criteria andArrivelocationIn(List<String> values) {
            addCriterion("ArriveLocation in", values, "arrivelocation");
            return (Criteria) this;
        }

        public Criteria andArrivelocationNotIn(List<String> values) {
            addCriterion("ArriveLocation not in", values, "arrivelocation");
            return (Criteria) this;
        }

        public Criteria andArrivelocationBetween(String value1, String value2) {
            addCriterion("ArriveLocation between", value1, value2, "arrivelocation");
            return (Criteria) this;
        }

        public Criteria andArrivelocationNotBetween(String value1, String value2) {
            addCriterion("ArriveLocation not between", value1, value2, "arrivelocation");
            return (Criteria) this;
        }

        public Criteria andAddressdetailIsNull() {
            addCriterion("AddressDetail is null");
            return (Criteria) this;
        }

        public Criteria andAddressdetailIsNotNull() {
            addCriterion("AddressDetail is not null");
            return (Criteria) this;
        }

        public Criteria andAddressdetailEqualTo(String value) {
            addCriterion("AddressDetail =", value, "addressdetail");
            return (Criteria) this;
        }

        public Criteria andAddressdetailNotEqualTo(String value) {
            addCriterion("AddressDetail <>", value, "addressdetail");
            return (Criteria) this;
        }

        public Criteria andAddressdetailGreaterThan(String value) {
            addCriterion("AddressDetail >", value, "addressdetail");
            return (Criteria) this;
        }

        public Criteria andAddressdetailGreaterThanOrEqualTo(String value) {
            addCriterion("AddressDetail >=", value, "addressdetail");
            return (Criteria) this;
        }

        public Criteria andAddressdetailLessThan(String value) {
            addCriterion("AddressDetail <", value, "addressdetail");
            return (Criteria) this;
        }

        public Criteria andAddressdetailLessThanOrEqualTo(String value) {
            addCriterion("AddressDetail <=", value, "addressdetail");
            return (Criteria) this;
        }

        public Criteria andAddressdetailLike(String value) {
            addCriterion("AddressDetail like", value, "addressdetail");
            return (Criteria) this;
        }

        public Criteria andAddressdetailNotLike(String value) {
            addCriterion("AddressDetail not like", value, "addressdetail");
            return (Criteria) this;
        }

        public Criteria andAddressdetailIn(List<String> values) {
            addCriterion("AddressDetail in", values, "addressdetail");
            return (Criteria) this;
        }

        public Criteria andAddressdetailNotIn(List<String> values) {
            addCriterion("AddressDetail not in", values, "addressdetail");
            return (Criteria) this;
        }

        public Criteria andAddressdetailBetween(String value1, String value2) {
            addCriterion("AddressDetail between", value1, value2, "addressdetail");
            return (Criteria) this;
        }

        public Criteria andAddressdetailNotBetween(String value1, String value2) {
            addCriterion("AddressDetail not between", value1, value2, "addressdetail");
            return (Criteria) this;
        }

        public Criteria andCarliscenceIsNull() {
            addCriterion("CarLiscence is null");
            return (Criteria) this;
        }

        public Criteria andCarliscenceIsNotNull() {
            addCriterion("CarLiscence is not null");
            return (Criteria) this;
        }

        public Criteria andCarliscenceEqualTo(String value) {
            addCriterion("CarLiscence =", value, "carliscence");
            return (Criteria) this;
        }

        public Criteria andCarliscenceNotEqualTo(String value) {
            addCriterion("CarLiscence <>", value, "carliscence");
            return (Criteria) this;
        }

        public Criteria andCarliscenceGreaterThan(String value) {
            addCriterion("CarLiscence >", value, "carliscence");
            return (Criteria) this;
        }

        public Criteria andCarliscenceGreaterThanOrEqualTo(String value) {
            addCriterion("CarLiscence >=", value, "carliscence");
            return (Criteria) this;
        }

        public Criteria andCarliscenceLessThan(String value) {
            addCriterion("CarLiscence <", value, "carliscence");
            return (Criteria) this;
        }

        public Criteria andCarliscenceLessThanOrEqualTo(String value) {
            addCriterion("CarLiscence <=", value, "carliscence");
            return (Criteria) this;
        }

        public Criteria andCarliscenceLike(String value) {
            addCriterion("CarLiscence like", value, "carliscence");
            return (Criteria) this;
        }

        public Criteria andCarliscenceNotLike(String value) {
            addCriterion("CarLiscence not like", value, "carliscence");
            return (Criteria) this;
        }

        public Criteria andCarliscenceIn(List<String> values) {
            addCriterion("CarLiscence in", values, "carliscence");
            return (Criteria) this;
        }

        public Criteria andCarliscenceNotIn(List<String> values) {
            addCriterion("CarLiscence not in", values, "carliscence");
            return (Criteria) this;
        }

        public Criteria andCarliscenceBetween(String value1, String value2) {
            addCriterion("CarLiscence between", value1, value2, "carliscence");
            return (Criteria) this;
        }

        public Criteria andCarliscenceNotBetween(String value1, String value2) {
            addCriterion("CarLiscence not between", value1, value2, "carliscence");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIsNull() {
            addCriterion("OrderStatus is null");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIsNotNull() {
            addCriterion("OrderStatus is not null");
            return (Criteria) this;
        }

        public Criteria andOrderstatusEqualTo(String value) {
            addCriterion("OrderStatus =", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotEqualTo(String value) {
            addCriterion("OrderStatus <>", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusGreaterThan(String value) {
            addCriterion("OrderStatus >", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusGreaterThanOrEqualTo(String value) {
            addCriterion("OrderStatus >=", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusLessThan(String value) {
            addCriterion("OrderStatus <", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusLessThanOrEqualTo(String value) {
            addCriterion("OrderStatus <=", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusLike(String value) {
            addCriterion("OrderStatus like", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotLike(String value) {
            addCriterion("OrderStatus not like", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIn(List<String> values) {
            addCriterion("OrderStatus in", values, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotIn(List<String> values) {
            addCriterion("OrderStatus not in", values, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusBetween(String value1, String value2) {
            addCriterion("OrderStatus between", value1, value2, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotBetween(String value1, String value2) {
            addCriterion("OrderStatus not between", value1, value2, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeIsNull() {
            addCriterion("DataChange_CreateTime is null");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeIsNotNull() {
            addCriterion("DataChange_CreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeEqualTo(Date value) {
            addCriterion("DataChange_CreateTime =", value, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeNotEqualTo(Date value) {
            addCriterion("DataChange_CreateTime <>", value, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeGreaterThan(Date value) {
            addCriterion("DataChange_CreateTime >", value, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("DataChange_CreateTime >=", value, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeLessThan(Date value) {
            addCriterion("DataChange_CreateTime <", value, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("DataChange_CreateTime <=", value, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeIn(List<Date> values) {
            addCriterion("DataChange_CreateTime in", values, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeNotIn(List<Date> values) {
            addCriterion("DataChange_CreateTime not in", values, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeBetween(Date value1, Date value2) {
            addCriterion("DataChange_CreateTime between", value1, value2, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("DataChange_CreateTime not between", value1, value2, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeIsNull() {
            addCriterion("DataChange_LastTime is null");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeIsNotNull() {
            addCriterion("DataChange_LastTime is not null");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeEqualTo(Date value) {
            addCriterion("DataChange_LastTime =", value, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeNotEqualTo(Date value) {
            addCriterion("DataChange_LastTime <>", value, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeGreaterThan(Date value) {
            addCriterion("DataChange_LastTime >", value, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("DataChange_LastTime >=", value, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeLessThan(Date value) {
            addCriterion("DataChange_LastTime <", value, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeLessThanOrEqualTo(Date value) {
            addCriterion("DataChange_LastTime <=", value, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeIn(List<Date> values) {
            addCriterion("DataChange_LastTime in", values, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeNotIn(List<Date> values) {
            addCriterion("DataChange_LastTime not in", values, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeBetween(Date value1, Date value2) {
            addCriterion("DataChange_LastTime between", value1, value2, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeNotBetween(Date value1, Date value2) {
            addCriterion("DataChange_LastTime not between", value1, value2, "datachangeLasttime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}