/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hashan
 */
public class ConfigParser {
    public static Map<?,?> parse(String stringBuffer) {
        ArrayList<String> splitted = new ArrayList<>(Arrays.asList(stringBuffer.split(" ")));
        for(String word: splitted) {
            if(word.equals(" ")) {
                continue;
            }
            if(!word.equals("")){}
        }
        return  null;
    }
}
