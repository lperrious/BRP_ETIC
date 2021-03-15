package fr.etic.brp.brp_front_end.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.etic.brp.brp_back_end.metier.modele.Projet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author louisrob
 */
public class OuvrirProjetSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject container = new JsonObject(); //Objet "conteneur JSON" pour structurer les données à sérialiser
        
        //Lecture des attributs de la requête (stockés par l'action)
        boolean ErrorState = (boolean)request.getAttribute("ErrorState");
        
        container.addProperty("ErrorState", ErrorState);
        
        if(ErrorState == false) //Deux sorties possibles
        {   
            Projet projet = (Projet)request.getAttribute("projet");
            container.addProperty("nomProjet", projet.getNomProjet());
            if(projet.getRefBRP() != null) container.addProperty("refBRP", projet.getRefBRP());
            if(projet.getTypeMarche() != null) container.addProperty("typeMarche", projet.getTypeMarche().toString());
            if(projet.getTypeConstruction() != null) container.addProperty("typeConstruction", projet.getTypeConstruction().toString());
            if(projet.getTypeLot() != null) container.addProperty("typeLot", projet.getTypeLot().toString());
            if(projet.getSite() != null) container.addProperty("site", projet.getSite().toString());
            if(projet.getDatePrixRef() != null) container.addProperty("anneeRef", projet.getDatePrixRef().toString());
            if(projet.getCoeffAdapt() != null) container.addProperty("coeffAdapt", projet.getCoeffAdapt());
            if(projet.getCoeffRaccordement() != null) {
                container.addProperty("localisationCoeffRaccordement", projet.getCoeffRaccordement().getLocalisation());
                container.addProperty("valeurCoeffRaccordement", projet.getCoeffRaccordement().getValeur());
            }
            if(projet.getCategorieConstruction() != null)
                container.addProperty("categorieConstruction", projet.getCategorieConstruction().getCodeCategorieConstruction());
            if(projet.getSousCategorieConstructionSelection() != null)
                container.addProperty("idSousCategorieConstruction", projet.getSousCategorieConstructionSelection().getIdSousCategorieConstruction());
            if(projet.getCaractDimSelection() != null)
                container.addProperty("codeCaractDim", projet.getCaractDimSelection().getCodeCaractDim());
        }
        
        //Formatage de la structure de données JSON => Ecriture sur le flux de sortie de la réponse
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}