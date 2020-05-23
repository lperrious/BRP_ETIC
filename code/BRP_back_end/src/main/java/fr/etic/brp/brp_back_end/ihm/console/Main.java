package fr.etic.brp.brp_back_end.ihm.console;

import com.google.common.hash.Hashing;
import fr.etic.brp.brp_back_end.dao.DomUtil;
import fr.etic.brp.brp_back_end.dao.JpaUtil;
import fr.etic.brp.brp_back_end.metier.modele.BasePrixRef;
import fr.etic.brp.brp_back_end.metier.modele.CaractDim;
import fr.etic.brp.brp_back_end.metier.modele.Categorie;
import fr.etic.brp.brp_back_end.metier.modele.CategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.CoeffRaccordement;
import fr.etic.brp.brp_back_end.metier.modele.Chapitre;
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
import fr.etic.brp.brp_back_end.metier.service.ImportService;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

/**
 *
 * @author louisrob
 */
public class Main {
    
    public static void main(String[] args) throws IOException {

        // Contrôlez l'affichage du log de JpaUtil grâce à la méthode log de la classe JpaUtil
        JpaUtil.init();
        DomUtil.init();

    //------------initialisations------------------
    
        // A faire tout le temps (l'ordre est important)
        InitialiserBasePrixRef();
        InitialiserCaractDim();
        InitialiserSousCategorieConstruction();
//        InitialiserCategorie();
        InitialiserCategorieConstruction();
        InitialiserCoeffRaccordement();
//        InitialiserChapitre();
        InitialiserDescriptif();
//        InitialiserFamille();
        InitialiserOperateur();
        InitialiserProjets();
//        InitialiserSousFamille();

        
    //----------tests-des-services-----------------
    
      //---------tests-primaires--------//
      

//        testerListerBasePrixRefs();
//        testerListerCaractDims();
//        testerListerCategories();
//        testerListerCategorieConstructions();
//        testerListerCoeffRaccordements();
//        testerListerChapitres();
//        testerListerDescriptifs();
//        testerListerFamilles();
//        testerListerOperateurs();
//        testerListerPrestations();
//        testerListerProjets();
//        testerListerSousCategorieConstructions();
//        testerListerSousFamilles();
//        testerAuthentifierOperateur();
//        testerCreerProjet();
//        testerRechercherProjetParId();
//        testerDupliquerProjet();
//        testerEditerNomProjet();
//        testerEditerInfoEnumProjet();
//        testerEditerDateProjet();
//        testerEditerCoeffAdaptProjet();
//        testerEditerCoeffRaccordementProjet();
//        testerEditerCategorieConstructionProjet();
//        testerAjouterChapitre();
//        testerAjouterCategorie();
//        testerAjouterFamille();
//        testerAjouterSousFamille();
//        testerAjouterOuvrageOuGenerique();
//        testerAjouterPrestation();
//        testerAjouterLigneChiffrage();
//        testerCoutSynthese();
//        testerSupprimerChapitre();
//        testerSupprimerCategorie();
//        testerSupprimerFamille();
//        testerSupprimerSousFamille();
//        testerSupprimerDescriptif();
//        testerSupprimerLigneChiffrage();
//        testerModifierDescriptionDescriptif();
//        testerModifierCourteDescriptionDescriptif();
//        testerModifierLocalisationDescriptif();
//        testerModifierQuantiteDescriptif();
//        testerModifBaseDescriptif();
//        testerModifBasePrixRef();
        
      //----------tests-secondaires------//
      
//        scenarioUnClient();
//        scenarioPlusieursDemandeConsultation(); //Pour ce scenario pensez à commenter l'initialisation des employes !

        JpaUtil.destroy();
        DomUtil.destroy();
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
        
        BasePrixRef basePrixRef = new BasePrixRef(2018, 1L, 1.0, 1.0, 3.0, "m2", 10.0);
        BasePrixRef basePrixRef2 = new BasePrixRef(2017, 2L, 1.0, 4.0, 6.0, "m2", 4.0);
        BasePrixRef basePrixRef3 = new BasePrixRef(2019, 1L, 1.0, 1.0, 3.0, "m2", 120.0);
        System.out.println("** BasePrixRef avant persistance: ");
        afficherBasePrixRef(basePrixRef);
        afficherBasePrixRef(basePrixRef2);
        afficherBasePrixRef(basePrixRef3);
        System.out.println();
        

        try {
            em.getTransaction().begin();
            em.persist(basePrixRef);
            em.persist(basePrixRef2);
            em.persist(basePrixRef3);
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
        afficherBasePrixRef(basePrixRef2);
        afficherBasePrixRef(basePrixRef3);
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
            
            //On crée le XML associé
            String uri = "../XMLfiles/1.xml";
            DocumentBuilder builder = DomUtil.obtenirBuilder();
            Document xml = builder.newDocument();
            
            //Création de la racine
            Element baliseProjet = xml.createElement("projet");
            baliseProjet.setAttribute("idProjet", "1");
            xml.appendChild(baliseProjet);
            
            //Ecriture du XML
            Transformer transformer = DomUtil.obtenirTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            DOMImplementation domImpl = xml.getImplementation();
            DocumentType doctype = domImpl.createDocumentType("doctype", "-//Oberon//YOUR PUBLIC DOCTYPE//EN", "reglesProjet.dtd");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
            DOMSource domSource = new DOMSource(xml);
            StreamResult streamResult = new StreamResult(uri);
            transformer.transform(domSource, streamResult);
            
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
    public static void InitialiserCategorie() {
        
        System.out.println();
        System.out.println("**** InitialiserCategorie() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();        
        
        Categorie categorie1 = new Categorie("02_AAA","intitule1");
         
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
        
        Generique generique1 = new Generique("02_AAA_01_01_01", "nomDescriptif1", "descriptionGenerique1", "courteDescriptionGenerique1");
        Ouvrage ouvrage1 = new Ouvrage("02_AAA_01_01_02", "nomOuvrage1", "descriptionOuvrage1", "courteDescriptionOuvrage1");
        Prestation prestation1 = new Prestation("02_AAA_01_01_02_01", "nomPrestation1", "descriptionPrestation1", "courteDescriptionPrestation1");
         
        System.out.println("** Descriptif avant persistance: ");
        afficherGenerique(generique1);
        afficherOuvrage(ouvrage1);
        afficherPrestation(prestation1);
        System.out.println();

        try {
            em.getTransaction().begin();
            //ajout des basePrixRef à un ouvrage et à une prestation
            List<BasePrixRef> listeBasePrixRefOuvrage = new ArrayList();
            listeBasePrixRefOuvrage.add(em.find(BasePrixRef.class, 1L));
            listeBasePrixRefOuvrage.add(em.find(BasePrixRef.class, 2L));
            ouvrage1.setListeBasePrixRefOuvrage(listeBasePrixRefOuvrage);
            
            List<BasePrixRef> listeBasePrixRefPrestation = new ArrayList();
            listeBasePrixRefPrestation.add(em.find(BasePrixRef.class, 3L));
            prestation1.setListeBasePrixRefPrestation(listeBasePrixRefPrestation);
            
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
        
        SousFamille sousFamille1 = new SousFamille("02_AAA_01_01","intituleSousFamille1");
         
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
    public static void InitialiserChapitre() {
        
        System.out.println();
        System.out.println("**** initialiserChapitre() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();        
        
        Chapitre chapitre1 = new Chapitre("01","chapitre1");
        Chapitre chapitre2 = new Chapitre("02","chapitre2"); 
        
        System.out.println("** Chapitre avant persistance: ");
        afficherChapitre(chapitre1);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(chapitre1);
            em.getTransaction().commit();
            
            em.getTransaction().begin();    //REMOVE
            em.persist(chapitre2);         //REMOVE
            em.getTransaction().commit();   //REMOVE
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserChapitre()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        System.out.println("** Chapitre après persistance: ");
        afficherChapitre(chapitre1);
        System.out.println();
    }
    public static void InitialiserFamille() {
        
        System.out.println();
        System.out.println("**** InitialiserFamille() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BRP_PU");
        EntityManager em = emf.createEntityManager();        
        
        Famille famille1 = new Famille("02_AAA_01","famille1");
         
        System.out.println("** Chapitre avant persistance: ");
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
    
    public static void testerEditerNomProjet() {
        
        System.out.println();
        System.out.println("**** testerEditerNomProjet() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String nouveauNomProjet = "nouveauNomTest";
        
        Boolean resultat = service.EditerNomProjet(idProjet, nouveauNomProjet);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
       
        //Ne doit pas fonctionner
        Long idProjet2 = 100L;
        String nouveauNomProjet2 = "nouveauNomTest2";
        
        Boolean resultat2 = service.EditerNomProjet(idProjet2, nouveauNomProjet2);
        if(resultat2)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet2);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet2);
        }
    }
    
    public static void testerEditerInfoEnumProjet() {
        
        System.out.println();
        System.out.println("**** testerEditerInfoEnumProjet() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String typeEnum = "TypeMarche";
        String valeurEnum = "marchePublic";
        
        Boolean resultat = service.EditerInfoEnumProjet(idProjet, typeEnum, valeurEnum);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
       
        //Doit fonctionner
        Long idProjet2 = 1L;
        String typeEnum2 = "TypeMarche";
        String valeurEnum2 = "marchePrive";
        
        Boolean resultat2 = service.EditerInfoEnumProjet(idProjet2, typeEnum2, valeurEnum2);
        if(resultat2)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet2);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet2);
        }
        
        //Ne doit pas fonctionner
        Long idProjet3 = 1L;
        String typeEnum3 = "Type non existant";
        String valeurEnum3 = "valeur non existante";
        
        Boolean resultat3 = service.EditerInfoEnumProjet(idProjet3, typeEnum3, valeurEnum3);
        if(resultat3)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet3);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet3);
        }
        
        //Ne doit pas fonctionner
        Long idProjet4 = 1L;
        String typeEnum4 = "TypeMarche";
        String valeurEnum4 = "valeur non existante";
        
        Boolean resultat4 = service.EditerInfoEnumProjet(idProjet4, typeEnum4, valeurEnum4);
        if(resultat4)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet4);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet4);
        }
        
        //Doit fonctionner
        Long idProjet5 = 1L;
        String typeEnum5 = "TypeConstruction";
        String valeurEnum5 = "neuf";
        
        Boolean resultat5 = service.EditerInfoEnumProjet(idProjet5, typeEnum5, valeurEnum5);
        if(resultat5)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet5);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet5);
        }
        
        //Ne doit pas fonctionner
        Long idProjet6 = 100L;
        String typeEnum6 = "TypeConstruction";
        String valeurEnum6 = "neuf";
        
        Boolean resultat6 = service.EditerInfoEnumProjet(idProjet6, typeEnum6, valeurEnum6);
        if(resultat6)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet6);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet6);
        }
        
        //Possibilité de faire d'autres tests
    }
    
    public static void testerEditerDateProjet() {
        
        System.out.println();
        System.out.println("**** testerEditerDateProjet() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        
        Boolean resultat = service.EditerDateProjet(idProjet, new Date());
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
       
        //Ne doit pas fonctionner
        Long idProjet2 = 100L;
        
        Boolean resultat2 = service.EditerDateProjet(idProjet2, new Date());
        if(resultat2)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet2);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet2);
        }
    }
    
    public static void testerEditerCoeffAdaptProjet() {
        
        System.out.println();
        System.out.println("**** testerEditerCoeffAdaptProjet() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        Float nouveauCoeffAdapt = 6.F;
        
        Boolean resultat = service.EditerCoeffAdaptProjet(idProjet, nouveauCoeffAdapt);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
       
        //Ne doit pas fonctionner
        Long idProjet2 = 100L;
        Float nouveauCoeffAdapt2 = 6.F;
        
        Boolean resultat2 = service.EditerCoeffAdaptProjet(idProjet2, nouveauCoeffAdapt2);
        if(resultat2)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet2);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet2);
        }
    }
    
    public static void testerEditerCoeffRaccordementProjet() {
        
        System.out.println();
        System.out.println("**** testerEditerCoeffRaccordementProjet() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        Long idCoeffRaccordement = 1L;
        
        Boolean resultat = service.EditerCoeffRaccordementProjet(idProjet, idCoeffRaccordement);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
       
        //Ne doit pas fonctionner
        Long idProjet2 = 100L;
        Long idCoeffRaccordement2 = 1L;
        
        Boolean resultat2 = service.EditerCoeffRaccordementProjet(idProjet2, idCoeffRaccordement2);
        if(resultat2)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet2);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet2);
        }
        
        //Ne doit pas fonctionner
        Long idProjet3 = 1L;
        Long idCoeffRaccordement3 = 2L;
        
        Boolean resultat3 = service.EditerCoeffRaccordementProjet(idProjet3, idCoeffRaccordement3);
        if(resultat3)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet3);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet3);
        }
    }
    
    public static void testerEditerCategorieConstructionProjet() {
        
        System.out.println();
        System.out.println("**** testerEditerCategorieConstructionProjet() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        Long idCategorieConstruction = 1L;
        
        Boolean resultat = service.EditerCategorieConstructionProjet(idProjet, idCategorieConstruction);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
       
        //Ne doit pas fonctionner
        Long idProjet2 = 100L;
        Long idCategorieConstruction2 = 1L;
        
        Boolean resultat2 = service.EditerCategorieConstructionProjet(idProjet2, idCategorieConstruction2);
        if(resultat2)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet2);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet2);
        }
        
        //Ne doit pas fonctionner
        Long idProjet3 = 1L;
        Long idCategorieConstruction3 = 2L;
        
        Boolean resultat3 = service.EditerCategorieConstructionProjet(idProjet3, idCategorieConstruction3);
        if(resultat3)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet3);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet3);
        }
    }
    
    public static void testerCoutSynthese() {
        
        System.out.println();
        System.out.println("**** testerCoutSynthese() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String typeBalise = "chapitre";
        String idBalise = "2";
        
        Double resultat = service.CoutSynthese(idProjet, typeBalise, idBalise);
        if(resultat != null){
            System.out.println("Cout de synthèse calculé: "+resultat+"€");
        } else {
            System.out.println("Erreur lors du calcul du cout de synthèse");
        }
        
        //si la balise n'existe pas -> echec(comme prevu)
        //si la balise existe mais ne possède pas de prix -> 0€ (comme prevu)
        //si la balise existe et possède un prix -> prix renvoyé (comme prévu)
    }
    
    public static void testerAjouterChapitre() {
        
        System.out.println();
        System.out.println("**** testerAjouterChapitre() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String idChapitre = "01";
        
        Boolean resultat = service.AjouterChapitre(idProjet, idChapitre);
        if(resultat)
        {
            System.out.println("Ajout avec succès du chapitre "+idChapitre);
        } else {
            System.out.println("Echec lors de l'ajout du chapitre "+idChapitre);
        }
        
        //Doit fonctionner
        Long idProjet2 = 1L;
        String idChapitre2 = "02";
        
        Boolean resultat3 = service.AjouterChapitre(idProjet2, idChapitre2);
        if(resultat3)
        {
            System.out.println("Ajout avec succès du chapitre "+idChapitre2);
        } else {
            System.out.println("Echec lors de l'ajout du chapitre "+idChapitre2);
        }
        
        //idProjet n'existe pas -> echec (comme prevu)
    }
    
    public static void testerAjouterCategorie() {
        
        System.out.println();
        System.out.println("**** testerAjouterCategorie() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner 
        Long idProjet = 1L;
        String idCategorie = "02_AAA";
        
        Boolean resultat = service.AjouterCategorie(idProjet, idCategorie);
        if(resultat)
        {
            System.out.println("Ajout avec succès de la categorie "+idCategorie);
        } else {
            System.out.println("Echec lors de l'ajout de la categorie "+idCategorie);
        }
        
        //idProjet n'existe pas -> echec (comme prevu)
        //idCategorie n'existe pas -> echec (comme prevu)
    }
    
    public static void testerAjouterFamille() {
        
        System.out.println();
        System.out.println("**** testerAjouterFamille() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner 
        Long idProjet = 1L;
        String idFamille = "02_AAA_01";
        
        Boolean resultat = service.AjouterFamille(idProjet, idFamille);
        if(resultat){
            System.out.println("Ajout avec succès de la famille "+idFamille);
        } else {
            System.out.println("Echec lors de l'ajout de la famille "+idFamille);
        }
        
        //idProjet n'existe pas -> echec (comme prevu)
        //idFamille n'existe pas -> echec (comme prevu)
    }
    
    public static void testerAjouterSousFamille() {
        
        System.out.println();
        System.out.println("**** testerAjouterSousFamille() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String idSousFamille = "02_AAA_01_01";
        
        Boolean resultat = service.AjouterSousFamille(idProjet, idSousFamille);
        if(resultat){
            System.out.println("Ajout avec succès de la sousFamille "+idSousFamille);
        } else {
            System.out.println("Echec lors de l'ajout de la sousFamille "+idSousFamille);
        }
        
        //idProjet n'existe pas -> echec (comme prevu)
        //idSousFamille n'existe pas -> echec (comme prevu)
    }
    

    public static void testerAjouterOuvrageOuGenerique() {
        
        System.out.println();
        System.out.println("**** testerAjouterOuvrageOuGenerique() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner 
        Long idProjet = 1L;
        String idDescriptif = "02_AAA_01_01_02";
        
        Boolean resultat = service.AjouterOuvrageOuGenerique(idProjet, idDescriptif);
        if(resultat){
            System.out.println("Ajout avec succès du descriptif "+idDescriptif);
        } else {
            System.out.println("Echec lors de l'ajout du descriptif "+idDescriptif);
        }
        
        //idProjet n'existe pas -> echec (comme prevu)
        //choisit bien en fonction de l'année la plus récente et de la fourchette de prix
    }
    
    public static void testerAjouterPrestation() {
        
        System.out.println();
        System.out.println("**** testerAjouterPrestation() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner (sinsère uniquement dans le premier chapitre)
        Long idProjet = 1L;
        String idPrestation = "02_AAA_01_01_02_01";
        
        Boolean resultat = service.AjouterPrestation(idProjet, idPrestation);
        if(resultat){
            System.out.println("Ajout avec succès de la prestation "+idPrestation);
        } else {
            System.out.println("Echec lors de l'ajout de la prestation "+idPrestation);
        }
        
        //idProjet n'existe pas -> echec (comme prevu)
        //idOuvrage n'existe pas -> echec (comme prevu)
    }
    
    public static void testerAjouterLigneChiffrage() {
        
        System.out.println();
        System.out.println("**** testerAjouterLigneChiffrage() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner (sinsère uniquement dans le premier chapitre)
        Long idProjet = 1L;
        String idDescriptif = "02_AAA_01_01_02_01";
        
        Boolean resultat = service.AjouterLigneChiffrage(idProjet, idDescriptif);
        if(resultat){
            System.out.println("Edition avec succès du projet n°" + idProjet+", descriptif n°"+idDescriptif);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet+", descriptif n°"+idDescriptif);
        }
        
        //idProjet n'existe pas -> echec (comme prevu)
        //idDescriptif n'existe pas -> echec (comme prevu)
    }
        
    public static void testerSupprimerChapitre() {
        
        System.out.println();
        System.out.println("**** testerSupprimerChapitre() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String idChapitre = "02";
        
        Boolean resultat = service.SupprimerChapitre(idProjet, idChapitre);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
        
        //idChapitre n'existe pas/plus -> echec (comme prevu)
    }
    
    public static void testerSupprimerCategorie() {
        
        System.out.println();
        System.out.println("**** testerSupprimerCategorie() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String idCategorie = "02_AAA";
        
        Boolean resultat = service.SupprimerCategorie(idProjet, idCategorie);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
        
        //idCategorie n'existe pas/plus -> echec (comme prevu)
    }
    
    public static void testerSupprimerFamille() {
        
        System.out.println();
        System.out.println("**** testerSupprimerFamille() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String idFamille = "02_AAA_01";
        
        Boolean resultat = service.SupprimerFamille(idProjet, idFamille);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
        
        //idFamille n'existe pas/plus -> echec (comme prevu)
    }
    
    public static void testerSupprimerSousFamille() {
        
        System.out.println();
        System.out.println("**** testerSupprimerSousFamille() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String idFamille = "02_AAA_01_01";
        
        Boolean resultat = service.SupprimerSousFamille(idProjet, idFamille);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
        
        //idSousFamille n'existe pas/plus -> echec (comme prevu)
    }
    
    public static void testerSupprimerDescriptif() {
        
        System.out.println();
        System.out.println("**** testerSupprimerDescriptif() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String idDescriptif = "02_AAA_01_01_02_01";
        
        Boolean resultat = service.SupprimerDescriptif(idProjet, idDescriptif);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
        
        //test avec prestation ou ouvrage : ok
        //test pas de descriptif existant : ok
    }
    
    public static void testerSupprimerLigneChiffrage() {
        
        System.out.println();
        System.out.println("**** testerSupprimerLigneChiffrage() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String idDescriptif = "02_AAA_01_01_02_01";
        String idLigneChiffrage = "1";
        
        Boolean resultat = service.SupprimerLigneChiffrage(idProjet, idDescriptif, idLigneChiffrage);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
        
        //test pas de descrpitif existant : echec comme prévu
        //test pas de ligne chiffrage existante : echec comme prévu
        
    }
    
    public static void testerModifierDescriptionDescriptif() {
        
        System.out.println();
        System.out.println("**** testerModifierDescriptionDescriptif() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String idDescriptif = "02_AAA_01_01_02_01";
        String newDescription = "testNew";
        
        Boolean resultat = service.ModifierDescriptionDescriptif(idProjet, idDescriptif, newDescription);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
        
        //test pas de descriptif existant : echec comme prévu
        //test ouvrage ou prestation : ok
    }
    
    public static void testerModifierCourteDescriptionDescriptif() {
        
        System.out.println();
        System.out.println("**** testerModifierCourteDescriptionDescriptif() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String idDescriptif = "02_AAA_01_01_02";
        String newDescription = "testNew";
        
        Boolean resultat = service.ModifierCourteDescriptionDescriptif(idProjet, idDescriptif, newDescription);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
        
        //test pas de descriptif existant : echec comme prévu
        //test ouvrage ou prestation : ok
    }
    
    public static void testerModifierLocalisationDescriptif() {
        
        System.out.println();
        System.out.println("**** testerModifierLocalisationDescriptif() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String idDescriptif = "02_AAA_01_01_02_01";
        String idLigneChiffrage = "2";
        String newLocalisation = "newLoc";
        
        Boolean resultat = service.ModifierLocalisationDescriptif(idProjet, idDescriptif, idLigneChiffrage, newLocalisation);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
        
        //test pas de descriptif existant : echec comme prévu
        //test pas de ligneChiffrage existante: echec comme prévu
        //test ouvrage ou prestation : ok
    }
    
    public static void testerModifierQuantiteDescriptif() {
        
        System.out.println();
        System.out.println("**** testerModifierQuantiteDescriptif() ****");
        System.out.println();
        
        Service service = new Service();
        
        //Doit fonctionner
        Long idProjet = 1L;
        String idDescriptif = "02_AAA_01_01_02_01";
        String idLigneChiffrage = "1";
        Double quantite = 2.0;
        
        Boolean resultat = service.ModifierQuantiteDescriptif(idProjet, idDescriptif, idLigneChiffrage, quantite);
        if(resultat)
        {
            System.out.println("Edition avec succès du projet n°" + idProjet);
        } else {
            System.out.println("Erreur d'édition du projet n°" + idProjet);
        }
        
        //test modif prestation : ok
        //test modif prestation avec ligneChiffrage non existante : ok
        //test modif mauvaise quantité : ok
        //test modif ouvrage : ok
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
    public static void testerListerChapitres() {
        
        System.out.println();
        System.out.println("**** testerListerChapitres() ****");
        System.out.println();
        
        Service service = new Service();
        List<Chapitre> listeChapitres = service.ListerChapitres();
        System.out.println("*** Liste des Chapitres");
        if (listeChapitres != null) {
            listeChapitres.forEach((chapitre) -> {
                afficherChapitre(chapitre);
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
    
//------------------------------------------------------------------------------
//----------------------------------- Imports  ---------------------------------
//------------------------------------------------------------------------------
    
    public static void testerModifBaseDescriptif(){
        ImportService service = new ImportService();
        String msgSuppr = "";
        
        //returnListe[0] = status
        //les autres contiennent les identifiants à supprimer
        ArrayList<String> returnListe = service.ModifBaseDescriptif();
        
        //les ajouts se sont bien passés, on passe aux suppressions
        if(returnListe.get(0).equals("Succes")){
            for(int i = 1; i < returnListe.size(); i++){
                msgSuppr = service.CompterEnfants(returnListe.get(i));
                //on envoie au comptage des enfants
                if(msgSuppr.equals("suppr ok")){ //on supprime direct car pas d'enfant
                    System.out.println(service.SupprObjet(returnListe.get(i)));
                }
                else{ //on demande la permission au client
                    System.out.println(msgSuppr);
                    System.out.println(service.SupprObjet(returnListe.get(i)));
                }
            }
        }
        else{       //on affiche l'erreur
            System.out.println(returnListe.get(0));
        }
    }
    
    public static void testerModifBasePrixRef(){
        ImportService service = new ImportService();
        String msgState = service.ModifBasePrixRef();
        System.out.println(msgState);
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
    public static void afficherChapitre(Chapitre chapitre) {
        System.out.println("-> " + chapitre);
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