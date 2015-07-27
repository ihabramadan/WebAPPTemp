/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.BB;

import GPStreet.DAO.GroupsDAO;
import GPStreet.DB.Mapping.Entity.GpstGroups;
import GPStreet.DB.Mapping.Entity.GpstPages;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "groupsBean", eager = true)
@SessionScoped
/**
 *
 * @author iramadan
 */
public class GroupsBean {

    int id;
    String name;
    String description;
    GpstGroups groupBean;
    List<GpstGroups> groupsList;
    List<GpstPages> pagesList;

    @PostConstruct
    public void init() {
        groupBean = new GpstGroups();
    }

    public List<GpstPages> getPagesList() {
        return pagesList;
    }

    public void setPagesList(List<GpstPages> pagesList) {
        this.pagesList = pagesList;
    }

    
    public GpstGroups getGroupBean() {
        return groupBean;
    }

    public void setGroupBean(GpstGroups groupBean) {
        this.groupBean = groupBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<GpstGroups> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<GpstGroups> groupsList) {
        this.groupsList = groupsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void bindGroups() {
        List<GpstGroups> allGroups = GroupsDAO.getAllGroups();
        if (allGroups != null) {
            setGroupsList(allGroups);
        }
    }
}
