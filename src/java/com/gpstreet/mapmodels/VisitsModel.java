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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
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
public class VisitsModel {

    private MapModel vModel;


    private GpstUsers currentUser;

    
    Logger logger = Logger.getLogger(VisitsModel.class);
    private LatLng midPoint;
    private Marker selectedMarker;
    private String startDate;
    private String endDate;
    private List<GpstTracker> searchedVisits;

    @PostConstruct
    public void init() {
        Date date= new Date();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        currentUser =(GpstUsers) session.getAttribute("CURRENT_USER");
        
        SimpleDateFormat dateFormat  =  new SimpleDateFormat(new Constants().dateFormat);
        startDate = dateFormat.format(date);
        endDate = dateFormat.format(date);
        
        
        vModel = new DefaultMapModel();

        ManageTracker mt = new ManageTracker();
        ManageUsers mu = new ManageUsers();
        List<GpstTracker> gpstTrackers = mt.getTracking(null, 1,currentUser.getId(),  0, 0, null, 0);
        List<LatLng> allLatLng = new ArrayList<>();
        for (GpstTracker tracker : gpstTrackers) {
            allLatLng.add(new LatLng(tracker.getLatitude(), tracker.getLongitude()));
        }
        LatLng midpoint = GeoUtils.midTrackers(gpstTrackers);
        this.midPoint = midpoint;
        
        //Polyline
        Polyline polyline = MapUtiles.createPolyline(gpstTrackers, 5, "#FF9900", 0.7);

        //Basic marker
        for (GpstTracker tracker : gpstTrackers) {

            vModel.addOverlay(MapUtiles.createMarker(tracker, MapUtiles.MAPICON_MAN));
        }
        vModel.addOverlay(polyline);

    }

    public List<GpstTracker> getSearchedVisits() {
        return searchedVisits;
    }

    public void setSearchedVisits(List<GpstTracker> searchedVisits) {
        this.searchedVisits = searchedVisits;
    }
    public GpstUsers getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(GpstUsers currentUser) {
        this.currentUser = currentUser;
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

    
    public MapModel getvModel() {
        return vModel;
    }

    public void setvModel(MapModel vModel) {
        this.vModel = vModel;
    }
    
    public Marker getSelectedMarker() {
        return selectedMarker;
    }

    public LatLng getMidPoint() {
        return midPoint;
    }

    public void setMidPoint(LatLng midPoint) {
        this.midPoint = midPoint;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        selectedMarker = (Marker) event.getOverlay();
    }

    private void clear() {
        this.endDate = null;
        this.startDate = null;
    }

    public void searchVisits() {
        try {
            Date sDate = new Date();
            Date eDate = new Date();
            ManageTracker mt = new ManageTracker();
            String startDateStr = startDate;
            String endDateStr = endDate;
            if (!"".equals(startDateStr)) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(new Constants().dateFormat);
                    sDate = dateFormat.parse(startDateStr);
                    eDate = dateFormat.parse(endDateStr);
                } catch (ParseException ex) {
                    logger.error(ex.getMessage());
                }
            }
            

            List<GpstTracker> gpstVisits = mt.getTracking(null,1, currentUser.getId(), 0, 0, sDate, eDate, 0);
            setSearchedVisits(gpstVisits);
            //Polyline polyline = MapUtiles.createPolyline(gpstTrackers , 7 , MapUtiles.GPST_COLORS.BLACK.toString() , 0.9);
            if (vModel != null) {
                vModel.getMarkers().clear();
                vModel.getPolylines().clear();
            }
            List<Marker> mapMarkers = new ArrayList<>();
            for (GpstTracker tracker : gpstVisits) {

                if (tracker.getGpstState().getId() == MapUtiles.GPST_STATES.CHECK_IN.code) {
                    mapMarkers.add(MapUtiles.createMarker(tracker, MapUtiles.MAPICON_CHECK_IN));
                } else if (tracker.getGpstState().getId() == MapUtiles.GPST_STATES.CHECK_OUT.code) {
                    mapMarkers.add(MapUtiles.createMarker(tracker, MapUtiles.MAPICON_CHECK_OUT));
                } else {
                    mapMarkers.add(MapUtiles.createMarker(tracker, null));
                }
            }
            PolyMark pm = new PolyMark();
            pm = MapUtiles.spiderfyMarkers(mapMarkers);
            //dbModel.getMarkers().clear();
            for (Marker newMarker : pm.markers) {
                vModel.addOverlay(newMarker);
            }
            for (Polyline p : pm.polylines) {
                vModel.addOverlay(p);
            }
            //dbModel.addOverlay(polyline);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }
}
