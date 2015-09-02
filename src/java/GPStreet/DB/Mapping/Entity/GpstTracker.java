package GPStreet.DB.Mapping.Entity;
// Generated Sep 1, 2015 9:02:26 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * GpstTracker generated by hbm2java
 */
public class GpstTracker  implements java.io.Serializable {


     private Integer id;
     private GpstLocations gpstLocations;
     private GpstState gpstState;
     private GpstUsers gpstUsers;
     private double latitude;
     private double longitude;
     private Date date;
     private double deviceId;
     private String notes;

    public GpstTracker() {
    }

	
    public GpstTracker(GpstState gpstState, GpstUsers gpstUsers, double latitude, double longitude, double deviceId) {
        this.gpstState = gpstState;
        this.gpstUsers = gpstUsers;
        this.latitude = latitude;
        this.longitude = longitude;
        this.deviceId = deviceId;
    }
    public GpstTracker(GpstLocations gpstLocations, GpstState gpstState, GpstUsers gpstUsers, double latitude, double longitude, Date date, double deviceId, String notes) {
       this.gpstLocations = gpstLocations;
       this.gpstState = gpstState;
       this.gpstUsers = gpstUsers;
       this.latitude = latitude;
       this.longitude = longitude;
       this.date = date;
       this.deviceId = deviceId;
       this.notes = notes;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public GpstLocations getGpstLocations() {
        return this.gpstLocations;
    }
    
    public void setGpstLocations(GpstLocations gpstLocations) {
        this.gpstLocations = gpstLocations;
    }
    public GpstState getGpstState() {
        return this.gpstState;
    }
    
    public void setGpstState(GpstState gpstState) {
        this.gpstState = gpstState;
    }
    public GpstUsers getGpstUsers() {
        return this.gpstUsers;
    }
    
    public void setGpstUsers(GpstUsers gpstUsers) {
        this.gpstUsers = gpstUsers;
    }
    public double getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public double getDeviceId() {
        return this.deviceId;
    }
    
    public void setDeviceId(double deviceId) {
        this.deviceId = deviceId;
    }
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }




}


