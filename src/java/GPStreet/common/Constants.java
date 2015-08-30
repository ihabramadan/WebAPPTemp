/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPStreet.common;

import GPStreet.BB.CommonBB;
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
    public final  String dateFormat = "dd-MM-yyyy HH:mm:ss";

    public  String getDateFormat() {
        return dateFormat;
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
