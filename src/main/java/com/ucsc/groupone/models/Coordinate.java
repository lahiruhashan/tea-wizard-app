/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.models;

/**
 *
 * @author hashan
 */
public class Coordinate {
    
    private int pointId;
    private double latitude;
    private double longitude;
    private double altitude;
    private double speed;

    public Coordinate() {
    }

    public Coordinate(int pointId, double latitude, double longitude, double altitude) {
        this.pointId = pointId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        
    }

    public Coordinate(int pointId, double latitude, double longitude, double altitude, double speed) {
        this.pointId = pointId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.speed = speed;
    }
    
    

    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    
    
    
}
