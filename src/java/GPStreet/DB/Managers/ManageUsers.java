/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.DB.Managers;

import GPStreet.DB.HibernateUtil;
import GPStreet.DB.Mapping.Entity.GpstGroups;
import GPStreet.DB.Mapping.Entity.GpstPages;
import GPStreet.DB.Mapping.Entity.GpstUsers;
import GPStreet.EJB.StartupBean;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author iramadan
 */
public class ManageUsers {

    Logger logger = Logger.getLogger(ManageUsers.class);
    Transaction tx = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    GpstUsers user = null;
    
    
    
    public List<GpstUsers> listALLUsers(){
        List<GpstUsers> users = new ArrayList<>();
        try {
            Query query = session.createQuery("from GpstUsers ");            
            List usersList = query.list();
            for (Iterator it = usersList.iterator(); it.hasNext();) {
                user = (GpstUsers) it.next();
                users.add(user);

            }
        } catch (HibernateException ex) {             
            logger.error(ex.getMessage());
            return null;
        }        
            return users;
        
    }

    /**
     * find some user using user name
     */
    public GpstUsers authenticateUser(String userName, String password) {

        Integer userId = null;
        try {
            Query query = session.createQuery("from GpstUsers where username =? and password = ?");
            query.setString(0, userName);
            query.setString(1, password);
            List usersList = query.list();
            for (Iterator it = usersList.iterator(); it.hasNext();) {
                user = (GpstUsers) it.next();

            }
        } catch (HibernateException ex) {             
            logger.error(ex.getMessage());
            return new GpstUsers();
        }        
            return user;
        
    }

    public boolean deleteUser(int userId){
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = new GpstUsers( );
            user.setId(userId);
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error(ex.getMessage());
            return false;
        }
        return true;
    }
    /**
     *
     * adding new user to the database
     * @param userName
     */
    public Integer addUser(String userName, String password, String email, String phone, String firstName, String lastName,int groupId) {
        GpstUsers user = null;
        GpstPages page = (GpstPages)session.get(GpstPages.class, 2);
       

        Integer userId = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Set<GpstGroups> groupSet = new HashSet();
            GpstGroups defaultGroup = (GpstGroups)session.get(GpstGroups.class, groupId);
            Set<GpstPages> pagesSet = new HashSet();
            pagesSet.add(page);
            defaultGroup.setGpstPageses(pagesSet);
            groupSet.add(defaultGroup);
            
            user = new GpstUsers(userName, password, email, phone, firstName, lastName,groupSet);
            
            tx = session.beginTransaction();
            userId = (int) session.save(user);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                
            }
            FacesContext.getCurrentInstance().addMessage("addError", new FacesMessage(StartupBean.localRB.getString("users.database_error")));
            logger.error(ex.getMessage());
            return null;
        }
        return userId;
    }
    public Integer findUser(String userName){
        Integer userId = null;
        GpstUsers user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            
            Query query = session.createQuery("from GpstUsers where username = ?");
            query.setString(0,userName);
            List usersList = query.list();
            for(Iterator it = usersList.iterator();it.hasNext();){
               user = (GpstUsers)it.next();
            }
            if(user == null)
                return null;
            else
                return user.getId();
            
        } catch (HibernateException ex) {
           
            logger.error(ex.getMessage());
            return null;
        }
        
    }
}
