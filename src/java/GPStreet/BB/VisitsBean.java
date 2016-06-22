/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.BB;

import GPStreet.DB.Managers.ManageProducts;
import GPStreet.DB.Mapping.Entity.GpstProducts;
import GPStreet.EJB.StartupBean;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ihab_ramadan
 */

@ManagedBean(name = "visitsBean")
@SessionScoped
public class VisitsBean {
    int id;
    Date startDate;
    Date endDate;
    int locationId;
    String comments;
    List<GpstProducts> productsList = new ArrayList<>();
    
    
    public void bindProducts(){
        ManageProducts mp = new ManageProducts();
        List<GpstProducts> allProducts = mp.getAllProducts();
        if (allProducts != null) {
            setProductsList(allProducts);
        }
    }    
    
    
    
    

    
    public List<GpstProducts> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<GpstProducts> productsList) {
        this.productsList = productsList;
    }
    
    
    
}
