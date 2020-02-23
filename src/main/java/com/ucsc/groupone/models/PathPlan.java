/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.models;

import java.util.HashMap;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author hashan
 */
public class PathPlan extends JLabel{
    
    private List<Coordinate>  coordinateList;
    private double speed;
    private String name;
    private String filePath;

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public void addCoordinate(Coordinate coordinate) {
        this.coordinateList.add(coordinate);
    }
    
}
