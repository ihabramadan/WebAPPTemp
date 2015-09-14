/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPStreet;


import GPStreet.DB.HibernateUtil;
import GPStreet.EJB.StartupBean;
import java.io.IOException;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import org.hibernate.Session;


/**
 *
 * @author iramadan
 */
public class GPStreetCL implements ServletContextListener {

    //StartupBean startupBean = lookupStartupBeanBean();
    Logger logger = Logger.getLogger(GPStreetCL.class);
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize the root path of the project to create the log file which is "build/web/logs"       
        Integer userId = null;
        ServletContext context =  sce.getServletContext();
        System.setProperty("rootPath", context.getRealPath("/").replace('\\', '/'));  
        StartupBean startupBean = new StartupBean();
        startupBean.init();
        
        
        
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   

}
