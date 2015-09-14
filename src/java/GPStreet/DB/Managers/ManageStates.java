/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.DB.Managers;

import GPStreet.DB.HibernateUtil;
import GPStreet.DB.Mapping.Entity.GpstPages;
import GPStreet.DB.Mapping.Entity.GpstState;
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
public class ManageStates {
    Logger logger = Logger.getLogger(ManagePages.class);
    Transaction tx = null;
    Session session = HibernateUtil.getGlobalSession();
    GpstState state = null;
    
    public List<GpstState> getAllStates(){
        List<GpstState> states = new ArrayList<>();
        try {
            Query query = session.createQuery("from GpstState ");            
            List statesList = query.list();
            for (Iterator it = statesList.iterator(); it.hasNext();) {
                state = (GpstState) it.next();
                states.add(state);

            }
        } catch (HibernateException ex) {             
            logger.error(ex.getMessage());
            return null;
        }        
            return states;
    }
}
