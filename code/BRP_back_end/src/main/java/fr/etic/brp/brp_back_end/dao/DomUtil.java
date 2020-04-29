package fr.etic.brp.brp_back_end.dao;

import static java.rmi.server.LogStream.log;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;

/**
 *
 * @author louisrob
 */
public class DomUtil {
    
    public static DocumentBuilderFactory documentBuilderFactory = null;
    
    public static TransformerFactory transformerFactory = null;
    
    public static DocumentBuilder builder = null;
    
    public static synchronized void init(){
        log("Initialisation du parseur");
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        transformerFactory = TransformerFactory.newInstance();
    }
    
    public static synchronized void destroy() {
        log("Libération de la factory de contexte de persistance");
        documentBuilderFactory = null;
    }
           
    public static DocumentBuilder obtenirBuilder() throws ParserConfigurationException {
        log("Obtention du DocumentBuilder");
        builder = documentBuilderFactory.newDocumentBuilder();
        return builder;
    }
    
    public static Transformer obtenirTransformer() throws TransformerConfigurationException {
        log("Obtention du Transformer");
        Transformer transformer = transformerFactory.newTransformer();
        return transformer;
    } 
}
