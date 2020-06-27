package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import fr.etic.brp.brp_back_end.metier.service.Service;
import static java.lang.Long.parseLong;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */
public class DeplacerDescriptifAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        Long idProjet = parseLong(request.getParameter("idProjet"));
        String idDescriptif = request.getParameter("idDescriptif");
        String placement = request.getParameter("placement");
        String idRef = request.getParameter("idRef");
        Boolean resultat = null;
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
        try{
            resultat = service.DeplacerDescriptif(idProjet, idDescriptif, placement, idRef);
            request.setAttribute("ErrorState", !resultat);
        }
        catch(Exception e) //Stockage de l'erreur dans l'attribut de la requete
        {
            request.setAttribute("ErrorState", true);
        }
    }
}
