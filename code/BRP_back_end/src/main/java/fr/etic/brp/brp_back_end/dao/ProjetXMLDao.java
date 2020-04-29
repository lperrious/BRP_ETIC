package fr.etic.brp.brp_back_end.dao;

import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


/**
 *
 * @author louisrob
 */
public class ProjetXMLDao {
    
    public void Creer(String urlXML, DOMSource source) throws TransformerConfigurationException, TransformerException {
        StreamResult streamResult = new StreamResult(new File(urlXML));
        Transformer transformer = DomUtil.obtenirTransformer();
        transformer.transform(source, streamResult);
    }
}
