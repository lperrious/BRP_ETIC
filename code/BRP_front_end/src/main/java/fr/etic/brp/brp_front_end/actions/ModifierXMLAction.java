package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import fr.etic.brp.brp_back_end.metier.service.Service;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */
public class ModifierXMLAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        Long idProjet = parseLong(request.getParameter("idProjet"));
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        String idParent = request.getParameter("id_parent");
        String idBefore = request.getParameter("id_before");
        String intitule = null;
        //String idDescriptif = null;
        String nomDescriptif = null;
        String description = null;
        String localisation = null;
        Double quantite = null;
        
        
        switch(type){
            case "titre":
                intitule = request.getParameter("intitule");
                break;
            case "descriptif":
                //idDescriptif = request.getParameter("idDescriptif");
                nomDescriptif = request.getParameter("nomDescriptif");
                description = request.getParameter("description");
                break;
            case "ligneChiffrage":
                localisation = request.getParameter("localisation");
                quantite = parseDouble(request.getParameter("quantite"));
                break;
        }
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
        Boolean testModification = null;
        try{
            switch(type){
                case "titre":
                    //c'est un ajout
                    if("_0".equals(id)){
                        //on cherche l'IdParent (s'il ny'en a pas, c'est un lot) et on détermine la balise
                        //on détermine si c'est un APPEND ou BEFORE
                        //on ajoute et on retourne l'idXML
                    }else{
                        testModification = service.ModifierIntituleTitre(idProjet, id, intitule);
                    }
                    break;
                case "descriptif":
                    //c'est un ajout
                    if("_0".equals(id)){
                        //on détermine si c'est un APPEND ou BEFORE
                        //on ajoute et on retourne l'idXML
                    }else{
                        //on modifie la description
                        testModification = service.ModifierDescriptionDescriptif(idProjet, id, description);
                        //on modifie le nom
                        if(testModification)
                            testModification = service.ModifierNomDescriptif(idProjet, id, nomDescriptif);
                    }
                    break;
                case "ligneChiffrage":
                    //c'est un ajout
                    if("0".equals(id)){
                        //on ajoute et on retourne l'idXML
                    }else{
                        //on modifie la localisation
                        testModification = service.ModifierLocalisationDescriptif(idProjet,idParent, id, localisation);
                        //on modifie la quantite et le prix unitaire
                        if(testModification)
                            testModification = service.ModifierQuantiteDescriptif(idProjet,idParent, id, quantite);
                    }
                    break;
            }
            request.setAttribute("ErrorState", false);
        }catch(Exception e){
            request.setAttribute("ErrorState", true);
        }
    }
}