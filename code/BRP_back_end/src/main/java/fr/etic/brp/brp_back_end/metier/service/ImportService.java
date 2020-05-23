package fr.etic.brp.brp_back_end.metier.service;

import fr.etic.brp.brp_back_end.dao.BasePrixRefDao;
import fr.etic.brp.brp_back_end.dao.CategorieDao;
import fr.etic.brp.brp_back_end.dao.ChapitreDao;
import fr.etic.brp.brp_back_end.dao.DescriptifDao;
import fr.etic.brp.brp_back_end.dao.FamilleDao;
import fr.etic.brp.brp_back_end.dao.JpaUtil;
import fr.etic.brp.brp_back_end.dao.PrestationDao;
import fr.etic.brp.brp_back_end.dao.SousFamilleDao;
import fr.etic.brp.brp_back_end.metier.modele.BasePrixRef;
import fr.etic.brp.brp_back_end.metier.modele.Categorie;
import fr.etic.brp.brp_back_end.metier.modele.Chapitre;
import fr.etic.brp.brp_back_end.metier.modele.Descriptif;
import fr.etic.brp.brp_back_end.metier.modele.Famille;
import fr.etic.brp.brp_back_end.metier.modele.Generique;
import fr.etic.brp.brp_back_end.metier.modele.Ouvrage;
import fr.etic.brp.brp_back_end.metier.modele.Prestation;
import fr.etic.brp.brp_back_end.metier.modele.SousFamille;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 *
 * @author quentinmarc
 */
public class ImportService {
    
    protected ChapitreDao chapitreDao = new ChapitreDao();
    protected DescriptifDao descriptifDao = new DescriptifDao();
    protected FamilleDao familleDao = new FamilleDao();
    protected CategorieDao categorieDao = new CategorieDao();
    protected SousFamilleDao sousFamilleDao = new SousFamilleDao();
    protected PrestationDao prestationDao = new PrestationDao();
    protected BasePrixRefDao basePrixRefDao = new BasePrixRefDao();
    
