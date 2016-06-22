/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.BB;

import GPStreet.DB.Managers.ManageProducts;
import GPStreet.DB.Mapping.Entity.GpstProducts;
import GPStreet.EJB.StartupBean;
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

@ManagedBean(name = "productsBean")
@SessionScoped
public class ProductsBean {
    int id;
    String name;
    String description;
    int typeId;
    List<GpstProducts> productsList = new ArrayList<>();
    private ManageProducts mp = new ManageProducts();
    
    public void bindProducts(){
        ManageProducts mp = new ManageProducts();
        List<GpstProducts> allProducts = mp.getAllProducts();
        if (allProducts != null) {
            setProductsList(allProducts);
        }
    }    
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public List<GpstProducts> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<GpstProducts> productsList) {
        this.productsList = productsList;
    }
    
    
    public boolean deleteProduct(int productId){
        boolean success = false;
        
        success = mp.deleteProduct(productId);
        if(success == false){
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(StartupBean.localRB.getString("database.error")));
            return false;
        }
        else{
            FacesContext.getCurrentInstance().addMessage("success", new FacesMessage(StartupBean.localRB.getString("products.delete_success")));
            return true;
        }
            
    }
    public void addProduct(){
        
    }
    
}
