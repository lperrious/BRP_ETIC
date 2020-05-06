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
    public Double CoutSynthese(Long idProjet, String typeBalise, String idBalise) {
        
        Double total = null;
        Double quantite = 0.0;
        Double prixUnitaire = 0.0;
        JpaUtil.creerContextePersistance();
        try {
            Projet projet = projetDao.ChercherParId(idProjet);
            
            //recuperer coeffAdapt & coeffRaccordement dans la table projet
            Float coeffAdapt = projet.getCoeffAdapt();
            Double coeffraccordement = projet.getCoeffRaccordement().getValeur();
            
            //on se positionne dans le corps souhaite
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            NodeList rootNodes = xml.getElementsByTagName(typeBalise);
                    
            String attribut = "id".concat(typeBalise.replaceFirst(".",(typeBalise.charAt(0)+"").toUpperCase()));   //on met l'attribut au bon format
            
            //on parcours les balises selectionnées 
            for (int i = 0; i<rootNodes.getLength(); i++) {
                Element balise = (Element) rootNodes.item(i);
                if(balise.getAttribute(attribut).equals(idBalise)){
                    total = 0.0;        //si on est ici c'est que la balise existe, on initialise le prix à 0€
                    //on est dans la bonne balise, on récupère toutes les lignes chiffrage
                    NodeList ligneChiffrageNodes = balise.getElementsByTagName("ligneChiffrage");
                    //on boucle les lignes chiffrage
                    for (int j = 0; j<ligneChiffrageNodes.getLength(); j++) {
                        Element ligneChiffrage = (Element) ligneChiffrageNodes.item(j);
                        
                        //Récuperer prixUnitaire et quantite dans le XML
                        NodeList quantiteNode = ligneChiffrage.getElementsByTagName("quantite");
                        NodeList prixUnitaireNode = ligneChiffrage.getElementsByTagName("prixUnitaire");
                        Element quantiteBalise = (Element) quantiteNode.item(0);
                        Element prixUnitaireBalise = (Element) prixUnitaireNode.item(0);
                        
                        quantite = Double.valueOf(quantiteBalise.getTextContent());
                        prixUnitaire = Double.valueOf(prixUnitaireBalise.getTextContent());
                          
                        //ajouter au total
                        total += coeffAdapt*coeffraccordement*quantite*prixUnitaire;
                    }
                }			
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service CoutSynthese(idProjet, typeBalise, idBalise)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return total;
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
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterFamille(Long idProjet, Long idCategorie, Long idFamille)", ex);
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
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterSousFamille(Long idProjet, Long idFamille, Long idSousFamille)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    //TO DO - si les infos ne sont pas renseigné dans la BDD alors chaine vide
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
            baliseDescriptif = xml.createElement("descriptif");
            baliseDescriptif.setAttribute("idDescriptif", idDescriptif);
            if(descriptif instanceof Generique){
                //Création balise descriptif
                baliseDescriptif.setAttribute("type", "generique");
            } else {
                baliseDescriptif.setAttribute("type", "ouvrage");
            }
            
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
                
                
                Element baliseLigneChiffrage = xml.createElement("ligneChiffrage"); 
                baliseLigneChiffrage.setAttribute("idLigneChiffrage", "1");
                Element baliseLocalisation = xml.createElement("localisation"); 
                Element baliseQuantite = xml.createElement("quantite"); 
                Element balisePrixUnitaire = xml.createElement("prixUnitaire");                                                                       
                balisePrixUnitaire.appendChild(xml.createTextNode(listeBasePrixRef.get(indiceRef).getPrixUnitaire().toString())); 
                baliseQuantite.appendChild(xml.createTextNode(quantite.toString()));
                baliseLigneChiffrage.appendChild(baliseLocalisation);
                baliseLigneChiffrage.appendChild(baliseQuantite);
                baliseLigneChiffrage.appendChild(balisePrixUnitaire);
               
                baliseDescriptif.appendChild(baliseUnite);    
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
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterOuvrageOuGenerique(Long idProjet, Long idSousFamille, String idDescriptif)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    //TO DO - si les infos ne sont pas renseigné dans la BDD alors chaine vide
    public Boolean AjouterPrestation(Long idProjet, String idDescriptif, String idPrestation){
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
            balisePrestation = xml.createElement("descriptif");
            balisePrestation.setAttribute("idDescriptif", idPrestation);
            balisePrestation.setAttribute("type", "prestation");
            
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

            Element baliseLigneChiffrage = xml.createElement("ligneChiffrage"); 
            baliseLigneChiffrage.setAttribute("idLigneChiffrage", "1");
            Element baliseLocalisation = xml.createElement("localisation"); 
            Element baliseQuantite = xml.createElement("quantite"); 
            Element balisePrixUnitaire = xml.createElement("prixUnitaire");                                                                       
            balisePrixUnitaire.appendChild(xml.createTextNode(listeBasePrixRef.get(indiceRef).getPrixUnitaire().toString())); 
            baliseQuantite.appendChild(xml.createTextNode(quantite.toString()));
            baliseLigneChiffrage.appendChild(baliseLocalisation);
            baliseLigneChiffrage.appendChild(baliseQuantite);
            baliseLigneChiffrage.appendChild(balisePrixUnitaire);

            balisePrestation.appendChild(baliseUnite);    
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
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterPrestation(Long idProjet, String idDescriptif, String idPrestation)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Boolean AjouterLigneChiffrage(Long idProjet, String idDescriptif){
        JpaUtil.creerContextePersistance();
        Boolean resultat = false;
        Boolean testInsertion = false;
        Double quantite = 1.0;
        Integer annee_max = 0;
        Integer indiceRef = -1;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            NodeList rootNodes = xml.getElementsByTagName("descriptif");
            
            //Création de ligneChiffrage
            Element baliseLigneChiffrage = xml.createElement("ligneChiffrage"); 
            Element baliseLocalisation = xml.createElement("localisation"); 
            Element baliseQuantite = xml.createElement("quantite"); 
            baliseQuantite.appendChild(xml.createTextNode(quantite.toString()));
            baliseLigneChiffrage.appendChild(baliseLocalisation); 
            baliseLigneChiffrage.appendChild(baliseQuantite); 
            
            //on parcours les descriptifs
            for (int i = 0; i<rootNodes.getLength(); i++) {
                Element descriptif = (Element) rootNodes.item(i);
                if(descriptif.getAttribute("idDescriptif").equals(idDescriptif)){
                    
                    //on compte les lignes chiffrages qu'il y a 
                    NodeList ligneChiffrageNodes = descriptif.getElementsByTagName("ligneChiffrage");
                    Integer nbLigneChiffrage = ligneChiffrageNodes.getLength()+1;
                    baliseLigneChiffrage.setAttribute("idLigneChiffrage", nbLigneChiffrage.toString());
                    
                    //on va chercher l'idDescriptif du parent
                    List<BasePrixRef> listeBasePrixRef = null;
                    if(descriptif.getAttribute("type").equals("ouvrage")){
                        Ouvrage ouvrage = (Ouvrage) descriptifDao.ChercherParId(idDescriptif); 
                        listeBasePrixRef = ouvrage.getListeBasePrixRefOuvrage(); 
                    }
                    else{
                        Prestation descriptifBD = prestationDao.ChercherParId(idDescriptif);
                        listeBasePrixRef = descriptifBD.getListeBasePrixRefPrestation(); 
                    }       
                    
                    //on va chercher le prix unitaire lié au parent
                    for(int j = 0; j<listeBasePrixRef.size(); j++){
                        if(listeBasePrixRef.get(j).getQteInf() <= quantite && listeBasePrixRef.get(j).getQteSup() >= quantite){
                            //on se trouve dans la bonne fourchette de quantite, on test l'annee
                            if(listeBasePrixRef.get(j).getAnnee() > annee_max){
                                annee_max = listeBasePrixRef.get(j).getAnnee();
                                indiceRef = j;
                            }
                        }
                    }
                    
                    Element balisePrixUnitaire = xml.createElement("prixUnitaire");                                                                       
                    balisePrixUnitaire.appendChild(xml.createTextNode(listeBasePrixRef.get(indiceRef).getPrixUnitaire().toString())); 
                    baliseLigneChiffrage.appendChild(balisePrixUnitaire);
                    
                    rootNodes.item(i).appendChild(baliseLigneChiffrage);
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
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AjouterPrestation(Long idProjet, String idDescriptif, String idPrestation)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
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
    //TO DO - si les infos ne sont pas renseigné dans la BDD alors chaine vide
    public Boolean SupprimerDescriptif(Long idProjet, String idDescriptif){
        JpaUtil.creerContextePersistance();
        Boolean testSuppression = false;
        Boolean resultat = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            NodeList listeDescriptif = xml.getElementsByTagName("descriptif");
            int nbPrestation = 0;
            //On va chercher celui à supprimer
            for(int i = 0; i < listeDescriptif.getLength(); i++){
                Element descriptif = (Element) listeDescriptif.item(i);
                if(descriptif.getAttribute("idDescriptif").equals(idDescriptif)){
                    if(descriptif.getAttribute("type").equals("prestation")){
                        //On check d'abord que ce n'est pas la dernière prestation
                        NodeList filsOuvrage = descriptif.getParentNode().getChildNodes();
                        for(int j = 0; j < filsOuvrage.getLength(); j++){
                            if(filsOuvrage.item(j).getNodeName().equals("descriptif")){
                                nbPrestation++;
                            }
                        }
                        if(nbPrestation > 1){
                            descriptif.getParentNode().removeChild(descriptif);
                        } else { //Il faut mettre les infos BasePrixRef les plus récentes dans l'ouvrage
                            Integer annee_max = 0;
                            int indiceRef = -1;
                            Double quantite = 1.0;
                            
                            Node ouvrageNode = descriptif.getParentNode();
                            Element ouvrageElement = (Element) ouvrageNode;
                            Ouvrage ouvrage = (Ouvrage) descriptifDao.ChercherParId(ouvrageElement.getAttribute("idDescriptif"));
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

                            //Balise ligneChiffrage
                            Element baliseLigneChiffrage = xml.createElement("ligneChiffrage"); 
                            baliseLigneChiffrage.setAttribute("idLigneChiffrage", "1");
                            //Balise localisation
                            Element baliseLocalisation = xml.createElement("localisation");
                            //Balise quantite
                            Element baliseQuantite = xml.createElement("quantite");
                            baliseQuantite.appendChild(xml.createTextNode(quantite.toString()));
                            //Balise prixUnitaire
                            Element balisePrixUnitaire = xml.createElement("prixUnitaire");                                                                       
                            balisePrixUnitaire.appendChild(xml.createTextNode(listeBasePrixRef.get(indiceRef).getPrixUnitaire().toString()));
                            
                            baliseLigneChiffrage.appendChild(baliseLocalisation); 
                            baliseLigneChiffrage.appendChild(baliseQuantite);
                            baliseLigneChiffrage.appendChild(balisePrixUnitaire);

                            ouvrageElement.appendChild(baliseUnite);    
                            ouvrageElement.appendChild(baliseLigneChiffrage);
                            
                            //On peut donc supprimer la prestation
                            descriptif.getParentNode().removeChild(descriptif);
                        }
                    } else {
                       descriptif.getParentNode().removeChild(descriptif); 
                    }
                    testSuppression = true;
                }
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
    
    public Boolean SupprimerLigneChiffrage(Long idProjet, String idDescriptif, String idLigneChiffrage){
        Boolean testSuppression = false;
        Boolean resultat = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            NodeList listeDescriptif = xml.getElementsByTagName("descriptif");
            int nbLigneChiffrage = 0;
            Element ligneChiffrageASupprimer = null;
            //On va cherche le descriptif dans lequel sa trouve la ligneChiffrage à supprimer
            for(int i = 0; i < listeDescriptif.getLength(); i++){
                Element baliseDescriptif = (Element) listeDescriptif.item(i);
                if(baliseDescriptif.getAttribute("idDescriptif").equals(idDescriptif)){
                    NodeList listeEnfantsDescriptif = baliseDescriptif.getChildNodes();
                    for(int j = 0; j < listeEnfantsDescriptif.getLength(); j++){
                        if(listeEnfantsDescriptif.item(j).getNodeName().equals("ligneChiffrage")){
                            nbLigneChiffrage++;
                            Element ligneChiffrage = (Element) listeEnfantsDescriptif.item(j);
                            if(ligneChiffrage.getAttribute("idLigneChiffrage").equals(idLigneChiffrage)){
                                ligneChiffrageASupprimer = ligneChiffrage;
                            }
                        }
                    }
                    if(nbLigneChiffrage > 1 && ligneChiffrageASupprimer != null){
                        ligneChiffrageASupprimer.getParentNode().removeChild(ligneChiffrageASupprimer);
                        testSuppression = true;
                    } else if(nbLigneChiffrage < 1) {
                        System.out.println("Il n'y a qu'une seule ligne");
                    }
                }
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
    
    //TO DO - si les infos ne sont pas renseigné dans la BDD alors chaine vide
    public Boolean ModifierDescriptionDescriptif(Long idProjet, String idDescriptif, String newDescription){
        Boolean testModif = false;
        Boolean resultat = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            NodeList listeDescriptif = xml.getElementsByTagName("descriptif");
            //On parcours les descriptifs pour modifier celui qui nous intéresse
            for(int i = 0; i < listeDescriptif.getLength(); i++){
                Element baliseDescriptif = (Element) listeDescriptif.item(i);
                if(baliseDescriptif.getAttribute("idDescriptif").equals(idDescriptif)){
                    //On cherche la balise description
                    NodeList listeEnfantsDescriptif = baliseDescriptif.getChildNodes();
                    for(int j = 0; j < listeEnfantsDescriptif.getLength(); j++){
                        if(listeEnfantsDescriptif.item(j).getNodeName().equals("description")){
                            //On modifie la balise description
                            Element baliseDescription = (Element) listeEnfantsDescriptif.item(j);
                            baliseDescription.setTextContent(newDescription);
                            testModif = true;
                        }
                    }
                }
            }

            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testModif){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ModifierDescriptionDescriptif(Long idProjet, String idDescriptif, String newDescription)", ex);
        }
        return resultat;
    }
    
    //TO DO - si les infos ne sont pas renseigné dans la BDD alors chaine vide
    public Boolean ModifierCourteDescriptionDescriptif(Long idProjet, String idDescriptif, String newDescription){
        Boolean testModif = false;
        Boolean resultat = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            NodeList listeDescriptif = xml.getElementsByTagName("descriptif");
            //On parcours les descriptifs pour modifier celui qui nous intéresse
            for(int i = 0; i < listeDescriptif.getLength(); i++){
                Element baliseDescriptif = (Element) listeDescriptif.item(i);
                if(baliseDescriptif.getAttribute("idDescriptif").equals(idDescriptif)){
                    //On cherche la balise description
                    NodeList listeEnfantsDescriptif = baliseDescriptif.getChildNodes();
                    for(int j = 0; j < listeEnfantsDescriptif.getLength(); j++){
                        if(listeEnfantsDescriptif.item(j).getNodeName().equals("courteDescription")){
                            //On modifie la balise description
                            Element baliseCourteDescription = (Element) listeEnfantsDescriptif.item(j);
                            baliseCourteDescription.setTextContent(newDescription);
                            testModif = true;
                        }
                    }
                }
            }

            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testModif){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ModifierCourteDescriptionDescriptif(Long idProjet, String idDescriptif, String newDescription)", ex);
        }
        return resultat;
    }
    
    //TO DO - si les infos ne sont pas renseigné dans la BDD alors chaine vide
    public Boolean ModifierLocalisationDescriptif(Long idProjet, String idDescriptif, String idLigneChiffrage, String newLocalisation){
        Boolean testModif = false;
        Boolean resultat = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            NodeList listeDescriptif = xml.getElementsByTagName("descriptif");
            //On parcours les descriptifs pour modifier celui qui nous intéresse
            for(int i = 0; i < listeDescriptif.getLength(); i++){
                Element baliseDescriptif = (Element) listeDescriptif.item(i);
                if(baliseDescriptif.getAttribute("idDescriptif").equals(idDescriptif)){
                    //On cherche la balise description
                    NodeList listeEnfantsDescriptif = baliseDescriptif.getChildNodes();
                    for(int j = 0; j < listeEnfantsDescriptif.getLength(); j++){
                        if(listeEnfantsDescriptif.item(j).getNodeName().equals("ligneChiffrage")){
                            Element ligneChiffrage = (Element) listeEnfantsDescriptif.item(j);
                            if(ligneChiffrage.getAttribute("idLigneChiffrage").equals(idLigneChiffrage)){
                                NodeList enfantsLigneChiffrage = ligneChiffrage.getChildNodes();
                                for(int k = 0; k < enfantsLigneChiffrage.getLength(); k++){
                                    if(enfantsLigneChiffrage.item(k).getNodeName().equals("localisation")){
                                        //On modifie la balise description
                                        Element baliseLocalisation = (Element) enfantsLigneChiffrage.item(k);
                                        baliseLocalisation.setTextContent(newLocalisation);
                                        testModif = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testModif){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ModifierLocalisationDescriptif(Long idProjet, String idDescriptif, String idLigneChiffrage, String newLocalisation)", ex);
        }
        return resultat;
    }
    
    //TO DO - si les infos ne sont pas renseigné dans la BDD alors chaine vide
    public Boolean ModifierQuantiteDescriptif(Long idProjet, String idDescriptif, String idLigneChiffrage, Double quantite){
        JpaUtil.creerContextePersistance();
        Boolean testModif = false;
        Boolean resultat = false;
        
        try {
            //Obtention du document
            String uri = "../XMLfiles/"+idProjet+".xml"; //Surement à changer lors de l'installation client
            Document xml = projetXMLDao.ObtenirDocument(uri);
            
            NodeList listeDescriptif = xml.getElementsByTagName("descriptif");
            //On parcours les descriptifs pour modifier celui qui nous intéresse
            for(int i = 0; i < listeDescriptif.getLength(); i++){
                Element baliseDescriptif = (Element) listeDescriptif.item(i);
                if(baliseDescriptif.getAttribute("idDescriptif").equals(idDescriptif)){
                    //On cherche la balise description
                    NodeList listeEnfantsDescriptif = baliseDescriptif.getChildNodes();
                    for(int j = 0; j < listeEnfantsDescriptif.getLength(); j++){
                        if(listeEnfantsDescriptif.item(j).getNodeName().equals("ligneChiffrage")){
                            Element ligneChiffrage = (Element) listeEnfantsDescriptif.item(j);
                            if(ligneChiffrage.getAttribute("idLigneChiffrage").equals(idLigneChiffrage)){
                                NodeList enfantsLigneChiffrage = ligneChiffrage.getChildNodes();
                                for(int k = 0; k < enfantsLigneChiffrage.getLength(); k++){
                                    if(enfantsLigneChiffrage.item(k).getNodeName().equals("quantite")){
                                        //On modifie la balise quantite
                                        Element baliseQuantite = (Element) enfantsLigneChiffrage.item(k);
                                        baliseQuantite.setTextContent(quantite.toString());
                                        testModif = true;
                                    } else if(enfantsLigneChiffrage.item(k).getNodeName().equals("prixUnitaire")){
                                        //On Modifie le prix unitaire
                                        //On va cherche le bon prix unitaire en fonction de la qte donnée
                                        Integer annee_max = 0;
                                        int indiceRef = -1;
                                        
                                        List<BasePrixRef> listeBasePrixRef = null;
                                        if(baliseDescriptif.getAttribute("type").equals("ouvrage")){ //On va cherche la liste des prix dans BasePrixRef
                                            Ouvrage ouvrage = (Ouvrage) descriptifDao.ChercherParId(baliseDescriptif.getAttribute("idDescriptif"));
                                            listeBasePrixRef = ouvrage.getListeBasePrixRefOuvrage();
                                        } else if(baliseDescriptif.getAttribute("type").equals("prestation")) {
                                            Prestation prestation = (Prestation) descriptifDao.ChercherParId(baliseDescriptif.getAttribute("idDescriptif"));
                                            listeBasePrixRef = prestation.getListeBasePrixRefPrestation();
                                        }
                                        if(listeBasePrixRef != null){
                                            for(int l = 0; l < listeBasePrixRef.size(); l++){
                                                if(listeBasePrixRef.get(l).getQteInf() <= quantite && listeBasePrixRef.get(l).getQteSup() >= quantite){
                                                    //on se trouve dans la bonne fourchette de quantite, on test l'annee
                                                    if(listeBasePrixRef.get(l).getAnnee() > annee_max){
                                                      annee_max = listeBasePrixRef.get(l).getAnnee();
                                                      indiceRef = l;
                                                    }
                                                }
                                            }

                                            //Balise prixUnitaire
                                            Element balisePrixUnitaire = (Element) enfantsLigneChiffrage.item(k);                                                                   
                                            balisePrixUnitaire.setTextContent(listeBasePrixRef.get(indiceRef).getPrixUnitaire().toString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
           
            //On écrit par dessus l'ancien XML
            projetXMLDao.saveXMLContent(xml, uri);
            
            if(testModif){
                resultat = true; //Si on est arrivé jusque là alors pas d'erreur
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ModifierQuantiteDescriptif(Long idProjet, String idDescriptif, String idLigneChiffrage, Double quantite)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
}