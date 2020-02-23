/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.popup;

import com.ucsc.groupone.logger.WizardLogger;
import com.ucsc.groupone.models.ClassifierModel;
import com.ucsc.groupone.utils.SystemVariables;
import java.io.File;
import java.util.logging.Level;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 *
 * @author hashan
 */
public class OpenProject {

    JFrame parentFrame;
    String fileExtension;

    public OpenProject() {
    }

    public OpenProject(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public ClassifierModel getModel() {
        JFileChooser modelChooser = new JFileChooser(SystemVariables.IDE_HOME_FOLDER);
        modelChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        modelChooser.showOpenDialog(parentFrame);
        File selectedFolder = modelChooser.getSelectedFile();
        if (selectedFolder == null) {
            return null;
        }
        SystemVariables.setProjectRootFolder(selectedFolder.getAbsolutePath());
        return populateModelToMap(selectedFolder);
    }

    public ClassifierModel populateModelToMap(File selectedFile) {
        ClassifierModel model = null;
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            model = new ClassifierModel();

            try {

                File fXmlFile = new File(filePath + "/teawizard.xml");
                if (fXmlFile.isFile()) {
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(fXmlFile);

                    doc.getDocumentElement().normalize();
                    NodeList nList = doc.getElementsByTagName("model");

                    Node nNode = nList.item(0);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;
                        String modelName = eElement.getElementsByTagName("name").item(0).getTextContent();
                        String modelPath = eElement.getElementsByTagName("path").item(0).getTextContent();

                        File modelFile = new File(modelPath);

                        Document modelDoc = dBuilder.parse(modelFile);

                        modelDoc.getDocumentElement().normalize();
                        NodeList modelPropList = modelDoc.getElementsByTagName("model");

                        Node modelNode = modelPropList.item(0);

                        if (modelNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element mElement = (Element) modelNode;
                            String name = mElement.getAttribute("name");
                            String path = mElement.getAttribute("path");
                            String figPath = null, oiPath = null, tiPath = null, cfPath = null, pipePath = null,
                                    annotatedPath = null, trainDSPath = null, testDSPath = null;
                            int numClasses = 0;
                            if (mElement.getElementsByTagName("figPath").item(0) != null) {
                                figPath = mElement.getElementsByTagName("figPath").item(0).getTextContent();
                            }
                            if (mElement.getElementsByTagName("oiPath").item(0) != null) {
                                oiPath = mElement.getElementsByTagName("oiPath").item(0).getTextContent();
                            }
                            if (mElement.getElementsByTagName("tiPath").item(0) != null) {
                                tiPath = mElement.getElementsByTagName("tiPath").item(0).getTextContent();
                            }
                            if (mElement.getElementsByTagName("cfPath").item(0) != null) {
                                cfPath = mElement.getElementsByTagName("cfPath").item(0).getTextContent();
                            }
                            if (mElement.getElementsByTagName("pipelineConfigurationPath").item(0) != null) {
                                pipePath = mElement.getElementsByTagName("pipelineConfigurationPath").item(0).getTextContent();
                            }
                            if (mElement.getElementsByTagName("annotatedImagesPath").item(0) != null) {
                                annotatedPath = mElement.getElementsByTagName("annotatedImagesPath").item(0).getTextContent();
                            }
                            if (mElement.getElementsByTagName("trainDatasetPath").item(0) != null) {
                                trainDSPath = mElement.getElementsByTagName("trainDatasetPath").item(0).getTextContent();
                            }
                            if (mElement.getElementsByTagName("testDatasetPath").item(0) != null) {
                                testDSPath = mElement.getElementsByTagName("testDatasetPath").item(0).getTextContent();
                            }
                            if (mElement.getElementsByTagName("numberOfClasses").item(0) != null) {
                                numClasses = Integer.parseInt(mElement.getElementsByTagName("numberOfClasses").item(0).getTextContent());
                            }

                            model.setName(name);
                            model.setPath(path);
                            model.setFigPath(figPath);
                            model.setOiPath(oiPath);
                            model.setTiPath(tiPath);
                            model.setCfPath(cfPath);
                            model.setPipelineConfiguration(pipePath);
                            model.setAnnotatedImagesPath(annotatedPath);
                            model.setTrainDatasetPath(trainDSPath);
                            model.setTestDatasetPath(testDSPath);
                            model.setNumberOfClasses(numClasses);
                        }
                    }
                }
            } catch (Exception e) {
                WizardLogger.log("Model not created. Opening Porject.", Level.INFO, null);
            }
        }
        return model;
    }

}
