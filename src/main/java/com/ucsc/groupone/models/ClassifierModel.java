/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.models;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author hashan
 */
public class ClassifierModel extends JLabel{
    String figPath;
    String tiPath;
    String oiPath;
    String cfPath;
    String path;
    String name;
    String pipelineConfiguration;
    String annotatedImagesPath;
    String trainDatasetPath;
    String testDatasetPath;
    int numberOfClasses;
    boolean traninable = true;
    
    public ClassifierModel() {
        
    }

    public ClassifierModel(ImageIcon imageIcon) {
        super(imageIcon);
    }

    public ClassifierModel(String figPath, String tiPath, String oiPath, String cfPath) {
        this.figPath = figPath;
        this.tiPath = tiPath;
        this.oiPath = oiPath;
        this.cfPath = cfPath;
    }
    
    public ClassifierModel(ImageIcon imageIcon, String figPath, String tiPath, String oiPath, String cfPath) {
        super(imageIcon);
        this.figPath = figPath;
        this.tiPath = tiPath;
        this.oiPath = oiPath;
        this.cfPath = cfPath;
    }

    public String getFigPath() {
        return figPath;
    }

    public void setFigPath(String figPath) {
        this.figPath = figPath;
    }

    public String getTiPath() {
        return tiPath;
    }

    public void setTiPath(String tiPath) {
        this.tiPath = tiPath;
    }

    public String getOiPath() {
        return oiPath;
    }

    public void setOiPath(String oiPath) {
        this.oiPath = oiPath;
    }

    public String getCfPath() {
        return cfPath;
    }

    public void setCfPath(String cfPath) {
        this.cfPath = cfPath;
    }
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPipelineConfiguration() {
        return pipelineConfiguration;
    }

    public void setPipelineConfiguration(String pipelineConfiguration) {
        this.pipelineConfiguration = pipelineConfiguration;
    }

    public String getAnnotatedImagesPath() {
        return annotatedImagesPath;
    }

    public void setAnnotatedImagesPath(String annotatedImagesPath) {
        this.annotatedImagesPath = annotatedImagesPath;
    }

    public String getTestDatasetPath() {
        return testDatasetPath;
    }

    public void setTestDatasetPath(String testDatasetPath) {
        this.testDatasetPath = testDatasetPath;
    }

    public String getTrainDatasetPath() {
        return trainDatasetPath;
    }

    public void setTrainDatasetPath(String trainDatasetPath) {
        this.trainDatasetPath = trainDatasetPath;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    public boolean isTraninable() {
        return traninable;
    }

    public void setTraninable(boolean traninable) {
        this.traninable = traninable;
    }
    
    
}
