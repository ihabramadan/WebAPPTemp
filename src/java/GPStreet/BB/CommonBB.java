/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPStreet.BB;

import GPStreet.DAO.GroupsDAO;
import GPStreet.DB.Mapping.Entity.GpstGroups;
import GPStreet.DB.Mapping.Entity.GpstState;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


/**
 *
 * @author iramadan
 */
@Named(value = "commonBB")
@RequestScoped
public class CommonBB {

    
    /**
     * Creates a new instance of CommonBB
     */
    static ResourceBundle localRB = ResourceBundle.getBundle("Messages",FacesContext.getCurrentInstance().getViewRoot().getLocale());
    List<GpstGroups> gpstGroups;
    List<GpstState> allStatus;
    
    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }
    public static Locale getLocal(){
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }
    public String getCurrentURL() {
		String pageUrl = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		pageUrl = pageUrl.trim();
		return pageUrl;
	}
    public ResourceBundle getLocalRB() {
        if(this.localRB == null)
            this.localRB = ResourceBundle.getBundle("Messages",getLocal());
        return localRB;
    }

    public void setLocalRB(ResourceBundle localRB) {
        this.localRB = localRB; 
    }
    
    
    public CommonBB() {
        
    }
    public static List<GpstGroups> getAllGroups(){
        return GroupsDAO.getAllGroups();
    }

   
}
