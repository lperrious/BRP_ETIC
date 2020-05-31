package fr.etic.brp.brp_back_end.metier.service;

import fr.etic.brp.brp_back_end.dao.JpaUtil;
import fr.etic.brp.brp_back_end.dao.ProjetDao;
import fr.etic.brp.brp_back_end.dao.ProjetXMLDao;
import fr.etic.brp.brp_back_end.metier.modele.Projet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static org.apache.poi.xwpf.usermodel.UnderlinePatterns.DASH;
import static org.apache.poi.xwpf.usermodel.UnderlinePatterns.SINGLE;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.w3c.dom.Node;

/**
 *
 * @author Quentin MARC & Louis ROB
 */
public class ExportService {
    
    protected ProjetXMLDao projetXMLDao = new ProjetXMLDao();
    protected ProjetDao projetDao = new ProjetDao();
    
    public Boolean ExporterProjet(Long idProjet, int choixTemplate) {
        Boolean resultat = false;
        
        Map<String, String> template1 = new HashMap<>();
        template1.put("titre1", "Titre1");
        template1.put("titre2", "Titre2");
        template1.put("titre3", "Titre3");
        template1.put("titre4", "Titre4");
        template1.put("titre5", "Titre5");
        Map<String, String> template1Immutable = Collections.unmodifiableMap(template1);
        //Coder ici les différentes templates de style WORD suivant le modèle ci-dessus

        try {
            //Obtention du document XML
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            //Choix de la template et création du document WORD en fonction
            Map<String, String> template = template1Immutable;
            XWPFDocument word;
            switch(choixTemplate) {
                case 1:
                    template = template1Immutable;
                    word = new XWPFDocument(new FileInputStream("../export_files/TemplatesWord/Template1/Template1_CCTP.docx"));
                    break;
                default :
                    throw new Exception(); //Template non reconnue
            }
            
            //Création du document EXCEL
            Workbook excel = new XSSFWorkbook(new FileInputStream("../export_files/TemplatesExcel/Template1/Template1_DPGF.xlsx"));
            
            //TRAITEMENT WORD
            //Pour chaque titre1
            NodeList listeTitre1 = xml.getElementsByTagName("titre1");
            for(int i = 0; i < listeTitre1.getLength(); i++){
                //On insère le titre1 
                XWPFParagraph paragraphTitre1 = word.createParagraph();
                paragraphTitre1.setStyle(template.get("titre1"));
                XWPFRun runTitre1 = paragraphTitre1.createRun();
                Element titre1 = (Element)listeTitre1.item(i);
                runTitre1.setText(titre1.getAttribute("intitule"));
                
                //Pour chaque enfant d'un titre1
                NodeList enfantsTitre1 = listeTitre1.item(i).getChildNodes();
                for(int j = 0; j < enfantsTitre1.getLength(); j++) {
                    if(enfantsTitre1.item(j).getNodeName().equals("titre2")) {
                        //On insère le titre2
                        XWPFParagraph paragraphTitre2 = word.createParagraph();
                        paragraphTitre2.setStyle(template.get("titre2"));
                        XWPFRun runTitre2 = paragraphTitre2.createRun();
                        Element titre2 = (Element)enfantsTitre1.item(j);
                        runTitre2.setText(titre2.getAttribute("intitule"));
                        
                        //Pour chaque enfant d'un titre2
                        NodeList enfantsTitre2 = enfantsTitre1.item(j).getChildNodes();
                        for(int k = 0; k < enfantsTitre2.getLength(); k++) {
                            if(enfantsTitre2.item(k).getNodeName().equals("titre3")) {
                                //On insère le titre3
                                XWPFParagraph paragraphTitre3 = word.createParagraph();
                                paragraphTitre3.setStyle(template.get("titre3"));
                                XWPFRun runTitre3 = paragraphTitre3.createRun();
                                Element titre3 = (Element)enfantsTitre2.item(j);
                                runTitre3.setText(titre3.getAttribute("intitule"));
                                
                                //Pour chaque enfant d'un titre3
                                NodeList enfantsTitre3 = enfantsTitre2.item(k).getChildNodes();
                                for(int l = 0; l < enfantsTitre3.getLength(); l++) {
                                    if(enfantsTitre3.item(l).getNodeName().equals("titre4")) {
                                        //On insère le titre4
                                        XWPFParagraph paragraphTitre4 = word.createParagraph();
                                        paragraphTitre4.setStyle(template.get("titre4"));
                                        XWPFRun runTitre4 = paragraphTitre4.createRun();
                                        Element titre4 = (Element)enfantsTitre3.item(j);
                                        runTitre4.setText(titre4.getAttribute("intitule"));
                                        
                                        //Pour chaque enfant d'un titre4
                                        NodeList enfantsTitre4 = enfantsTitre3.item(l).getChildNodes();
                                        for(int m = 0; m < enfantsTitre4.getLength(); m++) {
                                            //descriptif
                                            word = ExtractionDescriptif((Element)enfantsTitre4.item(m), template.get("titre5"), word);
                                        }
                                    } 
                                    if(enfantsTitre3.item(l).getNodeName().equals("descriptif")){
                                        //descriptif
                                        word = ExtractionDescriptif((Element)enfantsTitre3.item(l), template.get("titre4"), word);
                                    }
                                }
                            } 
                            if(enfantsTitre2.item(k).getNodeName().equals("descriptif")){
                                //descriptif
                                word = ExtractionDescriptif((Element)enfantsTitre2.item(k), template.get("titre3"), word);
                            }
                        }
                    } 
                    if(enfantsTitre1.item(j).getNodeName().equals("descriptif")){
                        //descriptif
                        word = ExtractionDescriptif((Element)enfantsTitre1.item(j), template.get("titre2"), word);
                    }
                }
            }
            
            //TRAITEMENT EXCEL
            CreationHelper createHelper = excel.getCreationHelper(); //Permet de créer le document plus simplement
            //Pour chaque titre1
            for(int i = 0; i < listeTitre1.getLength(); i++){
                //On créer un nouvel lot
                Sheet sheet = excel.createSheet("Lot_"+(i+1));
                sheet.autoSizeColumn(0); //adjust width of the first column (pour toutes ?)
                //On ajoute l'en-tête (ligne grise et ligne bleue)
                Row enTeteLot = sheet.createRow(0);
                CellStyle styleEnTeteLot = excel.createCellStyle();
                styleEnTeteLot.setAlignment(HorizontalAlignment.CENTER);
                //styleEnTeteLot.setFillForegroundColor(new XSSFColor(new java.awt.Color(128, 0, 128), new DefaultIndexedColorMap()));
                styleEnTeteLot.setFillForegroundColor((short)1234); /////??????
                enTeteLot.createCell(0).setCellValue(createHelper.createRichTextString("N° art. CCTP"));
                enTeteLot.getCell(0).setCellStyle(styleEnTeteLot);
                enTeteLot.createCell(1).setCellValue(createHelper.createRichTextString("DESIGNATION"));
                enTeteLot.getCell(1).setCellStyle(styleEnTeteLot);
                enTeteLot.createCell(2).setCellValue(createHelper.createRichTextString("DESCRIPTION SOMMAIRE \n" + "(se référer à l'article du CCTP)"));
                enTeteLot.getCell(2).setCellStyle(styleEnTeteLot);
                enTeteLot.createCell(3).setCellValue(createHelper.createRichTextString("U"));
                enTeteLot.getCell(3).setCellStyle(styleEnTeteLot);
                enTeteLot.createCell(4).setCellValue(createHelper.createRichTextString("Q"));
                enTeteLot.getCell(4).setCellStyle(styleEnTeteLot);
                enTeteLot.createCell(5).setCellValue(createHelper.createRichTextString("PU"));
                enTeteLot.getCell(5).setCellStyle(styleEnTeteLot);
                enTeteLot.createCell(6).setCellValue(createHelper.createRichTextString("Montant HT"));
                enTeteLot.getCell(6).setCellStyle(styleEnTeteLot);
                //Pour chaque titre2
                    //Insérer ligne vide
                    //On créer un en-tête titre2 (ligne vide puis ligne grise avec l'intitulé)
                    //Pour chaque titre3
                        //On créer un tuple (Nom, courte description)
                        //Si plusieurs lignes chiffrages
                            //Insérer ce tuple
                            //Pour chaque ligneChiffrage
                                //Créer tuple (n°article, localisation à la place du nom surligné en jaune, pas de description, unité, PU, Q et montant HT)
                        //Sinon
                            //Continuer le tuple (avec unité, quantité, prix unitaire et montant HT)
                    //Insérer ligne vide
                    //Insérer tuple récap titre2 ("SOUS-TOTAL [intitulé tite2]", prix)
                //Insérer tuple récap titre1 ("SOUS-TOTAL [intitulé titre1]", prix)
                //Insérer 4 lignes vides
                //Faire le RECAPITULATIF [n°LOT]
            }

            //On créer le dossier d'export du Projet
            JpaUtil.creerContextePersistance();
            Projet projet = projetDao.ChercherParId(idProjet);
            JpaUtil.fermerContextePersistance();
            Boolean succesCreationDossier = (new File("../export_files/Exports/"+ projet.getNomProjet() + "_" + projet.getIdProjet())).mkdirs();
            if (!succesCreationDossier) {
                throw new Exception();
            }
            
            //On nomme le CCTP et la DPGF
            String outputCCTP = "../export_files/Exports/" + projet.getNomProjet() + "_" + projet.getIdProjet() + "/" + projet.getNomProjet() + "_CCTP.docx"; //Surement à changer lors de l'installation client
            String outputDPGF = "../export_files/Exports/" + projet.getNomProjet() + "_" + projet.getIdProjet() + "/" + projet.getNomProjet() + "_DPGF.xlsx"; //Surement à changer lors de l'installation client

            //On écrit en sortie les documents WORD et EXCEL
            FileOutputStream out = new FileOutputStream(outputCCTP);
            word.write(out);
            out.close();
            word.close();
            OutputStream fileOut = new FileOutputStream(outputDPGF);
            excel.write(fileOut);
            
            resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ExporterProjet(Long idProjet, int choixTemplate)", ex);
        }
        return resultat;
    }
    
