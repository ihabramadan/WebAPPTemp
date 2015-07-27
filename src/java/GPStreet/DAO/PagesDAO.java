/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPStreet.DAO;

import GPStreet.DB.Managers.ManageGroups;
import GPStreet.DB.Managers.ManagePages;
import GPStreet.DB.Mapping.Entity.GpstGroups;
import GPStreet.DB.Mapping.Entity.GpstPages;
import GPStreet.EJB.StartupBean;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ihab_ramadan
 */
public class PagesDAO {
    
    public static List<GpstPages> getAllPages() {
        ManagePages pages = new ManagePages();
        List<GpstPages> allPages;
        allPages = pages.getAllPages();
        if (allPages != null) {
            if (allPages.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage(StartupBean.localRB.getString("database.error")));
                return null;
            }

        }

        return allPages;
    }
}
