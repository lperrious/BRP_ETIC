package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.modele.Projet;
import fr.etic.brp.brp_back_end.metier.service.Service;
import static java.lang.Long.parseLong;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author louisrob
 */
public class OuvrirProjetAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        Long idProjet = parseLong(request.getParameter("idProjet"));
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
        Projet projet = null;
        try{
            projet = service.RechercherProjetParId(idProjet);
        }
        catch(Exception e) //Stockage de l'erreur dans l'attribut de la requete
        {
            request.setAttribute("ErrorState", true);
        }

        //Stockage des résultats dans les attributs de la requête
        if(projet != null)
        {
            request.setAttribute("ErrorState", false);
            request.setAttribute("projet", projet);      
        } else {
            request.setAttribute("ErrorState", true);
        }
    }
}