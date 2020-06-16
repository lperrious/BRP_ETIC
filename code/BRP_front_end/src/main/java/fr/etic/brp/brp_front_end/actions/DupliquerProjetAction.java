package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.service.Service;
import static java.lang.Long.parseLong;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */
public class DupliquerProjetAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        Long idProjet = parseLong(request.getParameter("idProjet"));
        String nomProjet = (String)request.getParameter("nomProjet");
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
        Long newIdProjet = null;
        try {
            newIdProjet = service.DupliquerProjet(idProjet, nomProjet);
        } catch(Exception ex) {
            request.setAttribute("ErrorState", true);
        }
        
        if(newIdProjet != null) {
            request.setAttribute("ErrorState", false);
            request.setAttribute("newIdProjet", newIdProjet);
        }
    }
}
