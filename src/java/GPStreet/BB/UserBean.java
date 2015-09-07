/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPStreet.BB;

import GPStreet.DAO.GroupsDAO;
import GPStreet.DAO.UserDAO;
import GPStreet.DB.Mapping.Entity.GpstGroups;
import GPStreet.DB.Mapping.Entity.GpstPages;
import GPStreet.DB.Mapping.Entity.GpstState;
import GPStreet.DB.Mapping.Entity.GpstUsers;
import GPStreet.EJB.StartupBean;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author iramadan
 */
@ManagedBean
@SessionScoped
public class UserBean {
    String page;
    String userName;
    String password;
    String userFirstName;
    String userLastName;
    String userEmail;
    String userPhone;
    
    List<GpstUsers> usersList;
    
    List<GpstUsers> selectedUsers;
  
    
    private UIComponent submitBtn;
    private boolean loggedIn;
    Logger logger = Logger.getLogger(UserBean.class);

    @PostConstruct
    public void init(){
       
    }

    

    
    public List<GpstUsers> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<GpstUsers> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    
    
    public List<GpstUsers> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<GpstUsers> usersList) {
        this.usersList = usersList;
    }

    
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    
    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    
    
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    
    public UIComponent getSubmitBtn() {
        return submitBtn;
    }

    public void setSubmitBtn(UIComponent submitBtn) {
        this.submitBtn = submitBtn;
    }
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encryptPass(password);
    }
    
    public String encryptPass(String plainPassword){
        MessageDigest md = null;
         try
            {
              md = MessageDigest.getInstance("SHA"); //step 2
            }
            catch(NoSuchAlgorithmException e)
            {
             logger.error(e.getMessage());
            }
            try
            {
              md.update(plainPassword.getBytes("UTF-8")); //step 3
            }
            catch(UnsupportedEncodingException e)
            {
              logger.error(e.getMessage());
            }

            byte raw[] = md.digest(); //step 4
            String hash = (new BASE64Encoder()).encode(raw); //step 5
            return hash; //step 6
          }
    
    public void bindUsersList(){        
        List<GpstUsers> allUsers = UserDAO.getAllUsers();
        if(allUsers != null){
            setUsersList(allUsers);
        }
    }
    
    
    public Set<String> getUserPages(GpstUsers user){
         Set userGroup =  user.getGpstGroupses() ;
               Set userPages = new HashSet();
               GpstGroups group = null;
               GpstPages page = null;
               for(Iterator it = userGroup.iterator(); it.hasNext();){
                   group = (GpstGroups)it.next();
               }
               
               for(Iterator it = group.getGpstPageses().iterator(); it.hasNext();){
                   page = (GpstPages)it.next();
                   userPages.add(page.getName());
               }
               return userPages;
    }
    public String login(){
         
        GpstUsers  user;
        user = UserDAO.login(userName, password);
        if(user != null){
            setLoggedIn(true);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("CURRENT_USER", user);
            session.setAttribute("USER_PAGES", getUserPages(user));
            setUserFirstName(user.getFirstname());
            setUserLastName(user.getLastname());
            return "index.xhtml?faces-redirect=true";
        }
        else{ 
            setLoggedIn(false);
            return "login.xhtml";
        }
        }
    
    public boolean isAuthorized(String pageName){
        FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            
                Set<String> pages = (Set<String>) session.getAttribute("USER_PAGES");
               Path pagePath = Paths.get(pageName);
               if(!pages.contains(pagePath.getFileName().toString())){
                   return false;
               }
               return true;
                
                
            
    }
    public boolean deleteUser(int userId){
        boolean success = false;
        success = UserDAO.deleteUser(userId);
        if(success == false){
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(StartupBean.localRB.getString("database.error")));
            return false;
        }
        else{
            FacesContext.getCurrentInstance().addMessage("success", new FacesMessage(StartupBean.localRB.getString("users.delete_success")));
            return true;
        }
            
    }
    public void logout(){
            setLoggedIn(false);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("CURRENT_USER", null);
        }
    
}
