/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPStreet.DAO;

import GPStreet.DB.Managers.ManageGroups;
import GPStreet.DB.Mapping.Entity.GpstGroups;
import GPStreet.EJB.StartupBean;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author iramadan
 */
public class GroupsDAO {
    public static List<GpstGroups> getAllGroups(){
         ManageGroups groups =  new ManageGroups();
        List<GpstGroups> allGroups;
        allGroups = groups.getAllGoups();
        if(allGroups != null){
            if(allGroups.isEmpty()){
                FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage(StartupBean.localRB.getString("database.error")));
                return null;
            }
            
        }  
                
        return allGroups;
    }
    
}
