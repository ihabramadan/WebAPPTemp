/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpstreet.mapmodels;

import GPStreet.DB.Managers.ManageUsers;
import GPStreet.DB.Mapping.Entity.GpstLocations;
import GPStreet.DB.Mapping.Entity.GpstTracker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.apache.log4j.Logger;

import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;

/**
 *
 * @author ihab.ramadan
 */

public class MapUtiles {
    
    static Logger logger = Logger.getLogger(MapUtiles.class);
    public static String MAPICON_MAN = "http://maps.google.com/mapfiles/ms/micons/man.png";
    public static String MAPICON_CHECK_IN="http://maps.google.com/mapfiles/ms/micons/green-dot.png";
    public static String MAPICON_CHECK_OUT="http://maps.google.com/mapfiles/ms/micons/red-dot.png";
    public static String MAPICON_LOCATION="http://maps.google.com/mapfiles/ms/micons/road_shield1.png";


    
    public enum GPST_STATES{
        CHECK_IN (1),
        CHECK_OUT(2) ;
        int code;
         GPST_STATES(int num){
            this.code = num;
        }
    }

    public enum GPST_COLORS {

        BLACK("#000000", 1),
        WHITE("#FFFFFF", 2);
        private String s;
        private int code;

        GPST_COLORS(String s, int code) {
            this.s = s;
            this.code = code;
        }

        public String toString() {
            return s;
        }

        public int getCode() {
            return code;
        }
    }

    public static List<LatLng> createCoord(List<GpstTracker> trackers) {
        List<LatLng> result = new ArrayList<>();
        if (trackers != null) {
            try {

                for (GpstTracker track : trackers) {
                    LatLng coord = new LatLng(track.getLatitude(), track.getLongitude());
                    result.add(coord);
                }

            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        } else {
            return null;
        }
        return result;
    }

    public static Polyline createPolyline(List<GpstTracker> trackers, int strokeWeight, String strokeColor, double strokeOpacity) {
        Polyline result = new Polyline();
        List<LatLng> coords = new ArrayList<>();
        if (trackers != null) {
            try {

                for (GpstTracker track : trackers) {
                    LatLng coord = new LatLng(track.getLatitude(), track.getLongitude());

                    coords.add(coord);
                }
                result.setStrokeWeight(strokeWeight);
                result.setStrokeColor(strokeColor);
                result.setStrokeOpacity(strokeOpacity);
                result.getPaths().addAll(coords);

            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        } else {
            return null;
        }
        return result;
    }

    public static Marker createMarker(GpstTracker tracker, String markerIcon) {
        ManageUsers mu = new ManageUsers();
        LatLng coord = new LatLng(tracker.getLatitude(), tracker.getLongitude());
        return new Marker(coord, mu.getUser(tracker.getGpstUsers().getId()).getFirstname() + " " + mu.getUser(tracker.getGpstUsers().getId()).getLastname(), tracker, markerIcon);
    }
    
    public static Marker createMarker(GpstLocations location, String markerIcon) {
        
        LatLng coord = new LatLng(location.getLatitude(), location.getLongitude());
        return new Marker(coord, location.getName(), location , markerIcon);
    }
    
    public static PolyMark spiderfyMarkers(List<Marker> orgMarkers){
        PolyMark pm = new PolyMark();
        List<Polyline> polyTracks = new ArrayList<>();
        List<Marker> tmpMarkers = orgMarkers;
        
       
        for(int i = 0 ; i < tmpMarkers.size(); i ++){        
            List<LatLng> latlngList1 = new ArrayList<>();
            List<LatLng> latlngList2 = new ArrayList<>();
            for(int j = 0 ; j < tmpMarkers.size(); j++){
                
                if(i != j){
                if(Math.abs(tmpMarkers.get(i).getLatlng().getLat() - tmpMarkers.get(j).getLatlng().getLat()) <= .00001  && Math.abs(tmpMarkers.get(i).getLatlng().getLng() - tmpMarkers.get(j).getLatlng().getLng()) <= .00001 )
                {
                   
                   
                   LatLng orgLatLng = new LatLng(tmpMarkers.get(j).getLatlng().getLat(), tmpMarkers.get(j).getLatlng().getLng() );
                   LatLng oldLatLng = new LatLng(tmpMarkers.get(i).getLatlng().getLat()+ (Math.random() -.5) / 1500, tmpMarkers.get(i).getLatlng().getLng() + (Math.random() -.5) / 1500);
                   LatLng newLatLng = new LatLng(tmpMarkers.get(j).getLatlng().getLat()+ (Math.random() -.5) / 1500,tmpMarkers.get(j).getLatlng().getLng() + (Math.random() -.5) / 1500);
                   latlngList1.add(orgLatLng);
                   //2latlngList.add(oldLatLng);
                   latlngList1.add(oldLatLng);
                   latlngList2.add(orgLatLng);
                   latlngList2.add(newLatLng);
                   tmpMarkers.get(j).setLatlng(newLatLng);
                   tmpMarkers.get(i).setLatlng(oldLatLng);
                   polyTracks.add(new Polyline(latlngList1));
                   polyTracks.add(new Polyline(latlngList2));
                   
                }
            }
        }
        }
        pm.markers = tmpMarkers;
        pm.polylines = polyTracks;
        return pm;
    }
}
