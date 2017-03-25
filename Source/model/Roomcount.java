/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kelly
 */
@Entity
@Table(name = "pln_roomcount")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roomcount.findAll", query = "SELECT r FROM Roomcount r"),
    @NamedQuery(name = "Roomcount.findByRoomcountId", query = "SELECT r FROM Roomcount r WHERE r.roomcountId = :roomcountId"),
    @NamedQuery(name = "Roomcount.findByBathrooms", query = "SELECT r FROM Roomcount r WHERE r.bathrooms = :bathrooms"),
    @NamedQuery(name = "Roomcount.findByBedrooms", query = "SELECT r FROM Roomcount r WHERE r.bedrooms = :bedrooms"),
    @NamedQuery(name = "Roomcount.findByStoreys", query = "SELECT r FROM Roomcount r WHERE r.storeys = :storeys"),
    @NamedQuery(name = "Roomcount.findByKitchens", query = "SELECT r FROM Roomcount r WHERE r.kitchens = :kitchens"),
    @NamedQuery(name = "Roomcount.findByDining", query = "SELECT r FROM Roomcount r WHERE r.dining = :dining"),
    @NamedQuery(name = "Roomcount.findByLiving", query = "SELECT r FROM Roomcount r WHERE r.living = :living"),
    @NamedQuery(name = "Roomcount.findByLaundry", query = "SELECT r FROM Roomcount r WHERE r.laundry = :laundry")})
public class Roomcount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roomcount_id")
    private Integer roomcountId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bathrooms")
    private int bathrooms;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bedrooms")
    private int bedrooms;
    @Basic(optional = false)
    @NotNull
    @Column(name = "storeys")
    private int storeys;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kitchens")
    private int kitchens;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dining")
    private int dining;
    @Basic(optional = false)
    @NotNull
    @Column(name = "living")
    private int living;
    @Basic(optional = false)
    @NotNull
    @Column(name = "laundry")
    private int laundry;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "roomCount")
    private HousePlan housePlan;

    public Roomcount() {
    }

    public Roomcount(Integer roomcountId) {
        this.roomcountId = roomcountId;
    }

    public Roomcount(Integer roomcountId, int bathrooms, int bedrooms, int storeys, int kitchens, int dining, int living, int laundry) {
        this.roomcountId = roomcountId;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.storeys = storeys;
        this.kitchens = kitchens;
        this.dining = dining;
        this.living = living;
        this.laundry = laundry;
    }

    public Integer getRoomcountId() {
        return roomcountId;
    }

    public void setRoomcountId(Integer roomcountId) {
        this.roomcountId = roomcountId;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getStoreys() {
        return storeys;
    }

    public void setStoreys(int storeys) {
        this.storeys = storeys;
    }

    public int getKitchens() {
        return kitchens;
    }

    public void setKitchens(int kitchens) {
        this.kitchens = kitchens;
    }

    public int getDining() {
        return dining;
    }

    public void setDining(int dining) {
        this.dining = dining;
    }

    public int getLiving() {
        return living;
    }

    public void setLiving(int living) {
        this.living = living;
    }

    public int getLaundry() {
        return laundry;
    }

    public void setLaundry(int laundry) {
        this.laundry = laundry;
    }

    public HousePlan getHousePlan() {
        return housePlan;
    }

    public void setHousePlan(HousePlan housePlan) {
        this.housePlan = housePlan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomcountId != null ? roomcountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roomcount)) {
            return false;
        }
        Roomcount other = (Roomcount) object;
        if ((this.roomcountId == null && other.roomcountId != null) || (this.roomcountId != null && !this.roomcountId.equals(other.roomcountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Roomcount[ roomcountId=" + roomcountId + " ]";
    }
    
}
