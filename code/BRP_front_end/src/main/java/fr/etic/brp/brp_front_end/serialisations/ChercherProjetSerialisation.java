package fr.etic.brp.brp_front_end.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import fr.etic.brp.brp_back_end.metier.modele.Projet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author quentinmarc
 */
public class ChercherProjetSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject container = new JsonObject(); //Objet "conteneur JSON" pour structurer les données à sérialiser
        
        //Lecture des attributs de la requête (stockés par l'action)
        boolean Error = (boolean)request.getAttribute("ErrorState");
        List<Projet> listeProjets = (List<Projet>)request.getAttribute("listeProjets");
        
        container.addProperty("Error", Error);
        
        if(!Error){
            JsonArray jaProjets = new JsonArray();
            for(int i = 0; i < listeProjets.size(); i++) {
                Projet projet = listeProjets.get(i);
                JsonObject containerProjet = new JsonObject();
                containerProjet.addProperty("id", projet.getIdProjet());
                containerProjet.addProperty("nom", projet.getNomProjet());
                jaProjets.add(containerProjet);
            }
            container.add("listeProjets", jaProjets);
        }
        
        //Formatage de la structure de données JSON => Ecriture sur le flux de sortie de la réponse
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}