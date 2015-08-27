/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpstreet.mapmodels;

import GPStreet.DB.Managers.ManageUsers;
import GPStreet.DB.Mapping.Entity.GpstTracker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    public static Marker createMarker(GpstTracker tracker , String markerIcon){
        ManageUsers mu = new ManageUsers();
        LatLng coord =  new LatLng(tracker.getLatitude(), tracker.getLongitude());
        return new Marker(coord ,mu.getUser(tracker.getUserId()).getFirstname() + " " + mu.getUser(tracker.getUserId()).getLastname(),tracker, markerIcon);
    }
}
