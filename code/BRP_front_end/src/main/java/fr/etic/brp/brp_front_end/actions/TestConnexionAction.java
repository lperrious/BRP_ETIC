package fr.etic.brp.brp_front_end.actions;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author louisrob
 */
public class TestConnexionAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération du statut de connexion
        Long idOperateur = null;
        try {
            idOperateur = (Long)request.getSession().getAttribute("idOperateur");
        } catch (Exception ex) {
            request.setAttribute("ErrorState", true);
        }
        
        //Stockage des résultats dans les attributs de la requête
        if(idOperateur != null)
        {
            request.setAttribute("ErrorState", false);
        } else {
            request.setAttribute("ErrorState", true);
        }
    }
}