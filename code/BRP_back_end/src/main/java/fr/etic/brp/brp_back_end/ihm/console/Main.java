package fr.etic.brp.brp_back_end.ihm.console;

import com.google.common.hash.Hashing;
import fr.etic.brp.brp_back_end.dao.JpaUtil;
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
import fr.etic.brp.brp_back_end.metier.modele.Projet.Site;
import fr.etic.brp.brp_back_end.metier.modele.Projet.TypeConstruction;
import fr.etic.brp.brp_back_end.metier.modele.Projet.TypeLot;
import fr.etic.brp.brp_back_end.metier.modele.Projet.TypeMarche;
import fr.etic.brp.brp_back_end.metier.modele.SousCategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.SousFamille;
import fr.etic.brp.brp_back_end.metier.service.Service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import java.lang.Math;

/**
 *
 * @author louisrob
 */
public class Main {
    
    public static void main(String[] args) throws IOException {

        // Contrôlez l'affichage du log de JpaUtil grâce à la méthode log de la classe JpaUtil
        JpaUtil.init();

    //------------initialisations------------------
    
        // A faire tout le temps (l'ordre est important)
        InitialiserBasePrixRef();
        InitialiserCaractDim();
        InitialiserSousCategorieConstruction();
        InitialiserCategories();
        InitialiserCategorieConstruction();
        InitialiserCoeffRaccordement();
        InitialiserCorpsEtat();
        InitialiserDescriptif();
        InitialiserFamille();
        InitialiserOperateur();
        InitialiserProjets();
        InitialiserSousFamille();

        // Pour les tests primaires mais pas secondaires
        //initialiserClients();
        //initialiserConsultations(); 
        
    //----------tests-des-services-----------------
    
      //---------tests-primaires--------//
      
        testerListerBasePrixRefs();
        testerListerCaractDims();
        testerListerCategories();
        testerListerCategorieConstructions();
        testerListerCoeffRaccordements();
        testerListerCorpsEtats();
        testerListerDescriptifs();
        testerListerFamilles();
        testerListerOperateurs();
        testerListerPrestations();
        testerListerProjets();
        testerListerSousCategorieConstructions();
        testerListerSousFamilles();
        testerAuthentifierOperateur();
        testerCreerProjet();
        testerRechercherProjetParId();
        testerDupliquerProjet();
        
      //----------tests-secondaires------//
      
//        scenarioUnClient();
//        scenarioPlusieursDemandeConsultation(); //Pour ce scenario pensez à commenter l'initialisation des employes !

        JpaUtil.destroy();
    }
    
//------------------------------------------------------------------------------    
//---------------------------- INITIALISATIONS ---------------------------------
//------------------------------------------------------------------------------
    
