/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpstreet.mapmodels;

import GPStreet.DB.Managers.ManageLocations;
import GPStreet.DB.Managers.ManageUsers;
import GPStreet.DB.Mapping.Entity.GpstLocations;
import com.gpstreet.geo.GeoUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;

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
    private Marker selectedMarker;
    private LatLng addedMarkerLatLng;

    
    
    @PostConstruct
    public void init(){
        locationModel = new DefaultMapModel();
        createLocationsMarkers();
        if(allLocations != null || !allLocations.isEmpty()){
            midPoint = GeoUtils.midLocations(allLocations);
        }
        
    }
    public MapModel getLocationModel() {
        return locationModel;
    }

    public LatLng getAddedMarkerLatLng() {
        return addedMarkerLatLng;
    }

    public void setAddedMarkerLatLng(LatLng addedMarkerLatLng) {
        this.addedMarkerLatLng = addedMarkerLatLng;
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

    public Marker getSelectedMarker() {
        return selectedMarker;
    }

    public void setSelectedMarker(Marker selectedMarker) {
        this.selectedMarker = selectedMarker;
    }
    
    
    
    public void onMarkerSelect(OverlaySelectEvent event){
        selectedMarker =  (Marker) event.getOverlay();
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
        
        
        if(getAllLocations() == null || getAllLocations().isEmpty())
            setAllLocations(ml.getAllLocations());
        else
            setAllLocations(getAllLocations());
        
        for(GpstLocations location : getAllLocations()){
            mapMarkers.add(MapUtiles.createMarker(location, MapUtiles.MAPICON_CHECK_OUT));
            
        }
        
        PolyMark pm = new PolyMark();
        pm = MapUtiles.spiderfyMarkers(mapMarkers);
        //dbModel.getMarkers().clear();
        for(Marker newMarker : pm.markers){
            
            locationModel.addOverlay(newMarker);
        }
        for(Polyline p : pm.polylines){
            locationModel.addOverlay(p);
        }
        
    }
    
    
    
}