    //TO DO
    public ArrayList<String> ModifBaseDescriptif(){
        
        String idActuel = null;
        Boolean erreur = false;
        int countUnderscore = 0;
        int countRows = 0;
        ArrayList<ArrayList<String>> docListe =  new ArrayList<ArrayList<String>> ();       //Création d'un format indicé
        ArrayList<String> returnListe =  new ArrayList<String> ();
        returnListe.add("");
        
        try {
            //Importer le word
            FileInputStream fis = new FileInputStream("../import_files/baseDescriptifs.docx");
            XWPFDocument doc = new XWPFDocument(OPCPackage.open(fis));
            List<XWPFTable> table = doc.getTables();        //on extrait tous les tableaux 
            
            //Extraction des informations dans un format rigoureux et indicé
            for (XWPFTable xwpfTable : table) { 
                //On se trouve dans un tableau particulier. On en extrait les lignes
                countRows = 0;
                String chaineDescription = "";
                String chaineParagraph = "";
                String runStyle = "normal";
                String testingRunStyle = "normal";
                String oldParaStyle = "p";
                ArrayList<String> tableau = new ArrayList<String>();
                List<XWPFTableRow> row = xwpfTable.getRows(); 
                for (XWPFTableRow xwpfTableRow : row) { 
                    List<XWPFTableCell> cell = xwpfTableRow.getTableCells();
                    //on extrait les cellules (même si on en a qu'une par ligne)
                    for (XWPFTableCell xwpfTableCell : cell) { 
                        if(xwpfTableCell!=null) { 
                            if(countRows != 4){ //on est pas dans une description, pas besoin de traiter les styles
                                tableau.add(xwpfTableCell.getText());
                            }
                            else{
                                //on est dans une description. On extrait les styles
                                for (XWPFParagraph paragraph : xwpfTableCell.getParagraphs()) {
                                    if(!paragraph.getText().equals("")){
                                        
                                        chaineParagraph = "";
                                        runStyle = "normal";
                                        
                                        for (XWPFRun run : paragraph.getRuns()) {   //on extrait les runs
                                            
                                            testingRunStyle = "normal";
                                            
                                            //on test les textures
                                            if(!run.isBold() && run.getUnderline().toString().equals("SINGLE") && !run.isItalic())
                                                testingRunStyle = "u";
                                            if(!run.isBold() && run.getUnderline().toString().equals("DASH") && !run.isItalic())
                                                testingRunStyle = "underlineDash";
                                            if(!run.isBold() && run.getUnderline().toString().equals("NONE") && run.isItalic())
                                                testingRunStyle = "i";
                                            if(!run.isBold() && run.getUnderline().toString().equals("SINGLE") && run.isItalic())
                                                testingRunStyle = "italic_underline";
                                            if(run.isBold() && run.getUnderline().toString().equals("NONE") && !run.isItalic())
                                                testingRunStyle = "b";
                                            if(run.isBold() && run.getUnderline().toString().equals("SINGLE") && !run.isItalic())
                                                testingRunStyle = "bold_underline";
                                            if(run.isBold() && run.getUnderline().toString().equals("NONE") && run.isItalic())
                                                testingRunStyle = "bold_italic";
                                            if(run.isBold() && run.getUnderline().toString().equals("SINGLE") && run.isItalic())
                                                testingRunStyle = "bold_underline_italic";
                                            
                                            //on test les couleurs
                                            if("FF0000".equals(run.getColor()))
                                                testingRunStyle = "colorRed";
                                            if("E36C0A".equals(run.getColor()))
                                                testingRunStyle = "colorOrange";
                                            if("00B050".equals(run.getColor()))
                                                testingRunStyle = "colorGreen";
                                            if("0070C0".equals(run.getColor()))
                                                testingRunStyle = "colorBlue";
                                            
                                            //on test les surlignages
                                            if("yellow".equals(run.getTextHightlightColor().toString()))
                                                testingRunStyle = "highlightYellow";
                                            if("cyan".equals(run.getTextHightlightColor().toString()))
                                                testingRunStyle = "highlightCyan";
                                            if("red".equals(run.getTextHightlightColor().toString()))
                                                testingRunStyle = "highlightRed";
                                            if("green".equals(run.getTextHightlightColor().toString()))
                                                testingRunStyle = "highlightGreen";
                                            if("magenta".equals(run.getTextHightlightColor().toString()))
                                                testingRunStyle = "highlightMagenta";
                                            if("lightGray".equals(run.getTextHightlightColor().toString()))
                                                testingRunStyle = "highlightGrey";

                                            
                                            //si le style est différent de celui d'avant, on ferme le style d'avant et on ouvre le suivant
                                            if(!runStyle.equals(testingRunStyle)){
                                                //s'il y avait un style avant, on le ferme
                                                if(!"normal".equals(runStyle)){
                                                    chaineParagraph += "</"+runStyle+">";
                                                }
                                                if(!"normal".equals(testingRunStyle)){
                                                    chaineParagraph += "<"+testingRunStyle+">"; //on ouvre la nouvelle balise de style
                                                }
                                                chaineParagraph += run.text();
                                                runStyle = testingRunStyle;
                                            }
                                            else{
                                                chaineParagraph+= run.text();
                                            }
                                        }
                                        
                                        if(!"normal".equals(runStyle))
                                            chaineParagraph += "</"+runStyle+">"; //on ferme la dernière balise
               
                                        if("bullet".equals(paragraph.getNumFmt())){
                                            if("p".equals(oldParaStyle))
                                               chaineDescription += "<ul>"; 
                                            chaineDescription += "<li>"+chaineParagraph+"</li>"; //verif si fermante
                                            oldParaStyle = "ul";
                                        }
                                        else{
                                            if("ul".equals(oldParaStyle))
                                               chaineDescription += "</ul>"; 
                                            chaineDescription += "<p>"+chaineParagraph+"</p>";
                                            oldParaStyle = "p";
                                        }
                                    }
                                }
                                //System.out.println(chaineDescription);
                                tableau.add(chaineDescription);             
                            }
                        }
                    }
                    countRows++;
                }
                docListe.add(tableau);
            }           
         } catch(IOException | InvalidFormatException ex) {
             returnListe.set(0, "Erreur système: l'API POI ne parvient pas à extraire les données");
             erreur = true;
         }
        
        //si on a réussi à extraire les données du word, on peut démarrer l'exploitation des données
        if(!erreur){
            //on parcourt la liste des objets
            for(int i = 0; i < docListe.size(); i++){
                //on extrait l'identifiant et on déduit le type d'objet
                idActuel = docListe.get(i).get(0);
                //on compte le nombre de "_" dans l'ID
                countUnderscore = 0;
                for (int j = 0; j < idActuel.length(); j++) {
                    if (idActuel.charAt(j) == '_') 
                        countUnderscore++;
                }

                JpaUtil.creerContextePersistance();
                try {
                    if(countUnderscore < 4){
                        if(docListe.get(i).get(1).equals("SUPPR")){ //on traite les suppressions
                            //on ajoute à la liste de sortie les idnetifiant à supprimer
                            returnListe.add(idActuel);
                        }
                        else{       //on procède à l'insertion d'un "titre" dans la BD
                            switch(countUnderscore){
                                case 0:             //on importe un chapitre en BD
                                    Chapitre chapitre = null;
                                    chapitre = chapitreDao.ChercherParId(idActuel);
                                    JpaUtil.ouvrirTransaction();
                                    if(chapitre == null){   //on crée le chapitre
                                        chapitre = new Chapitre(idActuel, docListe.get(i).get(1));
                                        chapitreDao.Creer(chapitre);
                                    }
                                    else{   //on modifie l'intitule du chapitre
                                        chapitre.setIntituleChapitre(docListe.get(i).get(1));
                                        chapitreDao.Update(chapitre);
                                    }  
                                    JpaUtil.validerTransaction();
                                    break;
                                case 1:             //on importe une categorie en BD
                                    Categorie categorie = null;
                                    categorie = categorieDao.ChercherParId(idActuel);       //idActuel est l'identifiant de l'objet que l'on traite
                                    JpaUtil.ouvrirTransaction();
                                    if(categorie == null){   //on crée la categorie
                                        categorie = new Categorie(idActuel, docListe.get(i).get(1));
                                        categorieDao.Creer(categorie);  
                                    }
                                    else{   //on modifie l'initule de la categorie
                                        categorie.setIntituleCategorie(docListe.get(i).get(1));
                                        categorieDao.Update(categorie);
                                    } 
                                    //on va chercher le chapitre parent pour update listeCategorie
                                    Chapitre chapitreParent = chapitreDao.ChercherParId(idActuel.substring(0, idActuel.lastIndexOf('_'))); //on prend idActuel et on retire le dernier _ et ce qu'il y a derrière
                                    List<Categorie> listeCategorie = chapitreParent.getListCategorie();
                                    listeCategorie.add(categorie);
                                    chapitreParent.setListCategorie(listeCategorie);
                                    chapitreDao.Update(chapitreParent);
                                    JpaUtil.validerTransaction();
                                    break;  

                                case 2:             //on importe une categorie en BD
                                    Famille famille = null;
                                    famille = familleDao.ChercherParId(idActuel);
                                    JpaUtil.ouvrirTransaction();
                                    if(famille == null){   //on crée la famille
                                        famille = new Famille(idActuel, docListe.get(i).get(1));
                                        familleDao.Creer(famille);
                                    }
                                    else{   //on modifie l'intitule de la famille
                                        famille.setIntituleFamille(docListe.get(i).get(1));
                                        familleDao.Update(famille);
                                    } 
                                    //on va chercher la categorie parent pour update listeFamille
                                    Categorie categorieParent = categorieDao.ChercherParId(idActuel.substring(0, idActuel.lastIndexOf('_'))); //on prend idActuel et on retire le dernier _ et ce qu'il y a derrière
                                    List<Famille> listeFamille = categorieParent.getListeFamille();
                                    listeFamille.add(famille);
                                    categorieParent.setListeFamille(listeFamille);
                                    categorieDao.Update(categorieParent);
                                    JpaUtil.validerTransaction();
                                    break; 
                                case 3:             //on importe une sousFamille en BD
                                    SousFamille sousFamille = null;
                                    sousFamille = sousFamilleDao.ChercherParId(idActuel);
                                    JpaUtil.ouvrirTransaction();
                                    if(sousFamille == null){   //on crée la sousFamille
                                        sousFamille = new SousFamille(idActuel, docListe.get(i).get(1));
                                        sousFamilleDao.Creer(sousFamille);
                                    }
                                    else{   //on modifie l'intitule de la sousFamille
                                        sousFamille.setIntituleSousFamille(docListe.get(i).get(1));
                                        sousFamilleDao.Update(sousFamille);
                                    } 
                                    //on va chercher la famille parent pour update listeSousFamille
                                    Famille familleParent = familleDao.ChercherParId(idActuel.substring(0, idActuel.lastIndexOf('_'))); //on prend idActuel et on retire le dernier _ et ce qu'il y a derrière
                                    List<SousFamille> listeSousFamille = familleParent.getListSousFamille();
                                    listeSousFamille.add(sousFamille);
                                    familleParent.setListSousFamille(listeSousFamille);
                                    familleDao.Update(familleParent);
                                    JpaUtil.validerTransaction();
                                    break;
                            }
                        }
                    }
                    //on procède au traitement d'un descriptif
                    else{
                        //on traite les ajouts
                        if(docListe.get(i).get(1).equals("AJOUT")){
                            switch(docListe.get(i).get(2)){
                                case "OUVRAGE": 
                                    Ouvrage ouvrage = null;
                                    ouvrage = (Ouvrage) descriptifDao.ChercherParId(idActuel);
                                    JpaUtil.ouvrirTransaction();
                                    if(ouvrage == null){   //on crée le chapitre
                                        ouvrage = new Ouvrage(idActuel, docListe.get(i).get(3), docListe.get(i).get(4), docListe.get(i).get(5));
                                        descriptifDao.Creer(ouvrage);
                                    }
                                    else{   //on modifie le titre du chapitre
                                        ouvrage.setNomDescriptif(docListe.get(i).get(3));
                                        ouvrage.setDescription(docListe.get(i).get(4));
                                        ouvrage.setCourteDescription(docListe.get(i).get(5));
                                        descriptifDao.Update(ouvrage);
                                    } 
                                    //on va chercher la sousFamille parent pour update listeDescriptif
                                    SousFamille sousFamilleParent = sousFamilleDao.ChercherParId(idActuel.substring(0, idActuel.lastIndexOf('_'))); //on prend idActuel et on retire le dernier _ et ce qu'il y a derrière
                                    List<Descriptif> listeDescriptif = sousFamilleParent.getListDescriptif();
                                    listeDescriptif.add(ouvrage);
                                    sousFamilleParent.setListDescriptif(listeDescriptif);
                                    sousFamilleDao.Update(sousFamilleParent);
                                    JpaUtil.validerTransaction();
                                    break;
                                case "GENERIQUE":  
                                    Generique generique = null;
                                    generique = (Generique) descriptifDao.ChercherParId(idActuel);
                                    JpaUtil.ouvrirTransaction();
                                    if(generique == null){   //on crée le chapitre
                                        generique = new Generique(idActuel, docListe.get(i).get(3), docListe.get(i).get(4), docListe.get(i).get(5));
                                        descriptifDao.Creer(generique);
                                    }
                                    else{   //on modifie le titre du chapitre
                                        generique.setNomDescriptif(docListe.get(i).get(3));
                                        generique.setDescription(docListe.get(i).get(4));
                                        generique.setCourteDescription(docListe.get(i).get(5));
                                        descriptifDao.Update(generique);
                                    } 
                                    //on va chercher la sousFamille parent pour update listeDescriptif
                                    SousFamille sousFamilleParent2 = sousFamilleDao.ChercherParId(idActuel.substring(0, idActuel.lastIndexOf('_'))); //on prend idActuel et on retire le dernier _ et ce qu'il y a derrière
                                    List<Descriptif> listeDescriptif2 = sousFamilleParent2.getListDescriptif();
                                    listeDescriptif2.add(generique);
                                    sousFamilleParent2.setListDescriptif(listeDescriptif2);
                                    sousFamilleDao.Update(sousFamilleParent2);
                                    JpaUtil.validerTransaction();
                                    break;  
                                case "PRESTATION":  
                                    Prestation prestation = null;
                                    prestation = prestationDao.ChercherParId(idActuel);
                                    JpaUtil.ouvrirTransaction();
                                    if(prestation == null){   //on crée le chapitre
                                        prestation = new Prestation(idActuel, docListe.get(i).get(3), docListe.get(i).get(4), docListe.get(i).get(5));
                                        prestationDao.Creer(prestation);
                                    }
                                    else{   //on modifie le titre du chapitre
                                        prestation.setNomDescriptif(docListe.get(i).get(3));
                                        prestation.setDescription(docListe.get(i).get(4));
                                        prestation.setCourteDescription(docListe.get(i).get(5));
                                        prestationDao.Update(prestation);
                                    } 
                                    //on va chercher l'ouvrage  parent pour update listeprestation
                                    Ouvrage ouvrageParent = (Ouvrage) descriptifDao.ChercherParId(idActuel.substring(0, idActuel.lastIndexOf('_'))); //on prend idActuel et on retire le dernier _ et ce qu'il y a derrière
                                    List<Prestation> listePrestation = ouvrageParent.getListePrestation();
                                    listePrestation.add(prestation);
                                    ouvrageParent.setListePrestation(listePrestation);
                                    descriptifDao.Update(ouvrageParent);
                                    JpaUtil.validerTransaction();
                                    break; 
                            }
                        }
                        //on traite les suppressions
                        else{
                            //on ajoute à la liste de sortie les idnetifiant à supprimer
                            returnListe.add(idActuel);
                        }
                    }
                    
                } catch(Exception ex){
                    returnListe.set(0, "Problème d'insertion dans la base de donnée (problème de format?). ID: "+idActuel);
                    erreur = true;
                } finally {
                    JpaUtil.fermerContextePersistance();  
                }
            }
        }
        
        if(!erreur){
            returnListe.set(0, "Succes");
        }
        
        return returnListe;
    }
    