    public static void InitialiserBasePrixRef() {
        
        System.out.println();
        System.out.println("**** InitialiserBasePrixRef() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();        
        
        BasePrixRef basePrixRef = new BasePrixRef("anne1", 1.0, 2.0, 3.0, "ml", 4.0);
        System.out.println("** BasePrixRef avant persistance: ");
        afficherBasePrixRef(basePrixRef);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(basePrixRef);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service InitialiserBasePrixRef()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        System.out.println("** BasePrixRef après persistance: ");
        afficherBasePrixRef(basePrixRef);
        System.out.println();
    }
    public static void InitialiserProjets() {
        
        System.out.println();
        System.out.println("**** InitialiserProjets() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();        
        
        Projet projet1 = new Projet("nomProjet1");
        TypeMarche typeMarche = TypeMarche.marchePublic;
        projet1.setTypeMarche(typeMarche);
        TypeConstruction typeConstruction = TypeConstruction.renovation;
        projet1.setTypeConstruction(typeConstruction);
        TypeLot typeLot = TypeLot.lotSepare;
        projet1.setTypeLot(typeLot);
        Site site = Site.libre;
        projet1.setSite(site);
        projet1.setDatePrixRef(new Date());
        projet1.setCoeffAdapt(1F);
        
        System.out.println("** Projets avant persistance: ");
        afficherProjet(projet1);
        System.out.println();

        try {
            em.getTransaction().begin();
            
            CategorieConstruction categorieConstruction = em.find(CategorieConstruction.class, 1L);
            projet1.setCategorieConstruction(categorieConstruction);
            
            CoeffRaccordement coeffRaccordement = em.find(CoeffRaccordement.class, 1L);
            projet1.setCoeffRaccordement(coeffRaccordement);
            
            em.persist(projet1);
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service InitialiserProjets()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        System.out.println("** Projets après persistance: ");
        afficherProjet(projet1);
        System.out.println();
    }
    public static void InitialiserCaractDim() {
        
        System.out.println();
        System.out.println("**** InitialiserCaractDim() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();        
        
        CaractDim caractDim1 = new CaractDim("codeCaractDim1", 1.0);
         
        System.out.println("** CaractDim avant persistance: ");
        afficherCaractDim(caractDim1);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(caractDim1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service InitialiserCaractDim()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        System.out.println("** CaractDim après persistance: ");
        afficherCaractDim(caractDim1);
        System.out.println();
    }
    public static void InitialiserCategories() {
        
        System.out.println();
        System.out.println("**** initialiserCategories() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();        
        
        Categorie categorie1 = new Categorie("intitule1");
         
        System.out.println("** Categorie avant persistance: ");
        afficherCategorie(categorie1);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(categorie1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserCategories()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        System.out.println("** Categorie après persistance: ");
        afficherCategorie(categorie1);
        System.out.println();
    }
    public static void InitialiserCoeffRaccordement() {
        
        System.out.println();
        System.out.println("**** initialiserCoeffRaccordement() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();        
        
        CoeffRaccordement coeffRaccordement1 = new CoeffRaccordement("Paris", 1.0);
         
        System.out.println("** CoeffRaccordement avant persistance: ");
        afficherCoeffRaccordement(coeffRaccordement1);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(coeffRaccordement1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserCoeffRaccordement()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        System.out.println("** CoeffRaccordement après persistance: ");
        afficherCoeffRaccordement(coeffRaccordement1);
        System.out.println();
    }
    public static void InitialiserDescriptif() {
        
        System.out.println();
        System.out.println("**** InitialiserDescriptif() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();        
        
        Generique generique1 = new Generique("idDesceriptif1", "nomDescriptif1", "descriptionGenerique1", "courteDescriptionGenerique1");
        Ouvrage ouvrage1 = new Ouvrage("idOuvrage1", "nomOuvrage1", "descriptionOuvrage1", "courteDescriptionOuvrage1");
        Prestation prestation1 = new Prestation("idPrestation1", "nomPrestation1", "descriptionPrestation1", "courteDescriptionPrestation1");
         
        System.out.println("** Descriptif avant persistance: ");
        afficherGenerique(generique1);
        afficherOuvrage(ouvrage1);
        afficherPrestation(prestation1);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(generique1);
            em.persist(ouvrage1);
            em.persist(prestation1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service InitialiserDescriptif()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        System.out.println("** Descriptif après persistance: ");
        afficherGenerique(generique1);
        afficherOuvrage(ouvrage1);
        afficherPrestation(prestation1);
        System.out.println();
    }
    public static void InitialiserSousFamille() {
        
        System.out.println();
        System.out.println("**** InitialiserSousFamille() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();        
        
        SousFamille sousFamille1 = new SousFamille("intituleSousFamille1");
         
        System.out.println("** SousFamille avant persistance: ");
        afficherSousFamille(sousFamille1);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(sousFamille1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service InitialiserSousFamille()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        System.out.println("** SousFamille après persistance: ");
        afficherSousFamille(sousFamille1);
        System.out.println();
    }
    public static void InitialiserCategorieConstruction() {
        
        System.out.println();
        System.out.println("**** initialiserCategorieConstruction() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();        
        
        CategorieConstruction categorieConstruction1 = new CategorieConstruction("intituleCategorieConstruction1", "codeCategorieConstruction1");
        
        System.out.println("** CategorieConstruction avant persistance: ");
        afficherCategorieConstruction(categorieConstruction1);
        System.out.println();

        try {
            em.getTransaction().begin();
            
            SousCategorieConstruction sousCategorieConstruction = em.find(SousCategorieConstruction.class, 1L);
            List<SousCategorieConstruction> listeSousCategorieConstruction = new ArrayList<>();
            listeSousCategorieConstruction.add(0, sousCategorieConstruction);
            categorieConstruction1.setListeSousCategorieConstruction(listeSousCategorieConstruction);
            
            CaractDim caractDim = em.find(CaractDim.class, 1L);
            List<CaractDim> listeCaractDim = new ArrayList<>();
            listeCaractDim.add(0, caractDim);
            categorieConstruction1.setListeCaractDim(listeCaractDim);
            
            em.persist(categorieConstruction1);
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserCategorieConstruction()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        System.out.println("** CategorieConstruction après persistance: ");
        afficherCategorieConstruction(categorieConstruction1);
        System.out.println();
    } 
    public static void InitialiserCorpsEtat() {
        
        System.out.println();
        System.out.println("**** initialiserCorpsEtat() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();        
        
        CorpsEtat corpsEtat1 = new CorpsEtat("corpsEtat1");
         
        System.out.println("** CorpsEtat avant persistance: ");
        afficherCorpsEtat(corpsEtat1);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(corpsEtat1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserCorpsEtat()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        System.out.println("** CorpsEtat après persistance: ");
        afficherCorpsEtat(corpsEtat1);
        System.out.println();
    }
    public static void InitialiserFamille() {
        
        System.out.println();
        System.out.println("**** InitialiserFamille() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();        
        
        Famille famille1 = new Famille("famille1");
         
        System.out.println("** CorpsEtat avant persistance: ");
        afficherFamille(famille1);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(famille1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service InitialiserFamille()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        System.out.println("** Famille après persistance: ");
        afficherFamille(famille1);
        System.out.println();
    }
    public static void InitialiserOperateur() {
        
        System.out.println();
        System.out.println("**** InitialiserOperateur() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();     
        
        int salt = (int)(Math.random()*1000);
        String mdpConcat = "monMDP"+salt;
        String mdpHash = Hashing.sha256().hashString(mdpConcat, StandardCharsets.UTF_8).toString();
        
        Operateur operateur1 = new Operateur("quentinmarc@orange.fr", mdpHash, salt, "quentin");
         
        System.out.println("** Operateur avant persistance: ");
        afficherOperateur(operateur1);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(operateur1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service InitialiserOperateur()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        System.out.println("** Operateur après persistance: ");
        afficherOperateur(operateur1);
        System.out.println();
    }
    public static void InitialiserSousCategorieConstruction() {
        
        System.out.println();
        System.out.println("**** InitialiserSousCategorieConstruction() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();        
        
        SousCategorieConstruction sousCategorieConstruction1 = new SousCategorieConstruction("sousCategorieConstruction1");
         
        System.out.println("** SousCategorieConstruction avant persistance: ");
        afficherSousCategorieConstruction(sousCategorieConstruction1);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(sousCategorieConstruction1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service InitialiserSousCategorieConstruction()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        System.out.println("** SousCategorieConstruction après persistance: ");
        afficherSousCategorieConstruction(sousCategorieConstruction1);
        System.out.println();
    }
    
//------------------------------------------------------------------------------
//------------------------------ TESTS DES SERVICES  ---------------------------
//------------------------------------------------------------------------------
   
    public static void testerAuthentifierOperateur() {
        
        System.out.println();
        System.out.println("**** testerAuthentifierOperateur() ****");
        System.out.println();
        
        Service service = new Service();
        Operateur operateur;
        String mail;
        String mdpEntre;

        mail = "quentinmarc@orange.fr";
        mdpEntre = "monMDP";
        operateur = service.AuthentifierOperateur(mail, mdpEntre);
        if (operateur != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + mdpEntre + "'");
            afficherOperateur(operateur);
        }
        else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + mdpEntre + "'");
        }

        mail = "ada.lovelace@insa-lyon.fr";
        mdpEntre = "Ada2020";
        operateur = service.AuthentifierOperateur(mail, mdpEntre);
        if (operateur != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + mdpEntre + "'");
            afficherOperateur(operateur);
        }
        else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + mdpEntre + "'");
        }
        
        mail = "quentinmarc@orange.fr";
        mdpEntre = "Ada2020";
        operateur = service.AuthentifierOperateur(mail, mdpEntre);
        if (operateur != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + mdpEntre + "'");
            afficherOperateur(operateur);
        }
        else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + mdpEntre + "'");
        }
    }
    
    public static void testerCreerProjet() {
        
        System.out.println();
        System.out.println("**** testerCreerProjet() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        String nomProjet = "projet1";

        Boolean projetTest1 = service.CreerProjet(nomProjet);
        if (projetTest1) {
            System.out.println("Succès: création du projet: "+nomProjet);
        }
        else {
            System.out.println("Echec: impossible de créer: "+nomProjet);
        }
        
        //Doit fonctionner (nom deja présent BD)
        Boolean projetTest2 = service.CreerProjet(nomProjet);
        if (projetTest2) {
            System.out.println("Succès: création du projet: "+nomProjet);
        }
        else {
            System.out.println("Echec: impossible de créer: "+nomProjet);
        }
    }
    
    
    public static void testerRechercherProjetParId() {
        
        System.out.println();
        System.out.println("**** testerRechercherProjetParId() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjetTest = 1L;

        Projet projetTest1 = service.RechercherProjetParId(idProjetTest);
        if (projetTest1 != null) {
            System.out.println("Ouverture avec succès de" + projetTest1.getNomProjet());
            afficherProjet(projetTest1);
        }
        else {
            System.out.println("Aucun projet trouvé !");
        }
        
        //Ne doit pas fonctionner
        idProjetTest = 2L;

        Projet projetTest2 = service.RechercherProjetParId(idProjetTest);
        if (projetTest2 != null) {
            System.out.println("Ouverture avec succès de" + projetTest2.getNomProjet());
            afficherProjet(projetTest2);
        }
        else {
            System.out.println("Aucun projet correspondant à l'id" + idProjetTest + " !");
        }
    }
    
    public static void testerDupliquerProjet() {
        
        System.out.println();
        System.out.println("**** testerDupliquerProjet() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        
        Boolean resultat = service.DupliquerProjet(idProjet);
        if(resultat)
        {
            System.out.println("Duplication avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur de duplication du projet n°" + idProjet);
        }
       
        //Ne doit pas fonctionner
        Long idProjet2 = 100L;
        
        Boolean resultat2 = service.DupliquerProjet(idProjet2);
        if(resultat2)
        {
            System.out.println("Duplication avec succès du projet n°" + idProjet2);
        } else {
            System.out.println("Erreur de duplication du projet n°" + idProjet2);
        }
    }
    
                //-----------------------//

    public static void testerListerCategories() {
        
        System.out.println();
        System.out.println("**** testerListerCategories() ****");
        System.out.println();
        
        Service service = new Service();
        List<Categorie> listeCategorie = service.ListerCategories();
        System.out.println("*** Liste des Categories");
        if (listeCategorie != null) {
            listeCategorie.forEach((categorie) -> {
                afficherCategorie(categorie);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    public static void testerListerCategorieConstructions() {
        
        System.out.println();
        System.out.println("**** testerListerCategorieConstructions() ****");
        System.out.println();
        
        Service service = new Service();
        List<CategorieConstruction> listCategorieConstruction = service.ListerCategorieConstructions();
        System.out.println("*** Liste des CategorieConstructions");
        if (listCategorieConstruction != null) {
            listCategorieConstruction.forEach((categorieConstruction) -> {
                afficherCategorieConstruction(categorieConstruction);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    public static void testerListerCoeffRaccordements() {
        
        System.out.println();
        System.out.println("**** testerListerCoeffRaccordements() ****");
        System.out.println();
        
        Service service = new Service();
        List<CoeffRaccordement> listCoeffRaccordement = service.ListerCoeffRaccordements();
        System.out.println("*** Liste des CoeffRaccordements");
        if (listCoeffRaccordement != null) {
            listCoeffRaccordement.forEach((coeffRaccordement) -> {
                afficherCoeffRaccordement(coeffRaccordement);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    public static void testerListerCorpsEtats() {
        
        System.out.println();
        System.out.println("**** testerListerCorpsEtats() ****");
        System.out.println();
        
        Service service = new Service();
        List<CorpsEtat> listeCorpsEtats = service.ListerCorpsEtats();
        System.out.println("*** Liste des CorpsEtats");
        if (listeCorpsEtats != null) {
            listeCorpsEtats.forEach((corpsEtat) -> {
                afficherCorpsEtat(corpsEtat);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    public static void testerListerDescriptifs() {
        
        System.out.println();
        System.out.println("**** testerListerDescriptifs() ****");
        System.out.println();
        
        Service service = new Service();
        List<Descriptif> listeDescriptifs = service.ListerDescriptifs();
        System.out.println("*** Liste des Descriptifs");
        if (listeDescriptifs != null) {
            listeDescriptifs.forEach((descriptif) -> {
                afficherDescriptif(descriptif);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    public static void testerListerFamilles() {
        
        System.out.println();
        System.out.println("**** testerListerFamilles() ****");
        System.out.println();
        
        Service service = new Service();
        List<Famille> listeFamilles = service.ListerFamilles();
        System.out.println("*** Liste des Familles");
        if (listeFamilles != null) {
            listeFamilles.forEach((famille) -> {
                afficherFamille(famille);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    public static void testerListerOperateurs() {
        
        System.out.println();
        System.out.println("**** testerListerOperateurs() ****");
        System.out.println();
        
        Service service = new Service();
        List<Operateur> listeOperateurs = service.ListerOperateurs();
        System.out.println("*** Liste des Operateurs");
        if (listeOperateurs != null) {
            listeOperateurs.forEach((operateur) -> {
                afficherOperateur(operateur);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    public static void testerListerProjets() {
        
        System.out.println();
        System.out.println("**** testerListerProjets() ****");
        System.out.println();
        
        Service service = new Service();
        List<Projet> listeProjets = service.ListerProjets();
        System.out.println("*** Liste des Projets");
        if (listeProjets != null) {
            listeProjets.forEach((projet) -> {
                afficherProjet(projet);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    public static void testerListerSousCategorieConstructions() {
        
        System.out.println();
        System.out.println("**** testerListerSousCategorieConstructions() ****");
        System.out.println();
        
        Service service = new Service();
        List<SousCategorieConstruction> listeSousCategorieConstructions = service.ListerSousCategorieConstructions();
        System.out.println("*** Liste des SousCategorieConstructions");
        if (listeSousCategorieConstructions != null) {
            listeSousCategorieConstructions.forEach((sousCategorieConstruction) -> {
                afficherSousCategorieConstruction(sousCategorieConstruction);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    public static void testerListerSousFamilles() {
        
        System.out.println();
        System.out.println("**** testerListerSousFamilles() ****");
        System.out.println();
        
        Service service = new Service();
        List<SousFamille> listeSousFamilles = service.ListerSousFamilles();
        System.out.println("*** Liste des SousFamilles");
        if (listeSousFamilles != null) {
            listeSousFamilles.forEach((sousFamille) -> {
                afficherSousFamille(sousFamille);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    public static void testerListerBasePrixRefs() {
        
        System.out.println();
        System.out.println("**** testerListerBasePrixRefs() ****");
        System.out.println();
        
        Service service = new Service();
        List<BasePrixRef> listeBasePrixRefs = service.ListerBasePrixRefs();
        System.out.println("*** Liste des BasePrixRefs");
        if (listeBasePrixRefs != null) {
            listeBasePrixRefs.forEach((basePrixRef) -> {
                afficherBasePrixRef(basePrixRef);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    public static void testerListerCaractDims() {
        
        System.out.println();
        System.out.println("**** testerListerCaractDims() ****");
        System.out.println();
        
        Service service = new Service();
        List<CaractDim> listeCaractDims = service.ListerCaractDims();
        System.out.println("*** Liste des CaractDims");
        if (listeCaractDims != null) {
            listeCaractDims.forEach((caractDim) -> {
                afficherCaractDim(caractDim);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    public static void testerListerPrestations() {
        
        System.out.println();
        System.out.println("**** testerPrestations() ****");
        System.out.println();
        
        Service service = new Service();
        List<Prestation> listePrestations = service.ListerPrestations();
        System.out.println("*** Liste des Prestations");
        if (listePrestations != null) {
            listePrestations.forEach((prestation) -> {
                afficherPrestation(prestation);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    
                //-----------------------//
    
    /*public static void testerRechercheClientParId() {
        
        System.out.println();
        System.out.println("**** testerRechercheClient() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        Client client;

        id = 6;
        System.out.println("** Recherche du Client #" + id);
        client = service.RechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 3;
        System.out.println("** Recherche du Client #" + id);
        client = service.RechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 17;
        System.out.println("** Recherche du Client #" + id);
        client = service.RechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client #" + id + " non-trouvé");
        }
    }
    */
    
                //----------------------//
    
    /*public static void testerCommencerConsultation() {
        
        System.out.println();
        System.out.println("**** testerCommencerConsultation() ****");
        System.out.println();
        
        Service service = new Service();
    
        Client client = service.ListerClients().get(0);
        Medium medium = service.ListerMediums().get(0);
        Boolean resulat = service.CommencerConsultation(client, medium);
        
        if (resulat) {
            System.out.println(" => Employé contacté");
            System.out.println();
        } else {
            System.out.println(" => Pas d'employé trouvé pour une consultation");
            System.out.println();
        }
    }*/
    
    //Pensez aux scenarii
    
//------------------------------------------------------------------------------    
//-------------------------- METHODES AFFICHAGE -------------------------------
//------------------------------------------------------------------------------
    
    public static void afficherBasePrixRef(BasePrixRef basePrixRef) {
        System.out.println("-> " + basePrixRef);
    }
    public static void afficherCaractDim(CaractDim caractDim) {
        System.out.println("-> " + caractDim);
    }
    public static void afficherCategorieConstruction(CategorieConstruction categorieConstruction) {
        System.out.println("-> " + categorieConstruction);
    }
    public static void afficherCategorie(Categorie categorie) {
        System.out.println("-> " + categorie);
    }
    public static void afficherCoeffRaccordement(CoeffRaccordement coeffRaccordement) {
        System.out.println("-> " + coeffRaccordement);
    }
    public static void afficherCorpsEtat(CorpsEtat corpsEtat) {
        System.out.println("-> " + corpsEtat);
    }
    public static void afficherDescriptif(Descriptif descriptif) {
        if (descriptif.getClass() == Generique.class) afficherGenerique((Generique)descriptif);
        else if (descriptif.getClass() == Ouvrage.class) afficherOuvrage((Ouvrage)descriptif);
        else System.out.println("descriptif inconnu\n");
    }
    public static void afficherFamille(Famille famille) {
        System.out.println("-> " + famille);
    }
    public static void afficherGenerique(Generique generique) {
        System.out.println("-> " + generique);
    }
    public static void afficherOperateur(Operateur operateur) {
        System.out.println("-> " + operateur);
    }
    public static void afficherOuvrage(Ouvrage ouvrage) {
        System.out.println("-> " + ouvrage);
    }
    public static void afficherPrestation(Prestation prestation) {
        System.out.println("-> " + prestation);
    }
    public static void afficherProjet(Projet projet) {
        System.out.println("-> " + projet);
    }
    public static void afficherSousCategorieConstruction(SousCategorieConstruction sousCategorieConstruction) {
        System.out.println("-> " + sousCategorieConstruction);
    }
    public static void afficherSousFamille(SousFamille sousFamille) {
        System.out.println("-> " + sousFamille);
    }
}