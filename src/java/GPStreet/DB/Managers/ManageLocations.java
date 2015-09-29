/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.DB.Managers;

import GPStreet.DB.HibernateUtil;
import GPStreet.DB.Mapping.Entity.GpstLocations;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
}
