package fr.etic.brp.brp_back_end.dao;

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
    
    public static DocumentBuilderFactory documentBuilderFactory = null; //Utile pour lire le XML
    
    public static TransformerFactory transformerFactory = null; //Utile pour écrire/modifier le XML
    
    public static DocumentBuilder builder = null;
    
    public static Transformer transformer = null;
    
    
    public static synchronized void init(){
        //Initialisation du parseur
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(true); //Permet d'activer la vérification du fichier
        documentBuilderFactory.setIgnoringElementContentWhitespace(true); //Ignore les espaces dans le XML
        transformerFactory = TransformerFactory.newInstance();
    }
    
    public static synchronized void destroy() {
        //Libération de la factory DocumentBuilder et TransformerBuilder
        documentBuilderFactory = null;
        transformerFactory = null;
    }
           
    public static DocumentBuilder obtenirBuilder() throws ParserConfigurationException {
        //Obtention du DocumentBuilder
        builder = documentBuilderFactory.newDocumentBuilder();
        return builder;
    }
    
    public static Transformer obtenirTransformer() throws TransformerConfigurationException {
        //Obtention du Transformer
        transformer = transformerFactory.newTransformer();
        return transformer;
    } 
}
