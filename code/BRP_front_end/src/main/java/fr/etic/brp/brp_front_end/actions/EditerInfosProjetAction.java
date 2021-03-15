
package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.service.Service;
import static java.lang.Float.parseFloat;
import static java.lang.Long.parseLong;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */


public class EditerInfosProjetAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        Long idProjet = parseLong(request.getParameter("idProjet"));
        String nomProjet = request.getParameter("nomProjet");
        String refBRP = request.getParameter("refBRP");
        String typeMarche = request.getParameter("typeMarche");
        String typeConstruction = request.getParameter("typeConstruction");
        String typeLot = request.getParameter("typeLot");
        String typeSite = request.getParameter("typeSite");
        String coeffAdaptString = request.getParameter("coeffAdapt");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String datePrixrefString = request.getParameter("datePrixref")+"-01-02";
        Date datePrixref;
        try {
            datePrixref = format.parse(datePrixrefString);
        } catch (ParseException ex) {
            datePrixref = null;
        }
        String idCoeffRaccordementString = request.getParameter("idCoeffRaccordement");
        String idCategorieConstructionString = request.getParameter("idCategorieConstruction");
        String idSousCategorieConstructionString = request.getParameter("idSousCategorieConstruction");
        String idCaractDim = request.getParameter("idCaractDim");
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //on modifie les informations en base de donnée
        int nbErreur = 0;
        Boolean errorState = false;
        try{
           if(!nomProjet.equals("")){
               Boolean testEditerNom = service.EditerNomProjet(idProjet, nomProjet);
               if(!testEditerNom) nbErreur++;
           }
           
           if(!refBRP.equals("")){
               Boolean testEditerRefBRP = service.EditerRefBRPProjet(idProjet, refBRP);
           if(!testEditerRefBRP) nbErreur++;
           }
           
           if(!typeMarche.equals("")){
               Boolean testEditerTypeMarche = service.EditerInfoEnumProjet(idProjet, "TypeMarche", typeMarche);
               if(!testEditerTypeMarche) nbErreur++;
           }
           
           if(!typeConstruction.equals("")){
               Boolean testEditerTypeConstruction = service.EditerInfoEnumProjet(idProjet, "TypeConstruction", typeConstruction);
               if(!testEditerTypeConstruction) nbErreur++;
           }
           
           if(!typeLot.equals("")){
               Boolean testEditerTypeLot = service.EditerInfoEnumProjet(idProjet, "TypeLot", typeLot);
               if(!testEditerTypeLot) nbErreur++;
           }
           
           if(!typeSite.equals("")){
               Boolean testEditerTypeSite = service.EditerInfoEnumProjet(idProjet, "Site", typeSite);
               if(!testEditerTypeSite) nbErreur++;
           }
           
           if(!coeffAdaptString.equals("")){
               Float coeffAdapt = parseFloat(coeffAdaptString);
               Boolean testEditerCoeffAdaptProjet = service.EditerCoeffAdaptProjet(idProjet, coeffAdapt);
               if(!testEditerCoeffAdaptProjet) nbErreur++;
           }
           
           if(!datePrixrefString.equals("")){
               Boolean testEditerDateProjet = service.EditerDateProjet(idProjet, datePrixref);
               if(!testEditerDateProjet) nbErreur++;
           }

            if(!idCoeffRaccordementString.equals("")){
                Long idCoeffRaccordement = parseLong(idCoeffRaccordementString);
               Boolean testEditerCoeffRaccordementProjet = service.EditerCoeffRaccordementProjet(idProjet, idCoeffRaccordement);
               if(!testEditerCoeffRaccordementProjet) nbErreur++;
            }

           if(!idCategorieConstructionString.equals("")){
               Boolean testEditerCategorieConstructionProjet = service.EditerCategorieConstructionProjet(idProjet, idCategorieConstructionString);
               if(!testEditerCategorieConstructionProjet) nbErreur++;
           }   
           
           //Editer sousCategorieConstruction
           if(!idSousCategorieConstructionString.equals("")){
               long idSousCategorieConstruction = parseLong(idSousCategorieConstructionString);
               Boolean testEditerSousCategorieConstructionProjet = service.EditerSousCategorieConstructionProjet(idProjet, idSousCategorieConstruction);
               if(!testEditerSousCategorieConstructionProjet) nbErreur++;
           } 

           //editer CaractDim
           if(!idCaractDim.equals("")){
                Boolean testEditerCaractDim = service.EditerCaractDim(idProjet, parseLong(idCaractDim));
                if(!testEditerCaractDim) nbErreur++;
            }
           
        } catch(Exception ex) {
            errorState = true;
        }
        
        //Appel des services métiers (=méthodes de la classe Service)
        if(nbErreur > 0) {
            errorState = true;
        }
        request.setAttribute("ErrorState", errorState);
    }
}