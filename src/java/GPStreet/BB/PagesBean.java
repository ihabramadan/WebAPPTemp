/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPStreet.BB;


import GPStreet.DAO.PagesDAO;
import GPStreet.DB.Mapping.Entity.GpstPages;
import java.util.List;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "pagesBean")
/**
 *
 * @author ihab_ramadan
 */
public class PagesBean {
    int id;
    String name;
    String description;
    List pagesList;
    List selectedPages;

    public List getSelectedPages() {
        return selectedPages;
    }

    public void setSelectedPages(List selectedPages) {
        this.selectedPages = selectedPages;
    }

    
    public List getPagesList() {
        return pagesList;
    }

    public void setPagesList(List pagesList) {
        this.pagesList = pagesList;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
 
    public void saveGroupPages(){
        
    }
    public void bindPages(){
        List<GpstPages> allPages = PagesDAO.getAllPages();
        if (allPages != null) {
            setPagesList(allPages);
        }
    }
}
