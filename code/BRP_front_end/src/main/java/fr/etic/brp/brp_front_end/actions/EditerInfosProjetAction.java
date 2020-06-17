
package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.service.Service;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.Site.libre;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.Site.occupe;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.TypeConstruction.neuf;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.TypeConstruction.renovation;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.TypeLot.entrepriseGenerale;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.TypeLot.lotSepare;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.TypeMarche.marchePrive;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.TypeMarche.marchePublic;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */


public class EditerInfosProjetAction extends Action {
    
    public enum TypeMarche {
      marchePublic,
      marchePrive;
    }

    public enum TypeConstruction {
      neuf,
      renovation;
    }

    public enum TypeLot {
      lotSepare,
      entrepriseGenerale;
    }

    public enum Site {
      libre,
      occupe;
    }
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        TypeMarche typeMarche = null;
        if("marchePublic".equals(request.getParameter("typeMarche"))){
            typeMarche = marchePublic;
        }else if("marchePrive".equals(request.getParameter("typeMarche"))){
            typeMarche = marchePrive;
        }
        TypeConstruction typeConstruction = null;
        if("neuf".equals(request.getParameter("typeConstruction"))){
            typeConstruction = neuf;
        }else if("renovation".equals(request.getParameter("typeConstruction"))){
            typeConstruction = renovation;
        }
        TypeLot typeLot = null;
        if("lotSepare".equals(request.getParameter("typeLot"))){
            typeLot = lotSepare;
        }else if("entrepriseGenerale".equals(request.getParameter("typeLot"))){
            typeLot = entrepriseGenerale;
        }
        Site site = null;
        if("libre".equals(request.getParameter("typeSite"))){
            site = libre;
        }else if("occupe".equals(request.getParameter("typeSite"))){
            site = occupe;
        }
        

//        String nomProjet = request.getParameter("nomProjet");
//        String nomProjet = request.getParameter("nomProjet");
//        String nomProjet = request.getParameter("nomProjet");
//        String nomProjet = request.getParameter("nomProjet");
//        String nomProjet = request.getParameter("nomProjet");
//        String nomProjet = request.getParameter("nomProjet");
        
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
//        Long idProjet = null;
//        try {
//            idProjet = service.CreerProjet(nomProjet);
//        } catch(Exception ex) {
//            request.setAttribute("ErrorState", true);
//        }
//        
//        if(idProjet != null) {
//            request.setAttribute("ErrorState", false);
//            request.setAttribute("idProjet", idProjet);
//        }
    }
}