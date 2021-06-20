package paidagogos.speck.model;

import paidagogos.speck.map.OperationTime;

public class PlaceInfo {
    private String code;
    private String name;
    private String doroName;
    private String phoneNumber;
    private double lat;
    private double lng;
    private int todayVisitor;
    private int num;
    private OperationTime operationTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoroName() {
        return doroName;
    }

    public void setDoroName(String doroName) {
        this.doroName = doroName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getTodayVisitor() {
        return todayVisitor;
    }

    public void setTodayVisitor(int todayVisitor) {
        this.todayVisitor = todayVisitor;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public OperationTime getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(OperationTime operationTime) {
        this.operationTime = operationTime;
    }
}
