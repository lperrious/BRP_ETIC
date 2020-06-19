package fr.etic.brp.brp_front_end.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.etic.brp.brp_back_end.metier.modele.CaractDim;
import fr.etic.brp.brp_back_end.metier.modele.SousCategorieConstruction;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author louisrob
 */
public class ListerSousCatConstCaractDimSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject container = new JsonObject(); //Objet "conteneur JSON" pour structurer les données à sérialiser
        
        //Lecture des attributs de la requête (stockés par l'action)
        boolean ErrorState = (boolean)request.getAttribute("ErrorState");
        
        container.addProperty("ErrorState", ErrorState);
        
        if(ErrorState == false) //Deux sorties possibles
        {   
            List<SousCategorieConstruction> listeSousCategorieConstruction = (List<SousCategorieConstruction>)request.getAttribute("ListeSousCategorieConstruction");
            List<CaractDim> listeCaractDim = (List<CaractDim>)request.getAttribute("ListeCaractDim");
            JsonArray jaListeSousCategorieConstruction = new JsonArray();
            for(int i = 0; i < listeSousCategorieConstruction.size(); i++) {
                JsonObject containerSousCategorieConstruction = new JsonObject();
                containerSousCategorieConstruction.addProperty("intitule", listeSousCategorieConstruction.get(i).getIntituleSousCategorieConstruction());
                containerSousCategorieConstruction.addProperty("id", listeSousCategorieConstruction.get(i).getIdSousCategorieConstruction());
                jaListeSousCategorieConstruction.add(containerSousCategorieConstruction);
            }
            JsonArray jaListeCaractDim = new JsonArray();
            for(int i = 0; i < listeCaractDim.size(); i++) {
                JsonObject containerCaractDim = new JsonObject();
                containerCaractDim.addProperty("valeur", listeCaractDim.get(i).getValeur());
                containerCaractDim.addProperty("code", listeCaractDim.get(i).getCodeCaractDim());
                jaListeCaractDim.add(containerCaractDim);
            }
            container.add("listeSousCategorieConstruction", jaListeSousCategorieConstruction);
            container.add("listeCaractDim", jaListeCaractDim);
        }
        
        //Formatage de la structure de données JSON => Ecriture sur le flux de sortie de la réponse
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}