/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.mouseListeners;

import com.ucsc.groupone.popup.ModelOptionsPopUp;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author hashan
 */
public class ModelOptionsPopUpClickListener extends MouseAdapter{
    
    public void mousePressed(MouseEvent e){
        if(e.isPopupTrigger()){
            popUpOption(e);
        }
    }
    
    public void mouseReleased(MouseEvent e){
        if(e.isPopupTrigger()){
            popUpOption(e);
        }
    }

    private void popUpOption(MouseEvent e) {
        ModelOptionsPopUp popUp = new ModelOptionsPopUp(e.getComponent());
        popUp.show(e.getComponent(), e.getX(), e.getY());
    }
}
