package fr.etic.brp.brp_front_end.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.etic.brp.brp_back_end.metier.modele.Categorie;
import fr.etic.brp.brp_back_end.metier.modele.Chapitre;
import fr.etic.brp.brp_back_end.metier.modele.Descriptif;
import fr.etic.brp.brp_back_end.metier.modele.Famille;
import fr.etic.brp.brp_back_end.metier.modele.Generique;
import fr.etic.brp.brp_back_end.metier.modele.Ouvrage;
import fr.etic.brp.brp_back_end.metier.modele.Prestation;
import fr.etic.brp.brp_back_end.metier.modele.SousFamille;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author louisrob
 */
public class ArboDescriptifsSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject container = new JsonObject(); //Objet "conteneur JSON" pour structurer les données à sérialiser
        
        //Lecture des attributs de la requête (stockés par l'action)
        boolean Error = (boolean)request.getAttribute("Error");
        
        container.addProperty("Error", Error);
        
        if(Error == false) //Deux sorties possibles
        {   
            List<Chapitre> chapitres = (List<Chapitre>)request.getAttribute("Chapitres");
            //On créer l'array JSON contenant toute l'arbo
            JsonArray jaChapitres = new JsonArray();
            for(int i = 0; i < chapitres.size(); i++) {
                Chapitre chapitre = chapitres.get(i);
                JsonObject containerChapitre = new JsonObject();
                containerChapitre.addProperty("intituleChapitre", chapitre.getIntituleChapitre());
                containerChapitre.addProperty("idChapitre", chapitre.getIdChapitre());
                JsonArray jaCategories = new JsonArray();
                List<Categorie> categories = chapitre.getListCategorie();
                for(int j = 0; j < categories.size(); j++) {
                    Categorie categorie = categories.get(j);
                    JsonObject containerCategorie = new JsonObject();
                    containerCategorie.addProperty("intituleCategorie", categorie.getIntituleCategorie());
                    containerCategorie.addProperty("idCategorie", categorie.getIdCategorie());
                    JsonArray jaFamilles = new JsonArray();
                    List<Famille> familles = categorie.getListeFamille();
                    for(int k = 0; k < familles.size(); k++) {
                        Famille famille = familles.get(k);
                        JsonObject containerFamille = new JsonObject();
                        containerFamille.addProperty("intituleFamille", famille.getIntituleFamille());
                        containerFamille.addProperty("idFamille", famille.getIdFamille());
                        JsonArray jaSousFamilles = new JsonArray();
                        List<SousFamille> sousFamilles = famille.getListSousFamille();
                        for(int l = 0; l < sousFamilles.size(); l++) {
                            SousFamille sousFamille = sousFamilles.get(l);
                            JsonObject containerSousFamille = new JsonObject();
                            containerSousFamille.addProperty("intituleSousFamille", sousFamille.getIntituleSousFamille());
                            containerSousFamille.addProperty("idSousFamille", sousFamille.getIdSousFamille());
                            JsonArray jaDescriptifs = new JsonArray();
                            List<Descriptif> descriptifs = sousFamille.getListDescriptif();
                            for(int m = 0; m < descriptifs.size(); m++) {
                                Descriptif descriptif = descriptifs.get(m);
                                JsonObject containerDescriptif = new JsonObject();
                                if(descriptif instanceof Generique)
                                    containerDescriptif.addProperty("type", "generique");
                                else if(descriptif instanceof Ouvrage) {
                                    containerDescriptif.addProperty("type", "ouvrage");
                                    JsonArray jaPrestations = new JsonArray();
                                    List<Prestation> prestations = ((Ouvrage) descriptif).getListePrestation();
                                    for(int n = 0; n < prestations.size(); n++) {
                                        Prestation prestation = prestations.get(n);
                                        JsonObject containerPrestation = new JsonObject();
                                        containerPrestation.addProperty("type", "prestation");
                                        containerPrestation.addProperty("id", prestation.getIdDescriptif());
                                        containerPrestation.addProperty("nom", prestation.getNomDescriptif());
                                        containerPrestation.addProperty("description", prestation.getDescription());
                                        containerPrestation.addProperty("courteDescription", prestation.getCourteDescription());
                                        jaPrestations.add(containerPrestation);
                                    }
                                    containerDescriptif.add("prestations", jaPrestations);
                                }
                                containerDescriptif.addProperty("id", descriptif.getIdDescriptif());
                                containerDescriptif.addProperty("nom", descriptif.getNomDescriptif());
                                containerDescriptif.addProperty("description", descriptif.getDescription());
                                containerDescriptif.addProperty("courteDescription", descriptif.getCourteDescription());
                                jaDescriptifs.add(containerDescriptif);
                            }
                            containerSousFamille.add("descriptifs", jaDescriptifs);
                            jaSousFamilles.add(containerSousFamille);
                        }
                        containerFamille.add("sousFamilles", jaSousFamilles);
                        jaFamilles.add(containerFamille);
                    }
                    containerCategorie.add("familles", jaFamilles);
                    jaCategories.add(containerCategorie);
                }
                containerChapitre.add("categories", jaCategories);
                jaChapitres.add(containerChapitre);
            }
            container.add("arborescence", jaChapitres);
        }
        
        //Formatage de la structure de données JSON => Ecriture sur le flux de sortie de la réponse
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}