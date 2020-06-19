package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.modele.CategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.CoeffRaccordement;
import fr.etic.brp.brp_back_end.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */
public class ListerRaccordementCatConstAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
        List<CoeffRaccordement> listeCoeffRaccordement = null;
        List<CategorieConstruction> listeCategorieConstruction = null;
        try {
            listeCoeffRaccordement = service.ListerCoeffRaccordements();
            listeCategorieConstruction = service.ListerCategorieConstructions();
        } catch(Exception ex) {
            request.setAttribute("ErrorState", true);
        }
        
        if(listeCoeffRaccordement != null && listeCategorieConstruction != null) {
            request.setAttribute("ErrorState", false);
            request.setAttribute("ListeCoeffRacordement", listeCoeffRaccordement);
            request.setAttribute("ListeCategorieConstruction", listeCategorieConstruction);
        }
    }
}