    public XWPFDocument ExtractionDescriptif(Element descriptif, String style, XWPFDocument word) throws XmlException {
        //On extrait nomDescriptif et on le met dans un p
        XWPFParagraph pNomDescriptif = word.createParagraph();
        pNomDescriptif.setStyle(style);
        XWPFRun rNomDescriptif = pNomDescriptif.createRun();
        rNomDescriptif.setText(descriptif.getElementsByTagName("nomDescriptif").item(0).getTextContent());
        
        word.createParagraph(); //Espace entre les paragraphes
        
        //On extrait l'unité ou les unités seulement
        NodeList listeLigneChiffrage = descriptif.getElementsByTagName("ligneChiffrage");
        for(int i = 0; i < listeLigneChiffrage.getLength(); i++) {
            
            Element ligneChiffrage = (Element) listeLigneChiffrage.item(i);
            String unite = ligneChiffrage.getElementsByTagName("unite").item(0).getTextContent();
            
            //Paragraphe unité
            XWPFParagraph pUnite = word.createParagraph();
            XWPFRun rUnite = pUnite.createRun();
            rUnite.setText("Métré = " + unite);
            rUnite.setUnderline(SINGLE);
        }
        
        word.createParagraph(); //Espace entre les paragraphes
        
        //pour chaque balise (p ou ul): on selectionne uniquement les balises enfants et on les parcours
        Element description = (Element) descriptif.getElementsByTagName("description").item(0);
        NodeList enfantsDescription = description.getChildNodes();
        for(int i = 0; i<enfantsDescription.getLength(); i++) {
          if(enfantsDescription.item(i).getNodeType() == Node.ELEMENT_NODE) {        //on traite les balises p
            XWPFParagraph pDescription = word.createParagraph();
            if(enfantsDescription.item(i).getNodeName().equals("li")){ 
                pDescription.setStyle("Listepuces");
                pDescription.setIndentationLeft(700);   //valeur numérique correspondant à l'indentation de base d'une puce
            }
            Element p = (Element) enfantsDescription.item(i);
            //on boucle sur les enfants du paragraphe
            NodeList enfantsP = p.getChildNodes();
            for(int j = 0; j<enfantsP.getLength(); j++) {
                if(enfantsP.item(j).getNodeType() == Node.ELEMENT_NODE){
                    XWPFRun rDescritpion = pDescription.createRun();
                    rDescritpion.setText(enfantsP.item(j).getTextContent());
                    if(enfantsP.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        switch(enfantsP.item(j).getNodeName()){
                            case "u":
                                rDescritpion.setUnderline(SINGLE);
                                break;
                            case "underlineDash":
                                rDescritpion.setUnderline(DASH);
                                break;
                            case "i":
                                rDescritpion.setItalic(true);
                                break;
                            case "italic_underline":
                                rDescritpion.setItalic(true);
                                rDescritpion.setUnderline(SINGLE);
                                break;
                            case "b":
                                rDescritpion.setBold(true);
                                break;
                            case "bold_underline":
                                rDescritpion.setBold(true);
                                rDescritpion.setUnderline(SINGLE);
                                break;
                            case "bold_italic":
                                rDescritpion.setItalic(true);
                                rDescritpion.setBold(true);
                                break;
                            case "bold_underline_italic":
                                rDescritpion.setBold(true);
                                rDescritpion.setItalic(true);
                                rDescritpion.setUnderline(SINGLE);
                                break;
                            case "colorRed":
                                rDescritpion.setColor("FF0000");
                                break;
                            case "colorOrange":
                                rDescritpion.setColor("E36C0A");
                                break;
                            case "colorGreen":
                                rDescritpion.setColor("00B050");
                                break;
                            case "colorBlue":
                                rDescritpion.setColor("0070C0");
                                break;
                            case "highlightYellow":
                                rDescritpion.setTextHighlightColor("yellow");
                                break;
                            case "highlightCyan":
                                rDescritpion.setTextHighlightColor("cyan");
                                break;
                            case "highlightRed":
                                rDescritpion.setTextHighlightColor("red");
                                break;
                            case "highlightGreen":
                                rDescritpion.setTextHighlightColor("green");
                                break;
                            case "highlightMagenta":
                                rDescritpion.setTextHighlightColor("magenta");
                                break;
                            case "highlightGrey":
                                rDescritpion.setTextHighlightColor("lightGray");
                                break;  
                        }
                    }
                }
            }
          }				
        }
        
        word.createParagraph(); //Espace entre les paragraphes
        
        //On extrait les différents élements de ligneChiffrage et on les mets dans des p
        for(int i = 0; i < listeLigneChiffrage.getLength(); i++) {
            //on extrait toutes les informations
            Element ligneChiffrage = (Element) listeLigneChiffrage.item(i);
            //String unite = ligneChiffrage.getElementsByTagName("unite").item(0).getTextContent();
            String localisation = ligneChiffrage.getElementsByTagName("localisation").item(0).getTextContent();
            //Double quantite = Double.parseDouble(ligneChiffrage.getElementsByTagName("quantite").item(0).getTextContent());
            //Double prixUnitaire = Double.parseDouble(ligneChiffrage.getElementsByTagName("prixUnitaire").item(0).getTextContent());
            
            //Paragraphe localisation
            XWPFParagraph pLocalisation = word.createParagraph();
            XWPFRun rLocalisation = pLocalisation.createRun();
            rLocalisation.setText("Localisation : " + localisation);
            rLocalisation.setColor("7030A0");
            rLocalisation.setItalic(true);
            rLocalisation.setBold(true);
            
            /*
            //on rajoute le paragraphe (Surement à modifier)
            XWPFParagraph pLigneChiffrage = word.createParagraph();
            XWPFRun rLigneChiffrage = pLigneChiffrage.createRun();
            rLigneChiffrage.setText("Unité: "+unite+" / localisation: "+localisation+" / Quantite: "+quantite+" / Prix unitaire: "+prixUnitaire);
            */
        }  
         
        return word;
    }
    
    private static void createCell(Workbook wb, Row row, int column, HorizontalAlignment halign, VerticalAlignment valign, String textContent) {
    Cell cell = row.createCell(column);
    cell.setCellValue(textContent);
    CellStyle cellStyle = wb.createCellStyle();
    cellStyle.setAlignment(halign);
    cellStyle.setVerticalAlignment(valign);
    cell.setCellStyle(cellStyle);
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
