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
public class ManageGroups {
    Logger logger = Logger.getLogger(ManageGroups.class);
    Transaction tx = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    GpstGroups group = null;
    
    
    
    public boolean deleteGroup(int id){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
           
            
            group = new GpstGroups();
            group.setId(id);
            
            
            tx = session.beginTransaction();
            session.delete(group);
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
    
    public Integer addGroup(String groupName, String groupDesc) {
        GpstGroups group = null;
        
       

        Integer groupId = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
           
            
            group = new GpstGroups(groupName, groupDesc , null);
            
            tx = session.beginTransaction();
            groupId = (int) session.save(group);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                
            }
            FacesContext.getCurrentInstance().addMessage("addError", new FacesMessage(StartupBean.localRB.getString("users.database_error")));
            logger.error(ex.getMessage());
            return null;
        }
        return groupId;
    }
    
    public Integer findGroup(String groupName){
        Integer groupId = null;
        GpstGroups group = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            
            Query query = session.createQuery("from GpstGroups where groupname = ?");
            query.setString(0,groupName);
            List groupList = query.list();
            for(Iterator it = groupList.iterator();it.hasNext();){
               group = (GpstGroups)it.next();
            }
            if(group == null)
                return null;
            else
                return group.getId();
            
        } catch (HibernateException ex) {
           
            logger.error(ex.getMessage());
            return null;
        }
    }
    
    public List<GpstGroups> getAllGoups(){
        List<GpstGroups> groups = new ArrayList<>();
        try {
            Query query = session.createQuery("from GpstGroups ");            
            List groupsList = query.list();
            for (Iterator it = groupsList.iterator(); it.hasNext();) {
                group = (GpstGroups) it.next();
                groups.add(group);

            }
        } catch (HibernateException ex) {             
            logger.error(ex.getMessage());
            return null;
        }        
            return groups;
    }
}
