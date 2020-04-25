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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    //TO DO
    public Boolean CreerProjet(String nomProjet){
        return null;
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
    
    //TO DO
    public Boolean EditerInfosProjet(String typeInfo, String nouvelleValeur){
        return null;
    }
    
    //TO DO
    public Boolean ModifBibliotheque(){
        return null;
    }
    
    //TO DO - Fonctionne pour le moment mais il faut mieux peupler la BD pour de vrais tests !
    //Duplique un projet en donnant par défaut le nom "Nouveau Projet"
    public Boolean DupliquerProjet(Long idProjetADupliquer){
        Projet projetADupliquer = null;
        Projet projetDuplique = null;
        JpaUtil.creerContextePersistance();
        
        try {
            if(idProjetADupliquer != null)
            {
                projetADupliquer = projetDao.ChercherParId(idProjetADupliquer); //On va cherche le projet à duppliquer
                if(projetADupliquer != null) {
                    //On duplique ce projet en faisant attention aux liens divers
                    projetDuplique = new Projet("Nouveau Projet");
                    projetDuplique.setTypeMarche(projetADupliquer.getTypeMarche());
                    projetDuplique.setTypeConstruction(projetADupliquer.getTypeConstruction());
                    projetDuplique.setTypeLot(projetADupliquer.getTypeLot());
                    projetDuplique.setSite(projetADupliquer.getSite());
                    projetDuplique.setDatePrixRef(projetADupliquer.getDatePrixRef());
                    projetDuplique.setCoeffAdapt(projetADupliquer.getCoeffAdapt());
                    projetDuplique.setCategorieConstruction(projetADupliquer.getCategorieConstruction());
                    //Attention il faut bien que tous les liens soit recrées eux aussi (cascade ?)
                    //On enregistre dans la BD
                    JpaUtil.ouvrirTransaction();
                    projetDao.Creer(projetDuplique);
                    JpaUtil.validerTransaction();
                } else {
                    System.out.println("Pas de projet à dupliquer trouvé !");
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service DupliquerProjet(idProjet)", ex);
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
}