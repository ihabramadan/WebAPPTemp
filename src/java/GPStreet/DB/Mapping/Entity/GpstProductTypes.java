/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.DB.Mapping.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ihab_ramadan
 */
@Entity
@Table(name = "gpst_product_types", catalog = "gpstreet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GpstProductTypes.findAll", query = "SELECT g FROM GpstProductTypes g"),
    @NamedQuery(name = "GpstProductTypes.findById", query = "SELECT g FROM GpstProductTypes g WHERE g.id = :id"),
    @NamedQuery(name = "GpstProductTypes.findByType", query = "SELECT g FROM GpstProductTypes g WHERE g.type = :type"),
    @NamedQuery(name = "GpstProductTypes.findByDescription", query = "SELECT g FROM GpstProductTypes g WHERE g.description = :description")})
public class GpstProductTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type", nullable = false, length = 45)
    private String type;
    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<GpstProducts> gpstProductsList;

    public GpstProductTypes() {
    }

    public GpstProductTypes(Integer id) {
        this.id = id;
    }

    public GpstProductTypes(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<GpstProducts> getGpstProductsList() {
        return gpstProductsList;
    }

    public void setGpstProductsList(List<GpstProducts> gpstProductsList) {
        this.gpstProductsList = gpstProductsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GpstProductTypes)) {
            return false;
        }
        GpstProductTypes other = (GpstProductTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GPStreet.DB.Mapping.Entity.GpstProductTypes[ id=" + id + " ]";
    }
    
}
