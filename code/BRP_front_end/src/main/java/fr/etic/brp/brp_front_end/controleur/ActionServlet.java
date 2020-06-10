package fr.etic.brp.brp_front_end.controleur;

import fr.etic.brp.brp_back_end.dao.JpaUtil;
import fr.etic.brp.brp_front_end.actions.Action;
import fr.etic.brp.brp_front_end.actions.ConnectionAction;
import fr.etic.brp.brp_front_end.actions.InscriptionAction;
import fr.etic.brp.brp_front_end.actions.TestConnectionAction;
import fr.etic.brp.brp_front_end.serialisations.ConnectionSerialisation;
import fr.etic.brp.brp_front_end.serialisations.InscriptionSerialisation;
import fr.etic.brp.brp_front_end.serialisations.Serialisation;
import fr.etic.brp.brp_front_end.serialisations.TestConnectionSerialisation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quentin MARC & Louis ROB
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
}

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getSession(true); //Init de la session
        request.setCharacterEncoding("UTF-8"); //Encodage des param de la requête (pour une lecture correcte)
        String todo = request.getParameter("todo");
        //System.out.println("TODO= " + todo); //si besoin, pour le debug
        
        Action action = null;
        Serialisation serialisation = null;
        
        switch(todo) {
            case "inscription" : {
                action = new InscriptionAction();
                serialisation = new InscriptionSerialisation();  
                break;
            }
            case "connection" : {
                action = new ConnectionAction();
                serialisation = new ConnectionSerialisation();  
                break;
            }
            case "testConnection" : {
                action = new TestConnectionAction();
                serialisation = new TestConnectionSerialisation();
                break;
            }
        }
       
        if(action != null && serialisation != null) {
            action.execute(request); // Exécuter l'action
            serialisation.serialiser(request, response); // Sérialiser le résultat de l'action
        } else { // Erreur : pas d'action ou de Sérialisation pour traiter cette requête
            response.sendError(400, "Bad Request (pas d'action ou de serialisation pour traiter cette requete)");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}