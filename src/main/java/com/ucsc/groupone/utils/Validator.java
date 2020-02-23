/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.utils;

import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author hashan
 */
public class Validator {

    public static void validateNumberInput(KeyEvent evt, JTextField textField, JLabel messageLabel) {
        String value = textField.getText();
        int len = value.length();
        if ((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')
                || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getKeyCode() == KeyEvent.VK_DELETE) {
            textField.setEditable(true);
            messageLabel.setText("");
        } else {
            textField.setEditable(false);
            messageLabel.setText("Enter Only Numbers");
        }
    }

    public static void validateDoubleInput(KeyEvent evt, JTextField textField, JLabel messageLabel) {
        String value = textField.getText();
        int len = value.length();
        if ((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')
                || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getKeyCode() == KeyEvent.VK_DELETE || 
                evt.getKeyCode() == KeyEvent.VK_PERIOD) {
            textField.setEditable(true);
            messageLabel.setText("");
        } else {
            textField.setEditable(false);
            messageLabel.setText("Enter Only Numbers");
        }
    }

}
