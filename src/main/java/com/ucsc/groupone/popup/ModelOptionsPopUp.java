/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.popup;

import com.ucsc.groupone.frames.MasterFrame;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author hashan
 */
public class ModelOptionsPopUp extends JPopupMenu {
    JMenuItem deleteItem;
    
    public ModelOptionsPopUp(Component component){
        deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener((ActionEvent ae) -> {
            if(component.getClass().getName().equals("com.ucsc.groupone.models.ClassifierModel")){
                Component parent = component.getParent();
                MasterFrame mf = (MasterFrame) component.getParent().getParent().getParent().getParent().getParent();
                component.getParent().remove(component);
                mf.removeModel();
                parent.revalidate();
                parent.repaint();
            } else {
                System.out.println(component.getClass());
            }
        });
        add(deleteItem);
    }
}
