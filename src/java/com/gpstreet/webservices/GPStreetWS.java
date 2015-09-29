/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpstreet.webservices;

import GPStreet.BB.UserBean;
import GPStreet.DAO.UserDAO;
import GPStreet.DB.Managers.ManageUsers;
import GPStreet.DB.Mapping.Entity.GpstGroups;
import GPStreet.DB.Mapping.Entity.GpstUsers;
import javax.jws.WebService;

/**
 *
 * @author ihab.ramadan
 */
@WebService(endpointInterface = "com.gpstreet.webservices.IGPStreetWS", targetNamespace = "http://GPStreet.com/GPStreetWS")
public class GPStreetWS {
    public UserResponse login(String userName , String password){
        
        UserResponse userResponse = new UserResponse();        
        //TODO: this line should be removed later    
        password = UserBean.encryptPass(password);
         ManageUsers users = new ManageUsers();
        GpstUsers user = users.authenticateUser(userName, password);       
        
        if(user != null){
        
        userResponse.setId(user.getId());
        userResponse.setUsername( user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());
        userResponse.setGroupId(((GpstGroups)user.getGpstGroupses().iterator().next()).getId());
        userResponse.setPhone(user.getPhone());
        userResponse.setPassword(user.getPassword());
        }
        return userResponse;
    }
    
}
