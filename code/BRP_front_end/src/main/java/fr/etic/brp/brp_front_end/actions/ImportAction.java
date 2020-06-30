package fr.etic.brp.brp_front_end.actions;

import fr.etic.brp.brp_back_end.metier.service.ImportService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author quentinmarc
 */
public class ImportAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request){ //Implémentation de la méthode Action.execute()
        
        //Récupération des paramètres de la requête
        String name = request.getParameter("name");
        String operation = request.getParameter("operation");
        String resultat = null;
        String msgSuppr = null;
        ArrayList<String> returnListe = null;
        
        //Instanciation de la classe de Service
        ImportService service = new ImportService();
        
        //Appel des services métiers (=méthodes de la classe Service)
        try{
            if(operation.equals("ModifBasePrixRef")){
                resultat = service.ModifBasePrixRef(name);
                if(resultat.equals("Succès !")){
                    request.setAttribute("ErrorState", false);
                    resultat = "Import des prix réussi";
                }
                else{
                    request.setAttribute("ErrorState", true);
                }
                
            }
            else{
                returnListe = service.ModifBaseDescriptif(name);
                
                //les ajouts se sont bien passés, on passe aux suppressions
                if(returnListe.get(0).equals("Succes")){
                    int i = 1;
                    Boolean errorSuppr = false;
                    while(!errorSuppr && i < returnListe.size()){
                        
                        if(service.CompterEnfants(returnListe.get(i)).equals("suppr ok")){
                            msgSuppr = service.SupprObjet(returnListe.get(i));

                            if(!msgSuppr.equals("Succes")){
                                errorSuppr = true;
                                request.setAttribute("ErrorState", true);
                                resultat = msgSuppr;
                            }
                        }
                        else{
                            if(resultat == null){
                                resultat = service.CompterEnfants(returnListe.get(i));
                            }
                            else{
                                resultat += service.CompterEnfants(returnListe.get(i));
                            }
                        }
                        
                        i++;
                    }
                    
                    if(!errorSuppr){

                        if(service.TransformationWordVersExcel(name)){
                            request.setAttribute("ErrorState", false);
                            if(resultat == null)
                                resultat = "Import effectué avec succès. Vous trouverez l'Excel lié aux objets importés dans le dossier des imports";
                        }
                        else{
                            request.setAttribute("ErrorState", true);
                            resultat = "Import réalisé avec succès. Néanmoins, impossible de sortir l'Excel lié aux objets importés";
                        }
                    }
                }
                else{       //on affiche l'erreur
                    request.setAttribute("ErrorState", true);
                    resultat = returnListe.get(0);
                }
            }
//            request.setAttribute("ErrorState", false);
        }
        catch(Exception e) //Stockage de l'erreur dans l'attribut de la requete
        {
            request.setAttribute("ErrorState", true);
            resultat = "Une erreur système est survenue. Impossible d'accéder au service d'import";
        }
        
        request.setAttribute("Explication", resultat);
    }
}
