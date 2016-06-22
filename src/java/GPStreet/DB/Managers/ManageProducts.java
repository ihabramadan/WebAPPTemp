/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.DB.Managers;

import GPStreet.DB.HibernateUtil;
import GPStreet.DB.Mapping.Entity.GpstGroups;
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
public class ManageProducts {

    Logger logger = Logger.getLogger(ManageProducts.class);
    Transaction tx = null;
    Session session = HibernateUtil.getGlobalSession();
    GpstProducts product = null;

    public List<GpstProducts> getAllProducts() {
        List<GpstProducts> products = new ArrayList<>();
        try {
            Query query = session.createQuery("FROM GpstProducts");
            
            List productList = query.list();
            for (Iterator it = productList.iterator(); it.hasNext();) {
                product = (GpstProducts) it.next();
                products.add(product);

            }
        } catch (HibernateException ex) {
            logger.error(ex.getMessage());
            return null;
        }
        return products;
    }

    public Integer addProduct(String name, String desc, int typeId) {

        GpstProducts product = null;

        Integer productId = null;

        try {
            session = HibernateUtil.getGlobalSession();
            GpstProductTypes productType = new GpstProductTypes();
            productType = (GpstProductTypes)session.get(GpstProductTypes.class, typeId);
            product = new GpstProducts(productType,name, desc);
            GpstProducts existProduct = exists(product, name);
            if (existProduct == null) {
                tx = session.beginTransaction();
                productId = (int) session.save(product);
                tx.commit();
            } else {
                return -1;
            }
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();

            }
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(StartupBean.localRB.getString("users.database_error")));
            logger.error(ex.getMessage());
            return null;
        }
        return productId;
    }

    public GpstProducts exists(GpstProducts product, String name) {
        Query query = HibernateUtil.getGlobalSession().createQuery("FROM GpstProducts where name = :name");
        query.setString("name", name);

        return (GpstProducts) query.uniqueResult();
    }

    public boolean deleteProduct(int productId) {

        try {
            session = HibernateUtil.getGlobalSession();

            product = (GpstProducts) session.get(GpstProducts.class, productId);

            tx = session.beginTransaction();
            session.delete(product);
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
    
     public boolean updateProduct(int productId,String name,String description,int typeId){
        GpstProducts product = null;
        
       

        
        try {
            session = HibernateUtil.getGlobalSession();
            
            
            product= (GpstProducts)session.get(GpstProducts.class,productId);
            
            tx = session.beginTransaction();
            product.setName(name);
            product.setDescription(description);
            product.setGpstProductTypes((GpstProductTypes)session.get(GpstProductTypes.class, typeId));
            
            session.save(product);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                
            }
            FacesContext.getCurrentInstance().addMessage("addError", new FacesMessage(StartupBean.localRB.getString("users.database_error")));
            logger.error(ex.getMessage());
            return false;
        }
        return true;
    }

}
