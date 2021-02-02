package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import fr.etic.brp.brp_back_end.metier.service.Service;
import static java.lang.Long.parseLong;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */
public class SupprimerXMLAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        Long idProjet = parseLong(request.getParameter("idProjet"));
        String type = request.getParameter("type");
        String idXML = request.getParameter("idXML");
        String idDescriptifXML = null;
        Boolean resultat = false;
        int resultatSupprXML;
        if("ligneChiffrage".equals(type)){
            idDescriptifXML = request.getParameter("idDescriptifXML");
        }
        
        //Instanciation de la classe de Service
        Service service = new Service();
        
        //Appel des services métiers (=méthodes de la classe Service)
        try{
            if("ligneChiffrage".equals(type)){
                resultat = service.SupprimerLigneChiffrage(idProjet, idDescriptifXML, idXML);

                if(resultat)
                    request.setAttribute("ErrorCode", 4);
                else
                    request.setAttribute("ErrorCode", 0);
            }
            else{
                resultatSupprXML = service.SuppressionBalise(idProjet, idXML);
                request.setAttribute("ErrorCode", resultatSupprXML);
            }
        }
        catch(Exception e) //Stockage de l'erreur dans l'attribut de la requete
        {
            request.setAttribute("ErrorCode", 0);
        }
    }
}
