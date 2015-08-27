/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.DB.Managers;

import GPStreet.DB.HibernateUtil;
import GPStreet.DB.Mapping.Entity.GpstState;
import GPStreet.DB.Mapping.Entity.GpstTracker;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ihab.ramadan
 */
public class ManageTracker {
    Logger logger = Logger.getLogger(ManageUsers.class);
    Transaction tx = null;
    Session session = HibernateUtil.getSessionFactory().openSession();

    
    public List<GpstTracker> getTracking(Integer id , int stateId, int userId, double latitude, double longitude, Date date, double deviceId){
        
        List<GpstTracker> result;
        try{
        Criteria cr = session.createCriteria(GpstTracker.class);
        if(id != null)
        cr.add(Restrictions.eq("id", id));
        if(stateId != 0)
            cr.add(Restrictions.eq("gpstState.id", stateId));
        if(userId != 0)
            cr.add(Restrictions.eq("userId", userId));
        if(latitude != 0)
            cr.add(Restrictions.eq("latitude", latitude));
        if(longitude != 0)
            cr.add(Restrictions.eq("longitude", longitude));
        if(date != null)
            cr.add(Restrictions.ge("date", date));
        if(deviceId != 0)
            cr.add(Restrictions.eq("deviceId", deviceId));
        
        cr.addOrder(Order.desc("date"));
        result = cr.list();
        }catch(HibernateException ex){
            logger.error(ex.getMessage());
            return null;
        }
        return result;                                       
    }
}
