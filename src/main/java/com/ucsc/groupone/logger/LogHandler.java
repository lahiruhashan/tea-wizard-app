/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.logger;

import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;


/**
 *
 * @author hashan
 */
public class LogHandler extends StreamHandler {

    @Override
    public void publish(LogRecord record) {
        super.publish(record);
    }


    @Override
    public void flush() {
        super.flush();
    }


    @Override
    public void close() throws SecurityException {
        super.close();
    }
    
}
