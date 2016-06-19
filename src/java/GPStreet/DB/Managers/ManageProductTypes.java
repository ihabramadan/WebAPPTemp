/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.DB.Managers;

import GPStreet.DB.HibernateUtil;
import GPStreet.DB.Mapping.Entity.GpstLocations;
import GPStreet.DB.Mapping.Entity.GpstProductTypes;
import GPStreet.DB.Mapping.Entity.GpstProducts;
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
public class ManageProductTypes {

    Logger logger = Logger.getLogger(ManageProductTypes.class);
    Transaction tx = null;
    Session session = HibernateUtil.getGlobalSession();
    GpstProductTypes productType = null;

    public List<GpstProductTypes> getAllProductTypes() {
        List<GpstProductTypes> productTypes = new ArrayList<>();
        try {
            Query query = session.createQuery("FROM GpstProductTypes");
            
            List productList = query.list();
            for (Iterator it = productList.iterator(); it.hasNext();) {
                productType = (GpstProductTypes) it.next();
                productTypes.add(productType);

            }
        } catch (HibernateException ex) {
            logger.error(ex.getMessage());
            return null;
        }
        return productTypes;
    }

    public Integer addProductType(String name, String desc, String type) {

        GpstProductTypes productType = null;

        Integer typeId = null;

        try {
            session = HibernateUtil.getGlobalSession();
            productType = new GpstProductTypes(type,desc);
            
                typeId = (int) session.save(productType);
                tx.commit();
            
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();

            }
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(StartupBean.localRB.getString("users.database_error")));
            logger.error(ex.getMessage());
            return null;
        }
        return typeId;
    }

    

    

}
