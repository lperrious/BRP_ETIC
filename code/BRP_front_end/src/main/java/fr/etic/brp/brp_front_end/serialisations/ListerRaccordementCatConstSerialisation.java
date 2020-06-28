package fr.etic.brp.brp_front_end.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.etic.brp.brp_back_end.metier.modele.CategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.CoeffRaccordement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author louisrob
 */
public class ListerRaccordementCatConstSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject container = new JsonObject(); //Objet "conteneur JSON" pour structurer les données à sérialiser
        
        //Lecture des attributs de la requête (stockés par l'action)
        boolean ErrorState = (boolean)request.getAttribute("ErrorState");
        
        container.addProperty("ErrorState", ErrorState);
        
        if(ErrorState == false) //Deux sorties possibles
        {   
            List<CoeffRaccordement> listeCoeffRaccordement = (List<CoeffRaccordement>)request.getAttribute("ListeCoeffRacordement");
            List<CategorieConstruction> listeCategorieConstruction = (List<CategorieConstruction>)request.getAttribute("ListeCategorieConstruction");
            JsonArray jaListeCoeffRaccordement = new JsonArray();
            for(int i = 0; i < listeCoeffRaccordement.size(); i++) {
                JsonObject containerCoeffRaccordement = new JsonObject();
                containerCoeffRaccordement.addProperty("localisation", listeCoeffRaccordement.get(i).getLocalisation());
                containerCoeffRaccordement.addProperty("valeur", listeCoeffRaccordement.get(i).getValeur());
                containerCoeffRaccordement.addProperty("id", listeCoeffRaccordement.get(i).getIdCoeffRaccordement());
                jaListeCoeffRaccordement.add(containerCoeffRaccordement);
            }
            JsonArray jaListeCategorieConstruction = new JsonArray();
            for(int i = 0; i < listeCategorieConstruction.size(); i++) {
                JsonObject containerCategorieConstruction = new JsonObject();
                containerCategorieConstruction.addProperty("intitule", listeCategorieConstruction.get(i).getIntituleCategorieConstruction());
                containerCategorieConstruction.addProperty("code", listeCategorieConstruction.get(i).getCodeCategorieConstruction());
                jaListeCategorieConstruction.add(containerCategorieConstruction);
            }
            container.add("listeCoeffRaccordement", jaListeCoeffRaccordement);
            container.add("listeCategorieConstruction", jaListeCategorieConstruction);
        }
        
        //Formatage de la structure de données JSON => Ecriture sur le flux de sortie de la réponse
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}