/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.dtos;

/**
 *
 * @author hashan
 */
public class ClassifierModelDTO {
    private String frozen_graph;
    private String labels_file;
    private String test_image_directory;
    private int num_of_classes;
    private String test_video_file;

    public String getFrozen_graph() {
        return frozen_graph;
    }

    public void setFrozen_graph(String frozen_graph) {
        this.frozen_graph = frozen_graph;
    }

    public String getLabels_file() {
        return labels_file;
    }

    public void setLabels_file(String labels_file) {
        this.labels_file = labels_file;
    }

    public String getTest_image_directory() {
        return test_image_directory;
    }

    public void setTest_image_directory(String test_image_directory) {
        this.test_image_directory = test_image_directory;
    }

    public int getNum_of_classes() {
        return num_of_classes;
    }

    public void setNum_of_classes(int num_of_classes) {
        this.num_of_classes = num_of_classes;
    }

    public String getTest_video_file() {
        return test_video_file;
    }

    public void setTest_video_file(String test_video_file) {
        this.test_video_file = test_video_file;
    }
    
    
}
