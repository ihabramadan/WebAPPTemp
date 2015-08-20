/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.common;

import GPStreet.BB.GroupsBean;
import GPStreet.BB.PagesBean;
import GPStreet.BB.UserBean;
import GPStreet.DAO.UserDAO;
import GPStreet.DB.Managers.ManageGroups;
import GPStreet.DB.Mapping.Entity.GpstGroups;
import GPStreet.DB.Mapping.Entity.GpstUsers;
import GPStreet.EJB.StartupBean;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.ValidationException;
import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author iramadan
 */
@Named(value = "userData")
@ManagedBean
@ViewScoped
public class UserData implements Serializable {

    /**
     * Creates a new instance of UserData
     */
    public UserData() {

    }
    int userId;
    String userName;
    String password;
    String userFirstName;
    String userLastName;
    String userEmail;
    String userPhone;
    String userConfirmPassword;
    GroupsBean groupBean;
    PagesBean pageBean;
    GpstGroups mainGroup;
    boolean editMode;

    
    

    private static Map<String, Object> countries;
    private static Locale arabicLocal = new Locale("ar");
    private Logger logger = Logger.getLogger(UserData.class);
    private String local;

    
    @PostConstruct
    public void init() {
        editMode = false;
        groupBean = new GroupsBean();
        pageBean =  new PagesBean();
        mainGroup = new GpstGroups();

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    
    public GpstGroups getMainGroup() {
        return mainGroup;
    }

    public void setMainGroup(GpstGroups mainGroup) {
        this.mainGroup = mainGroup;
    }
    
    
    public GroupsBean getGroupBean() {
        return groupBean;
    }

    public void setGroupBean(GroupsBean groupBean) {
        this.groupBean = groupBean;
    }

    public PagesBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PagesBean pageBean) {
        this.pageBean = pageBean;
    }

    

    

    
    public Map<String, Object> getCountries() {
        return countries;
    }

    public String getUserConfirmPassword() {
        return userConfirmPassword;
    }

    public void setUserConfirmPassword(String userConfirmPassword) {
        this.userConfirmPassword = userConfirmPassword;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    static {

        countries = new LinkedHashMap<>();
        countries.put("English", Locale.ENGLISH);
        countries.put("Arabic", arabicLocal);
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
    
    public void localChanged(ValueChangeEvent e) {
        String newLocalValue = e.getNewValue().toString();
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
            if (entry.getValue().toString().equals(newLocalValue)) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
            }
        }
    }
    
    public void exitEditMode(){
        editMode = false;
        this.userName  =  null;
        this.password = null;
        this.userFirstName = null;
        this.userLastName = null;
        this.userPhone = null;
        this.userEmail = null;
        this.mainGroup.setId(0);
        this.userId = 0;
    }

    public  void handleEvent(final AjaxBehaviorEvent event) {
    //get the member from the FacesContext.
        editMode = true;
    FacesContext context = FacesContext.getCurrentInstance();
        GpstUsers userBean;
    userBean = context.getApplication().evaluateExpressionGet(context, "#{usersList}", GpstUsers.class);
    this.userName  =  userBean.getUsername();
    this.password = userBean.getPassword();
    this.userFirstName = userBean.getFirstname();
    this.userLastName = userBean.getLastname();
    this.userPhone = userBean.getPhone();
    this.userEmail = userBean.getEmail();
    this.mainGroup = userBean.getMainGroup();
    this.userId = userBean.getId();
    
    
  }
    public void linkLocalChanged(ActionEvent e) {
        String newLocalValue = e.getComponent().getId();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale((Locale) new Locale(newLocalValue));

    }
    public void validatePassword(ComponentSystemEvent event) throws ValidatorException{
         FacesContext fc = FacesContext.getCurrentInstance();
         try{
	  UIComponent components = event.getComponent();
          
	  // get password
	  UIInput uiInputPassword = (UIInput) components.findComponent("password");
	  String password = uiInputPassword.getLocalValue() == null ? ""
		: uiInputPassword.getLocalValue().toString();
	  String passwordId = uiInputPassword.getClientId();
 
	  // get confirm password
	  UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");
	  String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
		: uiInputConfirmPassword.getLocalValue().toString();
 
	  // Let required="true" do its job.
	  if (password.isEmpty() || confirmPassword.isEmpty()) {
		return;
	  }
 
	  if (!password.equals(confirmPassword)) {
              if(FacesContext.getCurrentInstance().isValidationFailed() == false){
		FacesMessage msg = new FacesMessage(StartupBean.localRB.getString("users.confirm_password_not_matched"));
                
		//msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		fc.addMessage("addError1", msg);
		fc.renderResponse();
                
              }
                
	  }
         }catch(ValidationException ex){
             logger.error(ex.getMessage());
         }
          
    }

    void clear(){
        //this.userName = null;
        //this.userFirstName = null;
        //this.userLastName = null;
        //this.userPhone = null;
        //this.userEmail =null;
        //this.password = null;
        //this.userConfirmPassword = null;
        //this.groupBean = new GroupsBean();
        
        editMode = false;
        this.userName  =  null;
        this.password = null;
        this.userConfirmPassword = null;
        this.userFirstName = null;
        this.userLastName = null;
        this.userPhone = null;
        this.userEmail = null;
        this.mainGroup = new GpstGroups();
        this.userId=0;
    }
    public void saveGroupPages(){
        ManageGroups mg = new ManageGroups();
            GpstGroups group = mg.getGroup(null,1);
        
    }
    public void saveChanges(){
        try{
        UserDAO.saveChanges(this.userId, this.userEmail, this.userPhone, this.userFirstName, this.userLastName, this.mainGroup.getId());
        }catch(Exception ex){
            logger.error(ex.getMessage());
        }
    }
    public void addUser() {
        try {
        boolean success = UserDAO.addUser(userName, password, userEmail, userPhone, userFirstName, userLastName ,mainGroup.getId());
        if(success){
            clear();
        }
        
            
                
        } catch (Exception ex) {
            logger.error(ex.getMessage());

        }
    }
}
