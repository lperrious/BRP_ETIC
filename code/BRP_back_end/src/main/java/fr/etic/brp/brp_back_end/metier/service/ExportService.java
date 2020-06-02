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
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
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
    
    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();
    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }
    //Permet la conversion en chiffres romains
    public final static String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }
    
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
            NodeList listeTitre1 = xml.getElementsByTagName("titre1");/*
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
            */
            //TRAITEMENT EXCEL
            CreationHelper createHelper = excel.getCreationHelper(); //Permet de créer le document plus simplement
            //Pour chaque titre1
            for(int i = 0; i < listeTitre1.getLength(); i++){
                //On créer un nouvel lot
                Sheet sheet = excel.createSheet("Lot_"+(i+1));
                //On ajoute l'en-tête (ligne grise et ligne bleue)
                //Ligne grise
                Row enTeteLot = sheet.createRow(0);
                CellStyle styleEnTeteLotLigneGrise = excel.createCellStyle();
                styleEnTeteLotLigneGrise.setAlignment(HorizontalAlignment.CENTER);
                styleEnTeteLotLigneGrise.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                styleEnTeteLotLigneGrise.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                styleEnTeteLotLigneGrise = createBorderedStyle(styleEnTeteLotLigneGrise);
                Font fontEnTeteLigneGrise = excel.createFont();
                fontEnTeteLigneGrise.setBold(true);
                styleEnTeteLotLigneGrise.setFont(fontEnTeteLigneGrise);
                enTeteLot.createCell(0).setCellValue(createHelper.createRichTextString("N° art. CCTP"));
                enTeteLot.getCell(0).setCellStyle(styleEnTeteLotLigneGrise);
                enTeteLot.createCell(1).setCellValue(createHelper.createRichTextString("DESIGNATION"));
                enTeteLot.getCell(1).setCellStyle(styleEnTeteLotLigneGrise);
                enTeteLot.createCell(2).setCellValue(createHelper.createRichTextString("DESCRIPTION SOMMAIRE \n" + "(se référer à l'article du CCTP)"));
                enTeteLot.getCell(2).setCellStyle(styleEnTeteLotLigneGrise);
                enTeteLot.createCell(3).setCellValue(createHelper.createRichTextString("U"));
                enTeteLot.getCell(3).setCellStyle(styleEnTeteLotLigneGrise);
                enTeteLot.createCell(4).setCellValue(createHelper.createRichTextString("Q"));
                enTeteLot.getCell(4).setCellStyle(styleEnTeteLotLigneGrise);
                enTeteLot.createCell(5).setCellValue(createHelper.createRichTextString("PU"));
                enTeteLot.getCell(5).setCellStyle(styleEnTeteLotLigneGrise);
                enTeteLot.createCell(6).setCellValue(createHelper.createRichTextString("Montant HT"));
                enTeteLot.getCell(6).setCellStyle(styleEnTeteLotLigneGrise);
                //Ligne bleue
                enTeteLot = sheet.createRow(1);
                Font fontEnTeteLigneBleue = excel.createFont();
                fontEnTeteLigneBleue.setBold(true);
                fontEnTeteLigneBleue.setFontHeightInPoints((short)14);
                CellStyle styleEnTeteLotLigneBleue = excel.createCellStyle();
                styleEnTeteLotLigneBleue.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex()); //Trouver une couleur plus proche ?
                styleEnTeteLotLigneBleue.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                styleEnTeteLotLigneBleue.setFont(fontEnTeteLigneBleue);
                styleEnTeteLotLigneBleue = createBorderedStyle(styleEnTeteLotLigneBleue);
                enTeteLot.createCell(0).setCellValue(createHelper.createRichTextString(toRoman(i+1)));
                enTeteLot.getCell(0).setCellStyle(styleEnTeteLotLigneBleue);
                enTeteLot.createCell(1).setCellValue(createHelper.createRichTextString("PROGRAMME DE BASE"));
                enTeteLot.getCell(1).setCellStyle(styleEnTeteLotLigneBleue);
                sheet.addMergedRegion(new CellRangeAddress(
                        1, //first row (0-based)
                        1, //last row  (0-based)
                        1, //first column (0-based)
                        6  //last column  (0-based)
                ));
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
                sheet.autoSizeColumn(0);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);
                sheet.autoSizeColumn(5);
                sheet.autoSizeColumn(6);
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
    
    public XWPFDocument ExtractionDescriptif(Element descriptif, String style, XWPFDocument word) {
        try {
            //On extrait nomDescriptif et on le met dans un p
            XWPFParagraph pNomDescriptif = word.createParagraph();
            pNomDescriptif.setStyle(style);
            XWPFRun rNomDescriptif = pNomDescriptif.createRun();
            rNomDescriptif.setText(descriptif.getElementsByTagName("nomDescriptif").item(0).getTextContent());

            word.createParagraph(); //Espace entre les paragraphes

            //On extrait l'unité
            String unite = descriptif.getElementsByTagName("unite").item(0).toString();
            
            //Paragraphe unité
            XWPFParagraph pUnite = word.createParagraph();
            XWPFRun rUnite = pUnite.createRun();
            rUnite.setText("Métré = " + unite);
            rUnite.setUnderline(SINGLE);

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
            NodeList listeLigneChiffrage = descriptif.getElementsByTagName("ligneChiffrage");
            if(listeLigneChiffrage.getLength() > 2) {
                //Paragraphe localisation
                XWPFParagraph pLocalisation = word.createParagraph();
                XWPFRun rLocalisation = pLocalisation.createRun();
                rLocalisation.setText("Localisation : ");
                rLocalisation.setColor("7030A0");
                rLocalisation.setItalic(true);
                rLocalisation.setBold(true);
                //Puces différentes localisations
                for(int i = 0; i < listeLigneChiffrage.getLength(); i++) {
                    Element ligneChiffrage = (Element) listeLigneChiffrage.item(i);
                    String localisation = ligneChiffrage.getElementsByTagName("localisation").item(0).getTextContent();
                    XWPFParagraph pPuces = word.createParagraph();
                    pPuces.setStyle("Listepuces");
                    pPuces.setIndentationLeft(700);   //valeur numérique correspondant à l'indentation de base d'une puce
                    XWPFRun rLocalisationPuces = pPuces.createRun();
                    rLocalisationPuces.setText(localisation);
                    rLocalisationPuces.setColor("7030A0");
                    rLocalisationPuces.setItalic(true);
                    rLocalisationPuces.setBold(true);
                }
            } else {
                Element ligneChiffrage = (Element) listeLigneChiffrage.item(0);
                String localisation = ligneChiffrage.getElementsByTagName("localisation").item(0).getTextContent();
                //Paragraphe localisation
                XWPFParagraph pLocalisation = word.createParagraph();
                XWPFRun rLocalisation = pLocalisation.createRun();
                rLocalisation.setText("Localisation : " + localisation);
                rLocalisation.setColor("7030A0");
                rLocalisation.setItalic(true);
                rLocalisation.setBold(true);
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ExporterProjet(Long idProjet, int choixTemplate)", ex);
        }
        return word;
    }
    
    private static CellStyle createBorderedStyle(CellStyle style) {
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }
}