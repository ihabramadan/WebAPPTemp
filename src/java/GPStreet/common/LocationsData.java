/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPStreet.common;

import GPStreet.DAO.UserDAO;
import GPStreet.DB.Managers.ManageLocations;
import GPStreet.EJB.StartupBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.print.attribute.standard.Severity;

/**
 *
 * @author ihab_ramadan
 */

@ManagedBean
@SessionScoped

public class LocationsData {
    int id;
    String name;
    String description;
    double latitude;
    double longitude;

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public void saveLocation(){
        ManageLocations ml = new ManageLocations();
        Integer locationId =null;
        locationId = ml.addLocation(this.name, this.description, this.latitude, this.longitude);
        if(locationId == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,StartupBean.localRB.getString("users.database_error"),""));
        }else if(locationId == -1)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,StartupBean.localRB.getString("locationsModal.alreadyExists"),""));
         else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,StartupBean.localRB.getString("locationsModal.add_succseded"),""));
            clearDate();
        }
    }
    
    public void clearDate(){
        this.description = null;
        this.name = null;    }
    public boolean deleteLocation(int locationId){
        boolean success = false;
        ManageLocations ml = new ManageLocations();
        success = ml.deleteLocation(locationId);
        if(success == false){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, StartupBean.localRB.getString("database.error"),""));
            return false;
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,StartupBean.localRB.getString("locations.delete_success"),""));
            return true;
        }
    }
    
}
