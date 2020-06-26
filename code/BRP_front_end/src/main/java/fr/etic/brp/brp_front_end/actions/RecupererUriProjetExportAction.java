package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.service.Service;
import static java.lang.Long.parseLong;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */
public class RecupererUriProjetExportAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        Long idProjet = parseLong(request.getParameter("idProjet"));
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
        String resultat = null;
        try {
            resultat = service.RecupererUriProjetExport(idProjet);
        } catch(Exception ex) {
            request.setAttribute("ErrorState", true);
        }
        
        if(resultat != null) {
            request.setAttribute("ErrorState", false);
            request.setAttribute("uriExport", resultat);
        } else {
            request.setAttribute("ErrorState", true);
        }
    }
}