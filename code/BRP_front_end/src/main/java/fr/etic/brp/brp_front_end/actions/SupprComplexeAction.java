package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import fr.etic.brp.brp_back_end.metier.service.ImportService;
import fr.etic.brp.brp_back_end.metier.service.Service;
import static java.lang.Long.parseLong;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */
public class SupprComplexeAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        String msgSuppr = null;
        String id = request.getParameter("id");
        
        //Instanciation de la classe de Service
        ImportService service = new ImportService();
        
        //Appel des services métiers (=méthodes de la classe Service)
        try{
            msgSuppr = service.SupprObjet(id);

            if(!msgSuppr.equals("Succes")){
                request.setAttribute("ErrorState", true);
            }
            else{
                request.setAttribute("ErrorState", false);
            }
        }
        catch(Exception e) //Stockage de l'erreur dans l'attribut de la requete
        {
            request.setAttribute("ErrorState", true);
            msgSuppr = "Une erreur système est survenue. Impossible d'accéder au service de suppression";
        }
        
        request.setAttribute("Explication", msgSuppr);
    }
}