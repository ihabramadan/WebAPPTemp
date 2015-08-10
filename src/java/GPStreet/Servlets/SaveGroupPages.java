/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.Servlets;

import GPStreet.BB.UserBean;
import GPStreet.DB.Managers.ManageGroups;
import GPStreet.EJB.StartupBean;
import com.sun.faces.spi.ConfigurationResourceProviderFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ihab.ramadan
 */
public class SaveGroupPages extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        Logger logger = Logger.getLogger(SaveGroupPages.class);
        try (PrintWriter out = response.getWriter()) {
            try{
                int groupId = Integer.parseInt(request.getParameter("groupId"));
            String groupPages= request.getParameter("pagesIds");
            List<Integer> pagesIds = new ArrayList<>();
            if(!groupPages.equals("[]")){   
            String[] temp = groupPages.split(",");
            
            
                
            for(int i =  0 ; i < temp.length ;i++)
            pagesIds.add( Integer.parseInt(temp[i].replaceAll("\\D+","")));
            }
            ManageGroups mg;
            mg = new ManageGroups();
            Integer newGroupId = mg.addGroupPages(groupId, pagesIds);
            }catch(Exception e){
                logger.error(e.getMessage());
            }
            
            
            
            
            
            
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
