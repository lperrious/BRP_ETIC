package fr.etic.brp.brp_back_end.dao;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;


/**
 *
 * @author louisrob
 */
public class ProjetXMLDao {
    
    public void Creer(String uri, Document xml) throws TransformerConfigurationException, TransformerException {
        Transformer transformer = DomUtil.obtenirTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        DOMImplementation domImpl = xml.getImplementation();
        DocumentType doctype = domImpl.createDocumentType("doctype", "-//Oberon//YOUR PUBLIC DOCTYPE//EN", "reglesProjet.dtd");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
        
        DOMSource source = new DOMSource(xml);
        StreamResult streamResult = new StreamResult(new File(uri));
        
        transformer.transform(source, streamResult);
    }
    
    public Document Ouvrir(String uri) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder builder = DomUtil.obtenirBuilder();
        //création de notre objet d'erreurs pour le XML
        ErrorHandler errHandler = new SimpleErrorHandler();
        builder.setErrorHandler(errHandler);
        File fileXML = new File(uri);
        return builder.parse(fileXML);
    }
    
    public Boolean Update(String uri, Document xml, Long idProjet) throws TransformerException {
        Transformer transformer = DomUtil.obtenirTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        DOMImplementation domImpl = xml.getImplementation();
        DocumentType doctype = domImpl.createDocumentType("doctype", "-//Oberon//YOUR PUBLIC DOCTYPE//EN", "reglesProjet.dtd");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());

        DOMSource domSource = new DOMSource(xml);
        StreamResult streamResult = new StreamResult(new File(uri));
        
        transformer.transform(domSource, streamResult);
        return true;
    }
}
