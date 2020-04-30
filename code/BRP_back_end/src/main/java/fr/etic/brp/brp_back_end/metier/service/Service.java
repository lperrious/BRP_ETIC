package fr.etic.brp.brp_back_end.metier.service;

import com.google.common.hash.Hashing;
import fr.etic.brp.brp_back_end.dao.BasePrixRefDao;
import fr.etic.brp.brp_back_end.dao.CaractDimDao;
import fr.etic.brp.brp_back_end.dao.CategorieConstructionDao;
import fr.etic.brp.brp_back_end.dao.CategorieDao;
import fr.etic.brp.brp_back_end.dao.CoeffRaccordementDao;
import fr.etic.brp.brp_back_end.dao.CorpsEtatDao;
import fr.etic.brp.brp_back_end.dao.DescriptifDao;
import fr.etic.brp.brp_back_end.dao.DomUtil;
import static fr.etic.brp.brp_back_end.dao.DomUtil.builder;
import fr.etic.brp.brp_back_end.dao.FamilleDao;
import fr.etic.brp.brp_back_end.dao.JpaUtil;
import fr.etic.brp.brp_back_end.dao.OperateurDao;
import fr.etic.brp.brp_back_end.dao.PrestationDao;
import fr.etic.brp.brp_back_end.dao.ProjetDao;
import fr.etic.brp.brp_back_end.dao.ProjetXMLDao;
import fr.etic.brp.brp_back_end.dao.SimpleErrorHandler;
import fr.etic.brp.brp_back_end.dao.SousCategorieConstructionDao;
import fr.etic.brp.brp_back_end.dao.SousFamilleDao;
import fr.etic.brp.brp_back_end.metier.modele.BasePrixRef;
import fr.etic.brp.brp_back_end.metier.modele.CaractDim;
import fr.etic.brp.brp_back_end.metier.modele.Categorie;
import fr.etic.brp.brp_back_end.metier.modele.CategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.CoeffRaccordement;
import fr.etic.brp.brp_back_end.metier.modele.CorpsEtat;
import fr.etic.brp.brp_back_end.metier.modele.Descriptif;
import fr.etic.brp.brp_back_end.metier.modele.Famille;
import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import fr.etic.brp.brp_back_end.metier.modele.Prestation;
import fr.etic.brp.brp_back_end.metier.modele.Projet;
import fr.etic.brp.brp_back_end.metier.modele.SousCategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.SousFamille;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author louisrob
 */
public class Service {
    
    protected CategorieConstructionDao categorieConstructionDao = new CategorieConstructionDao();
    protected CategorieDao categorieDao = new CategorieDao();
    protected CoeffRaccordementDao coeffRaccordementDao = new CoeffRaccordementDao();
    protected CorpsEtatDao corpsEtatDao = new CorpsEtatDao();
    protected DescriptifDao descriptifDao = new DescriptifDao();
    protected FamilleDao familleDao = new FamilleDao();
    protected OperateurDao operateurDao = new OperateurDao();
    protected ProjetDao projetDao = new ProjetDao();
    protected SousCategorieConstructionDao sousCategorieConstructionDao = new SousCategorieConstructionDao();
    protected SousFamilleDao sousFamilleDao = new SousFamilleDao();
    protected BasePrixRefDao basePrixRefDao = new BasePrixRefDao();
    protected CaractDimDao caractDimDao = new CaractDimDao();
    protected PrestationDao prestationDao = new PrestationDao();
    protected ProjetXMLDao projetXMLDao = new ProjetXMLDao();
    
