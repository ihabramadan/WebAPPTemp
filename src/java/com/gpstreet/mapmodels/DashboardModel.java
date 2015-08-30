/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpstreet.mapmodels;

import GPStreet.DB.Managers.ManageTracker;
import GPStreet.DB.Managers.ManageUsers;

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
    
    
}
