package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import fr.etic.brp.brp_back_end.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author louisrob
 */
public class ConnectionAction extends Action {
    
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
            resultat = service.A​uthentifierOperateur(mail, password);
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
            request.getSession().setAttribute("operateur", resultat.getIdOperateur());
            request.getSession().setAttribute("sameSite", "None");
            
            // Si on veut recupérer le paramettre de la session il suffit de faire :
            // request.getSession().getAttribute("userType");
            // meme si le user va sur des pages différente on peut l'utiliser
            // il y a un cookie JSESSIONID et tomcat le lit tout seul et va chercher ses atributs
            // On pourrait faire comme tu as fais mais ce n'est pas sécurisé (on peut changer la valeur des cookies comme on veut)
            // et il faudrait pas mal de lignes en plus pour le faire bien
            // (si tu veux que je t'explique comment on peut faire je peux t'expliquer ou tu peux aller voir les jwt)
            // donc pour gagner du temps et en simplicité je te propose de passer par ça 
            
            // Le cookie et cree automatiquement lorqu'un user se connecte
            // et quand il se logout il faut le detruire avec request.getSession().invalidate();
            
            // https://javarevisited.blogspot.com/2012/08/what-is-jsessionid-in-j2ee-web.html            
        } else {
            request.setAttribute("ErrorState", true);
        }
    }
}