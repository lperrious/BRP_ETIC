
package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.dao.CoeffRaccordementDao;
import fr.etic.brp.brp_back_end.metier.modele.CaractDim;
import fr.etic.brp.brp_back_end.metier.modele.CategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.CoeffRaccordement;
import fr.etic.brp.brp_back_end.metier.modele.SousCategorieConstruction;
import fr.etic.brp.brp_back_end.metier.service.Service;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.Site.libre;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.Site.occupe;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.TypeConstruction.neuf;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.TypeConstruction.renovation;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.TypeLot.entrepriseGenerale;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.TypeLot.lotSepare;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.TypeMarche.marchePrive;
import static fr.etic.brp.brp_front_end.actions.EditerInfosProjetAction.TypeMarche.marchePublic;
import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Long.parseLong;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
        String nomProjet = request.getParameter("nomProjet");
        String refBRP = request.getParameter("refBRP");
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
        Float coeffAdapt = parseFloat(request.getParameter("coeffAdapt"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Date datePrixref = null;
        try {
            datePrixref = format.parse(request.getParameter("datePrixref"));
        } catch (ParseException ex) {
            datePrixref = null;
        }
        Long idCoeffRaccordement = parseLong(request.getParameter("idCoeffRaccordement"));
        Long idCategorieConstruction = parseLong(request.getParameter("idCategorieConstruction"));
        Long idSousCategorieConstruction = parseLong(request.getParameter("idSousCategorieConstruction"));
        Long idCaractDim = parseLong(request.getParameter("idCaractDim"));
        
        CoeffRaccordement coeffRaccordement = null;
        CategorieConstruction categorieConstruction = null;
        SousCategorieConstruction sousCategorieConstruction = null;
        CaractDim caractDim = null;
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        List<CoeffRaccordement> listeCoeffRaccordement = Service.ListerCoeffRaccordements();
        for(int i = 0; i < listeCoeffRaccordement.size(); i++){
            if(listeCoeffRaccordement.get(i).getIdCoeffRaccordement() == idCoeffRaccordement){
                coeffRaccordement = listeCoeffRaccordement.get(i);
            }
        }
        List<CategorieConstruction> listeCategorieConstruction = Service.ListerCategorieConstructions();
        for(int i = 0; i < listeCategorieConstruction.size(); i++){
            if(listeCategorieConstruction.get(i).getIdCategorieConstruction() == idCategorieConstruction){
                categorieConstruction = listeCategorieConstruction.get(i);
            }
        }
        List<SousCategorieConstruction> listeSousCategorieConstruction = Service.ListerSousCategorieConstructions();
        for(int i = 0; i < listeSousCategorieConstruction.size(); i++){
            if(listeSousCategorieConstruction.get(i).getIdSousCategorieConstruction() == idSousCategorieConstruction){
                sousCategorieConstruction = listeSousCategorieConstruction.get(i);
            }
        }
        List<CaractDim> listeCaractDim = Service.ListerCaractDim();
        for(int i = 0; i < listeCaractDim.size(); i++){
            if(listeCaractDim.get(i).getIdCaracteristiqueDimensionnelle() == idCaractDim){
                caractDim = listeCaractDim.get(i);
            }
        }
        
        //on modifie les informations en base de donnée
        //on choppe l'ID
        //on modifie en try catch
        
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