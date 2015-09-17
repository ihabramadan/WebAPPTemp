/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPStreet.common;

import GPStreet.BB.CommonBB;
import com.gpstreet.mapmodels.MapUtiles;

import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author iramadan
 */
@ManagedBean
public  class Constants {
    private  final String arabicCode="ar";
    private  final String englishCode="en";
    //public static final ResourceBundle LOCAL_RB = ResourceBundle.getBundle("Messages",CommonBB.getLocal());
    private final String arabic = "عربى";
    private final String english = "English";
    public final  String dateFormat = "dd-MM-yyyy HH:mm";
    public final String dateTimePickerFormat="d-m-Y H:i";
    private String mapIconCheckIn=MapUtiles.MAPICON_CHECK_IN;
    private String mapIconCheckOut=MapUtiles.MAPICON_CHECK_OUT;
    private String mapIconMan=MapUtiles.MAPICON_MAN;

    public String getMapIconCheckOut() {
        return mapIconCheckOut;
    }

    public void setMapIconCheckOut(String mapIconCheckOut) {
        this.mapIconCheckOut = mapIconCheckOut;
    }

    public String getMapIconMan() {
        return mapIconMan;
    }

    public void setMapIconMan(String mapIconMan) {
        this.mapIconMan = mapIconMan;
    }
    
    
    public String getMapIconCheckIn() {
        return mapIconCheckIn;
    }

    public void setMapIconCheckIn(String mapIconCheckIn) {
        this.mapIconCheckIn = mapIconCheckIn;
    }

    
    
    public  String getDateFormat() {
        return dateFormat;
    }

    public String getDateTimePickerFormat() {
        return dateTimePickerFormat;
    }

    

    
    public String getArabic() {
        return arabic;
    }

    public String getEnglish() {
        return english;
    }
    
    
    public String getArabicCode() {
        return arabicCode;
    }

    public String getEnglishCode() {
        return englishCode;
    }
    
}
