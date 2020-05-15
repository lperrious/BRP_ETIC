package fr.etic.brp.brp_back_end.metier.service;

import fr.etic.brp.brp_back_end.dao.ProjetDao;
import fr.etic.brp.brp_back_end.dao.ProjetXMLDao;
import fr.etic.brp.brp_back_end.metier.modele.Projet;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    //TO DO - Applique les stylesheet XLST au XML du projet puis exporte la CCTP (Word) et la DPGF (Excel)
    public Boolean ExporterProjet(Long idProjet){

        //Insérer les données du projet avant l'export - A TESTER
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            Projet projet = projetDao.ChercherParId(idProjet); //? JpaUtil.CreerContextePeristence() ?
            
            Element nomProjet = xml.createElement(projet.getNomProjet());
            Element typeMarche = xml.createElement(projet.getTypeMarche().toString()); //Si ça fonctionne pas il faut surcharger l'opérateur toString pour TypeMarche (et les autres)
            Element typeConstruction = xml.createElement(projet.getTypeConstruction().toString());
            Element typeLot = xml.createElement(projet.getTypeLot().toString());
            Element site = xml.createElement(projet.getSite().toString());
            Element datePrixRef = xml.createElement(projet.getDatePrixRef().toString());
            Element coeffAdapt = xml.createElement(projet.getCoeffAdapt().toString());
            
            Element root = xml.getDocumentElement();
            NodeList listeChapitres = xml.getElementsByTagName("chapitre"); 
            root.insertBefore(nomProjet, listeChapitres.item(0));  //On insère avant le premier chapitre
            root.insertBefore(typeMarche, listeChapitres.item(0));
            root.insertBefore(typeConstruction, listeChapitres.item(0));
            root.insertBefore(typeLot, listeChapitres.item(0));
            root.insertBefore(site, listeChapitres.item(0));
            root.insertBefore(datePrixRef, listeChapitres.item(0));
            root.insertBefore(coeffAdapt, listeChapitres.item(0));
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service d'Export", ex);
        }
        
        //On exporte CCTP(Word) et DPGF(Excel)
        
        return null;
    }
}
