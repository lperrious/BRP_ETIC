package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import fr.etic.brp.brp_back_end.metier.service.Service;
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
        
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
        

        //Stockage des résultats dans les attributs de la requête
        request.setAttribute("ErrorState", false);
        request.setAttribute("idProjet", idProjet);
        
    }
}