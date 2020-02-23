/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.utils;

/**
 *
 * @author hashan
 */
public class SystemVariables {
    
    public static final String IDE_HOME_FOLDER = System.getProperty("user.home").concat("/").concat(SystemConstants.IDE_FOLDER);
    public static String projectRootFolder = IDE_HOME_FOLDER;
    public static final String INSTALLATION_FOLDER = "/home/hashan/NetBeansProjects/Tea-Wizard";
    public static final String PYTHON_FOLDER = INSTALLATION_FOLDER + "/src/main/java/com/ucsc/groupone/TeaLeafDetectionApi";
    public static final String OBJECT_DETECTION_FOLDER = PYTHON_FOLDER 
            + "/Classifier/ObjectDetectionApi/research/object_detection";
    public static final String TRAINING_FOLDER = OBJECT_DETECTION_FOLDER 
            + "/training";
    public static final String DATA_FOLDER = OBJECT_DETECTION_FOLDER 
            + "/data";
    public static final String TEA_MODEL_FOLDER = PYTHON_FOLDER + "/Classifier/TeaModel";
    


    public static void setProjectRootFolder(String prf) {
        projectRootFolder = prf;
    }
    
    public static boolean isProjectCreated(){
        if (projectRootFolder.equals(IDE_HOME_FOLDER)){
            return false;
        }
        return true;
    }
    
}
