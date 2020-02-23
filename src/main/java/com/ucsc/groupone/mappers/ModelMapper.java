/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.mappers;

import com.ucsc.groupone.models.ClassifierModel;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 *
 * @author hashan
 */
public class ModelMapper {

    public static ClassifierModel mapToClassifierModel(HashMap<String, String> modelProperties) {
        ClassifierModel classifierModel = new ClassifierModel(
                new ImageIcon(
                "/home/hashan/NetBeansProjects/Tea-Wizard/src/main/java/images/model-workspace-icon.png"),
                modelProperties.get("figPath"), 
                modelProperties.get("tiPath"), 
                modelProperties.get("oiPath"), 
                modelProperties.get("cfPath"));
        classifierModel.setPath(modelProperties.get("path"));
        return classifierModel;
    }
    
}
