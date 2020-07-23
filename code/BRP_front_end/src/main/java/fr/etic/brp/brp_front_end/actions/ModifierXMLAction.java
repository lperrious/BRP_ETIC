package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.service.Service;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;
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
        String idRefPlacement = request.getParameter("idRefPlacement");
        String placement = request.getParameter("placement");
        String intitule = null;
        String idDescriptif = null;
        String nomDescriptif = null;
        String description = null;
        String localisation = null;
        Double quantite = null;
        String titreType = null;
        String idInsere = null;
        
        
        switch(type){
            case "titre":
                intitule = request.getParameter("intitule");
                titreType = request.getParameter("titreType");
                break;
            case "descriptif":
                idDescriptif = request.getParameter("idDescriptif");
                nomDescriptif = request.getParameter("nomDescriptif");
                description = request.getParameter("description");
                break;
            case "ligneChiffrage":
                localisation = request.getParameter("localisation");
                if(!request.getParameter("quantite").equals(""))
                    quantite = parseDouble(request.getParameter("quantite"));
                else quantite = 0.0;
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
                        //on ajoute et on retourne l'idXML
                        switch(titreType){
                            case "lot":
                                testModification = service.AjouterLot(idProjet, placement, idRefPlacement);
                                break;
                            case "titre1":
                                testModification = service.AjouterTitre1(idProjet, placement, idRefPlacement);
                                break;
                            case "titre2":
                                testModification = service.AjouterTitre2(idProjet, placement, idRefPlacement);
                                break;
                            case "titre3":
                                testModification = service.AjouterTitre3(idProjet, placement, idRefPlacement);
                                break;
                            case "titre4":
                                testModification = service.AjouterTitre4(idProjet, placement, idRefPlacement);
                                break;
                        }
                        //on va chercher l'idInsere si on était dans une insertion
                        if(testModification)
                            idInsere = service.GetIdInsere(idProjet);
                    }else{
                        testModification = service.ModifierIntituleTitre(idProjet, id, intitule);
                    }
                    break;
                case "descriptif":
                    //c'est un ajout
                    if("_0".equals(id)){
                        testModification = service.AjouterDescriptif(idProjet, placement, idRefPlacement, idDescriptif);
                        //on va chercher l'idInsere si on était dans une insertion
                        if(testModification)
                            idInsere = service.GetIdInsere(idProjet);
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
                        testModification = service.AjouterLigneChiffrage(idProjet, idRefPlacement);
                        if(testModification)
                            idInsere = "_0";
                    }else{
                        //on modifie la localisation
                        testModification = service.ModifierLocalisationDescriptif(idProjet,idRefPlacement, id, localisation);
                        //on modifie la quantite et le prix unitaire
                        if(testModification)
                            testModification = service.ModifierQuantiteDescriptif(idProjet,idRefPlacement, id, quantite);
                    }
                    break;
            }
            request.setAttribute("ErrorState", !testModification);
            request.setAttribute("idInsere", idInsere);     //a faire pour balise
        }catch(Exception e){
            request.setAttribute("ErrorState", true);
        }
    }
}