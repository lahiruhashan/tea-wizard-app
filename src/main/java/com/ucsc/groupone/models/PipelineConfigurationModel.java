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
public class PipelineConfigurationModel {
    
    private int classCount;
    private int batchSize;
    private int testDataCount;
    private String modelDirectory;
    private String trainDatasetFile;
    private String testDatasetFile;
    private String labelMapFile;

    public PipelineConfigurationModel(int classCount, int batchSize, int testDataCount, String modelDirectory, String trainDatasetFile, String testDatasetFile, String labelMapFile) {
        this.classCount = classCount;
        this.batchSize = batchSize;
        this.testDataCount = testDataCount;
        this.modelDirectory = modelDirectory;
        this.trainDatasetFile = trainDatasetFile;
        this.testDatasetFile = testDatasetFile;
        this.labelMapFile = labelMapFile;
    }

    public PipelineConfigurationModel() {
    }

    public int getClassCount() {
        return classCount;
    }

    public void setClassCount(int classCount) {
        this.classCount = classCount;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public int getTestDataCount() {
        return testDataCount;
    }

    public void setTestDataCount(int testDataCount) {
        this.testDataCount = testDataCount;
    }

    public String getModelDirectory() {
        return modelDirectory;
    }

    public void setModelDirectory(String modelDirectory) {
        this.modelDirectory = modelDirectory;
    }

    public String getTrainDatasetFile() {
        return trainDatasetFile;
    }

    public void setTrainDatasetFile(String trainDatasetFile) {
        this.trainDatasetFile = trainDatasetFile;
    }

    public String getTestDatasetFile() {
        return testDatasetFile;
    }

    public void setTestDatasetFile(String testDatasetFile) {
        this.testDatasetFile = testDatasetFile;
    }

    public String getLabelMapFile() {
        return labelMapFile;
    }

    public void setLabelMapFile(String labelMapFile) {
        this.labelMapFile = labelMapFile;
    }
    
    
    
    
}
