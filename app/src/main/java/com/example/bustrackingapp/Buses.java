package com.example.bustrackingapp;

public class Buses {

    String BusNumber;
    String FromLocation;
    String ToLocation;
    String BusLatitude;
    String BusLongitude;
    String BusSpeed;



    public Buses() {
    }

    public Buses(String busNumber, String fromLocation, String toLocation, String busLatitude, String busLongitude, String busSpeed) {
        this.BusNumber = busNumber;
        this.FromLocation = fromLocation;
        this.ToLocation = toLocation;
        this.BusLatitude = busLatitude;
        this.BusLongitude = busLongitude;
        this.BusSpeed = busSpeed;
    }

    public String getBusNumber() {
        return BusNumber;
    }

    public void setBusNumber(String busNumber) {
        this.BusNumber = busNumber;
    }

    public String getFromLocation() {
        return FromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.FromLocation = fromLocation;
    }

    public String getToLocation() {
        return ToLocation;
    }

    public void setToLocation(String toLocation) {
        this.ToLocation = toLocation;
    }

    public String getBusLatitude() {
        return BusLatitude;
    }

    public void setBusLatitude(String busLatitude) {
        this.BusLatitude = busLatitude;
    }

    public String getBusLongitude() {
        return BusLongitude;
    }

    public void setBusLongitude(String busLongitude) {
        this.BusLongitude = busLongitude;
    }

    public String getBusSpeed() {
        return BusSpeed;
    }

    public void setBusSpeed(String busSpeed) {
        this.BusSpeed = busSpeed;
    }
}
