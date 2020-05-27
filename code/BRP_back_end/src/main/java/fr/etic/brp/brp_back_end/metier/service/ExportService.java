package fr.etic.brp.brp_back_end.metier.service;

import fr.etic.brp.brp_back_end.dao.JpaUtil;
import fr.etic.brp.brp_back_end.dao.ProjetDao;
import fr.etic.brp.brp_back_end.dao.ProjetXMLDao;
import fr.etic.brp.brp_back_end.metier.modele.Projet;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.Map;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author Quentin MARC & Louis ROB
 */
public class ExportService {
    
    protected ProjetXMLDao projetXMLDao = new ProjetXMLDao();
    protected ProjetDao projetDao = new ProjetDao();
    
    Map<String, String> template1 = Map.of("titre1", "Titre1", "titre2", "Titre2", "titre3", "Titre3", "titre4", "Titre4");
    //Mettre ici les différentes templates de style word
    
    public Boolean ExporterProjet(Long idProjet, int choixTemplate) {
        Boolean resultat = false;
        
        //Choix de la template
        Map<String, String> template = template1;
        switch(choixTemplate) {
            case 1:
                template = template1;
                break;
            default :
                //Template non reconnue
                break;
        }
        
        try {
            //Nom de l'export
            JpaUtil.creerContextePersistance();
            //JpaUtil.ouvrirTransaction(); //utile ?
            Projet projet = projetDao.ChercherParId(idProjet);
            String output = "../export_files/Exports/"+projet.getNomProjet()+".docx"; //Surement à changer lors de l'installation client
            //JpaUtil.validerTransaction(); //utile ?
            JpaUtil.fermerContextePersistance();
            
            //Obtention du document XML
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            //Création du document WORD
            XWPFDocument word = new XWPFDocument();
            
            //TRAITEMENT
            //Pour chaque titre1
            NodeList listeTitre1 = xml.getElementsByTagName("titre1");
            for(int i = 0; i < listeTitre1.getLength(); i++){
                //On insère le titre1
                XWPFParagraph paragraphTitre1 = word.createParagraph();
                paragraphTitre1.setStyle(template.get("titre1"));
                XWPFRun runTitre1 = paragraphTitre1.createRun();
                runTitre1.setText(listeTitre1.item(i).getTextContent());
                
                //Pour chaque enfant d'un titre1
                NodeList enfantsTitre1 = listeTitre1.item(i).getChildNodes();
                for(int j = 0; j < enfantsTitre1.getLength(); j++) {
                    if(enfantsTitre1.item(j).getNodeName().equals("titre2")) {
                        //On insère le titre2
                        XWPFParagraph paragraphTitre2 = word.createParagraph();
                        paragraphTitre2.setStyle(template.get("titre2"));
                        XWPFRun runTitre2 = paragraphTitre2.createRun();
                        runTitre2.setText(enfantsTitre1.item(j).getTextContent());
                        
                        //Pour chaque enfant d'un titre2
                        NodeList enfantsTitre2 = enfantsTitre1.item(j).getChildNodes();
                        for(int k = 0; k < enfantsTitre2.getLength(); k++) {
                            if(enfantsTitre2.item(k).getNodeName().equals("titre3")) {
                                //On insère le titre3
                                XWPFParagraph paragraphTitre3 = word.createParagraph();
                                paragraphTitre3.setStyle(template.get("titre3"));
                                XWPFRun runTitre3 = paragraphTitre2.createRun();
                                runTitre3.setText(enfantsTitre2.item(k).getTextContent());

                                //Pour chaque enfant d'un titre3
                                NodeList enfantsTitre3 = enfantsTitre2.item(k).getChildNodes();
                                for(int l = 0; l < enfantsTitre3.getLength(); l++) {
                                    if(enfantsTitre3.item(l).getNodeName().equals("titre4")) {
                                        //On insère le titre4
                                        XWPFParagraph paragraphTitre4 = word.createParagraph();
                                        paragraphTitre4.setStyle(template.get("titre4"));
                                        XWPFRun runTitre4 = paragraphTitre2.createRun();
                                        runTitre4.setText(enfantsTitre2.item(l).getTextContent());

                                        //Pour chaque enfant d'un titre4
                                        NodeList enfantsTitre4 = enfantsTitre3.item(l).getChildNodes();
                                        for(int m = 0; m < enfantsTitre4.getLength(); m++) {
                                            //descriptif
                                        }
                                    } else {
                                        //descriptif
                                    }
                                }
                            } else {
                                //descriptif
                            }
                        }
                    } else {
                        //descriptif
                    }
                }
            }
            
            //On écrit en sortie le document WORD
            FileOutputStream out = new FileOutputStream(output);
            word.write(out);
            out.close();
            word.close();
            
            resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ExporterProjet(Long idProjet, int choixTemplate)", ex);
        }
        return resultat;
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
