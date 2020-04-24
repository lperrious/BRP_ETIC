package fr.etic.brp.brp_back_end.ihm.console;

import fr.etic.brp.brp_back_end.dao.JpaUtil;
import fr.etic.brp.brp_back_end.metier.modele.Categorie;
import fr.etic.brp.brp_back_end.metier.modele.CategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.CoeffRaccordement;
import fr.etic.brp.brp_back_end.metier.modele.CorpsEtat;
import fr.etic.brp.brp_back_end.metier.modele.Descriptif;
import fr.etic.brp.brp_back_end.metier.modele.Famille;
import fr.etic.brp.brp_back_end.metier.modele.Generique;
import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import fr.etic.brp.brp_back_end.metier.modele.Ouvrage;
import fr.etic.brp.brp_back_end.metier.modele.Projet;
import fr.etic.brp.brp_back_end.metier.modele.SousCategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.SousFamille;
import java.io.IOException;

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
        //initialiserEmploye();
        //initialiserMediums();
        
        // Pour les tests primaires mais pas secondaires
        //initialiserClients();
        //initialiserConsultations(); 
        
    //----------tests-des-services-----------------
    
      //---------tests-primaires--------//
      
//        testerInscriptionClient();
//        testerAuthentifierAppUser();
//        
//        testerListerClients();
//        testerListerEmployes();
//        testerListerMediums();
//        testerListerConsultations();
//        testerListerConsultationsNonDebutees();
//        testerListerConsultationsEnCours();
//        testerListerConsultationsTerminees();
//        testerConsultationEnCours();
//        
//        testerRechercheClientParId();
//        testerRechercheEmployeParId();
//        testerRechercheConsulationParId();
//        testerRechercheMediumParId();
//        
//        testerCommencerConsultation();
//        testerAccepterConsultation();
//       testerTerminerConsultation();
//        
//        testerGetFeedback();
//        testerAskInspiration();
        
      //----------tests-secondaires------//
      
//        scenarioUnClient();
//        scenarioPlusieursDemandeConsultation(); //Pour ce scenario pensez à commenter l'initialisation des employes !

        JpaUtil.destroy();
    }
    
//------------------------------------------------------------------------------    
//---------------------------- INITIALISATIONS ---------------------------------
//------------------------------------------------------------------------------
    
    /*public static void initialiserClients() {
        
        System.out.println();
        System.out.println("**** initialiserClients() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fr.insalyon.dasi_Predict-IF_jar_1.0PU");
        EntityManager em = emf.createEntityManager();
        
        Date dateOfBirth = new Date(1912, 10, 12);
        
        
        Client alice = new Client(dateOfBirth, "20 av Albert Einstein", "alice.lovelace@insa-lyon.fr", "Alice1012", "0667897668", "Alice", "Lovelace");
        Client kevin = new Client(dateOfBirth, "20 av Albert Einstein", "kevin.lovelace@insa-lyon.fr", "KEKE", "0667897669", "Kevin", "keke");
        
        AstroTest astroTest = new AstroTest();
            List<String> listeAstroProfile;   
        try {
            listeAstroProfile = astroTest.getProfil(alice.getFirstName(), alice.getBirthDate());
            
            AstralProfile astralProfile = new AstralProfile(listeAstroProfile.get(0), listeAstroProfile.get(1), listeAstroProfile.get(2), listeAstroProfile.get(3));
            alice.setAstralProfile(astralProfile);

            System.out.println("** Client avant persistance: ");
            afficherClient(alice);
            afficherClient(kevin);
            System.out.println();

            try {
                em.getTransaction().begin();
                em.persist(alice);
                em.persist(kevin);
                em.getTransaction().commit();
            } catch (Exception ex) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserClients()", ex);
                try {
                    em.getTransaction().rollback();
                }
                catch (IllegalStateException ex2) {
                    // Ignorer cette exception...
                }
            } finally {
                em.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Exception lors de l'appel au Service initialiserClients()", ex);
        }
        
        System.out.println("** Client après persistance: ");
        afficherClient(alice);
        afficherClient(kevin);
        System.out.println();
    }
    */
    
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
    }
    
                //-----------------------//

    public static void testerListerClients() {
        
        System.out.println();
        System.out.println("**** testerListeClients() ****");
        System.out.println();
        
        Service service = new Service();
        List<Client> listeClients = service.ListerClients();
        System.out.println("*** Liste des Clients");
        if (listeClients != null) {
            listeClients.forEach((client) -> {
                afficherClient(client);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    */
    
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
    public static void afficherprojet(Projet projet) {
        System.out.println("-> " + projet);
    }
    public static void afficherSousCategorieConstruction(SousCategorieConstruction sousCategorieConstruction) {
        System.out.println("-> " + sousCategorieConstruction);
    }
    public static void afficherSousFamille(SousFamille sousFamille) {
        System.out.println("-> " + sousFamille);
    }
}