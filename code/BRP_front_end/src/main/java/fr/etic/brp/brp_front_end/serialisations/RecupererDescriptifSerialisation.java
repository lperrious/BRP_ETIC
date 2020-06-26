package fr.etic.brp.brp_front_end.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.etic.brp.brp_back_end.metier.modele.Descriptif;
import fr.etic.brp.brp_back_end.metier.modele.Ouvrage;
import fr.etic.brp.brp_back_end.metier.modele.Prestation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author quentinmarc
 */
public class RecupererDescriptifSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject container = new JsonObject(); //Objet "conteneur JSON" pour structurer les données à sérialiser
        
        //Lecture des attributs de la requête (stockés par l'action)
        Boolean ErrorState = (boolean)request.getAttribute("ErrorState");
        
        container.addProperty("ErrorState", ErrorState);
        
        if(ErrorState == false)
        {   
            Descriptif descriptif = (Descriptif)request.getAttribute("descriptif");
            container.addProperty("nomDescriptif", descriptif.getNomDescriptif());
            container.addProperty("descriptionDescriptif", descriptif.getDescription());
            container.addProperty("courteDescriptionDescriptif", descriptif.getCourteDescription());
            if(descriptif instanceof Ouvrage) {
                container.addProperty("typeDescriptif", "ouvrage");
                container.addProperty("unite", ((Ouvrage)descriptif).getUnite());
            } else if (descriptif instanceof Prestation){
                container.addProperty("typeDescriptif", "prestation");
                container.addProperty("unite", ((Prestation)descriptif).getUnite());
            } else {
                container.addProperty("typeDescriptif", "generique");
            }
        }
        
        //Formatage de la structure de données JSON => Ecriture sur le flux de sortie de la réponse
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}