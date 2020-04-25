package fr.etic.brp.brp_back_end.metier.service;

import fr.etic.brp.brp_back_end.dao.CategorieConstructionDao;
import fr.etic.brp.brp_back_end.dao.CategorieDao;
import fr.etic.brp.brp_back_end.dao.CoeffRaccordementDao;
import fr.etic.brp.brp_back_end.dao.CorpsEtatDao;
import fr.etic.brp.brp_back_end.dao.DescriptifDao;
import fr.etic.brp.brp_back_end.dao.FamilleDao;
import fr.etic.brp.brp_back_end.dao.JpaUtil;
import fr.etic.brp.brp_back_end.dao.OperateurDao;
import fr.etic.brp.brp_back_end.dao.ProjetDao;
import fr.etic.brp.brp_back_end.dao.SousCategorieConstructionDao;
import fr.etic.brp.brp_back_end.dao.SousFamilleDao;
import fr.etic.brp.brp_back_end.metier.modele.Categorie;
import fr.etic.brp.brp_back_end.metier.modele.CategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.CoeffRaccordement;
import fr.etic.brp.brp_back_end.metier.modele.CorpsEtat;
import fr.etic.brp.brp_back_end.metier.modele.Descriptif;
import fr.etic.brp.brp_back_end.metier.modele.Famille;
import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import fr.etic.brp.brp_back_end.metier.modele.Projet;
import fr.etic.brp.brp_back_end.metier.modele.SousCategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.SousFamille;
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
    
    //Squelette
    /*
    public ValeurRetour NomService(Arguments) {
        JpaUtil.creerContextePersistance();
        // On trouve d'abord un employé disponible
        try {
            //Essayer le service
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service NomService(Arguments)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return valeurRetour;
    }
    */
    
    //TO DO
    public Operateur AuthentifierOperateur(String mail, String mdpEntre) {
        Operateur resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche de l'operateur
            Operateur operateur = operateurDao.ChercherParMail(mail);
            if (operateur != null) {
                // Vérification du mot de passe
                if (operateur.getMail().equals(mdpEntre)) {
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
    
    //TO DO
    public Projet OuvrirProjet(Long idProjet){
        return null;
    }
    
    //TO DO
    public Boolean EditerInfosProjet(String typeInfo, String nouvelleValeur){
        return null;
    }
    
    //TO DO
    public Boolean ModifBibliotheque(){
        return null;
    }
    
    //TO DO
    public Boolean DupliquerProjet(Long idProjet){
        return null;
    }
}