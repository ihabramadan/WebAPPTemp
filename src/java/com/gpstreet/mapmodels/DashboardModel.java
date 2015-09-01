/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpstreet.mapmodels;

import GPStreet.DB.Managers.ManageTracker;
import GPStreet.DB.Managers.ManageUsers;

import GPStreet.DB.Mapping.Entity.GpstTracker;
import GPStreet.DB.Mapping.Entity.GpstUsers;
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
        Date sDate =  new Date();
        Date eDate = new Date();
        ManageTracker mt =  new ManageTracker();
         try{
        
        String startDateStr = startDate;
        String endDateStr = endDate;
        
        
        SimpleDateFormat dateFormat  =  new SimpleDateFormat(new Constants().dateFormat);
        sDate = dateFormat.parse(startDateStr);
        eDate = dateFormat.parse(endDateStr);
        }catch(ParseException ex){
            logger.error(ex.getMessage());
        }
         String  user = selectedUsers.get(0).toString();
         
        List<GpstTracker> gpstTrackers = mt.getTracking(null,1 , Integer.parseInt(user), 0, 0, sDate,0 );
        Polyline polyline = MapUtiles.createPolyline(gpstTrackers , 7 , "#000000" , 0.9);
        dbModel.addOverlay(polyline);
        
    }
    
}
