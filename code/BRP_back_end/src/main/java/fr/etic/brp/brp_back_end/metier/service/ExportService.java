package fr.etic.brp.brp_back_end.metier.service;

import fr.etic.brp.brp_back_end.dao.JpaUtil;
import fr.etic.brp.brp_back_end.dao.ProjetDao;
import fr.etic.brp.brp_back_end.dao.ProjetXMLDao;
import fr.etic.brp.brp_back_end.metier.modele.Projet;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author quentinmarc
 */
public class ExportService {
    
    protected ProjetXMLDao projetXMLDao = new ProjetXMLDao();
    protected ProjetDao projetDao = new ProjetDao();
    
    public Boolean ExporterProjet(Long idProjet){
        JpaUtil.creerContextePersistance();
        Boolean resultat = false;

        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            Projet projet = projetDao.ChercherParId(idProjet);
            
            //Si les balises nomProjet etc. sont présentes ont écrit par dessus sinon on les crée
            Element nomProjetBalise = (Element) xml.getElementsByTagName("nomProjet").item(0);
            if(nomProjetBalise != null) { //On suppose que si nomProjet est présent alors les autes sont présentes aussi
                nomProjetBalise.setTextContent(projet.getNomProjet());
                Element typeMarche = (Element) xml.getElementsByTagName("typeMarche").item(0);
                typeMarche.setTextContent(projet.getTypeMarche().toString());
                Element typeConstruction = (Element) xml.getElementsByTagName("typeConstruction").item(0);
                typeConstruction.setTextContent(projet.getTypeConstruction().toString());
                Element typeLot = (Element) xml.getElementsByTagName("typeLot").item(0);
                typeLot.setTextContent(projet.getTypeLot().toString());
                Element site = (Element) xml.getElementsByTagName("site").item(0);
                site.setTextContent(projet.getSite().toString());
                Element datePrixRef = (Element) xml.getElementsByTagName("datePrixRef").item(0);
                datePrixRef.setTextContent(projet.getDatePrixRef().toString());
                Element coeffAdapt = (Element) xml.getElementsByTagName("coeffAdapt").item(0);
                coeffAdapt.setTextContent(projet.getCoeffAdapt().toString());
            } else {
                Element nomProjet = xml.createElement("nomProjet");
                nomProjet.setTextContent(projet.getNomProjet());
                Element typeMarche = xml.createElement("typeMarche"); //Si ça fonctionne pas il faut surcharger l'opérateur toString pour TypeMarche (et les autres)
                typeMarche.setTextContent(projet.getTypeMarche().toString());
                Element typeConstruction = xml.createElement("typeConstruction");
                typeConstruction.setTextContent(projet.getTypeConstruction().toString());
                Element typeLot = xml.createElement("typeLot");
                typeLot.setTextContent(projet.getTypeLot().toString());
                Element site = xml.createElement("site");
                site.setTextContent(projet.getSite().toString());
                Element datePrixRef = xml.createElement("datePrixRef");
                datePrixRef.setTextContent(projet.getDatePrixRef().toString()); //Prendre que l'année ?
                Element coeffAdapt = xml.createElement("coeffAdapt");
                coeffAdapt.setTextContent(projet.getCoeffAdapt().toString());

                Element root = xml.getDocumentElement();
                NodeList listeTitre1 = xml.getElementsByTagName("titre1");
                root.insertBefore(nomProjet, listeTitre1.item(0));  //On insère avant le premier titre1
                root.insertBefore(typeMarche, listeTitre1.item(0));
                root.insertBefore(typeConstruction, listeTitre1.item(0));
                root.insertBefore(typeLot, listeTitre1.item(0));
                root.insertBefore(site, listeTitre1.item(0));
                root.insertBefore(datePrixRef, listeTitre1.item(0));
                root.insertBefore(coeffAdapt, listeTitre1.item(0));
            }
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service d'Export", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        //On exporte CCTP(Word) et DPGF(Excel)
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            
            Source xsltCCTP = new StreamSource(new File("../export_files/pays_du_monde_tableau_3IF.xsl"));
            //Source xsltDPGF = new StreamSource(new File("../export_files/pays_du_monde_tableau_3IF.xsl"));
            Transformer transformerCCTP = factory.newTransformer(xsltCCTP);
            //Transformer transformerDPGF = factory.newTransformer(xsltDPGF);

            Source xml = new StreamSource(new File("../XMLfiles/countriesTP.xml"));
            transformerCCTP.transform(xml, new StreamResult(new File("../CCTP/document.xml")));
            //transformerDPGF.transform(xml, new StreamResult(new File("../CCTP/document.xml")));
            
            resultat = true;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service d'Export", ex);
        }

        return resultat;
    }
}
