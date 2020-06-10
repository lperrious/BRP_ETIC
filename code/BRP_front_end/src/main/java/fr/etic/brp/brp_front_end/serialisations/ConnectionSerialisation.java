package fr.etic.brp.brp_front_end.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author louisrob
 */
public class ConnectionSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject container = new JsonObject(); //Objet "conteneur JSON" pour structurer les données à sérialiser
        
        //Lecture des attributs de la requête (stockés par l'action)
        boolean ErrorState = (boolean)request.getAttribute("ErrorState");
        Operateur operateur = (Operateur)request.getAttribute("Operateur");
        
        container.addProperty("Error", ErrorState);
        
        if(ErrorState == false) //Deux sorties possibles
        {   //On créer un cookie
            JsonObject jsonOperateur = new JsonObject();
            jsonOperateur.addProperty("idOperateur", operateur.getIdOperateur());
            container.add("operateur", jsonOperateur);
        }
        
        //Formatage de la structure de données JSON => Ecriture sur le flux de sortie de la réponse
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}