/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.utils;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

/**
 *
 * @author hashan
 */
public class FileChooserOptions {
    
    public static void chooseFilePath(String title, int selectionType, JTextField textField, String startLocation) {
        File selectedFile = setUpFileChooser(title, selectionType, textField, startLocation);
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            textField.setText(filePath);
        }
    }
    
    public static void chooseFilePath(String title, int selectionType, JTextField textField, 
            String startLocation, String append, String remove) {
        File selectedFile = setUpFileChooser(title, selectionType, textField, startLocation);
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            String trimmedFilePath = filePath.replaceFirst(remove, "");
            textField.setText(trimmedFilePath + append);
        }
    }
    
    private static File setUpFileChooser(String title, int selectionType, JTextField textField, 
            String startLocation) {
        if (startLocation == null) {
            startLocation = "/home/hashan/Tea-Wizard";
        }
        
        JFileChooser jFileChooser = new JFileChooser(startLocation);
        jFileChooser.setDialogTitle(title);
        jFileChooser.setFileSelectionMode(selectionType);
        jFileChooser.showOpenDialog(null);
        File selectedFile = jFileChooser.getSelectedFile();
        return selectedFile;
    }
    
    public static final String FIG_PATH_PROPERTY = "FIG_PATH";
    public static final String OI_PATH_PROPERTY = "OI_PATH";
    public static final String TI_PATH_PROPERTY = "TI_PATH";
    public static final String CLASSES_PATH_PROPERTY = "CLASSES_PATH";
    
}
