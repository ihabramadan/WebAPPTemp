/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPStreet.common;

import GPStreet.BB.GroupsBean;
import GPStreet.DAO.GroupsDAO;
import GPStreet.DAO.UserDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;


@ManagedBean
@SessionScoped
/**
 *
 * @author iramadan
 */
public class GroupData {
    
    String groupName;
    String groupDesc;
    boolean editMode;
    
    private Logger logger = Logger.getLogger(GroupData.class);
    public String getGroupName() {
        return groupName;
    }

    public void exitEditMode(){
        editMode = false;
        //this.groupName  =  null;
        //this.groupDesc = null;
        
    }
    
    public void setGroupName(String grouName) {
        this.groupName = grouName;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    
    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }
    
    
    public void addGroup(){
        try {
        boolean success = GroupsDAO.addGroup(groupName,groupDesc);
        if(success){
            
            clear();
        }
        
            
                
        } catch (Exception ex) {
            logger.error(ex.getMessage());

        }
    }
    
    void clear(){
        this.groupName = null;
        this.groupDesc = null;
        
    }
}
