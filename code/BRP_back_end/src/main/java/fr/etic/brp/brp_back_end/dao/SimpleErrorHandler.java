package fr.etic.brp.brp_back_end.dao;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author louisrob
 */
public class SimpleErrorHandler implements ErrorHandler {
    
    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.out.println("WARNING : " + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println("ERROR : " + e.getMessage());
        throw e;
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("FATAL ERROR : " + e.getMessage());
        throw e;
    }
}