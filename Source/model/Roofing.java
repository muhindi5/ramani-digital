/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
 * @author kelly
 */
@Entity
@Table(name = "pln_roofing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roofing.findAll", query = "SELECT r FROM Roofing r"),
    @NamedQuery(name = "Roofing.findByRoofingId", query = "SELECT r FROM Roofing r WHERE r.roofingId = :roofingId"),
    @NamedQuery(name = "Roofing.findByRoofType", query = "SELECT r FROM Roofing r WHERE r.roofType = :roofType")})
public class Roofing implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roofing_id")
    private Integer roofingId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "roof_type")
    private String roofType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roof")
    private Collection<HousePlan> housePlanCollection;

    public Roofing() {
        housePlanCollection = new ArrayList<>();
    }

    public Roofing(Integer roofingId) {
        this.roofingId = roofingId;
    }

    public Roofing(Integer roofingId, String roofType) {
        this.roofingId = roofingId;
        this.roofType = roofType;
    }

    public Integer getRoofingId() {
        return roofingId;
    }

    public void setRoofingId(Integer roofingId) {
        this.roofingId = roofingId;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    @XmlTransient
    public Collection<HousePlan> getHousePlanCollection() {
        return housePlanCollection;
    }

    public void setHousePlanCollection(Collection<HousePlan> housePlanCollection) {
        this.housePlanCollection = housePlanCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roofingId != null ? roofingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roofing)) {
            return false;
        }
        Roofing other = (Roofing) object;
        if ((this.roofingId == null && other.roofingId != null) || (this.roofingId != null && !this.roofingId.equals(other.roofingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return roofType;
    }
    
}
