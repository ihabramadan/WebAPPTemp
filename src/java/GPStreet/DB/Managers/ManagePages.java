/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPStreet.DB.Managers;

import GPStreet.DB.HibernateUtil;
import GPStreet.DB.Mapping.Entity.GpstGroups;
import GPStreet.DB.Mapping.Entity.GpstPages;
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
 * @author ihab_ramadan
 */
public class ManagePages {
    
    
    Logger logger = Logger.getLogger(ManagePages.class);
    Transaction tx = null;
    Session session = HibernateUtil.getGlobalSession();
    GpstPages page = null;
    
    
    public List<GpstPages> getAllPages(){
        List<GpstPages> pages = new ArrayList<>();
        try {
            Query query = session.createQuery("from GpstPages ");            
            List PagesList = query.list();
            for (Iterator it = PagesList.iterator(); it.hasNext();) {
                page = (GpstPages) it.next();
                pages.add(page);

            }
        } catch (HibernateException ex) {             
            logger.error(ex.getMessage());
            return null;
        }finally{
            
        }        
            return pages;
    }
    
}
