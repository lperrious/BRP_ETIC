package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.modele.CaractDim;
import fr.etic.brp.brp_back_end.metier.modele.CategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.SousCategorieConstruction;
import fr.etic.brp.brp_back_end.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */
public class ListerSousCatConstCaractDimAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        String idCategorieConstruction = (String)request.getParameter("idCategorieConstruction");
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
        List<SousCategorieConstruction> listeSousCategorieConstruction = null;
        List<CaractDim> listeCaractDim = null;
        try {
            CategorieConstruction categorieConstruction = service.RechercherCategorieConstructionParId(idCategorieConstruction);
            listeSousCategorieConstruction = categorieConstruction.getListeSousCategorieConstruction();
            listeCaractDim = categorieConstruction.getListeCaractDim();
        } catch(Exception ex) {
            request.setAttribute("ErrorState", true);
        }
        
        if(listeSousCategorieConstruction != null && listeCaractDim != null) {
            request.setAttribute("ErrorState", false);
            request.setAttribute("ListeSousCategorieConstruction", listeSousCategorieConstruction);
            request.setAttribute("ListeCaractDim", listeCaractDim);
        }
    }
}