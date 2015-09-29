/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpstreet.mapmodels;

import GPStreet.DB.Managers.ManageLocations;
import GPStreet.DB.Managers.ManageUsers;
import GPStreet.DB.Mapping.Entity.GpstLocations;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author ihab.ramadan
 */
@ManagedBean
@SessionScoped
public class LocationsModel {

    private MapModel locationModel;
    Logger logger = Logger.getLogger(LocationsModel.class);
    private LatLng midPoint;
    private List<GpstLocations> allLocations;
    @PostConstruct
    public void init(){
        locationModel = new DefaultMapModel();
        createLocationsMarkers();
    }
    public MapModel getLocationModel() {
        return locationModel;
    }

    public void setLocationModel(MapModel locationModel) {
        this.locationModel = locationModel;
    }

    public LatLng getMidPoint() {
        return midPoint;
    }

    public void setMidPoint(LatLng midPoint) {
        this.midPoint = midPoint;
    }

    public List<GpstLocations> getAllLocations() {
        return allLocations;
    }

    public void setAllLocations(List<GpstLocations> allLocations) {
        this.allLocations = allLocations;
    }
    public void bindAllLocations(){
        ManageLocations ms =  new ManageLocations();
        List<GpstLocations> allLocations = ms.getAllLocations();
        if(allLocations != null){
            setAllLocations(allLocations);
        }
    }
    
    public void createLocationsMarkers(){
        ManageLocations ms =  new ManageLocations();
        ManageLocations ml =  new ManageLocations();
        List<Marker> mapMarkers = new ArrayList<>();
        
        List<GpstLocations> allLocations = null;
        if(getAllLocations() == null)
            setAllLocations(ml.getAllLocations());
        allLocations = getAllLocations();
        
        for(GpstLocations location : allLocations){
            mapMarkers.add(MapUtiles.createMarker(location, MapUtiles.MAPICON_CHECK_OUT));
            
        }
        for(Marker newMarker : mapMarkers){
            locationModel.addOverlay(newMarker);
        }
        
    }
    
}
