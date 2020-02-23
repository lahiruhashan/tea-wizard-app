/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucsc.groupone.utils;

import com.ucsc.groupone.logger.WizardLogger;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author hashan
 */
public class ProjectXMLFileCreator {

    public static boolean updateProjectFile(String name, String path) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("project");
            doc.appendChild(rootElement);

            // model elements
            Element model = doc.createElement("model");
            rootElement.appendChild(model);

            // name elements
            Element nameEl = doc.createElement("name");
            nameEl.appendChild(doc.createTextNode(name));
            model.appendChild(nameEl);

            // path elements
            Element pathEl = doc.createElement("path");
            pathEl.appendChild(doc.createTextNode(path));
            model.appendChild(pathEl);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "ues");
//            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(SystemVariables.projectRootFolder + "/teawizard.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            return false;
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean createProjectFile(String name, String path) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("project");
            doc.appendChild(rootElement);

            // model elements
            Element model = doc.createElement("model");
            rootElement.appendChild(model);

            // name elements
            Element nameEl = doc.createElement("name");
            nameEl.appendChild(doc.createTextNode(name));
            model.appendChild(nameEl);

            // path elements
            Element pathEl = doc.createElement("path");
            pathEl.appendChild(doc.createTextNode(path));
            model.appendChild(pathEl);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "ues");
//            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);

            File file = new File(path + "/teawizard.xml");
            if (file.createNewFile()) {
                StreamResult result = new StreamResult(new File(file.getAbsolutePath()));
                transformer.transform(source, result);
                WizardLogger.log("Project File Created", Level.INFO, null);
            } else {
                WizardLogger.log("Project File Creation Failed", Level.INFO, null);
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            return false;
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
            return false;
        } catch (IOException ex) {
            Logger.getLogger(ProjectXMLFileCreator.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
