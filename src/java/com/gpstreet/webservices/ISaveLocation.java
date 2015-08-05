/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpstreet.webservices;


import com.gpstreet.location.LocationUtils.SendingStat;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author ihab.ramadan
 */

@WebService 
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ISaveLocation {
  @WebMethod public SendingStat sendLocation();
     
}
