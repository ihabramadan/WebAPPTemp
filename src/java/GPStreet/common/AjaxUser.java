/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.common;

import GPStreet.BB.UserBean;
import GPStreet.DB.Mapping.Entity.GpstUsers;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author ihab.ramadan
 */
@ManagedBean
public class AjaxUser {
    private GpstUsers userBean;

    
    public final void handleEvent(final AjaxBehaviorEvent event) {
    //get the member from the FacesContext.
    FacesContext context = FacesContext.getCurrentInstance();
    this.userBean = context.getApplication().evaluateExpressionGet(context, "#{usersList}", GpstUsers.class);
  }
    public GpstUsers getUserBean() {
        return userBean;
    }

    public void setUserBean(GpstUsers userBean) {
        this.userBean = userBean;
    }
    
}
