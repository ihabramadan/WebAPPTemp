/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpstreet.geo;

import GPStreet.DB.Mapping.Entity.GpstTracker;
import java.util.List;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author ihab.ramadan
 */
public class GeoUtils {
    public static void midPoint(double lat1,double lon1,double lat2,double lon2){

    double dLon = Math.toRadians(lon2 - lon1);

    //convert to radians
    lat1 = Math.toRadians(lat1);
    lat2 = Math.toRadians(lat2);
    lon1 = Math.toRadians(lon1);

    double Bx = Math.cos(lat2) * Math.cos(dLon);
    double By = Math.cos(lat2) * Math.sin(dLon);
    double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
    double lon3 = lon1 + Math.atan2(By, Math.cos(lat1) + Bx);

    //print out in degrees
    System.out.println(Math.toDegrees(lat3) + " " + Math.toDegrees(lon3));
}
    
    public static LatLng midpoint(List<LatLng> points) {
        double Totweight = 0;
        double xt = 0;
        double yt = 0;
        double zt = 0;
        for (LatLng point : points) {
            Double latitude = point.getLat();
            Double longitude = point.getLng();

            /**
             * Convert Lat and Lon from degrees to radians.
             */
            double latn = latitude * Math.PI / 180;
            double lonn = longitude * Math.PI / 180;

            /**
             * Convert lat/lon to Cartesian coordinates
             */
            double xn = Math.cos(latn) * Math.cos(lonn);
            double yn = Math.cos(latn) * Math.sin(lonn);
            double zn = Math.sin(latn);

            /**
             * Compute weight (by time) If locations are to be weighted equally,
             * set wn to 1
             */
            double years = 0;
            double months = 0;
            double days = 0;
            double wn = true ? 1 : (years * 365.25) + (months * 30.4375) + days;

            /**
             * Compute combined total weight for all locations.
             */
            Totweight = Totweight + wn;
            xt += xn * wn;
            yt += yn * wn;
            zt += zn * wn;
        }

        /**
         * Compute weighted average x, y and z coordinates.
         */
        double x = xt / Totweight;
        double y = yt / Totweight;
        double z = zt / Totweight;

        /**
         * If abs(x) < 10-9 and abs(y) < 10-9 and abs(z) < 10-9 then the
         * geographic midpoint is the center of the earth.
         */
        double lat = -0.001944;
        double lon = -78.455833;
        if (Math.abs(x) < Math.pow(10, -9) && Math.abs(y) < Math.pow(10, -9) && Math.abs(z) < Math.pow(10, -9)) {
        } else {

            /**
             * Convert average x, y, z coordinate to latitude and longitude.
             * Note that in Excel and possibly some other applications, the
             * parameters need to be reversed in the atan2 function, for
             * example, use atan2(X,Y) instead of atan2(Y,X).
             */
            lon = Math.atan2(y, x);
            double hyp = Math.sqrt(x * x + y * y);
            lat = Math.atan2(z, hyp);

            /**
             * Convert lat and lon to degrees.
             */
            lat = lat * 180 / Math.PI;
            lon = lon * 180 / Math.PI;
        }
        
        return new LatLng(lat, lon);
    }
    public static LatLng midTrackers(List<GpstTracker> trackers) {
        double Totweight = 0;
        double xt = 0;
        double yt = 0;
        double zt = 0;
        for (GpstTracker tracker : trackers) {
            Double latitude = tracker.getLatitude();
            Double longitude = tracker.getLongitude();

            /**
             * Convert Lat and Lon from degrees to radians.
             */
            double latn = latitude * Math.PI / 180;
            double lonn = longitude * Math.PI / 180;

            /**
             * Convert lat/lon to Cartesian coordinates
             */
            double xn = Math.cos(latn) * Math.cos(lonn);
            double yn = Math.cos(latn) * Math.sin(lonn);
            double zn = Math.sin(latn);

            /**
             * Compute weight (by time) If locations are to be weighted equally,
             * set wn to 1
             */
            double years = 0;
            double months = 0;
            double days = 0;
            double wn = true ? 1 : (years * 365.25) + (months * 30.4375) + days;

            /**
             * Compute combined total weight for all locations.
             */
            Totweight = Totweight + wn;
            xt += xn * wn;
            yt += yn * wn;
            zt += zn * wn;
        }

        /**
         * Compute weighted average x, y and z coordinates.
         */
        double x = xt / Totweight;
        double y = yt / Totweight;
        double z = zt / Totweight;

        /**
         * If abs(x) < 10-9 and abs(y) < 10-9 and abs(z) < 10-9 then the
         * geographic midpoint is the center of the earth.
         */
        double lat = -0.001944;
        double lon = -78.455833;
        if (Math.abs(x) < Math.pow(10, -9) && Math.abs(y) < Math.pow(10, -9) && Math.abs(z) < Math.pow(10, -9)) {
        } else {

            /**
             * Convert average x, y, z coordinate to latitude and longitude.
             * Note that in Excel and possibly some other applications, the
             * parameters need to be reversed in the atan2 function, for
             * example, use atan2(X,Y) instead of atan2(Y,X).
             */
            lon = Math.atan2(y, x);
            double hyp = Math.sqrt(x * x + y * y);
            lat = Math.atan2(z, hyp);

            /**
             * Convert lat and lon to degrees.
             */
            lat = lat * 180 / Math.PI;
            lon = lon * 180 / Math.PI;
        }
        
        return new LatLng(lat, lon);
    }

    
}
