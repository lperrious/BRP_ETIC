package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmar
 */
public class CreationProjetAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        Long idProjet = service.CreerProjet(request.getParameter("nomProjet"));
        
        request.setAttribute("idProjet", idProjet);
    }
}