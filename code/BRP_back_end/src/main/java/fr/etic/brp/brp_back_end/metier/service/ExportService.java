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
            
            //On créer le dossier d'export du Projet
            JpaUtil.creerContextePersistance();
            Projet projet = projetDao.ChercherParId(idProjet);
            JpaUtil.fermerContextePersistance();
            Boolean succesCreationDossier = (new File("../export_files/Exports/"+ projet.getNomProjet() + "_" + projet.getIdProjet())).mkdirs();
            if (!succesCreationDossier) {
                throw new Exception();
            }
            
            //TRAITEMENT WORD
            //Pour chaque lot
            NodeList listeLot = xml.getElementsByTagName("lot");
            for(int h = 0; h < listeLot.getLength(); h++){
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
                                    Element titre3 = (Element)enfantsTitre2.item(k);
                                    runTitre3.setText(titre3.getAttribute("intitule"));
                                    
                                    //Pour chaque enfant d'un titre3
                                    NodeList enfantsTitre3 = enfantsTitre2.item(k).getChildNodes();
                                    for(int l = 0; l < enfantsTitre3.getLength(); l++) {
                                        if(enfantsTitre3.item(l).getNodeName().equals("titre4")) {
                                            //On insère le titre4
                                            XWPFParagraph paragraphTitre4 = word.createParagraph();
                                            paragraphTitre4.setStyle(template.get("titre4"));
                                            XWPFRun runTitre4 = paragraphTitre4.createRun();
                                            Element titre4 = (Element)enfantsTitre3.item(l);
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
                //On nomme la CCTP
                String outputCCTP = "../export_files/Exports/" + projet.getNomProjet() + "_" + projet.getIdProjet() + "/" + projet.getNomProjet() + "_LOT_" + h + "_CCTP.docx"; //Surement à changer lors de l'installation client
                //On écrit en sortie le document WORD
                FileOutputStream out = new FileOutputStream(outputCCTP);
                word.write(out);
                out.close();
                word.close();
            }
            
            //TRAITEMENT EXCEL
            //Création du document EXCEL
            Workbook excel = new XSSFWorkbook(new FileInputStream("../export_files/TemplatesExcel/Template1/Template1_DPGF.xlsx"));
            CreationHelper createHelper = excel.getCreationHelper(); //Permet de créer le document "plus simplement"
            
            //Pour chaque lot
                //S'il existe un ouvrage/prestation dans le lot
                    //On créer un nouveau lot
                    //On créer l'en-tête lot (ligne grise) --> Si possible y mettre en 'mode survol'
                    //Pour chaque titre1
                        //Créer l'en-tête titre1 (ligne bleue)
                        //Pour chaque enfants titre1
                            //Si Element && NON generique
                                //Si descriptif
                                    //DPGFDescriptif(...)
                                //Sinon
                                    //Créer l'en-tête titre2 (ligne blanche, ligne grise prononcée)
                                    //Pour chaque enfants titre2
                                    //Si Element && NON generique
                                        //Si descriptif
                                            //DPGFDescriptif(...)
                                        //Sinon
                                            //Créer l'en-tête titre3 (ligne grise moins prononcée)
                                            //Pour chaque enfants titre3
                                                //Si Element && NON generique
                                                        //DPGFDescriptif(...)
                                            //Créer tuple récap titre3 ("SOUS-TOTAL [intitulé tite3]", prix)
                                    //Créer ligne blanche
                                    //Créer tuple récap titre2 ("SOUS-TOTAL [intitulé tite2]", prix)
                        //Créer tuple récap titre1 ("SOUS-TOTAL [intitulé tite1]", prix)
                        //Créer ligne blanche
                    //Créer 4 lignes vides
                    //Créer RECAPITULATIF [n°LOT]
                    //Pour chaque titre1
                        //Créer ligne bleue avec [intitulé titre1]
                        //Créer ligne (N°, DESIGNATION, PRIX TOTAL)
                        //Pour chaque enfants titre2
                            //Tuple correspondant au sous-total titre2
                        //Créer ligne MONTANT TOTAL H.T [intitulé Titre1]
                        //créer ligne blanche
                    //Créer ligne grise bien prononcée MONTANT TOTAL H.T [LOT 1]
                    //Créer une ligne blanche
            //FIN //NB : (PAS après "Fait à" inclus)
            
            //On nomme la DPGF
            String outputDPGF = "../export_files/Exports/" + projet.getNomProjet() + "_" + projet.getIdProjet() + "/" + projet.getNomProjet() + "_DPGF.xlsx"; //Surement à changer lors de l'installation client

            //On écrit en sortie le document EXCEL
            OutputStream fileOut = new FileOutputStream(outputDPGF);
            excel.write(fileOut);
            
            resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ExporterProjet(Long idProjet, int choixTemplate)", ex);
        }
        return resultat;
    }
    
    //Procédure DPGFDescriptif(...)
        //On créer le tuple (n°, nom, courte description)
        //Si une seule ligne chiffrage
            //on continue ce tuple (unite, quantité, prixUnitaire, montant HT)
        //Sinon
            //Pour chaque ligneChiffrage
                //On créer un tuple (n°, localisation, unite, quantité, prixUnitaire, montant HT)
    //FIN
    public Workbook DPFGDescriptif(Workbook excel, int nbSheet, Element descriptif, CreationHelper createHelper, int nbTitre1, int nbTitre2, int nbTitre3, int nbTitre4) {
        //On créer le tuple (n°, nom, courte description)
        //On créer les styles
        CellStyle styleBorderLRBold = createBorderedStyleLR(excel.createCellStyle());
        Font bold11 = excel.createFont();
        bold11.setBold(true);
        styleBorderLRBold.setFont(bold11);
        CellStyle styleBorderLR = createBorderedStyleLR(excel.createCellStyle());
        //On créer la ligne du descriptif
        Sheet sheet = excel.getSheetAt(nbSheet);
        Row ligneDescriptif = sheet.createRow(sheet.getLastRowNum()+1);
        ligneDescriptif.createCell(0).setCellStyle(styleBorderLRBold);
        ligneDescriptif.createCell(1).setCellStyle(styleBorderLRBold);
        ligneDescriptif.createCell(2).setCellStyle(styleBorderLR);
        ligneDescriptif.createCell(3).setCellStyle(styleBorderLR);
        ligneDescriptif.createCell(4).setCellStyle(styleBorderLR);
        ligneDescriptif.createCell(5).setCellStyle(styleBorderLR);
        ligneDescriptif.createCell(6).setCellStyle(styleBorderLR);
        //On le rempli des infos
        if(nbTitre3 == -1)
            ligneDescriptif.getCell(0).setCellValue(createHelper.createRichTextString(toRoman(nbTitre1+1) + "." + (nbTitre2+1)));
        else if(nbTitre4 == -1)
            ligneDescriptif.getCell(0).setCellValue(createHelper.createRichTextString(toRoman(nbTitre1+1) + "." + (nbTitre2+1) + "." + (nbTitre3+1)));
        else
            ligneDescriptif.getCell(0).setCellValue(createHelper.createRichTextString(toRoman(nbTitre1+1) + "." + (nbTitre2+1) + "." + (nbTitre3+1)) + "." + (nbTitre4+1));
        ligneDescriptif.getCell(1).setCellValue(createHelper.createRichTextString(descriptif.getElementsByTagName("nomDescriptif").item(0).toString()));
        ligneDescriptif.getCell(2).setCellValue(createHelper.createRichTextString(descriptif.getElementsByTagName("courteDescription").item(0).toString()));
        NodeList listeLigneChiffrage = descriptif.getElementsByTagName("ligneChiffrage");
        //Si plusieurs lignes chiffrages
        if(listeLigneChiffrage.getLength() > 1) {
           //Laisser le tuple débuté
           //Pour chaque ligneChiffrage
            for(int l = 0; l < listeLigneChiffrage.getLength(); l++) {
                //Créer tuple (n°article, localisation à la place du nom surligné en jaune, pas de description, unité, PU, Q et montant HT)
                Row ligneChiffrage = sheet.createRow(sheet.getLastRowNum()+1);
                //On créer le style pour les différentes localisations
                CellStyle styleBorderLRYellow = createBorderedStyleLR(excel.createCellStyle());
                styleBorderLRYellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex()); //Trouver une couleur plus proche ?
                styleBorderLRYellow.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                styleBorderLRYellow.setAlignment(HorizontalAlignment.RIGHT);
                //On applique les styles
                ligneChiffrage.createCell(0).setCellStyle(styleBorderLR);
                ligneChiffrage.createCell(1).setCellStyle(styleBorderLRYellow);
                ligneChiffrage.createCell(2).setCellStyle(styleBorderLR);
                ligneChiffrage.createCell(3).setCellStyle(styleBorderLR);
                ligneChiffrage.createCell(4).setCellStyle(styleBorderLR);
                ligneChiffrage.createCell(5).setCellStyle(styleBorderLR);
                ligneChiffrage.createCell(6).setCellStyle(styleBorderLR);
                //On rempli la ligne
                Element baliseLigneChiffrage = (Element)listeLigneChiffrage.item(l);
                String localisation = baliseLigneChiffrage.getElementsByTagName("localisation").toString();
                String unite = baliseLigneChiffrage.getElementsByTagName("unite").toString();
                String quantite = baliseLigneChiffrage.getElementsByTagName("quantite").toString();
                String prixUnitaire = baliseLigneChiffrage.getElementsByTagName("prixUnitaire").toString();
                Double montantHT = Double.parseDouble(prixUnitaire)*Double.parseDouble(quantite);
                ligneChiffrage.getCell(1).setCellValue(createHelper.createRichTextString(localisation));
                ligneChiffrage.getCell(3).setCellValue(createHelper.createRichTextString(unite));
                ligneChiffrage.getCell(4).setCellValue(createHelper.createRichTextString(quantite));
                ligneChiffrage.getCell(5).setCellValue(createHelper.createRichTextString(prixUnitaire));
                ligneChiffrage.getCell(6).setCellValue(createHelper.createRichTextString(montantHT.toString()));
            }            
        } else {
            //Sinon continuer le tuple (avec unité, quantité, prix unitaire et montant HT)
            Element baliseLigneChiffrage = (Element)listeLigneChiffrage.item(0);
            String unite = baliseLigneChiffrage.getElementsByTagName("unite").toString();
            String quantite = baliseLigneChiffrage.getElementsByTagName("quantite").toString();
            String prixUnitaire = baliseLigneChiffrage.getElementsByTagName("prixUnitaire").toString();
            Double montantHT = Double.parseDouble(prixUnitaire)*Double.parseDouble(quantite);
            ligneDescriptif.getCell(3).setCellValue(createHelper.createRichTextString(unite));
            ligneDescriptif.getCell(4).setCellValue(createHelper.createRichTextString(quantite));
            ligneDescriptif.getCell(5).setCellValue(createHelper.createRichTextString(prixUnitaire));
            ligneDescriptif.getCell(6).setCellValue(createHelper.createRichTextString(montantHT.toString()));
        }
        return excel;
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
            String unite = descriptif.getElementsByTagName("unite").item(0).getTextContent();
            
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
    
    private static CellStyle createBorderedStyleLR(CellStyle style) {
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }
    
    private String getCharForNumber(int i) { //0-based
        return i > -1 && i < 26 ? String.valueOf((char)(i + 97)) : null;
    }
}