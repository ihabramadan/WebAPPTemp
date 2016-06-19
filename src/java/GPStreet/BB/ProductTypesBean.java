/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.BB;

import GPStreet.DB.Managers.ManageProductTypes;
import GPStreet.DB.Managers.ManageProducts;
import GPStreet.DB.Mapping.Entity.GpstProductTypes;
import GPStreet.DB.Mapping.Entity.GpstProducts;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Ihab_ramadan
 */

@ManagedBean(name = "productTypesBean")
public class ProductTypesBean {
    int id;
    String type;
    String description;
    
    List<GpstProductTypes> productTypesList = new ArrayList<>();
    
    public void bindProductTypes(){
        ManageProductTypes mp = new ManageProductTypes();
        List<GpstProductTypes> allProductTypes = mp.getAllProductTypes();
        if (allProductTypes != null) {
            setProductTypesList(allProductTypes);
        }
    }    
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GpstProductTypes> getProductTypesList() {
        return productTypesList;
    }

    public void setProductTypesList(List<GpstProductTypes> productTypesList) {
        this.productTypesList = productTypesList;
    }

    
    
}
