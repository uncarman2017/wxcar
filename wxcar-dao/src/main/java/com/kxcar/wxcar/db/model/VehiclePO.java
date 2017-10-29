package com.kxcar.wxcar.db.model;

/**
 * Created by uncar_000 on 2016-12-25.
 */
public class VehiclePO
{
    private int vehicleID;

    private String carLiscence;


    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getCarLiscence() {
        return carLiscence;
    }

    public void setCarLiscence(String carLiscence) {
        this.carLiscence = carLiscence;
    }
}
