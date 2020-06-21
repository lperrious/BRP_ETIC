package fr.etic.brp.brp_front_end.actions;

import com.google.common.hash.Hashing;
import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import fr.etic.brp.brp_back_end.metier.service.Service;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author louisrob
 */
public class InscriptionAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        String lastName = request.getParameter("lastName");
        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        Boolean isAdmin;
        if(request.getParameter("isAdmin").equals("true")) isAdmin = true;
        else isAdmin = false;
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
        int salt = (int)(Math.random()*1000);
        String mdpConcat = password + salt;
        String mdpHash = Hashing.sha256().hashString(mdpConcat, StandardCharsets.UTF_8).toString();
        
        Operateur newOperateur = new Operateur(mail, mdpHash, salt, lastName, isAdmin);
        
        Boolean resultat = null;
        try{
            resultat = service.InscrireOperateur(newOperateur);
        }
        catch(Exception e) //Stockage de l'erreur dans l'attribut de la requete
        {
            request.setAttribute("ErrorState", true);
        }

        //Stockage des résultats dans les attributs de la requête
        if(resultat != null && resultat != false)
        {
            request.setAttribute("ErrorState", false);
        } else {
            request.setAttribute("ErrorState", true);
        }
    }
}