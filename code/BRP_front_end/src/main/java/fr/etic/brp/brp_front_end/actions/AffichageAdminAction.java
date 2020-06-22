package fr.etic.brp.brp_front_end.actions;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author louisrob
 */
public class AffichageAdminAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération du statut de connexion
        Boolean isAdmin = null;
        try {
            isAdmin = (boolean)request.getSession().getAttribute("isAdmin");
        } catch (Exception ex) {
            request.setAttribute("ErrorState", true);
        }
        
        //Stockage des résultats dans les attributs de la requête
        if(isAdmin != null)
        {
            request.setAttribute("ErrorState", false);
            request.setAttribute("isAdmin", isAdmin);
        } else {
            request.setAttribute("ErrorState", true);
        }
    }
}