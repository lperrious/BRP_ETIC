package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import fr.etic.brp.brp_back_end.metier.service.ImportService;
import fr.etic.brp.brp_back_end.metier.service.Service;
import static java.lang.Long.parseLong;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */
public class ImportAction extends Action {
    
    protected String rootImportFiles = "../../../../code/BRP_front_end/src/main/webapp/import_files/";
    protected ArrayList<String> returnListe = null;
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        String uri = rootImportFiles+request.getParameter("name");
        String operation = request.getParameter("operation");
        String resultat = null;
        String msgSuppr = null;
        
        //Instanciation de la classe de Service
        ImportService service = new ImportService();
        
        //Appel des services métiers (=méthodes de la classe Service)
        try{
            if(operation == "ModifBasePrixRef"){
                resultat = service.ModifBasePrixRef(uri);
            }
            else{
                returnListe = service.ModifBaseDescriptif(uri);
                resultat = resultat = returnListe.get(0);
                
                //les ajouts se sont bien passés, on passe aux suppressions
//                if(returnListe.get(0).equals("Succes")){
//                    for(int i = 1; i < returnListe.size(); i++){
//                        msgSuppr = service.CompterEnfants(returnListe.get(i));
//                        //on envoie au comptage des enfants
//                        if(msgSuppr.equals("suppr ok")){ //on supprime direct car pas d'enfant
//                            System.out.println(service.SupprObjet(returnListe.get(i)));
//                        }
//                        else{ //on demande la permission au client
//                            System.out.println(msgSuppr);
//                            System.out.println(service.SupprObjet(returnListe.get(i)));
//                        }
//                    }
//                }
//                else{       //on affiche l'erreur
//                    System.out.println(returnListe.get(0));
//                }
            }
            request.setAttribute("ErrorState", false);
        }
        catch(Exception e) //Stockage de l'erreur dans l'attribut de la requete
        {
            request.setAttribute("ErrorState", true);
        }
        
        request.setAttribute("Explication", resultat);
    }
}
