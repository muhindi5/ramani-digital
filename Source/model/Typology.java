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
@Table(name = "pln_typology")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typology.findAll", query = "SELECT t FROM Typology t"),
    @NamedQuery(name = "Typology.findByTypologyId", query = "SELECT t FROM Typology t WHERE t.typologyId = :typologyId"),
    @NamedQuery(name = "Typology.findByStyle", query = "SELECT t FROM Typology t WHERE t.style = :style")})
public class Typology implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "typology_id")
    private Integer typologyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "style")
    private String style;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typology")
    private Collection<HousePlan> housePlanCollection;

    public Typology() {
        housePlanCollection = new ArrayList<>();
    }

    public Typology(Integer typologyId) {
        this.typologyId = typologyId;
    }

    public Typology(Integer typologyId, String style) {
        this.typologyId = typologyId;
        this.style = style;
    }

    public Integer getTypologyId() {
        return typologyId;
    }

    public void setTypologyId(Integer typologyId) {
        this.typologyId = typologyId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
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
        hash += (typologyId != null ? typologyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typology)) {
            return false;
        }
        Typology other = (Typology) object;
        if ((this.typologyId == null && other.typologyId != null) || (this.typologyId != null && !this.typologyId.equals(other.typologyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return style; //override this to populate typology selectmenu
    }
    
}
