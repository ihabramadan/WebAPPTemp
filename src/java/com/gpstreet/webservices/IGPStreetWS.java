/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpstreet.webservices;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author ihab.ramadan
 */
@WebService 
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IGPStreetWS {
    @WebMethod public UserResponse login(@WebParam(name="userName") String userName ,@WebParam(name="password") String password);
}
