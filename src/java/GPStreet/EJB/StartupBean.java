/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPStreet.EJB;


import GPStreet.DB.Managers.ManageUsers;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 *
 * @author iramadan
 */

@Singleton
public class StartupBean {
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public static ResourceBundle localRB;
    Logger logger = Logger.getLogger(StartupBean.class);
  public void init(){
      try{
          //initShiro();
      localRB = ResourceBundle.getBundle("GPStreet.web.messages.Messages",FacesContext.getCurrentInstance().getViewRoot().getLocale());
      //initShiro();
      }catch(Exception ex){
          logger.error(ex.getMessage());
      }
  }
  
  
  public static void getUserInfo(HttpServletRequest request, String userName){
      
  }
  private void initShiro(){
      ExternalContext context =  FacesContext.getCurrentInstance().getExternalContext();
      
      Factory<SecurityManager> factory = new IniSecurityManagerFactory("E:\\GPStreet\\GPStreet\\web\\WEB-INF\\Shiro.ini");
      SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();
              if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                logger.info("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                logger.info("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                logger.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
        }

        //say who they are:
        //print their identifying principal (in this case, a username):
        logger.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

        //test a role:
        if (currentUser.hasRole("schwartz")) {
            logger.info("May the Schwartz be with you!");
        } else {
            logger.info("Hello, mere mortal.");
        }

        //test a typed permission (not instance-level)
        if (currentUser.isPermitted("lightsaber:weild")) {
            logger.info("You may use a lightsaber ring.  Use it wisely.");
        } else {
            logger.info("Sorry, lightsaber rings are for schwartz masters only.");
        }

        //a (very powerful) Instance Level permission:
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
            logger.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            logger.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }
        
  }

    
}