    public Descriptif RechercherDescriptifParId(Long id) {
        Descriptif resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = descriptifDao.ChercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherDescriptifParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public List<Categorie> ListerCategories() {
        List<Categorie> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = categorieDao.ListerCategories();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerCategories()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    public List<CategorieConstruction> ListerCategorieConstructions() {
        List<CategorieConstruction> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = categorieConstructionDao.ListerCategorieConstructions();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerCategorieConstructions()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    public List<CoeffRaccordement> ListerCoeffRaccordements() {
        List<CoeffRaccordement> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = coeffRaccordementDao.ListerCoeffRaccordements();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerCoeffRaccordements()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    public List<CorpsEtat> ListerCorpsEtats() {
        List<CorpsEtat> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = corpsEtatDao.ListerCorpsEtat();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerCorpsEtat()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    public List<Descriptif> ListerDescriptifs() {
        List<Descriptif> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = descriptifDao.ListerDescriptifs();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerDescriptifs()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    public List<Famille> ListerFamilles() {
        List<Famille> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = familleDao.ListerFamilles();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerFamilles()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    public List<Operateur> ListerOperateurs(){
        List<Operateur> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = operateurDao.ListerOperateurs();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerOperateurs()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    public List<Projet> ListerProjets(){
        List<Projet> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = projetDao.ListerProjets();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerProjets()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    public List<SousCategorieConstruction> ListerSousCategorieConstructions(){
        List<SousCategorieConstruction> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = sousCategorieConstructionDao.ListerSousCategorieConstructions();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerSousCategorieConstructions()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    public List<SousFamille> ListerSousFamilles(){
        List<SousFamille> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = sousFamilleDao.ListerSousFamilles();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerSousFamilles()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    public List<BasePrixRef> ListerBasePrixRefs(){
        List<BasePrixRef> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = basePrixRefDao.ListerBasePrixRefs();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerBasePrixRefs()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    public List<CaractDim> ListerCaractDims(){
        List<CaractDim> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = caractDimDao.ListerCaractDims();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerCaractDims()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    public List<Prestation> ListerPrestations(){
        List<Prestation> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = prestationDao.ListerPrestations();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerPrestations()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Operateur AuthentifierOperateur(String mail, String mdpEntre) {
        Operateur resultat = null;
        Integer salt = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche de l'operateur
            salt = operateurDao.RecupererSalt(mail);
            String mdpConcat = mdpEntre+salt;
            String mdpHash = Hashing.sha256().hashString(mdpConcat, StandardCharsets.UTF_8).toString();
            Operateur operateur = operateurDao.ChercherParMail(mail);
            if (operateur != null) {
                // Vérification du mot de passe
                if (operateur.getMdp().equals(mdpHash)) {
                    resultat = operateur;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierOperateur(mail,mdp)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Boolean CreerProjet(String nomProjet) {
        JpaUtil.creerContextePersistance();
        Projet newProjet = null;
        Long idProjet = null;
        
        try {
            //on crée l'objet projet
            newProjet = new Projet(nomProjet);
            //On enregistre dans la BD
            JpaUtil.ouvrirTransaction();
            projetDao.Creer(newProjet);
            JpaUtil.validerTransaction();
            
            //Creation du XML si tout a fonctionné
            Projet projet = projetDao.ChercherDernierParNom(nomProjet);
            idProjet = projet.getIdProjet();
            String uri = "../XMLfiles/"+idProjet+".xml";
            Document xml = projetXMLDao.Creer();
            
            //Création de la racine
            Element baliseProjet = xml.createElement("projet");
            baliseProjet.setAttribute("idProjet", idProjet.toString());
            xml.appendChild(baliseProjet);
            
            //Ecriture du XML
            projetXMLDao.saveXMLContent(xml, uri);
              
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service CreerProjet(nomProjet)", ex);
            //Supprimer le projet dans la BD si erreur XML
            try {
                JpaUtil.ouvrirTransaction();
                projetDao.Remove(newProjet);
                JpaUtil.validerTransaction();
            } catch (Exception e) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Le projet n°" + idProjet + " n'a pas pu être supprimer ! Il faut le supprimer à la main !", e);
            }
            newProjet = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
     
        //Si l'opération a réussi on renvoi true
        if(newProjet != null)
            return true;
        else
            return false;
    }
    
    public Projet RechercherProjetParId(Long id) {
        Projet resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = projetDao.ChercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherProjetParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Boolean EditerNomProjet(Long idProjet, String nouveauNomProjet){
        Projet projetAModifier = null;
        JpaUtil.creerContextePersistance();
        try{
            if(idProjet != null) {
                projetAModifier = projetDao.ChercherParId(idProjet);
                if(projetAModifier != null) {
                    projetAModifier.setNomProjet(nouveauNomProjet);
                    
                    //On enregistre dans la BD
                    JpaUtil.ouvrirTransaction();
                    projetDao.Update(projetAModifier);
                    JpaUtil.validerTransaction();
                }
            }
        } catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service EditerNomProjet(idProjet, nouveauNomProjet)", ex);
            projetAModifier = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if(projetAModifier != null)
            return true;
        else
            return false;
    }
    
    public Boolean EditerInfoEnumProjet(Long idProjet, String typeEnum, String valeurEnum){
        Projet projetAModifier = null;
        JpaUtil.creerContextePersistance();
        try{
            if(idProjet != null) {
                projetAModifier = projetDao.ChercherParId(idProjet);
                if(projetAModifier != null) {
                    switch(typeEnum) {
                        case "TypeMarche":
                            switch(valeurEnum) {
                                case "marchePublic":
                                    projetAModifier.setTypeMarche(Projet.TypeMarche.marchePublic);
                                    break;
                                case "marchePrive":
                                    projetAModifier.setTypeMarche(Projet.TypeMarche.marchePrive);
                                    break;
                                default:
                                    throw new Exception();
                            }
                            break;
                        case "TypeConstruction":
                            switch(valeurEnum) {
                                case "neuf":
                                    projetAModifier.setTypeConstruction(Projet.TypeConstruction.neuf);
                                    break;
                                case "renovation":
                                    projetAModifier.setTypeConstruction(Projet.TypeConstruction.renovation);
                                    break;
                                default:
                                    throw new Exception();
                            }
                            break;
                        case "TypeLot":
                            switch(valeurEnum) {
                                case "lotSepare":
                                    projetAModifier.setTypeLot(Projet.TypeLot.lotSepare);
                                    break;
                                case "entrepriseGenerale":
                                    projetAModifier.setTypeLot(Projet.TypeLot.entrepriseGenerale);
                                    break;
                                default:
                                    throw new Exception();
                            }
                            break;
                        case "Site":
                            switch(valeurEnum) {
                                case "libre":
                                    projetAModifier.setSite(Projet.Site.libre);
                                    break;
                                case "occupe":
                                    projetAModifier.setSite(Projet.Site.occupe);
                                    break;
                                default:
                                    throw new Exception();
                            }
                            break;
                        default:
                            throw new Exception();
                    }
                    //On enregistre dans la BD
                    JpaUtil.ouvrirTransaction();
                    projetDao.Update(projetAModifier);
                    JpaUtil.validerTransaction();
                }
            }
        } catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service EditerInfoEnumProjet(idProjet, typeEnum, valeurEnum)", ex);
            projetAModifier = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if(projetAModifier != null)
            return true;
        else
            return false;
    }
    
    public Boolean EditerDateProjet(Long idProjet, Date nouvelleDate){
        Projet projetAModifier = null;
        JpaUtil.creerContextePersistance();
        try{
            if(idProjet != null) {
                projetAModifier = projetDao.ChercherParId(idProjet);
                if(projetAModifier != null) {
                    projetAModifier.setDatePrixRef(nouvelleDate);
                    
                    //On enregistre dans la BD
                    JpaUtil.ouvrirTransaction();
                    projetDao.Update(projetAModifier);
                    JpaUtil.validerTransaction();
                }
            }
        } catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service EditerDateProjet(idProjet, nouvelleDate)", ex);
            projetAModifier = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if(projetAModifier != null)
            return true;
        else
            return false;
    }
    
    public Boolean EditerCoeffAdaptProjet(Long idProjet, Float nouveauCoeffAdapt){
        Projet projetAModifier = null;
        JpaUtil.creerContextePersistance();
        try{
            if(idProjet != null) {
                projetAModifier = projetDao.ChercherParId(idProjet);
                if(projetAModifier != null) {
                    projetAModifier.setCoeffAdapt(nouveauCoeffAdapt);
                    
                    //On enregistre dans la BD
                    JpaUtil.ouvrirTransaction();
                    projetDao.Update(projetAModifier);
                    JpaUtil.validerTransaction();
                }
            }
        } catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service EditerCoeffAdaptProjet(idProjet, nouveauCoeffAdapt)", ex);
            projetAModifier = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if(projetAModifier != null)
            return true;
        else
            return false;
    }
    
    public Boolean EditerCoeffRaccordementProjet(Long idProjet, Long idCoeffRaccordement){
        Projet projetAModifier = null;
        CoeffRaccordement coeffRaccordement = null;
        JpaUtil.creerContextePersistance();
        try{
            if(idProjet != null) {
                projetAModifier = projetDao.ChercherParId(idProjet);
                if(projetAModifier != null) {
                    coeffRaccordement = coeffRaccordementDao.ChercherParId(idCoeffRaccordement);
                    if(coeffRaccordement != null) {
                        projetAModifier.setCoeffRaccordement(coeffRaccordement);
                    
                        //On enregistre dans la BD
                        JpaUtil.ouvrirTransaction();
                        projetDao.Update(projetAModifier);
                        JpaUtil.validerTransaction();
                    } else {
                        projetAModifier = null;
                    }
                }
            }
        } catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service EditerCoeffRaccordementProjet(idProjet, idCoeffRaccordement)", ex);
            projetAModifier = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if(projetAModifier != null)
            return true;
        else
            return false;
    }
    
    public Boolean EditerCategorieConstructionProjet(Long idProjet, Long idCategorieConstruction){
        Projet projetAModifier = null;
        CategorieConstruction categorieConstruction = null;
        JpaUtil.creerContextePersistance();
        try{
            if(idProjet != null) {
                projetAModifier = projetDao.ChercherParId(idProjet);
                if(projetAModifier != null) {
                    categorieConstruction = categorieConstructionDao.ChercherParId(idCategorieConstruction);
                    if(categorieConstruction != null) {
                        projetAModifier.setCategorieConstruction(categorieConstruction);
                    
                        //On enregistre dans la BD
                        JpaUtil.ouvrirTransaction();
                        projetDao.Update(projetAModifier);
                        JpaUtil.validerTransaction();
                    } else {
                        projetAModifier = null;
                    }
                }
            }
        } catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service EditerCategorieConstructionProjet(idProjet, idCategorieConstruction)", ex);
            projetAModifier = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if(projetAModifier != null)
            return true;
        else
            return false;
    }
    
    //TO DO
    public String ModifBaseDescriptif(){
        //Importer le word
        //Parser le document cas par cas (Vide OU ajout OU suppr)
        //Si vide tu sautes
        //Si ajout on collecte les infos. Puis on regarde si pas déjà ds la BD. Si c'est le cas alors on delete l'ancien. Puis on ajoute le nouveau
        //Remarque : Il faut check d'abord les styles du RUN pour les svg sous forme de balise dans la BD !
        //Si suppr on delete dans la BD.
        //Dans tous les cas (sauf vide) on update l'attribut listeDescriptif de l'instance SousFamille correspondante
        //Si erreur alors on affiche l'erreur correspondante
        //Si succès alors on retourne "succes"
        
        return null;
    }
    
    //TO DO
    public String ModifBasePrixRef(){
        //Importer le CSV
        //Parser le document cas par cas (ajout OU suppr) en sautant la premiere ligne
        //Si suppr alors on delete dans la BD
        //Si ajout on add
        //Dans tous les cas on met à jour soit listeBasePrixRefOuvrage soit listeBasePrixRefPrestation
        //Si erreur alors on affiche l'erreur correspondante
        //Si succès alors on retourne "succes"
        
        return null;
    }
    
    //Duplique un projet en donnant par défaut le nom "Nouveau Projet"
    public Boolean DupliquerProjet(Long idProjetADupliquer){
        Projet projetADupliquer = null;
        Projet projetDuplique = null;
        Long idProjet = null;
        
        JpaUtil.creerContextePersistance();
        
        try {
            if(idProjetADupliquer != null)
            {
                projetADupliquer = projetDao.ChercherParId(idProjetADupliquer); //On va chercher le projet à duppliquer
                if(projetADupliquer != null) {
                    //On duplique ce projet en faisant attention aux liens divers
                    String nomProjetDuplique = "Nouveau Projet"; //Par défaut
                    projetDuplique = new Projet(nomProjetDuplique);
                    projetDuplique.setTypeMarche(projetADupliquer.getTypeMarche());
                    projetDuplique.setTypeConstruction(projetADupliquer.getTypeConstruction());
                    projetDuplique.setTypeLot(projetADupliquer.getTypeLot());
                    projetDuplique.setSite(projetADupliquer.getSite());
                    projetDuplique.setDatePrixRef(projetADupliquer.getDatePrixRef());
                    projetDuplique.setCoeffAdapt(projetADupliquer.getCoeffAdapt());
                    projetDuplique.setCategorieConstruction(projetADupliquer.getCategorieConstruction());
                    projetDuplique.setCoeffRaccordement(projetADupliquer.getCoeffRaccordement());
                    
                    //On enregistre dans la BD
                    JpaUtil.ouvrirTransaction();
                    projetDao.Creer(projetDuplique);
                    JpaUtil.validerTransaction();
                    
                    //Copie du XML si tout a fonctionné
                    Projet projet = projetDao.ChercherDernierParNom(nomProjetDuplique);
                    idProjet = projet.getIdProjet();
                    String uriNewXML = "../XMLfiles/"+idProjet+".xml";
                    String uriOldXML = "../XMLfiles/"+idProjetADupliquer+".xml";
                    Document xml = projetXMLDao.ObtenirDocument(uriOldXML);

                    //MAJ de l'id de la racine projet
                    Element root = xml.getDocumentElement();
                    root.setAttribute("idProjet", idProjet.toString());
                    
                    //Sortie du XML
                    projetXMLDao.saveXMLContent(xml, uriNewXML);      
                } else {
                    System.out.println("Pas de projet à dupliquer trouvé !");
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service DupliquerProjet(idProjet)", ex);
            //Supprimer le projet dans la BD si erreur XML
            try {
                JpaUtil.ouvrirTransaction();
                projetDao.Remove(projetDuplique);
                JpaUtil.validerTransaction();
            } catch (Exception e) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Le projet n°" + idProjet + " n'a pas pu être supprimer ! Il faut le supprimer à la main !", e);
            }
            projetADupliquer = null;
            projetDuplique = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        //Si l'opération a réussi on renvoi true
        if(projetDuplique != null)
            return true;
        else
            return false;
    }    
            
    //TO DO - Permet d'avoir une vue de synthèse des couts - Demander à Benoit des précisions sur la synthèse des couts (par corps d'etat ou autre chose ?)
    public Double CoutSynthese(Long idProjet, Long idCorpsEtat) {
        //recuperer coeffAdapt & coeffRaccordement dans la table projet
        //Récuperer prixUnitaire et quantite dans le XML
        return null;
    }
    
    public Boolean AjouterCorpsEtat(Long idProjet, Long idCorpsEtat) {
        JpaUtil.creerContextePersistance();
        Boolean resultat = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            Element root = xml.getDocumentElement();
            //Création balise corpsEtat
            Element baliseCorpsEtat = xml.createElement("corpsEtat");
            baliseCorpsEtat.setAttribute("idCorpsEtat", idCorpsEtat.toString());
            //Création de la balise intitule
            Element baliseIntitule = xml.createElement("intitule");
            CorpsEtat corpsEtat = corpsEtatDao.ChercherParId(idCorpsEtat);
            baliseIntitule.appendChild(xml.createTextNode(corpsEtat.getIntituleCorpsEtat()));
   
            baliseCorpsEtat.appendChild(baliseIntitule);
            root.appendChild(baliseCorpsEtat);
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            resultat = true; //Si on est arrivé jusque là alors pas d'erreur
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterCorpsEtat(idProjet, idCorpsEtat)");
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    //TO DO - Ajoute un champ Categorie dans le XML
    public Boolean AjouterCategorie(Long idProjet, Long idCategorie, Long idCorpsEtat){
        JpaUtil.creerContextePersistance();
        Boolean resultat = false;
        Boolean existenceCorpsEtat = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            Element root = xml.getDocumentElement();
            NodeList rootNodes = root.getChildNodes();
            //Création balise categorie
            Element baliseCategorie = xml.createElement("categorie");
            baliseCategorie.setAttribute("idCategorie", idCategorie.toString());
            //Création de la balise intitule
            Element baliseIntitule = xml.createElement("intitule");
            JpaUtil.ouvrirTransaction(); ////////////////////////////////////////////////////////////////// Nécessaire ??????
            Categorie categorie = categorieDao.ChercherParId(idCategorie);
            JpaUtil.validerTransaction();
            baliseIntitule.appendChild(xml.createTextNode(categorie.getIntituleCategorie()));
   
            baliseCategorie.appendChild(baliseIntitule);
            
            //on parcours le xml à la recherche du corpsEtat parent
            for (int i = 0; i<rootNodes.getLength(); i++) 
            {
              if(rootNodes.item(i).getNodeName().equals("corpsEtat")) 
              {
                  Element corpsEtat = (Element) rootNodes.item(i);
                  if(corpsEtat.getAttribute("idCorpsEtat").equals(idCorpsEtat.toString())){
                      rootNodes.item(i).appendChild(baliseCategorie);
                      existenceCorpsEtat = true;
                      break;
                  }
              } 			
            }
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(existenceCorpsEtat){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterCategorie(Long idProjet, Long idCategorie)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    //TO DO - Ajoute un champ Famille dans le XML
    public Boolean AjouterFamille(Long idProjet, Long idFamille){
        return null;
    }
    
    //TO DO - Ajoute un champ SousFamille dans le XML
    public Boolean AjouterSousFamille(Long idProjet, Long idSousFamille){
        return null;
    }
    
    //TO DO - Ajoute un ouvrage ou generique dans une sous famille
    public Boolean AjouterOuvrageOuGenerique(Long idProjet, Long idDescriptif){
        return null;
    }
    
    //TO DO - Ajoute une prestation dans un ouvrage. Penser à retirer prix ouvrage
    public Boolean AjouterPrestation(Long idProjet, Long idDescriptif){
        return null;
    }
    
    //TO DO - Ajoute un champ Localisation ainsi qu'un champ Quantite dans le XML
    public Boolean AjouterLigneChiffrage(Long idProjet, Long idDescriptif){
        return null;
    }
    
    //TO DO - Supprime un champ CorpsEtat dans le XML
    public Boolean SupprimerCorpsEtat(Long idProjet, Long idCorpsEtat){
        return null;
    }
    
    //TO DO - Supprime un champ Categorie dans le XML
    public Boolean SupprimerCategorie(Long idProjet, Long idCategorie){
        return null;
    }
    
    //TO DO - Supprime un champ Famille dans le XML
    public Boolean SupprimerFamille(Long idProjet, Long idFamille){
        return null;
    }
    
    //TO DO - Supprime un champ SousFamille dans le XML
    public Boolean SupprimerSousFamille(Long idProjet, Long idSousFamille){
        return null;
    }
    
    //TO DO - Supprime un descriptif. Attention prestation: s'il n'y plus de prestation dans un ouvrage, remettre prixOuvrage
    public Boolean SupprimerDescriptif(Long idProjet, Long idDescriptif){
        return null;
    }
    
    //TO DO - Supprime un champ Localisation ainsi qu'un champ Quantite dans le XML
    public Boolean SupprimerLigneChiffrage(Long idProjet, Long idDescriptif){
        return null;
    }
    
    //TO DO - Modifie la descripition seulement dans le XML
    public Boolean ModifierDescriptionDescriptif(Long idProjet, Long idDescriptif, String newDescription){
        return null;
    }
    
    //TO DO - Modifie la localisation seulement dans le XML
    public Boolean ModifierLocalisationDescriptif(Long idProjet, Long idDescriptif, Long idLigneChiffrage, String newLocalisation){
        return null;
    }
    
    //TO DO - Modifie la quté seulement dans le XML
    public Boolean ModifierQuantiteDescriptif(Long idProjet, Long idDescriptif, Long idLigneChiffrage, Double quantite){
        return null;
    }
}