    public String CompterEnfants(String idDescriptif){
        List<Descriptif> descriptifsSuppr = null;
        String msgStatement = "";
        
        //on sélectionne tous les descriptifs dont l'id commence par: idDescriptif
        JpaUtil.creerContextePersistance();
        try {
            descriptifsSuppr = descriptifDao.compterDescriptifsSuppr(idDescriptif);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherDescriptifParId(id)", ex);
            descriptifsSuppr = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if(descriptifsSuppr.size() <= 1){ //lui même
            msgStatement = "suppr ok";
        }
        else{
            msgStatement = "En supprimant l'objet "+idDescriptif+", vous supprimerez un total de "+descriptifsSuppr.size()+" descriptifs";
        }
        
        return msgStatement;
    }
    
    //TODO
    public String SupprObjet(String idSuppr){
        String msgStatement = "";
        
        //on détermine la nature de l'objet
        int countUnderscore = 0;
        for (int i = 0; i < idSuppr.length(); i++) {
            if (idSuppr.charAt(i) == '_') 
                countUnderscore++;
        }
        
        JpaUtil.creerContextePersistance();
        try {
            switch(countUnderscore){
                case 0:             //on suppr un chapitre
                    Chapitre chapitre = null;
                    chapitre = chapitreDao.ChercherParId(idSuppr);
                    if(chapitre != null){   
                        JpaUtil.ouvrirTransaction();
                        chapitreDao.Remove(chapitre);
                        JpaUtil.validerTransaction();
                    }
                    break;
                case 1:             //on suppr une categorie
                    Categorie categorie = null;
                    categorie = categorieDao.ChercherParId(idSuppr);
                    if(categorie != null){   
                        JpaUtil.ouvrirTransaction();
                        //on va chercher la categorie parent pour update listeFamille
                        Chapitre chapitreParent = chapitreDao.ChercherParId(idSuppr.substring(0, idSuppr.lastIndexOf('_'))); //on prend idActuel et on retire le dernier _ et ce qu'il y a derrière
                        List<Categorie> listCategorie = chapitreParent.getListCategorie();
                        listCategorie.remove(categorie);
                        chapitreParent.setListCategorie(listCategorie);
                        chapitreDao.Update(chapitreParent);
                        categorieDao.Remove(categorie);
                        JpaUtil.validerTransaction();
                    }
                    break;  
                case 2:             //on suppr une famille
                    Famille famille = null;
                    famille = familleDao.ChercherParId(idSuppr);
                    if(famille != null){   
                        JpaUtil.ouvrirTransaction();
                        //on va chercher la categorie parent pour update listeFamille
                        Categorie categorieParent = categorieDao.ChercherParId(idSuppr.substring(0, idSuppr.lastIndexOf('_'))); //on prend idActuel et on retire le dernier _ et ce qu'il y a derrière
                        List<Famille> listeFamille = categorieParent.getListeFamille();
                        listeFamille.remove(famille);
                        categorieParent.setListeFamille(listeFamille);
                        categorieDao.Update(categorieParent);
                        familleDao.Remove(famille);
                        JpaUtil.validerTransaction();
                    }
                    break; 
                case 3:             //on suppr une sousFamille
                    SousFamille sousFamille = null;
                    sousFamille = sousFamilleDao.ChercherParId(idSuppr);
                    if(sousFamille != null){   
                        JpaUtil.ouvrirTransaction();
                        //on va chercher la famille parent pour update listeSousFamille
                        Famille familleParent = familleDao.ChercherParId(idSuppr.substring(0, idSuppr.lastIndexOf('_'))); //on prend idActuel et on retire le dernier _ et ce qu'il y a derrière
                        List<SousFamille> listSousFamille = familleParent.getListSousFamille();
                        listSousFamille.remove(sousFamille);
                        familleParent.setListSousFamille(listSousFamille);
                        familleDao.Update(familleParent);
                        sousFamilleDao.Remove(sousFamille);
                        JpaUtil.validerTransaction();
                    }
                    break;
                case 4:            //on suppr un descrptif
                    Descriptif descriptif = null;
                    descriptif = descriptifDao.ChercherParId(idSuppr);
                    if(descriptif != null){   
                        JpaUtil.ouvrirTransaction();
                          //on va chercher la sousFamille parent pour update listeDescriptif
                        SousFamille sousFamilleParent = sousFamilleDao.ChercherParId(idSuppr.substring(0, idSuppr.lastIndexOf('_'))); //on prend idActuel et on retire le dernier _ et ce qu'il y a derrière
                        List<Descriptif> listeDescriptif = sousFamilleParent.getListDescriptif();
                        listeDescriptif.remove(descriptif);
                        sousFamilleParent.setListDescriptif(listeDescriptif);
                        sousFamilleDao.Update(sousFamilleParent);
                        descriptifDao.Remove(descriptif);
                        JpaUtil.validerTransaction();
                    }
                    break;
                case 5:
                    Prestation prestation = null;
                    prestation = (Prestation) descriptifDao.ChercherParId(idSuppr);
                    if(prestation != null){   
                        JpaUtil.ouvrirTransaction();
                          //on va chercher l'ouvrage parent pour update listePrestation
                        Ouvrage ouvrageParent = (Ouvrage) descriptifDao.ChercherParId(idSuppr.substring(0, idSuppr.lastIndexOf('_'))); //on prend idActuel et on retire le dernier _ et ce qu'il y a derrière
                        List<Prestation> listePrestation = ouvrageParent.getListePrestation();
                        listePrestation.remove(prestation);
                        ouvrageParent.setListePrestation(listePrestation);
                        descriptifDao.Update(ouvrageParent);
                        descriptifDao.Remove(prestation);
                        JpaUtil.validerTransaction();
                    }
                    break;
            }

            msgStatement = "Succes";
        } catch(Exception ex){
            msgStatement = "une erreur est survenue lors de la suppression de "+idSuppr;
        } finally {
            JpaUtil.fermerContextePersistance();  
        }
        
        return msgStatement;
    }
    
    //TO DO - Essayer sur un jeu de test
    public String ModifBasePrixRef(){
        //Importer le CSV
        //Parser le document cas par cas (ajout OU suppr) en sautant la premiere ligne
        //Si suppr alors on delete dans la BD - ????
        //Si ajout on add
        //Dans tous les cas on met à jour soit listeBasePrixRefOuvrage soit listeBasePrixRefPrestation
        //Si erreur alors on affiche l'erreur correspondante
        //Si succès alors on retourne "succes"
        
        JpaUtil.creerContextePersistance();
        
        String rapport = null;
        //Input file which needs to be parsed
        String fileToParse = "../import_files/ModifBasePrixRef.csv";
        BufferedReader fileReader = null;
         
        //Delimiter used in CSV file
        final String DELIMITER = ";";
        try
        {
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileToParse));
             
            //We skip the first line then we read the file line by line
            String line = fileReader.readLine();
            while ((line = fileReader.readLine()) != null) 
            {
                //Get all attributes available in line
                String[] attributes = line.split(DELIMITER);
                
                //On cherche à savoir s'il s'agit d'un ouvrage ou d'une prestation
                String typeDescriptif = null;
                if(attributes[2].length() == 15)
                    typeDescriptif = "Ouvrage";
                else
                    typeDescriptif = "Prestation";
                
                //Utile pour la suite
                BasePrixRef newBasePrixRef = null;
                Ouvrage ouvrageAModifier = null;
                Prestation prestationAModifier = null;
                List<BasePrixRef> listeBasePrixRef = null;
                Boolean testDejaPresent = false;
                
                switch(attributes[1])
                {
                    case "AJOUT":
                        try {
                            JpaUtil.ouvrirTransaction();
                            //Si des valeurs chaines vides alors on le prend en compte pour la BDD en tant que null
                            Integer annee;
                            if(attributes[9].equals("-")) {
                                annee = null;
                            } else
                                annee = Integer.parseInt(attributes[9]);
                            Long nbPrixRef = Long.parseLong(attributes[3]);
                            Double BT;
                            if(attributes[4].equals("-"))
                                BT = null;
                            else
                                BT = Double.parseDouble(attributes[4]);
                            Double qteInf;
                            if(attributes[5].equals("-"))
                                qteInf = null;
                            else
                                qteInf = Double.parseDouble(attributes[5]);
                            Double qteSup;
                            if(attributes[6].equals("-"))
                                qteSup = null;
                            else
                                qteSup = Double.parseDouble(attributes[6]);
                            String unite = null;
                            if(attributes[7].equals("-"))
                                qteSup = null;
                            else
                                unite = attributes[7];
                            Double prixUnitaire;
                            if(attributes[8].equals("-"))
                                prixUnitaire = null;
                            else
                                prixUnitaire = Double.parseDouble(attributes[8]);
                            
                            //On vérifie si le prix en question n'existe pas déjà par ailleurs dans la BD
                            if(typeDescriptif.equals("Ouvrage")) {
                                ouvrageAModifier = (Ouvrage)descriptifDao.ChercherParId(attributes[2]);
                                listeBasePrixRef = ouvrageAModifier.getListeBasePrixRefOuvrage();
                                for(BasePrixRef basePrixRefParcours : listeBasePrixRef) {
                                    if(basePrixRefParcours.getNbPrixRef().compareTo(nbPrixRef) == 0) {
                                        //On écrase donc le basePrixRef en question
                                        basePrixRefParcours.setAnnee(annee);
                                        basePrixRefParcours.setBT(BT);
                                        basePrixRefParcours.setQteInf(qteInf);
                                        basePrixRefParcours.setQteSup(qteSup);
                                        basePrixRefParcours.setUnite(unite);
                                        basePrixRefParcours.setPrixUnitaire(prixUnitaire);
                                        ouvrageAModifier.setListeBasePrixRefOuvrage(listeBasePrixRef);
                                        descriptifDao.Update(ouvrageAModifier);
                                        basePrixRefDao.Update(basePrixRefParcours);
                                        testDejaPresent = true;
                                        break;
                                    }
                                }
                            } else {
                                prestationAModifier = (Prestation)descriptifDao.ChercherParId(attributes[2]);
                                listeBasePrixRef = prestationAModifier.getListeBasePrixRefPrestation();
                                for(BasePrixRef basePrixRefParcours : listeBasePrixRef) {
                                    if(basePrixRefParcours.getNbPrixRef().compareTo(nbPrixRef) == 0) {
                                        //On écrase donc le basePrixRef en question
                                        basePrixRefParcours.setAnnee(annee);
                                        basePrixRefParcours.setBT(BT);
                                        basePrixRefParcours.setQteInf(qteInf);
                                        basePrixRefParcours.setQteSup(qteSup);
                                        basePrixRefParcours.setUnite(unite);
                                        basePrixRefParcours.setPrixUnitaire(prixUnitaire);
                                        prestationAModifier.setListeBasePrixRefPrestation(listeBasePrixRef);
                                        descriptifDao.Update(prestationAModifier);
                                        basePrixRefDao.Update(basePrixRefParcours);
                                        testDejaPresent = true;
                                        break;
                                    }
                                }
                            }
                            if(!testDejaPresent) {
                                newBasePrixRef = new BasePrixRef(annee, Long.parseLong(attributes[3]), BT, qteInf, qteSup, unite, prixUnitaire);
                                basePrixRefDao.Creer(newBasePrixRef);
                                if(typeDescriptif.equals("Ouvrage")) {
                                    listeBasePrixRef.add(newBasePrixRef);
                                    ouvrageAModifier.setListeBasePrixRefOuvrage(listeBasePrixRef);
                                    descriptifDao.Update(ouvrageAModifier);
                                } else {
                                    listeBasePrixRef.add(newBasePrixRef);
                                    prestationAModifier.setListeBasePrixRefPrestation(listeBasePrixRef);
                                    descriptifDao.Update(prestationAModifier);
                                }
                            }
                            JpaUtil.validerTransaction();
                        } catch (Exception ex) {
                            rapport += " | Erreur lors de l'insertion de " + attributes[0];
                            throw new Exception();
                        }
                        break;
                    case "SUPPR":
                        try {
                            JpaUtil.ouvrirTransaction();
                            if(typeDescriptif.equals("Ouvrage")) {
                                ouvrageAModifier = (Ouvrage)descriptifDao.ChercherParId(attributes[2]);
                                listeBasePrixRef = ouvrageAModifier.getListeBasePrixRefOuvrage();
                                //On va chercher le prix qui nous interesse
                                for(BasePrixRef basePrixRefParcours : listeBasePrixRef) {
                                    if(basePrixRefParcours.getNbPrixRef().compareTo(Long.parseLong(attributes[3])) == 0) {
                                        //On supprime d'abord le lien entre l'ouvrage et le prix, puis on supprime le prix
                                        listeBasePrixRef.remove(basePrixRefParcours);
                                        ouvrageAModifier.setListeBasePrixRefOuvrage(listeBasePrixRef);
                                        descriptifDao.Update(ouvrageAModifier);
                                        basePrixRefDao.Remove(basePrixRefParcours);
                                        break;
                                    }
                                }
                            } else {
                                prestationAModifier = (Prestation)descriptifDao.ChercherParId(attributes[2]);
                                listeBasePrixRef = prestationAModifier.getListeBasePrixRefPrestation();
                                //On va chercher le prix qui nous interesse
                                for(BasePrixRef basePrixRefParcours : listeBasePrixRef) {
                                    if(basePrixRefParcours.getNbPrixRef().compareTo(Long.parseLong(attributes[3])) == 0) {
                                        //On supprime d'abord le lien entre l'ouvrage et le prix, puis on supprime le prix
                                        listeBasePrixRef.remove(basePrixRefParcours);
                                        prestationAModifier.setListeBasePrixRefPrestation(listeBasePrixRef);
                                        descriptifDao.Update(prestationAModifier);
                                        basePrixRefDao.Remove(basePrixRefParcours);
                                        break;
                                    }
                                }
                            }
                            JpaUtil.validerTransaction();
                        } catch (Exception ex) {
                            rapport += " | Erreur lors de la suppression de " + attributes[0];
                            throw new Exception();
                        }
                        break;
                    default:
                        rapport += " opération non reconnue";
                        break;
                }
                /*if(rapport == null) { //MAJ du descriptif lié
                    try {
                        JpaUtil.ouvrirTransaction();
                        if(attributes[1].equals("AJOUT")) {
                            if(typeDescriptif.equals("Ouvrage")) {
                                //ouvrageAModifier = (Ouvrage)descriptifDao.ChercherParId(attributes[2]);
                                listeBasePrixRef.add(newBasePrixRef);
                                ouvrageAModifier.setListeBasePrixRefOuvrage(listeBasePrixRef);
                                descriptifDao.Update(ouvrageAModifier);
                            } else {
                                //prestationAModifier = (Prestation)descriptifDao.ChercherParId(attributes[2]);
                                listeBasePrixRef.add(newBasePrixRef);
                                prestationAModifier.setListeBasePrixRefPrestation(listeBasePrixRef);
                                descriptifDao.Update(prestationAModifier);
                            }
                        } else {
                            if(typeDescriptif.equals("Ouvrage")) {
                                //ouvrageAModifier = (Ouvrage)descriptifDao.ChercherParId(attributes[2]);
                                //List<BasePrixRef> listeBasePrixRef = ouvrageAModifier.getListeBasePrixRefOuvrage();
                                listeBasePrixRef.remove(newBasePrixRef);
                                ouvrageAModifier.setListeBasePrixRefOuvrage(listeBasePrixRef);
                                descriptifDao.Update(prestationAModifier);
                            } else {
                                //prestationAModifier = (Prestation)descriptifDao.ChercherParId(attributes[2]);
                                //List<BasePrixRef> listeBasePrixRef = prestationAModifier.getListeBasePrixRefPrestation();
                                listeBasePrixRef.remove(newBasePrixRef);
                                prestationAModifier.setListeBasePrixRefPrestation(listeBasePrixRef);
                                descriptifDao.Update(prestationAModifier);
                            }
                        }
                        JpaUtil.validerTransaction();
                    } catch (Exception e) {
                        rapport += " | Erreur avec " + attributes[0];
                        throw new Exception();
                    }
                }*/
            }
        } 
        catch (Exception e) {
            //L'exception aura déjà été collectée et traitée en amont
        } 
        finally
        {
            JpaUtil.fermerContextePersistance();
            if(rapport == null)
                rapport = "Succès !";
            try {
                fileReader.close();
            } catch (IOException e) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Erreur lors de la fermeture du fichier", e);
            }
        }
        return rapport;
    }
}
