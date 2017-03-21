/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.controllers;

import model.HousePlan;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Roofing;
import model.Roomcount;
import model.Typology;
import model.facade.HousePlanFacade;
import model.facade.RoofingFacade;
import model.facade.RoomcountFacade;
import model.facade.TypologyFacade;


/**
 *
 * @author kelly
 */
@Named(value = "planController")
@SessionScoped
public class HousePlanController implements Serializable {

    /**
     * Creates a new instance of HousePlanController
     */
    
    /*Inject entiy manager*/
    @PersistenceContext(unitName = "ramani-digitalPU")
    private EntityManager em;
    @EJB
    private HousePlanFacade housePlanFacade;
    @EJB
    private RoofingFacade roofingFacade;
    @EJB
    private TypologyFacade typologyFacade;
    @EJB
    private RoomcountFacade roomCountFacade;

    private HousePlan newPlan;
    private Roofing roofType;
    private Roomcount numOfRooms;
    private Typology typology;

    public HousePlanController() {
        newPlan = new HousePlan();
        roofType = new Roofing();
        numOfRooms = new Roomcount();
        typology = new Typology();
    }
    
    /*Create newPlan entity and associated entities and persist to db*/
    public void savePlan(){
        newPlan.setUploadDate(GregorianCalendar.getInstance().getTime());
        //TODO: Random 4 digit generator relate both: images and option files
        newPlan.setImgFilesetDir("0012");
        newPlan.setOptFilesetDir("2309");

        //create plan
        roofingFacade.create(roofType);
        newPlan.setRoofId(roofType);//rooftype?
        typologyFacade.create(typology);
        newPlan.setTypologyId(typology);//house style? 
        roomCountFacade.create(numOfRooms);
        newPlan.setRoomcountId(numOfRooms);//room count?
        housePlanFacade.create(newPlan);
        FacesContext.getCurrentInstance().addMessage
        (null, new FacesMessage("Saving plan: #"+newPlan.toString()));
        System.out.println("Success! Saved Plan");
    }
    
    public void saveAndPublishPlan(){
        housePlanFacade.create(newPlan);
    }

    public HousePlan getNewPlan() {
        return newPlan;
    }

    public void setNewPlan(HousePlan newPlan) {
        this.newPlan = newPlan;
    }

    
    public Roofing getRoofType() {
        return roofType;
    }

    public void setRoofType(Roofing roofType) {
        this.roofType = roofType;
    }

    public Roomcount getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(Roomcount numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public Typology getTypology() {
        return typology;
    }

    public void setTypology(Typology typology) {
        this.typology = typology;
    }

    
    
}
