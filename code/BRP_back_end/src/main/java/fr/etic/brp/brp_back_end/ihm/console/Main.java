package fr.etic.brp.brp_back_end.ihm.console;

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
import fr.etic.brp.brp_back_end.metier.modele.SousCategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.SousFamille;
import fr.etic.brp.brp_back_end.metier.service.Service;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author louisrob
 */
public class Main {
    
    public static void main(String[] args) throws IOException {

        // Contrôlez l'affichage du log de JpaUtil grâce à la méthode log de la classe JpaUtil
        JpaUtil.init();

    //------------initialisations------------------
    
        // A faire tout le temps
        InitialiserBasePrixRef();
        InitialiserCaractDim();
        InitialiserCategories();
        InitialiserCategorieConstruction();
        InitialiserCoeffRaccordement();
        InitialiserCorpsEtat();
        InitialiserDescriptif();
        InitialiserFamille();
        InitialiserOperateur();
        InitialiserProjets();
        InitialiserSousCategorieConstruction();
        InitialiserSousFamille();

        // Pour les tests primaires mais pas secondaires
        //initialiserClients();
        //initialiserConsultations(); 
        
    //----------tests-des-services-----------------
    
      //---------tests-primaires--------//
      
        testerListerCategories();
        testerListerCategorieConstructions();
        testerListerCoeffRaccordements();
        testerListerCorpsEtats();
        testerListerDescriptifs();
        testerListerFamilles();
        testerListerOperateurs();
        testerListerProjets();
        testerListerSousCategorieConstructions();
        testerListerSousFamilles();
        
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
        
        System.out.println("** Projets avant persistance: ");
        afficherProjet(projet1);
        System.out.println();

        try {
            em.getTransaction().begin();
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
        
        Operateur operateur1 = new Operateur("quentinmarc@orange.fr", "monMDP", 31424, "quentin");
         
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
    
    /*public static void testerInscriptionClient(){
        
        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        
        Service service = new Service();
        
        Date dateOfBirth = new Date(1912, 10, 11);
        Client alice = new Client(dateOfBirth, "20 av Albert Einstein", "alice2.lovelace@insa-lyon.fr", "Alice1012", "0667897668", "Alice", "Lovelace");
        AstroTest astroTest = new AstroTest();
        List<String> listeAstroProfile;
        try {
            listeAstroProfile = astroTest.getProfil(alice.getFirstName(), alice.getBirthDate());
            AstralProfile astralProfile = new AstralProfile(listeAstroProfile.get(0), listeAstroProfile.get(1), listeAstroProfile.get(2), listeAstroProfile.get(3));
            alice.setAstralProfile(astralProfile);

            Long idAlice = service.InscrireClient(alice);
            if (idAlice != null) {
                System.out.println("> Succès inscription");
            } else {
                System.out.println("> Échec inscription");
            }
            afficherClient(alice);
            System.out.println();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void testerAuthentifierAppUser() {
        
        System.out.println();
        System.out.println("**** testerAuthentifierAppUser() ****");
        System.out.println();
        
        Service service = new Service();
        AppUser user;
        String mail;
        String password;

        mail = "alice.lovelace@insa-lyon.fr";
        password = "Alice1012";
        user = service.AuthentifierAppUser(mail, password);
        if (user != null && user.getClass() == Client.class) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + password + "'");
            afficherClient((Client)user);
        }
        else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + password + "'");
        }

        mail = "ada.lovelace@insa-lyon.fr";
        password = "Ada2020";
        user = service.AuthentifierAppUser(mail, password);
        if (user != null && user.getClass() == Client.class) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + password + "'");
            afficherClient((Client)user);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + password + "'");
        }
                
        mail = "cgayel5170@free.fr";
        password = "mdpCamille";
        user = service.AuthentifierAppUser(mail, password);
        if (user != null && user.getClass() == Employee.class) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + password + "'");
            afficherEmploye((Employee)user);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + password + "'");
        }

        mail = "ada.lovelace@insa-lyon.fr";
        password = "Ada2020";
        user = service.AuthentifierAppUser(mail, password);
        if (user != null && user.getClass() == Employee.class) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + password + "'");
            afficherEmploye((Employee)user);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + password + "'");
        }
    }*/
    
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