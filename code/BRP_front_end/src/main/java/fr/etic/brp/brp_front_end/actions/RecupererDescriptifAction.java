package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.modele.Descriptif;
import fr.etic.brp.brp_back_end.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */
public class RecupererDescriptifAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        String idDescriptif = request.getParameter("idDescriptif");
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
        Descriptif descriptif = null;
        try {
            descriptif = service.RechercherDescriptifParId(idDescriptif);
        } catch(Exception ex) {
            request.setAttribute("ErrorState", true);
        }
        
        if(descriptif != null) {
            request.setAttribute("ErrorState", false);
            request.setAttribute("descriptif", descriptif);
        }
    }
}