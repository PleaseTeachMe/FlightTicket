package com.zl.fiight.entity;


import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/6/3.
 */
public class AirTicket extends BmobObject  {
    private String startLocation;
    private String endLocation;
    private String Category;
    private String Startairport;
    private String Endairport;
    private String FlightName;
    private int Price;
    private String FlightNumber;
    private String startTime1;
    private String startTime2;
    private String endTime1;
    private String endTime2;

    public String getEndTime1() {
        return endTime1;
    }

    public void setEndTime1(String endTime1) {
        this.endTime1 = endTime1;
    }

    public String getEndTime2() {
        return endTime2;
    }

    public void setEndTime2(String endTime2) {
        this.endTime2 = endTime2;
    }

    public String getStartTime1() {
        return startTime1;
    }

    public void setStartTime1(String startTime1) {
        this.startTime1 = startTime1;
    }

    public String getStartTime2() {
        return startTime2;
    }

    public void setStartTime2(String startTime2) {
        this.startTime2 = startTime2;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getEndairport() {
        return Endairport;
    }

    public void setEndairport(String endairport) {
        Endairport = endairport;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getFlightName() {
        return FlightName;
    }

    public void setFlightName(String flightName) {
        FlightName = flightName;
    }

    public String getFlightNumber() {
        return FlightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        FlightNumber = flightNumber;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getStartairport() {
        return Startairport;
    }

    public void setStartairport(String startairport) {
        Startairport = startairport;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

}
