package fr.etic.brp.brp_back_end.metier.service;

import com.google.common.hash.Hashing;
import fr.etic.brp.brp_back_end.dao.BasePrixRefDao;
import fr.etic.brp.brp_back_end.dao.CaractDimDao;
import fr.etic.brp.brp_back_end.dao.CategorieConstructionDao;
import fr.etic.brp.brp_back_end.dao.CategorieDao;
import fr.etic.brp.brp_back_end.dao.CoeffRaccordementDao;
import fr.etic.brp.brp_back_end.dao.CorpsEtatDao;
import fr.etic.brp.brp_back_end.dao.DescriptifDao;
import fr.etic.brp.brp_back_end.dao.FamilleDao;
import fr.etic.brp.brp_back_end.dao.JpaUtil;
import fr.etic.brp.brp_back_end.dao.OperateurDao;
import fr.etic.brp.brp_back_end.dao.PrestationDao;
import fr.etic.brp.brp_back_end.dao.ProjetDao;
import fr.etic.brp.brp_back_end.dao.ProjetXMLDao;
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
import fr.etic.brp.brp_back_end.metier.modele.Generique;
import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import fr.etic.brp.brp_back_end.metier.modele.Ouvrage;
import fr.etic.brp.brp_back_end.metier.modele.Prestation;
import fr.etic.brp.brp_back_end.metier.modele.Projet;
import fr.etic.brp.brp_back_end.metier.modele.SousCategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.SousFamille;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
    
    public Descriptif RechercherDescriptifParId(String id) {
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
            
    //TO DO - Permet d'avoir une vue de synthèse des couts à chaque étage de l'arborescence
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
    
    public Boolean AjouterCategorie(Long idProjet, Long idCorpsEtat, Long idCategorie){
        JpaUtil.creerContextePersistance();
        Boolean resultat = false;
        Boolean testInsertion = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            NodeList rootNodes = xml.getElementsByTagName("corpsEtat");
            //Création balise categorie
            Element baliseCategorie = xml.createElement("categorie");
            baliseCategorie.setAttribute("idCategorie", idCategorie.toString());
            //Création de la balise intitule
            Element baliseIntitule = xml.createElement("intitule");
            Categorie categorie = categorieDao.ChercherParId(idCategorie);
            baliseIntitule.appendChild(xml.createTextNode(categorie.getIntituleCategorie()));
   
            baliseCategorie.appendChild(baliseIntitule);
            
            //on parcours les corpsEtat 
            for (int i = 0; i<rootNodes.getLength(); i++) {
                Element corpsEtat = (Element) rootNodes.item(i);
                if(corpsEtat.getAttribute("idCorpsEtat").equals(idCorpsEtat.toString())){
                    //on est dans le bon corpsEtat, on peut y insérer la categorie
                    rootNodes.item(i).appendChild(baliseCategorie);
                    testInsertion = true;
                    break;
                }			
            }
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testInsertion){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterCategorie(Long idProjet, Long idCategorie)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Boolean AjouterFamille(Long idProjet, Long idCategorie, Long idFamille){
        JpaUtil.creerContextePersistance();
        Boolean resultat = false;
        Boolean testInsertion = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            NodeList rootNodes = xml.getElementsByTagName("categorie");
            //Création balise famille
            Element baliseFamille = xml.createElement("famille");
            baliseFamille.setAttribute("idFamille", idFamille.toString());
            //Création de la balise intitule
            Element baliseIntitule = xml.createElement("intitule");
            Famille famille = familleDao.ChercherParId(idFamille);
            baliseIntitule.appendChild(xml.createTextNode(famille.getIntituleFamille()));
   
            baliseFamille.appendChild(baliseIntitule);
            
            //on parcours les categories
            for (int i = 0; i<rootNodes.getLength(); i++) {
                Element categorie = (Element) rootNodes.item(i);
                if(categorie.getAttribute("idCategorie").equals(idCategorie.toString())){
                    //on est dans la bonne categorie, on insère la famille
                    rootNodes.item(i).appendChild(baliseFamille);
                    testInsertion = true;
                    break;
                }			
            }
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testInsertion){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterCategorie(Long idProjet, Long idCategorie)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Boolean AjouterSousFamille(Long idProjet, Long idFamille, Long idSousFamille){
        JpaUtil.creerContextePersistance();
        Boolean resultat = false;
        Boolean testInsertion = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            NodeList rootNodes = xml.getElementsByTagName("famille");
            //Création balise sousFamille
            Element baliseSousFamille = xml.createElement("sousFamille");
            baliseSousFamille.setAttribute("idSousFamille", idSousFamille.toString());
            //Création de la balise intitule
            Element baliseIntitule = xml.createElement("intitule");
            SousFamille sousFamille = sousFamilleDao.ChercherParId(idSousFamille);
            baliseIntitule.appendChild(xml.createTextNode(sousFamille.getIntituleSousFamille()));
   
            baliseSousFamille.appendChild(baliseIntitule);
            
            //on parcours les familles
            for (int i = 0; i<rootNodes.getLength(); i++) {
                Element famille = (Element) rootNodes.item(i);
                if(famille.getAttribute("idFamille").equals(idFamille.toString())){
                    //on est dans la bonne famille
                    rootNodes.item(i).appendChild(baliseSousFamille);
                    testInsertion = true;
                    break;
                }			
            }
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testInsertion){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterCategorie(Long idProjet, Long idCategorie)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    
    //REMOVE?
    public Boolean AjouterOuvrageOuGenerique(Long idProjet, Long idSousFamille, String idDescriptif){
        JpaUtil.creerContextePersistance();
        Boolean resultat = false;
        Boolean testInsertion = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            NodeList rootNodes = xml.getElementsByTagName("sousFamille");
            Element baliseDescriptif = null;
            
            //on récupère le descriptif
            Descriptif descriptif = descriptifDao.ChercherParId(idDescriptif); 
            if(descriptif instanceof Generique){
                //Création balise descriptif
                baliseDescriptif = xml.createElement("generique");
            }
            else{
                baliseDescriptif = xml.createElement("ouvrage");
            }
            baliseDescriptif.setAttribute("idDescriptif", idDescriptif);
            
            //Création des enfants de descriptif
            Element baliseNomDescriptif = xml.createElement("nomDescriptif");                                                                       
            baliseNomDescriptif.appendChild(xml.createTextNode(descriptif.getNomDescriptif())); 
            Element baliseDescription = xml.createElement("description");                                                                       
            baliseDescription.appendChild(xml.createTextNode(descriptif.getDescription())); 
            Element baliseCourteDescription = xml.createElement("courteDescription");                                                                      
            baliseCourteDescription.appendChild(xml.createTextNode(descriptif.getCourteDescription())); 
            //Remplissage de la balise descriptif
            baliseDescriptif.appendChild(baliseNomDescriptif);    
            baliseDescriptif.appendChild(baliseDescription); 
            baliseDescriptif.appendChild(baliseCourteDescription); 
            
            //si c'est un ouvrage, on ajoute en plus tout ce qui est relatif aux prix
            if(descriptif instanceof Ouvrage){
                Integer annee_max = 0;
                int indiceRef = -1;
                Double quantite = 1.0;
                
                Ouvrage ouvrage = (Ouvrage) descriptifDao.ChercherParId(idDescriptif); 
                List<BasePrixRef> listeBasePrixRef = ouvrage.getListeBasePrixRefOuvrage();          
                
                for(int i = 0; i<listeBasePrixRef.size(); i++){
                    if(listeBasePrixRef.get(i).getQteInf() <= quantite && listeBasePrixRef.get(i).getQteSup() >= quantite){
                        //on se trouve dans la bonne fourchette de quantite, on test l'annee
                        if(listeBasePrixRef.get(i).getAnnee() > annee_max){
                            annee_max = listeBasePrixRef.get(i).getAnnee();
                            indiceRef = i;
                        }
                    }
                }
                
                Element baliseUnite = xml.createElement("unite");                                                                       
                baliseUnite.appendChild(xml.createTextNode(listeBasePrixRef.get(indiceRef).getUnite())); 
                Element balisePrixUnitaire = xml.createElement("prixUnitaire");                                                                       
                balisePrixUnitaire.appendChild(xml.createTextNode(listeBasePrixRef.get(indiceRef).getPrixUnitaire().toString())); 
                
                Element baliseLigneChiffrage = xml.createElement("ligneChiffrage"); 
                baliseLigneChiffrage.setAttribute("idLigneChiffrage", "1");
                Element baliseLocalisation = xml.createElement("localisation"); 
                Element baliseQuantite = xml.createElement("quantite"); 
                baliseQuantite.appendChild(xml.createTextNode(quantite.toString()));
                baliseLigneChiffrage.appendChild(baliseLocalisation); 
                baliseLigneChiffrage.appendChild(baliseQuantite);
               
                baliseDescriptif.appendChild(baliseUnite);    
                baliseDescriptif.appendChild(balisePrixUnitaire); 
                baliseDescriptif.appendChild(baliseLigneChiffrage); 
            }
            
            //on parcours les sousFamilles
            for (int i = 0; i<rootNodes.getLength(); i++) {
                Element sousFamille = (Element) rootNodes.item(i);
                if(sousFamille.getAttribute("idSousFamille").equals(idSousFamille.toString())){
                    //on est dans la bonne sousFamille
                    rootNodes.item(i).appendChild(baliseDescriptif);
                    testInsertion = true;
                    break;
                }			
            }
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testInsertion){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterCategorie(Long idProjet, Long idCategorie)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Boolean AjouterOuvrageOuGenerique2(Long idProjet, Long idSousFamille, String idDescriptif){
        JpaUtil.creerContextePersistance();
        Boolean resultat = false;
        Boolean testInsertion = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            NodeList rootNodes = xml.getElementsByTagName("sousFamille");
            Element baliseDescriptif = null;
            
            //on récupère le descriptif
            Descriptif descriptif = descriptifDao.ChercherParId(idDescriptif); 
            baliseDescriptif = xml.createElement("descriptif");
            baliseDescriptif.setAttribute("idDescriptif", idDescriptif);
            
            //Création des enfants de descriptif
            Element baliseNomDescriptif = xml.createElement("nomDescriptif");                                                                       
            baliseNomDescriptif.appendChild(xml.createTextNode(descriptif.getNomDescriptif())); 
            Element baliseDescription = xml.createElement("description");                                                                       
            baliseDescription.appendChild(xml.createTextNode(descriptif.getDescription())); 
            Element baliseCourteDescription = xml.createElement("courteDescription");                                                                      
            baliseCourteDescription.appendChild(xml.createTextNode(descriptif.getCourteDescription())); 
            //Remplissage de la balise descriptif
            baliseDescriptif.appendChild(baliseNomDescriptif);    
            baliseDescriptif.appendChild(baliseDescription); 
            baliseDescriptif.appendChild(baliseCourteDescription); 
            
            //si c'est un ouvrage, on ajoute en plus tout ce qui est relatif aux prix
            if(descriptif instanceof Ouvrage){
                Integer annee_max = 0;
                int indiceRef = -1;
                Double quantite = 1.0;
                
                Ouvrage ouvrage = (Ouvrage) descriptifDao.ChercherParId(idDescriptif); 
                List<BasePrixRef> listeBasePrixRef = ouvrage.getListeBasePrixRefOuvrage();          
                
                for(int i = 0; i<listeBasePrixRef.size(); i++){
                    if(listeBasePrixRef.get(i).getQteInf() <= quantite && listeBasePrixRef.get(i).getQteSup() >= quantite){
                        //on se trouve dans la bonne fourchette de quantite, on test l'annee
                        if(listeBasePrixRef.get(i).getAnnee() > annee_max){
                            annee_max = listeBasePrixRef.get(i).getAnnee();
                            indiceRef = i;
                        }
                    }
                }
                
                Element baliseUnite = xml.createElement("unite");                                                                       
                baliseUnite.appendChild(xml.createTextNode(listeBasePrixRef.get(indiceRef).getUnite())); 
                Element balisePrixUnitaire = xml.createElement("prixUnitaire");                                                                       
                balisePrixUnitaire.appendChild(xml.createTextNode(listeBasePrixRef.get(indiceRef).getPrixUnitaire().toString())); 
                
                Element baliseLigneChiffrage = xml.createElement("ligneChiffrage"); 
                baliseLigneChiffrage.setAttribute("idLigneChiffrage", "1");
                Element baliseLocalisation = xml.createElement("localisation"); 
                Element baliseQuantite = xml.createElement("quantite"); 
                baliseQuantite.appendChild(xml.createTextNode(quantite.toString()));
                baliseLigneChiffrage.appendChild(baliseLocalisation); 
                baliseLigneChiffrage.appendChild(baliseQuantite);
               
                baliseDescriptif.appendChild(baliseUnite);    
                baliseDescriptif.appendChild(balisePrixUnitaire); 
                baliseDescriptif.appendChild(baliseLigneChiffrage); 
            }
            
            //on parcours les sousFamilles
            for (int i = 0; i<rootNodes.getLength(); i++) {
                Element sousFamille = (Element) rootNodes.item(i);
                if(sousFamille.getAttribute("idSousFamille").equals(idSousFamille.toString())){
                    //on est dans la bonne sousFamille
                    rootNodes.item(i).appendChild(baliseDescriptif);
                    testInsertion = true;
                    break;
                }			
            }
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testInsertion){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterCategorie(Long idProjet, Long idCategorie)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Boolean AjouterPrestation(Long idProjet, String idDescriptif, String idPrestation){
        JpaUtil.creerContextePersistance();
        Boolean resultat = false;
        Boolean testInsertion = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            NodeList rootNodes = xml.getElementsByTagName("ouvrage");
            Element balisePrestation = null;
            
            //on récupère la prestation
            Prestation prestation = prestationDao.ChercherParId(idPrestation); 
            balisePrestation = xml.createElement("prestation");
            balisePrestation.setAttribute("idDescriptif", idPrestation);
            
            //Création des enfants de prestation
            Element baliseNomDescriptif = xml.createElement("nomDescriptif");                                                                       
            baliseNomDescriptif.appendChild(xml.createTextNode(prestation.getNomDescriptif())); 
            Element baliseDescription = xml.createElement("description");                                                                       
            baliseDescription.appendChild(xml.createTextNode(prestation.getDescription())); 
            Element baliseCourteDescription = xml.createElement("courteDescription");                                                                      
            baliseCourteDescription.appendChild(xml.createTextNode(prestation.getCourteDescription())); 
            //Remplissage de la balise descriptif
            balisePrestation.appendChild(baliseNomDescriptif);    
            balisePrestation.appendChild(baliseDescription); 
            balisePrestation.appendChild(baliseCourteDescription);   
     
            //on ajoute tout ce qui est relatif aux prix
            Integer annee_max = 0;
            int indiceRef = -1;
            Double quantite = 1.0;

            List<BasePrixRef> listeBasePrixRef = prestation.getListeBasePrixRefPrestation();          

            for(int i = 0; i<listeBasePrixRef.size(); i++){
                if(listeBasePrixRef.get(i).getQteInf() <= quantite && listeBasePrixRef.get(i).getQteSup() >= quantite){
                    //on se trouve dans la bonne fourchette de quantite, on test l'annee
                    if(listeBasePrixRef.get(i).getAnnee() > annee_max){
                        annee_max = listeBasePrixRef.get(i).getAnnee();
                        indiceRef = i;
                    }
                }
            }

            Element baliseUnite = xml.createElement("unite");                                                                       
            baliseUnite.appendChild(xml.createTextNode(listeBasePrixRef.get(indiceRef).getUnite())); 
            Element balisePrixUnitaire = xml.createElement("prixUnitaire");                                                                       
            balisePrixUnitaire.appendChild(xml.createTextNode(listeBasePrixRef.get(indiceRef).getPrixUnitaire().toString())); 

            Element baliseLigneChiffrage = xml.createElement("ligneChiffrage"); 
            baliseLigneChiffrage.setAttribute("idLigneChiffrage", "1");
            Element baliseLocalisation = xml.createElement("localisation"); 
            Element baliseQuantite = xml.createElement("quantite"); 
            baliseQuantite.appendChild(xml.createTextNode(quantite.toString()));
            baliseLigneChiffrage.appendChild(baliseLocalisation); 
            baliseLigneChiffrage.appendChild(baliseQuantite);

            balisePrestation.appendChild(baliseUnite);    
            balisePrestation.appendChild(balisePrixUnitaire); 
            balisePrestation.appendChild(baliseLigneChiffrage);
            
            //on parcours les ouvrages
            for (int i = 0; i<rootNodes.getLength(); i++) {
                Element ouvrage = (Element) rootNodes.item(i);
                if(ouvrage.getAttribute("idDescriptif").equals(idDescriptif)){
                    //on est dans le bon ouvrage. On supprime d'éventuelles infos liées aux prix
                    NodeList nodeUnite = ouvrage.getElementsByTagName("unite");
                    if(nodeUnite != null){
                        Element unite = (Element) nodeUnite.item(0);
                        rootNodes.item(i).removeChild(unite);
                    }
                    NodeList nodePrixUnitaire = ouvrage.getElementsByTagName("prixUnitaire");
                    if(nodePrixUnitaire != null){
                        Element prixUnitaire = (Element) nodePrixUnitaire.item(0);
                        rootNodes.item(i).removeChild(prixUnitaire);
                    }
                    NodeList nodeListeLigneChiffrage = ouvrage.getElementsByTagName("ligneChiffrage");
                    if(nodeListeLigneChiffrage != null){
                        for(int j=0; j < nodeListeLigneChiffrage.getLength(); j++){
                            Element ligneChiffrage = (Element) nodeListeLigneChiffrage.item(j);
                            rootNodes.item(i).removeChild(ligneChiffrage);
                        }
                    }
                    
                    rootNodes.item(i).appendChild(balisePrestation);
                    testInsertion = true;
                    break;
                }			
            }
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testInsertion){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterCategorie(Long idProjet, Long idCategorie)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Boolean AjouterPrestation2(Long idProjet, String idDescriptif, String idPrestation){
        JpaUtil.creerContextePersistance();
        Boolean resultat = false;
        Boolean testInsertion = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            NodeList rootNodes = xml.getElementsByTagName("descriptif");
            Element balisePrestation = null;
            
            //on récupère la prestation
            Prestation prestation = prestationDao.ChercherParId(idPrestation); 
            balisePrestation = xml.createElement("prestation");
            balisePrestation.setAttribute("idDescriptif", idPrestation);
            
            //Création des enfants de prestation
            Element baliseNomDescriptif = xml.createElement("nomDescriptif");                                                                       
            baliseNomDescriptif.appendChild(xml.createTextNode(prestation.getNomDescriptif())); 
            Element baliseDescription = xml.createElement("description");                                                                       
            baliseDescription.appendChild(xml.createTextNode(prestation.getDescription())); 
            Element baliseCourteDescription = xml.createElement("courteDescription");                                                                      
            baliseCourteDescription.appendChild(xml.createTextNode(prestation.getCourteDescription())); 
            //Remplissage de la balise descriptif
            balisePrestation.appendChild(baliseNomDescriptif);    
            balisePrestation.appendChild(baliseDescription); 
            balisePrestation.appendChild(baliseCourteDescription);   
     
            //on ajoute tout ce qui est relatif aux prix
            Integer annee_max = 0;
            int indiceRef = -1;
            Double quantite = 1.0;

            List<BasePrixRef> listeBasePrixRef = prestation.getListeBasePrixRefPrestation();          

            for(int i = 0; i<listeBasePrixRef.size(); i++){
                if(listeBasePrixRef.get(i).getQteInf() <= quantite && listeBasePrixRef.get(i).getQteSup() >= quantite){
                    //on se trouve dans la bonne fourchette de quantite, on test l'annee
                    if(listeBasePrixRef.get(i).getAnnee() > annee_max){
                        annee_max = listeBasePrixRef.get(i).getAnnee();
                        indiceRef = i;
                    }
                }
            }

            Element baliseUnite = xml.createElement("unite");                                                                       
            baliseUnite.appendChild(xml.createTextNode(listeBasePrixRef.get(indiceRef).getUnite())); 
            Element balisePrixUnitaire = xml.createElement("prixUnitaire");                                                                       
            balisePrixUnitaire.appendChild(xml.createTextNode(listeBasePrixRef.get(indiceRef).getPrixUnitaire().toString())); 

            Element baliseLigneChiffrage = xml.createElement("ligneChiffrage"); 
            baliseLigneChiffrage.setAttribute("idLigneChiffrage", "1");
            Element baliseLocalisation = xml.createElement("localisation"); 
            Element baliseQuantite = xml.createElement("quantite"); 
            baliseQuantite.appendChild(xml.createTextNode(quantite.toString()));
            baliseLigneChiffrage.appendChild(baliseLocalisation); 
            baliseLigneChiffrage.appendChild(baliseQuantite);

            balisePrestation.appendChild(baliseUnite);    
            balisePrestation.appendChild(balisePrixUnitaire); 
            balisePrestation.appendChild(baliseLigneChiffrage);
            
            //on parcours les descriptifs
            for (int i = 0; i<rootNodes.getLength(); i++) {
                Element descriptif = (Element) rootNodes.item(i);
                if(descriptif.getAttribute("idDescriptif").equals(idDescriptif)){
                    //on est dans le bon ouvrage. On supprime d'éventuelles infos liées aux prix
                    NodeList nodeUnite = descriptif.getElementsByTagName("unite");
                    if(nodeUnite != null){
                        Element unite = (Element) nodeUnite.item(0);
                        rootNodes.item(i).removeChild(unite);
                    }
                    NodeList nodePrixUnitaire = descriptif.getElementsByTagName("prixUnitaire");
                    if(nodePrixUnitaire != null){
                        Element prixUnitaire = (Element) nodePrixUnitaire.item(0);
                        rootNodes.item(i).removeChild(prixUnitaire);
                    }
                    NodeList nodeListeLigneChiffrage = descriptif.getElementsByTagName("ligneChiffrage");
                    if(nodeListeLigneChiffrage != null){
                        for(int j=0; j < nodeListeLigneChiffrage.getLength(); j++){
                            Element ligneChiffrage = (Element) nodeListeLigneChiffrage.item(j);
                            rootNodes.item(i).removeChild(ligneChiffrage);
                        }
                    }
                    
                    rootNodes.item(i).appendChild(balisePrestation);
                    testInsertion = true;
                    break;
                }			
            }
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testInsertion){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterCategorie(Long idProjet, Long idCategorie)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    //TO DO - Ajoute un champ Localisation ainsi qu'un champ Quantite dans le XML
    public Boolean AjouterLigneChiffrage(Long idProjet, Long idDescriptif){
//        JpaUtil.creerContextePersistance();
//        Boolean resultat = false;
//        Boolean testInsertion = false;
//        
//        try {
//            //Obtention du document
//            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
//            Document xml = projetXMLDao.ObtenirDocument(uri);
//            NodeList rootNodes = xml.getElementsByTagName("ouvrage");
//            Element balisePrestation = null;
//            
//            //on récupère la prestation
//            Prestation prestation = prestationDao.ChercherParId(idPrestation); 
//            balisePrestation = xml.createElement("prestation");
//            balisePrestation.setAttribute("idDescriptif", idPrestation);
//            
//            //Création de la balise ligne chiffrage
//            Element baliseNomDescriptif = xml.createElement("nomDescriptif");                                                                       
//            baliseNomDescriptif.appendChild(xml.createTextNode(prestation.getNomDescriptif())); 
//            Element baliseDescription = xml.createElement("description");                                                                       
//            baliseDescription.appendChild(xml.createTextNode(prestation.getDescription())); 
//            Element baliseCourteDescription = xml.createElement("courteDescription");                                                                      
//            baliseCourteDescription.appendChild(xml.createTextNode(prestation.getCourteDescription())); 
//            //Remplissage de la balise descriptif
//            balisePrestation.appendChild(baliseNomDescriptif);    
//            balisePrestation.appendChild(baliseDescription); 
//            balisePrestation.appendChild(baliseCourteDescription);   
//     
//           
//            
//            //on parcours les ouvrages
//            for (int i = 0; i<rootNodes.getLength(); i++) {
//                Element ouvrage = (Element) rootNodes.item(i);
//                if(ouvrage.getAttribute("idDescriptif").equals(idDescriptif)){
//                    //on est dans le bon ouvrage. On supprime d'éventuelles infos liées aux prix
//                    NodeList nodeUnite = ouvrage.getElementsByTagName("unite");
//                    if(nodeUnite != null){
//                        Element unite = (Element) nodeUnite.item(0);
//                        rootNodes.item(i).removeChild(unite);
//                    }
//                    NodeList nodePrixUnitaire = ouvrage.getElementsByTagName("prixUnitaire");
//                    if(nodePrixUnitaire != null){
//                        Element prixUnitaire = (Element) nodePrixUnitaire.item(0);
//                        rootNodes.item(i).removeChild(prixUnitaire);
//                    }
//                    NodeList nodeListeLigneChiffrage = ouvrage.getElementsByTagName("ligneChiffrage");
//                    if(nodeListeLigneChiffrage != null){
//                        for(int j=0; j < nodeListeLigneChiffrage.getLength(); j++){
//                            Element ligneChiffrage = (Element) nodeListeLigneChiffrage.item(j);
//                            rootNodes.item(i).removeChild(ligneChiffrage);
//                        }
//                    }
//                    
//                    rootNodes.item(i).appendChild(balisePrestation);
//                    testInsertion = true;
//                    break;
//                }			
//            }
//            
//            //On écrit par dessus l'ancien XML
//            projetXMLDao.saveXMLContent(xml, uri);
//            
//            if(testInsertion){
//                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
//            }
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterCategorie(Long idProjet, Long idCategorie)", ex);
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//        return resultat;
        return null;
    }
    
    //TO DO - Mettre a jour la correspondance de l'id -> Dans les TESTS du main !
    public Boolean SupprimerCorpsEtat(Long idProjet, Long idCorpsEtat){
        Boolean testSuppression = false;
        Boolean resultat = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            NodeList listNodes = xml.getElementsByTagName("corpsEtat");
            //on parcours la liste des corps d'Etats à le recherche de celui à éliminer
            for (int i = 0; i < listNodes.getLength(); i++) {
                Element corpsEtat = (Element) listNodes.item(i);
                if(corpsEtat.getAttribute("idCorpsEtat").equals(idCorpsEtat.toString())){
                    corpsEtat.getParentNode().removeChild(corpsEtat);
                    testSuppression = true;
                }
            }
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testSuppression){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service SupprimerCorpsEtat(Long idProjet, Long idCorpsEtat)", ex);
        }
        return resultat;
    }
    
    //TO DO - Mettre a jour la correspondance de l'id -> Dans les TESTS du main !
    public Boolean SupprimerCategorie(Long idProjet, Long idCategorie){
        Boolean testSuppression = false;
        Boolean resultat = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            NodeList listNodes = xml.getElementsByTagName("categorie");
            //on parcours la liste à le recherche de celui à éliminer
            for (int i = 0; i < listNodes.getLength(); i++) {
                Element categorie = (Element) listNodes.item(i);
                if(categorie.getAttribute("idCategorie").equals(idCategorie.toString())){
                    categorie.getParentNode().removeChild(categorie);
                    testSuppression = true;
                }
            }
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testSuppression){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service SupprimerCategorie(Long idProjet, Long idCategorie)", ex);
        }
        return resultat;
    }
    
    //TO DO - Mettre a jour la correspondance de l'id -> Dans les TESTS du main !
    public Boolean SupprimerFamille(Long idProjet, Long idFamille){
        Boolean testSuppression = false;
        Boolean resultat = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            NodeList listNodes = xml.getElementsByTagName("famille");
            //on parcours la liste à le recherche de celui à éliminer
            for (int i = 0; i < listNodes.getLength(); i++) {
                Element famille = (Element) listNodes.item(i);
                if(famille.getAttribute("idFamille").equals(idFamille.toString())){
                    famille.getParentNode().removeChild(famille);
                    testSuppression = true;
                }
            }
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testSuppression){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service SupprimerFamille(Long idProjet, Long idFamille)", ex);
        }
        return resultat;
    }
    
    //TO DO - Mettre a jour la correspondance de l'id -> Dans les TESTS du main !
    public Boolean SupprimerSousFamille(Long idProjet, Long idSousFamille){
        Boolean testSuppression = false;
        Boolean resultat = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            NodeList listNodes = xml.getElementsByTagName("sousFamille");
            //on parcours la liste à le recherche de celui à éliminer
            for (int i = 0; i < listNodes.getLength(); i++) {
                Element sousFamille = (Element) listNodes.item(i);
                if(sousFamille.getAttribute("idSousFamille").equals(idSousFamille.toString())){
                    sousFamille.getParentNode().removeChild(sousFamille);
                    testSuppression = true;
                }
            }
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testSuppression){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service SupprimerSousFamille(Long idProjet, Long idSousFamille)", ex);
        }
        return resultat;
    }
    
    //TO DO - Mettre a jour la correspondance de l'id -> Dans les TESTS du main !
    public Boolean SupprimerDescriptif(Long idProjet, String idDescriptif){
        JpaUtil.creerContextePersistance();
        Boolean testSuppression = false;
        Boolean resultat = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            NodeList listeSousFamille = xml.getElementsByTagName("sousFamille"); //On doit parcourir les sousFamilles car pas de balise descriptif
            Boolean testPrestation = false;
            Element ouvrageEnfantDetruit = null;
            //on parcours la liste à le recherche de celui à éliminer
            for (int i = 0; i < listeSousFamille.getLength(); i++) {
                NodeList enfantsSousFamille = listeSousFamille.item(i).getChildNodes();
                for(int j = 0; j < enfantsSousFamille.getLength(); j++){
                    Element descriptif = (Element) enfantsSousFamille.item(j);
                    if(descriptif.getAttribute("idDescriptif").equals(idDescriptif)){ //Si ça match direct alors Generique ou Ouvrage -> pas de test supplémentaire
                        descriptif.getParentNode().removeChild(descriptif);
                        testSuppression = true;
                    } else if (enfantsSousFamille.item(j).getNodeName().equals("ouvrage")){
                        NodeList enfantsOuvrage = enfantsSousFamille.item(j).getChildNodes();
                        for(int k = 0; k < enfantsOuvrage.getLength(); k++){
                            Element prestation = (Element) enfantsOuvrage.item(k);
                            if(prestation.getAttribute("idDescriptif").equals(idDescriptif)){
                                ouvrageEnfantDetruit = (Element) enfantsSousFamille.item(j);
                                prestation.getParentNode().removeChild(prestation);
                                testPrestation = true; //On devra donc checker si c'est la dernière prestation
                            }
                        }   
                    }
                }    
            }
            
            //Si prestation supprimée -> s'il n'y plus de prestation dans un ouvrage, remettre les infos BasePrixRef
            if(testPrestation && ouvrageEnfantDetruit != null){
                testSuppression = false;
                NodeList listeEnfantOuvrage = ouvrageEnfantDetruit.getChildNodes();
                if(listeEnfantOuvrage.getLength() == 3){ //S'il ne reste plus que nomDescriptif / description / courteDescription
                    //On remet donc les balises unite, prixUnitaire et une ligneChiffrage dans le XML
                    Integer annee_max = 0;
                    int indiceRef = -1;
                    Double quantite = 1.0;
                    
                    Ouvrage ouvrage = (Ouvrage) descriptifDao.ChercherParId(ouvrageEnfantDetruit.getAttribute("idDescriptif"));
                    List<BasePrixRef> listeBasePrixRef = ouvrage.getListeBasePrixRefOuvrage();        

                    for(int l = 0; l < listeBasePrixRef.size(); l++){
                        if(listeBasePrixRef.get(l).getQteInf() <= quantite && listeBasePrixRef.get(l).getQteSup() >= quantite){
                            //on se trouve dans la bonne fourchette de quantite, on test l'annee
                            if(listeBasePrixRef.get(l).getAnnee() > annee_max){
                                annee_max = listeBasePrixRef.get(l).getAnnee();
                                indiceRef = l;
                            }
                        }
                    }

                    //Balise unite
                    Element baliseUnite = xml.createElement("unite");                                                                       
                    baliseUnite.appendChild(xml.createTextNode(listeBasePrixRef.get(indiceRef).getUnite()));

                    //Balise prixUnitaire
                    Element balisePrixUnitaire = xml.createElement("prixUnitaire");                                                                       
                    balisePrixUnitaire.appendChild(xml.createTextNode(listeBasePrixRef.get(indiceRef).getPrixUnitaire().toString())); 

                    //Balise ligneChiffrage
                    Element baliseLigneChiffrage = xml.createElement("ligneChiffrage"); 
                    baliseLigneChiffrage.setAttribute("idLigneChiffrage", "1");
                    //Balise localisation
                    Element baliseLocalisation = xml.createElement("localisation");
                    //Balise quantite
                    Element baliseQuantite = xml.createElement("quantite");
                    baliseQuantite.appendChild(xml.createTextNode(quantite.toString()));

                    baliseLigneChiffrage.appendChild(baliseLocalisation); 
                    baliseLigneChiffrage.appendChild(baliseQuantite);


                    Element baliseOuvrage = (Element) ouvrageEnfantDetruit;
                    baliseOuvrage.appendChild(baliseUnite);    
                    baliseOuvrage.appendChild(balisePrixUnitaire); 
                    baliseOuvrage.appendChild(baliseLigneChiffrage); 
                }   
                    testSuppression = true;
            }
            
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testSuppression){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service SupprimerDescriptif(Long idProjet, Long idDescriptif)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    //TO DO - A Tester une fois qu'ajouterLigneChiffrage sera faite
    public Boolean SupprimerLigneChiffrage(Long idProjet, String idDescriptif, String idLigneChiffrage){
        Boolean testSuppression = false;
        Boolean resultat = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            NodeList listeSousFamille = xml.getElementsByTagName("sousFamille"); //On doit parcourir les sousFamilles car pas de balise descriptif
            Element ligneChiffrageASupprimer = null;
            //Element descriptifLigneASupprimer = null;
            int nbLigneChiffrage = 0;
            //on parcours la liste à la recherche de celui à éliminer
            for (int i = 0; i < listeSousFamille.getLength(); i++) {
                NodeList enfantsSousFamille = listeSousFamille.item(i).getChildNodes();
                for(int j = 0; j < enfantsSousFamille.getLength(); j++){
                    Element descriptif = (Element) enfantsSousFamille.item(j);
                    if(descriptif.getAttribute("idDescriptif").equals(idDescriptif)){ //Si ça match direct alors Ouvrage -> On va chercher la ligneChiffrage
                        NodeList enfantsOuvrage = enfantsSousFamille.item(j).getChildNodes();
                        for(int k = 0; k < enfantsOuvrage.getLength(); k++){
                            if(enfantsOuvrage.item(k).getNodeName().equals("ligneChiffrage")){
                                nbLigneChiffrage++; //Utile pour savoir si c'est pas la dernière ligneChiffrage
                                Element ligneChiffrage = (Element) enfantsOuvrage.item(k);
                                if(ligneChiffrage.getAttribute("idLigneChiffrage").equals(idLigneChiffrage)){   
                                    ligneChiffrageASupprimer = ligneChiffrage;
                                    //descriptifLigneASupprimer = (Element) enfantsSousFamille.item(i);
                                }
                            }
                        }
                    } else if (enfantsSousFamille.item(j).getNodeName().equals("ouvrage")){
                        NodeList enfantsOuvrage = enfantsSousFamille.item(j).getChildNodes();
                        for(int m = 0; m < enfantsOuvrage.getLength(); m++){
                            if(enfantsOuvrage.item(m).getNodeName().equals("prestation")){
                                Element prestationAnalyser = (Element) enfantsOuvrage.item(m);
                                if(prestationAnalyser.getAttribute("idDescriptif").equals(idDescriptif)){
                                   NodeList enfantsPrestation = enfantsOuvrage.item(m).getChildNodes();
                                    for(int l = 0; l < enfantsPrestation.getLength(); l++){
                                        if(enfantsPrestation.item(l).getNodeName().equals("ligneChiffrage")){
                                           nbLigneChiffrage++;
                                           Element ligneChiffrage = (Element) enfantsPrestation.item(l);
                                            if(ligneChiffrage.getAttribute("idLigneChiffrage").equals(idLigneChiffrage)){
                                                ligneChiffrageASupprimer = ligneChiffrage;
                                                //descriptifLigneASupprimer = (Element) enfantsOuvrage.item(m); 
                                            }
                                        }
                                    } 
                                }
                            }
                        }   
                    }
                }    
            }
            
            //On vérifie qu'il ne s'agit pas de la dernière ligne avant de supprimer
            if(nbLigneChiffrage > 1 && ligneChiffrageASupprimer != null){
                ligneChiffrageASupprimer.getParentNode().removeChild(ligneChiffrageASupprimer);
                testSuppression = true;
            } else {
                System.out.println("Il ne reste qu'une ligne !");
            }

            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testSuppression){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service SupprimerLigneChiffrage(Long idProjet, String idDescriptif, String idLigneChiffrage)", ex);
        }
        return resultat;
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