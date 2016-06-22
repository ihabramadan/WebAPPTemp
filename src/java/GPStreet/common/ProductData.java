/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.common;

import GPStreet.BB.GroupsBean;
import GPStreet.BB.PagesBean;
import GPStreet.DAO.UserDAO;
import GPStreet.DB.Managers.ManageProducts;
import GPStreet.DB.Mapping.Entity.GpstGroups;
import GPStreet.DB.Mapping.Entity.GpstProductTypes;
import GPStreet.DB.Mapping.Entity.GpstProducts;
import GPStreet.DB.Mapping.Entity.GpstUsers;
import GPStreet.EJB.StartupBean;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.log4j.Logger;

/**
 *
 * @author iramadan
 */
@Named(value = "productData")
@ManagedBean
@ViewScoped
public class ProductData implements Serializable {

    private Logger logger = Logger.getLogger(ProductData.class);
    private ManageProducts mp = new ManageProducts();
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

    public  void handleEvent(final AjaxBehaviorEvent event) {
    //get the member from the FacesContext.
        editMode = true;
    FacesContext context = FacesContext.getCurrentInstance();
        GpstProducts product;
    product = context.getApplication().evaluateExpressionGet(context, "#{productsList}", GpstProducts.class);
    this.id  =  product.getId();
    this.name = product.getName();
    this.description = product.getDescription();
    this.type = product.getGpstProductTypes();
    }
    public void clear() {
        this.id = 0;
        this.name = "";
        this.type = new GpstProductTypes();
        this.description = "";
        editMode = false;
    }

    @PostConstruct
    public void init() {
        editMode = false;
        type = new GpstProductTypes();
    }

    
    public void exitEditMode(){
        editMode = false;
        this.name  =  null;
        this.type= new GpstProductTypes();
        this.description = null;
        this.id = 0;
        clearMessages();
        
    }

    
    public void addProduct() {
        try {
            
            
            Integer productId = mp.addProduct(name, description, type.getId());
            
            if(productId == -1){
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(StartupBean.localRB.getString("users.database_error")));
            clear();
            }else{
            FacesContext.getCurrentInstance().addMessage("success", new FacesMessage(StartupBean.localRB.getString("products.add_success")));
            
            }
        
        } catch (Exception e) {
            logger.error(e.getMessage());
            clear();
        }
    }
    
    void clearMessages(){
        Iterator<String> itIds = FacesContext.getCurrentInstance().getClientIdsWithMessages();
        while (itIds.hasNext()) {
            List<FacesMessage> messageList = FacesContext.getCurrentInstance().getMessageList(itIds.next());
            if (!messageList.isEmpty()) { // if empty, it will be unmodifiable and throw UnsupportedOperationException...
                messageList.clear();
            }
}
    }
    public Boolean saveChanges(){
        try{
        Boolean result = mp.updateProduct(this.id, this.name, this.description, this.type.getId());
        if(result == false){
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(StartupBean.localRB.getString("users.database_error")));
            clear();
            return false;
        }else{
            FacesContext.getCurrentInstance().addMessage("success", new FacesMessage(StartupBean.localRB.getString("products.updated")));
            
            return true;
            
        }
        }catch(Exception ex){
            logger.error(ex.getMessage());
            return false;
        }
    }
    

    }
