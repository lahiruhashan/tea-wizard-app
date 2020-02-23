/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.logger;

import com.ucsc.groupone.utils.SystemConstants;
import com.ucsc.groupone.utils.SystemVariables;
import com.ucsc.groupone.utils.TextAreaOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author hashan
 */
public class WizardLogger {
    
    private static Handler fileHandler;
    private static Logger logger = Logger.getLogger(WizardLogger.class.getName());
    
    public static void log(String logText, Level level, JTextArea logArea) {
        
        try {
            LogManager.getLogManager().readConfiguration
                (new FileInputStream(SystemConstants.RESOURCE_FOLDER + "/logging.properties"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
        try {
            logToFile(logger, logText);
            if (logArea != null) {
                logToTextArea(logArea, logText);
            }
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }  
    }
    
    public static void logToFile(Logger logger, String logText) throws IOException {
        logger.setLevel(Level.FINE);
        logger.addHandler(new LogHandler());
        fileHandler = new FileHandler(SystemVariables.projectRootFolder 
                + SystemConstants.PROJECT_LOG_FOLDER + "/logger.log", true);
        fileHandler.setFormatter(new LogFormatter());
        //setting custom filter for FileHandler
        fileHandler.setFilter(new LogFilter());
        logger.addHandler(fileHandler);
        logger.log(Level.INFO, logText);
    }
    
    public static void logToTextArea(JTextArea logArea, String logText) {
        TextAreaOutputStream txtStream = new TextAreaOutputStream(logArea);
        txtStream.write(logText.getBytes());
    }
}
