/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.DB.Managers;

import GPStreet.DB.HibernateUtil;
import GPStreet.DB.Mapping.Entity.GpstLocations;
import GPStreet.DB.Mapping.Entity.GpstUsers;
import GPStreet.EJB.StartupBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ihab.ramadan
 */
public class ManageLocations {

    Logger logger = Logger.getLogger(ManageGroups.class);
    Transaction tx = null;
    Session session = HibernateUtil.getGlobalSession();
    GpstLocations location = null;
    
    public List<GpstLocations> getAllLocations(){
        List<GpstLocations> locations = new ArrayList<>();
        try {
            Query query = session.createQuery("from GpstLocations ");            
            List LocationsList = query.list();
            for (Iterator it = LocationsList.iterator(); it.hasNext();) {
                location = (GpstLocations) it.next();
                locations.add(location);

            }
        } catch (HibernateException ex) {             
            logger.error(ex.getMessage());
            return null;
        }        
            return locations;
    }
    public Integer addLocation(String name , String desc,double lat , double lng){
        
        GpstLocations location = null;
        
       

        Integer locationId = null;
        
        try {
            session = HibernateUtil.getGlobalSession();
            location = new GpstLocations(name,desc,lat,lng,null);
            GpstLocations existLocation = exists(location, name);
            if(existLocation == null){
            tx = session.beginTransaction();
            locationId = (int) session.save(location);
            tx.commit();
            }else
                return -1;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                
            }
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(StartupBean.localRB.getString("users.database_error")));
            logger.error(ex.getMessage());
            return null;
        }
        return locationId;
    }
    
    public GpstLocations exists (GpstLocations location,String name) {
    Query query = HibernateUtil.getGlobalSession().createQuery("FROM GpstLocations where name = :name" );
   query.setString("name", name);
  
    return (GpstLocations)query.uniqueResult();
}
    
        public boolean deleteLocation(int locationId){
        
        try {
            session = HibernateUtil.getGlobalSession();
            
            location = (GpstLocations)session.get(GpstLocations.class, locationId);
            
           
            tx = session.beginTransaction();
            session.delete(location);
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

    
}
