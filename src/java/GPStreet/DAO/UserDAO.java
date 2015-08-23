/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.DAO;

import GPStreet.DB.Managers.ManageUsers;
import GPStreet.DB.Mapping.Entity.GpstUsers;
import GPStreet.EJB.StartupBean;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author iramadan
 */
public class UserDAO {
    
    public static List<GpstUsers> getAllUsers(){
        ManageUsers users =  new ManageUsers();
        List<GpstUsers> allUsers;
        allUsers = users.listALLUsers();
        if(allUsers != null){
            if(allUsers.isEmpty()){
                FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage(StartupBean.localRB.getString("database.error")));
                return null;
            }
            
        }  
                
        return allUsers;
        
    }

    
    public static boolean addUser(String userName,String password,String userEmail,String userPhone,String userFirstName,String userLastName , int groupId){
            ManageUsers mu = new ManageUsers();
            Integer userId = null;
        try {
            userId = mu.findUser(userName);
            if(userId == null){
                
            
            userId = mu.addUser(userName, password, userEmail, userPhone, userFirstName, userLastName ,groupId);
            if(userId == null)
                return false;
            FacesContext.getCurrentInstance().addMessage("addSuccess1", new FacesMessage(StartupBean.localRB.getString("users.user_added")));
            FacesContext.getCurrentInstance().addMessage("success", new FacesMessage(StartupBean.localRB.getString("users.user_added")));
                return true;
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("addError1", new FacesMessage(StartupBean.localRB.getString("users.user_exists")));
                FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(StartupBean.localRB.getString("users.user_exists")));
                return false;
            }
            
                
               
                
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(StartupBean.localRB.getString("users.database_error")));
            return false;
        }
        
        }
    
        public static boolean deleteUser(int userId){
            ManageUsers users = new ManageUsers();
            boolean success = users.deleteUser(userId);
            if(success == false){                
                return false;
            }
            return true;
        }
    public static GpstUsers login(String userName, String password) {
        ManageUsers users = new ManageUsers();
        GpstUsers user = users.authenticateUser(userName, password);
        if (user != null) {
            if (user.getId() != null) {
                return user;
            } else {
                FacesContext.getCurrentInstance().addMessage("loginForm:submitBtn", new FacesMessage(StartupBean.localRB.getString("database.error")));
                return null;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("loginForm:submitBtn", new FacesMessage(StartupBean.localRB.getString("login.failed")));
            return null;
        }
    }
    
    public static boolean saveChanges(int userId ,String userEmail,String userPhone,String userFirstName,String userLastName , int groupId){
        ManageUsers mu = new ManageUsers();
        boolean result = mu.updateUser(userId, userEmail, userPhone, userFirstName, userLastName, groupId);
        if(result == false){
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(StartupBean.localRB.getString("users.database_error")));
            return false;
        }else{
            FacesContext.getCurrentInstance().addMessage("success", new FacesMessage(StartupBean.localRB.getString("users.updated")));
            return true;
            
        }
            
    }
    
}
