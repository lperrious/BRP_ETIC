package fr.etic.brp.brp_back_end.metier.service;

import fr.etic.brp.brp_back_end.dao.JpaUtil;
import fr.etic.brp.brp_back_end.dao.ProjetDao;
import fr.etic.brp.brp_back_end.dao.ProjetXMLDao;
import fr.etic.brp.brp_back_end.metier.modele.Projet;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.Map;

/**
 *
 * @author quentinmarc
 */
public class ExportService {
    
    protected ProjetXMLDao projetXMLDao = new ProjetXMLDao();
    protected ProjetDao projetDao = new ProjetDao();
    
    Map<String, String> template1 = Map.of("titre1", "T1", "titre2", "Titre 2", "titre3", "T3", "titre4", "Titre 4");
    
    public Boolean ExporterProjet(Long idProjet, int choixTemplate){
        //Création du document word
        //Traitement
            //Donner le nom du document : nom du projet
            //Pour chaque titre1 (getElementsByTagName)
                //Si titre2
                    //On insère le titre2
                    //Pour chaque titre2 (getElementsByTagName)
                        //Si titre3
                            //On insère le titre3
                            //Pour chaque titre3 (getElementsByTagName)
                                //Si titre4 (getElementsByTagName)
                                    //On insère le titre4
                                    //Pour chaque titre4 (getElementsByTagName)
                                        //On appelle la méthode d'extraction de style de descriptif puis on insère dans le doc
                                //Si descriptif
                                    //On appelle la méthode d'extraction de style de descriptif puis on insère dans le doc
                        //Si descriptif
                            //On appelle la méthode d'extraction de style de descriptif puis on insère dans le doc
                //Si descriptif
                    //On appelle la méthode d'extraction de style de descriptif puis on insère dans le doc
        //Sortie du document word
        return null;
    }
    
    public List<XWPFParagraph> ExtractionDescriptif(Element descriptif) {
        //On extrait nomDescriptif et on le met dans un p
        //On extrait la description et on le met dans un p
            //On la parcours (que ce soit des balises un String)
            //On créer des RUNS en fonction des balises de style
            //Il faut créer de nouvraux p à chaque fois qu'on rencontre la balise p
        //On extrait les différents élements de ligneChiffrage et on les mets dans des p
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*public Boolean ExporterProjet(Long idProjet){
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
    }*/
}
