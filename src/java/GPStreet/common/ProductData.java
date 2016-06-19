/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.common;

import GPStreet.DB.Mapping.Entity.GpstProductTypes;
import GPStreet.DB.Mapping.Entity.GpstUsers;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author iramadan
 */
@Named(value = "productData")
@ManagedBean
@ViewScoped
public class ProductData implements Serializable {

    /**
     * Creates a new instance of Product Data
     */
    public ProductData() {

    }
    int id;
    String name;
    String description;
    GpstProductTypes type;
    Boolean editMode;

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

    public GpstProductTypes getType() {
        return type;
    }

    public void setType(GpstProductTypes type) {
        this.type = type;
    }

    
    public Boolean getEditMode() {
        return editMode;
    }

    public void setEditMode(Boolean editMode) {
        this.editMode = editMode;
    }

//    public  void handleEvent(final AjaxBehaviorEvent event) {
//    //get the member from the FacesContext.
//        editMode = true;
//    FacesContext context = FacesContext.getCurrentInstance();
//        GpstUsers userBean;
//    userBean = context.getApplication().evaluateExpressionGet(context, "#{usersList}", GpstUsers.class);
//    this.userName  =  userBean.getUsername();
//    this.password = userBean.getPassword();
//    this.userFirstName = userBean.getFirstname();
//    this.userLastName = userBean.getLastname();
//    this.userPhone = userBean.getPhone();
//    this.userEmail = userBean.getEmail();
//    this.mainGroup = userBean.getMainGroup();
//    this.userId = userBean.getId();
//    
    
    void clear(){
        this.id = 0;
        this.name="";
        this.type= null;
        this.description="";
        editMode = false;
    }
    
    
}
