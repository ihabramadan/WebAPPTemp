/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpstreet.webservices;

import com.gpstreet.location.LocationUtils;
import com.gpstreet.location.LocationUtils.SendingStat;
import javax.jws.WebService;




/**
 *
 * @author ihab.ramadan
 */
@WebService(endpointInterface = "com.gpstreet.webservices.ISaveLocation", targetNamespace = "http://GPStreet.com/SaveLocation")
    public class SaveLocation implements ISaveLocation {
    public LocationUtils.SendingStat sendLocation(){
        return SendingStat.SUCCESS;
    }
    
}
