
package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */


public class EditerInfosProjetAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        String nomProjet = request.getParameter("nomProjet");
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
//        Long idProjet = null;
//        try {
//            idProjet = service.CreerProjet(nomProjet);
//        } catch(Exception ex) {
//            request.setAttribute("ErrorState", true);
//        }
//        
//        if(idProjet != null) {
//            request.setAttribute("ErrorState", false);
//            request.setAttribute("idProjet", idProjet);
//        }
    }
}