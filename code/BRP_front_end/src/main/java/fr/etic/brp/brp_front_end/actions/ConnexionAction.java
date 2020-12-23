package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import fr.etic.brp.brp_back_end.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author louisrob
 */
public class ConnexionAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
        Operateur resultat = null;
        try{
            resultat = service.AuthentifierOperateur(mail, password);
        }
        catch(Exception e) //Stockage de l'erreur dans l'attribut de la requete
        {
            request.setAttribute("ErrorState", true);
        }

        //Stockage des résultats dans les attributs de la requête
        if(resultat != null)
        {
            request.setAttribute("ErrorState", false);
            request.setAttribute("Operateur", resultat);
            
            // On creer des attributs dans la session pour stocker le userType et l'idUser de cette maniere :
            request.getSession().setAttribute("idOperateur", resultat.getIdOperateur());
            request.getSession().setAttribute("isAdmin", resultat.getAdmin());
            request.getSession().setAttribute("sameSite", "None");
        } else {
            request.setAttribute("ErrorState", true);
        }
    }
}