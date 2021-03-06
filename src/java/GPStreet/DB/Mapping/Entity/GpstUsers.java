package GPStreet.DB.Mapping.Entity;
// Generated Sep 1, 2015 9:02:26 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * GpstUsers generated by hbm2java
 */
public class GpstUsers  implements java.io.Serializable {


     private Integer id;
     private String username;
     private String password;
     private String email;
     private String phone;
     private String firstname;
     private String lastname;
     
     private Set gpstGroupses = new HashSet(0);
      private GpstGroups mainGroup;

    public GpstUsers() {
    }

    public GpstGroups getMainGroup() {
	
        if(gpstGroupses.toArray().length > 0)
            return (GpstGroups)gpstGroupses.toArray()[0];
        else 
            return new GpstGroups();
        
    }

    public void setMainGroup(GpstGroups mainGroup) {
        this.mainGroup = mainGroup;
    }

	
    public GpstUsers(String username) {
        this.username = username;
    }
    public GpstUsers(String username, String password, String email, String phone, String firstname, String lastname,  Set gpstGroupses) {
       this.username = username;
       this.password = password;
       this.email = email;
       this.phone = phone;
       this.firstname = firstname;
       this.lastname = lastname;
       
       this.gpstGroupses = gpstGroupses;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getFirstname() {
        return this.firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
   
    public Set getGpstGroupses() {
        return this.gpstGroupses;
    }
    
    public void setGpstGroupses(Set gpstGroupses) {
        this.gpstGroupses = gpstGroupses;
    }




}


