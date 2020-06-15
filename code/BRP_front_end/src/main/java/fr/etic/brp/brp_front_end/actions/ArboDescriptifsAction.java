package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.modele.Chapitre;
import fr.etic.brp.brp_back_end.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author louisrob
 */
public class ArboDescriptifsAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
        List<Chapitre> resultat = null;
        try{
            resultat = service.ListerChapitres();
        }
        catch(Exception e) //Stockage de l'erreur dans l'attribut de la requete
        {
            request.setAttribute("Error", true);
        }

        //Stockage des résultats dans les attributs de la requête
        if(resultat != null)
        {
            request.setAttribute("Error", false);
            request.setAttribute("Chapitres", resultat);      
        } else {
            request.setAttribute("Error", true);
        }
    }
}