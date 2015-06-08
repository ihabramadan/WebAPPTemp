/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPStreet.DB.Managers;

import GPStreet.DB.HibernateUtil;
import GPStreet.DB.Mapping.Entity.GpstGroups;
import GPStreet.DB.Mapping.Entity.GpstUsers;
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
 * @author iramadan
 */
public class ManageGroups {
    Logger logger = Logger.getLogger(ManageGroups.class);
    Transaction tx = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    GpstGroups group = null;
    
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
