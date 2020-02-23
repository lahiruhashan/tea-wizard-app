/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.io;

import com.ucsc.groupone.utils.TextAreaOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author hashan
 */
public class AnyFileWriter {
    
    private final String filePath;
    private final String fileExtension;
    private final String fileName;
    private File file;
    private final String fileSeparator = System.getProperty("file.separator");
    
    public AnyFileWriter(String filePath, String fileName, String fileExtension) {
        this.filePath = filePath;
        this.fileExtension = fileExtension;
        this.fileName = fileName;
    }
    
    public void create(JTextArea textArea) {
        file = new File(this.filePath + this.fileSeparator + this.fileName + 
                this.fileSeparator + this.fileExtension);
        TextAreaOutputStream logToTextArea = new TextAreaOutputStream(textArea);
        try {
            if(file.createNewFile()) {
                logToTextArea.write((this.fileName + this.fileExtension + " file created").getBytes());
            } else {
                logToTextArea.write((this.fileName + this.fileExtension + " already exists").getBytes());
            }
        } catch (IOException ex) {
            Logger.getLogger(AnyFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
