package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.service.Service;
import static java.lang.Long.parseLong;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */
public class EnregistrerUriProjetExportAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        Long idProjet = parseLong(request.getParameter("idProjet"));
        String uriProjetExport = request.getParameter("uriProjetExport");
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
        Boolean resultat = null;
        try {
            resultat = service.EnregistrerUriProjetExport(idProjet, uriProjetExport);
        } catch(Exception ex) {
            request.setAttribute("ErrorState", true);
        }
        
        if(resultat != null && resultat != false) {
            request.setAttribute("ErrorState", false);
        } else {
            request.setAttribute("ErrorState", true);
        }
    }
}
