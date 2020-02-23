/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.utils;

import com.ucsc.groupone.dialogs.PipelineConfigurationSetter;
import com.ucsc.groupone.models.PipelineConfigurationModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author hashan
 */
public class PipelineConfigurer {

    public File config(String filePath) {

        File file = new File(filePath);
        String[] filePathSplit = filePath.split("/");
        String fileName = filePathSplit[filePathSplit.length - 1];
        File tempFile = new File(SystemVariables.TRAINING_FOLDER + "/temp_"+fileName);
        PipelineConfigurationModel pipeline = new PipelineConfigurationModel();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String currentLine;
            String previous1 = null;

            while ((currentLine = bufferedReader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.startsWith("#")) {
                    continue;
                }
                if (trimmedLine.startsWith("num_classes")) {
                    pipeline.setClassCount(Integer.parseInt(trimmedLine.split(":")[1].trim()));
                } else if (trimmedLine.startsWith("num_examples")) {
                    pipeline.setTestDataCount(Integer.parseInt(trimmedLine.split(":")[1].trim()));
                } else if (trimmedLine.startsWith("batch_size")) {
                    pipeline.setBatchSize(Integer.parseInt(trimmedLine.split(":")[1].trim()));
                } else if (trimmedLine.startsWith("fine_tune_checkpoint")) {
                    pipeline.setModelDirectory(trimmedLine.split(":")[1].trim().replaceAll("\"", ""));
                } else if (trimmedLine.startsWith("label_map_path")) {
                    pipeline.setLabelMapFile(trimmedLine.split(":")[1].trim().replaceAll("\"", ""));
                } else if (trimmedLine.startsWith("train_input_reader")) {
                    previous1 = "train_input_reader";
                } else if (trimmedLine.startsWith("eval_input_reader")) {
                    previous1 = "eval_input_reader";
                } else if (previous1 != null && previous1.equals("train_input_reader") && trimmedLine.startsWith("input_path")) {
                    pipeline.setTrainDatasetFile(trimmedLine.split(":")[1].trim().replaceAll("\"", ""));
                } else if (previous1 != null && previous1.equals("eval_input_reader") && trimmedLine.startsWith("input_path")) {
                    pipeline.setTestDatasetFile(trimmedLine.split(":")[1].trim().replaceAll("\"", ""));
                }
            }
            
            bufferedReader.close();
            PipelineConfigurationModel userDataModel = getUserData(pipeline);
            
            if (userDataModel == null) {
                return null;
            }
            BufferedReader bufferedReaderToWrite = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String previous2 = null;
            String currentLine2;


            while ((currentLine2 = bufferedReaderToWrite.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine2.trim();
                if (trimmedLine.startsWith("#")) {
                    continue;
                }
                if (trimmedLine.startsWith("num_classes")) {
                    currentLine2 = "    num_classes: " + userDataModel.getClassCount();
                } else if (trimmedLine.startsWith("num_examples")) {
                    currentLine2 = "  num_examples: " + userDataModel.getTestDataCount();
                } else if (trimmedLine.startsWith("batch_size")) {
                    currentLine2 = "  batch_size: " + userDataModel.getBatchSize();
                } else if (trimmedLine.startsWith("fine_tune_checkpoint")) {
                    currentLine2 = "  fine_tune_checkpoint: " + "\"" + userDataModel.getModelDirectory() + "\"";
                } else if (trimmedLine.startsWith("label_map_path")) {
                    currentLine2 = "  label_map_path: " + "\"" + userDataModel.getLabelMapFile() + "\"";
                } else if (trimmedLine.startsWith("train_input_reader")) {
                    previous2 = "train_input_reader";
                } else if (trimmedLine.startsWith("eval_input_reader")) {
                    previous2 = "eval_input_reader";
                } else if (previous2 != null && previous2.equals("train_input_reader") && trimmedLine.startsWith("input_path")) {
                    currentLine2 = "    input_path: " + "\"" + userDataModel.getTrainDatasetFile() + "\"";
                } else if (previous2 != null && previous2.equals("eval_input_reader") && trimmedLine.startsWith("input_path")) {
                    currentLine2 = "    input_path: " + "\"" + userDataModel.getTestDatasetFile() + "\"";
                }
                writer.write(currentLine2 + System.lineSeparator());
            }

            writer.close();
            bufferedReaderToWrite.close();
            tempFile.renameTo(file);
        } catch (IOException e) {
        }
        return tempFile.getAbsoluteFile();
    }

    private PipelineConfigurationModel getUserData(PipelineConfigurationModel pipeModel) {
        PipelineConfigurationSetter pipelineDialog = new PipelineConfigurationSetter(null, true);
        return pipelineDialog.showDialog(pipeModel);
    }
}
