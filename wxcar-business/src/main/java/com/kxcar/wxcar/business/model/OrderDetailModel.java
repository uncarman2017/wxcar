package com.kxcar.wxcar.business.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaxYu on 2017/1/27.
 */
public class OrderDetailModel extends OrderModel
{
    public List<ListItemModel> orderStatusList = new ArrayList<>();

    public List<ListItemModel> productTypeList = new ArrayList<>();

    public List<ListItemModel> vehicleLevelList = new ArrayList<>();


    public OrderDetailModel(){
        //super();
        orderStatusList.add(new ListItemModel("新建","Submitted"));
        orderStatusList.add(new ListItemModel("进行中","Pending"));
        orderStatusList.add(new ListItemModel("完成","Completed"));
        orderStatusList.add(new ListItemModel("取消","Cancelled"));
        orderStatusList.add(new ListItemModel("过期","Expired"));

        productTypeList.add(new ListItemModel("国内接机","JieJi"));
        productTypeList.add(new ListItemModel("国内送机","SongJi"));

        vehicleLevelList.add(new ListItemModel("经济型","Economy"));
        vehicleLevelList.add(new ListItemModel("舒适型","Comfort"));
        vehicleLevelList.add(new ListItemModel("商务型","Business"));


    }


}

