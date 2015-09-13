/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpstreet.mapmodels;

import GPStreet.DB.Managers.ManageStates;
import GPStreet.DB.Managers.ManageTracker;
import GPStreet.DB.Managers.ManageUsers;
import GPStreet.DB.Mapping.Entity.GpstState;

import GPStreet.DB.Mapping.Entity.GpstTracker;

import GPStreet.common.Constants;
import com.gpstreet.geo.GeoUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
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
@ViewScoped
public class DashboardModel {
    private MapModel dbModel;
    Logger logger = Logger.getLogger(ManageUsers.class);
    private LatLng midPoint;
    private Marker selectedMarker;
    private String startDate;
    private String endDate;
    private List selectedUsers;
    private List selectedStates;
    private List<GpstState> allStates;
    
    
    @PostConstruct
    public void init() {
       
       
        Date date =  new Date();
        try{
        dbModel =new DefaultMapModel();
        String dateStr = "25-08-2015 05:05:25";
        
        
        SimpleDateFormat dateFormat  =  new SimpleDateFormat(new Constants().dateFormat);
        date = dateFormat.parse(dateStr);
        }catch(ParseException ex){
            logger.error(ex.getMessage());
        }
        ManageTracker mt =  new ManageTracker();
        ManageUsers mu = new ManageUsers();
        List<GpstTracker> gpstTrackers = mt.getTracking(null, 1, 0, 0, 0, date,0 );
        List<LatLng> allLatLng = new ArrayList<>();
        for(GpstTracker tracker : gpstTrackers ){
            allLatLng.add(new LatLng(tracker.getLatitude() , tracker.getLongitude()));
        }
        LatLng midpoint = GeoUtils.midTrackers(gpstTrackers);
        this.midPoint = midpoint;
        List<LatLng> coords = MapUtiles.createCoord(gpstTrackers);
        
        //Polyline
        Polyline polyline = MapUtiles.createPolyline(gpstTrackers , 5 , "#FF9900" , 0.7);
        
        
        //Basic marker
        for(GpstTracker tracker : gpstTrackers ){
            
            dbModel.addOverlay(MapUtiles.createMarker(tracker, MapUtiles.MAPICON_MAN));
        }
        dbModel.addOverlay(polyline);
        
    }

    public List getSelectedStates() {
        return selectedStates;
    }

    public void setSelectedStates(List selectedStates) {
        this.selectedStates = selectedStates;
    }

    
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public List getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    
   

    
    public Marker getSelectedMarker() {
        return selectedMarker;
    }

    
    
    public MapModel getDbModel() {
        return dbModel;
    }

    public void setDbModel(MapModel dbModel) {
        this.dbModel = dbModel;
    }

    public LatLng getMidPoint() {
        return midPoint;
    }

    public void setMidPoint(LatLng midPoint) {
        this.midPoint = midPoint;
    }
    public void onMarkerSelect(OverlaySelectEvent event){
        selectedMarker =  (Marker) event.getOverlay();
    }
    
    private void clear(){
        
        this.endDate = null;
        this.startDate = null;
        
        this.selectedUsers = null;
        this.selectedUsers = null;
        
    }
    public void valueChanged(ValueChangeEvent event) {
        Date date =  new Date();
        ManageTracker mt =  new ManageTracker();
         try{
        dbModel =new DefaultMapModel();
        String dateStr = "25-08-2015 05:05:25";
        
        
        SimpleDateFormat dateFormat  =  new SimpleDateFormat(new Constants().dateFormat);
        date = dateFormat.parse(dateStr);
        }catch(ParseException ex){
            logger.error(ex.getMessage());
        }
        List<GpstTracker> gpstTrackers = mt.getTracking(null, 1, 1, 0, 0, date,0 );
        Polyline polyline = MapUtiles.createPolyline(gpstTrackers , 7 , "#FFFFFF" , 0.9);
        dbModel.addOverlay(polyline);
    }
    public void trackUsers(){
        try{
        Date sDate =  new Date();
        Date eDate = new Date();
        ManageTracker mt =  new ManageTracker();
        String startDateStr = startDate;
        String endDateStr = endDate;
        if(!"".equals(startDateStr)){
         try{
        
        
        
        
        SimpleDateFormat dateFormat  =  new SimpleDateFormat(new Constants().dateFormat);
        sDate = dateFormat.parse(startDateStr);
        eDate = dateFormat.parse(endDateStr);
        }catch(ParseException ex){
            logger.error(ex.getMessage());
        }
         }
         List<Integer> iListStates = new ArrayList<>();
         for(int i = 0 ; i< selectedStates.size(); i++){
             iListStates.add(Integer.parseInt(selectedStates.get(i).toString()));
         }
         List<Integer> iListUsers = new ArrayList<>();
         for(int i = 0 ; i< selectedUsers.size(); i++){
             iListUsers.add(Integer.parseInt(selectedUsers.get(i).toString()));
         }
         
         
        List<GpstTracker> gpstTrackers = mt.getTracking(null,iListStates,iListUsers , 0, 0, sDate,eDate,0 );
        //Polyline polyline = MapUtiles.createPolyline(gpstTrackers , 7 , MapUtiles.GPST_COLORS.BLACK.toString() , 0.9);
        if(dbModel != null){
            dbModel.getMarkers().clear();
            dbModel.getPolylines().clear();
        }
        List<Marker> mapMarkers = new ArrayList<>();
       for(GpstTracker tracker : gpstTrackers ){
            
           if(tracker.getGpstState().getId() == MapUtiles.GPST_STATES.CHECK_IN.code){
                mapMarkers.add(MapUtiles.createMarker(tracker, MapUtiles.MAPICON_CHECK_IN));
           }
           else if(tracker.getGpstState().getId() == MapUtiles.GPST_STATES.CHECK_IN.code){
               mapMarkers.add(MapUtiles.createMarker(tracker, MapUtiles.MAPICON_CHECK_OUT));
           }
           else
               mapMarkers.add(MapUtiles.createMarker(tracker, null));
        }
        PolyMark pm = new PolyMark();
        pm = MapUtiles.spiderfyMarkers(mapMarkers);
        //dbModel.getMarkers().clear();
        for(Marker newMarker : pm.markers){
            dbModel.addOverlay(newMarker);
        }
        for(Polyline p : pm.polylines){
            dbModel.addOverlay(p);
        }
        //dbModel.addOverlay(polyline);
        }catch(Exception ex){
            logger.error(ex.getMessage());
        }
        
        
    }
     public List<GpstState> getAllStates() {
        return allStates;
    }

    public void setAllStates(List<GpstState> allStates) {
        this.allStates = allStates;
    }
    
    
    public void bindAllStates(){
        ManageStates ms =  new ManageStates();
        List<GpstState> allStates = ms.getAllStates();
        if(allStates != null){
            setAllStates(allStates);
        }
    }
}
