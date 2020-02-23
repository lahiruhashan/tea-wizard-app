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
public class SystemConstants {
    
    // project creation constants
    public static final String IDE_FOLDER = "Tea-Wizard";
    public static final String PROJECT_LOG_FOLDER = "/logs";
    public static final String PROJECT_EXIST_NOTE = "Project Already Exists";
    public static final String PROJECT_CREATION_ERROR = "Error In Creating The Project";
    public static final String ERROR = "ERROR";
    public static final String PACKAGE_FOLDER = System.getProperty("user.dir") +
            "/src/main/java/com/ucsc/groupone";
    public static final String RESOURCE_FOLDER = System.getProperty("user.dir") +
            "/src/main/java/resources";
    
    //tea model
    public static final String TEA_MODEL_NAME = "pre-set-tea-model";
    public static final String TEA_MODEL_PATH = SystemVariables.projectRootFolder + "/pre-set-tea-model.xml";
    public static final String TEA_GRAPH = "tea_graph.pb";
    public static final String TEA_LABEL_MAP = "tea-label-map.pbtxt";
    
    // return values from dialogs
    public static final String RETURN_ERROR = "Error Returned";
    public static final String RETURN_CANCEL = "Cancel Returned";
    public static final String RETURN_SUCCESS = "Success Returned";
    
    // model creation/deletion constants
    public static final String MODEL_NOT_CREATED = "Model Not Created. Try Again";
    public static final String INVALID_HASHMAP = "Error : Invalid HashMap Returned";
    public static final String MODEL_AVAILABLE = "Model Is Already Created On The Workspace";
    public static final String REQUIRED = "All The Fields Needs To Be Filled";
    public static final String MODEL_ADDED = "Model Added";
    public static final String MODEL_DELETED = "Model Removed";
    
    // images paths
    public static final String PATH_TO_MODEL_ICON = RESOURCE_FOLDER + "/model-workspace-icon.png";
    public static final String INVALID_COORDINATES = "Invalid Coordinates";
    public static final String INVALID_UAV_SPEED = "Invalid UAV Speed";
    
    //python api
    public static final String BASE_URL = "http://localhost:5000";
}
