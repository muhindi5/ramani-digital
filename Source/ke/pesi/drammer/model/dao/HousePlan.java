/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.pesi.drammer.model.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.inject.Scope;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kelly
 */
@Entity
@Table(name = "house_plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HousePlan.findAll", query = "SELECT h FROM HousePlan h"),
    @NamedQuery(name = "HousePlan.findByPlanId", query = "SELECT h FROM HousePlan h WHERE h.planId = :planId"),
    @NamedQuery(name = "HousePlan.findByUploadDate", query = "SELECT h FROM HousePlan h WHERE h.uploadDate = :uploadDate"),
    @NamedQuery(name = "HousePlan.findByTitle", query = "SELECT h FROM HousePlan h WHERE h.title = :title"),
    @NamedQuery(name = "HousePlan.findByImgFilesetDir", query = "SELECT h FROM HousePlan h WHERE h.imgFilesetDir = :imgFilesetDir"),
    @NamedQuery(name = "HousePlan.findByOptFilesetDir", query = "SELECT h FROM HousePlan h WHERE h.optFilesetDir = :optFilesetDir"),
    @NamedQuery(name = "HousePlan.findByPricePlan", query = "SELECT h FROM HousePlan h WHERE h.pricePlan = :pricePlan"),
    @NamedQuery(name = "HousePlan.findByPriceBoq", query = "SELECT h FROM HousePlan h WHERE h.priceBoq = :priceBoq"),
    @NamedQuery(name = "HousePlan.findByPriceMtrSchedule", query = "SELECT h FROM HousePlan h WHERE h.priceMtrSchedule = :priceMtrSchedule"),
    @NamedQuery(name = "HousePlan.findByTotalArea", query = "SELECT h FROM HousePlan h WHERE h.totalArea = :totalArea"),
    @NamedQuery(name = "HousePlan.findByFeaturedState", query = "SELECT h FROM HousePlan h WHERE h.featuredState = :featuredState"),
    @NamedQuery(name = "HousePlan.findByArchivedState", query = "SELECT h FROM HousePlan h WHERE h.archivedState = :archivedState")})

public class HousePlan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "plan_id")
    private Integer planId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "upload_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "img_fileset_dir")
    private String imgFilesetDir;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "opt_fileset_dir")
    private String optFilesetDir;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price_plan")
    private BigDecimal pricePlan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price_boq")
    private BigDecimal priceBoq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price_mtr_schedule")
    private BigDecimal priceMtrSchedule;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_area")
    private long totalArea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "featured_state")
    private int featuredState;
    @Basic(optional = false)
    @NotNull
    @Column(name = "archived_state")
    private int archivedState;
    @JoinColumn(name = "roof_id", referencedColumnName = "roofing_id")
    @ManyToOne(optional = false)
    private Roofing roof;
    @JoinColumn(name = "roomcount_id", referencedColumnName = "roomcount_id")
    @OneToOne(optional = false)
    private Roomcount roomCount;
    @JoinColumn(name = "typology_id", referencedColumnName = "typology_id")
    @ManyToOne(optional = false)
    private Typology typology;

    public HousePlan() {
    }

    public HousePlan(Integer planId) {
        this.planId = planId;
    }

    public HousePlan(Integer planId, Date uploadDate, String imgFilesetDir, String optFilesetDir, BigDecimal pricePlan, BigDecimal priceBoq, BigDecimal priceMtrSchedule, long totalArea, int featuredState, int archivedState) {
        this.planId = planId;
        this.uploadDate = uploadDate;
        this.imgFilesetDir = imgFilesetDir;
        this.optFilesetDir = optFilesetDir;
        this.pricePlan = pricePlan;
        this.priceBoq = priceBoq;
        this.priceMtrSchedule = priceMtrSchedule;
        this.totalArea = totalArea;
        this.featuredState = featuredState;
        this.archivedState = archivedState;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgFilesetDir() {
        return imgFilesetDir;
    }

    public void setImgFilesetDir(String imgFilesetDir) {
        this.imgFilesetDir = imgFilesetDir;
    }

    public String getOptFilesetDir() {
        return optFilesetDir;
    }

    public void setOptFilesetDir(String optFilesetDir) {
        this.optFilesetDir = optFilesetDir;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPricePlan() {
        return pricePlan;
    }

    public void setPricePlan(BigDecimal pricePlan) {
        this.pricePlan = pricePlan;
    }

    public BigDecimal getPriceBoq() {
        return priceBoq;
    }

    public void setPriceBoq(BigDecimal priceBoq) {
        this.priceBoq = priceBoq;
    }

    public BigDecimal getPriceMtrSchedule() {
        return priceMtrSchedule;
    }

    public void setPriceMtrSchedule(BigDecimal priceMtrSchedule) {
        this.priceMtrSchedule = priceMtrSchedule;
    }

    public long getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(long totalArea) {
        this.totalArea = totalArea;
    }

    public int getFeaturedState() {
        return featuredState;
    }

    public void setFeaturedState(int featuredState) {
        this.featuredState = featuredState;
    }

    public int getArchivedState() {
        return archivedState;
    }

    public void setArchivedState(int archivedState) {
        this.archivedState = archivedState;
    }

    public Roofing getRoof() {
        return roof;
    }

    public void setRoof(Roofing roofId) {
        this.roof = roofId;
    }

    public Roomcount getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Roomcount roomCount) {
        this.roomCount = roomCount;
    }

    public Typology getTypology() {
        return typology;
    }

    public void setTypology(Typology typology) {
        this.typology = typology;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planId != null ? planId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HousePlan)) {
            return false;
        }
        HousePlan other = (HousePlan) object;
        if ((this.planId == null && other.planId != null) || (this.planId != null && !this.planId.equals(other.planId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.HousePlan[ planId=" + planId + " ]";
    }
    
}
