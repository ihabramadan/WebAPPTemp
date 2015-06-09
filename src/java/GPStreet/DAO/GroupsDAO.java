/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.DAO;

import GPStreet.DB.Managers.ManageGroups;
import GPStreet.DB.Managers.ManageUsers;
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

    String groupName;
    String groupDesc;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public static boolean addGroup(String groupName, String groupDesc) {
        ManageGroups mu = new ManageGroups();
        Integer groupId = null;
        try {
            groupId = mu.findGroup(groupName);
            if (groupId == null) {

                groupId = mu.addGroup(groupName, groupDesc);
                if (groupId == null) {
                    return false;
                }
                FacesContext.getCurrentInstance().addMessage("addGroupForm:addGroupSuccess", new FacesMessage(StartupBean.localRB.getString("groups.group_added")));
                return true;
            } else {
                FacesContext.getCurrentInstance().addMessage("addGroupForm:addGroupError", new FacesMessage(StartupBean.localRB.getString("groups.group_exists")));
                return false;
            }

        } catch (Exception ex) {

            return false;
        }

    }

    public static List<GpstGroups> getAllGroups() {
        ManageGroups groups = new ManageGroups();
        List<GpstGroups> allGroups;
        allGroups = groups.getAllGoups();
        if (allGroups != null) {
            if (allGroups.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage(StartupBean.localRB.getString("database.error")));
                return null;
            }

        }

        return allGroups;
    }

}
