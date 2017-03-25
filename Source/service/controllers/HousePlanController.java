/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 */

package service.controllers;

/**
 *
 * @project: ramani-digital
 * @author kelly
 * 
 */
import model.HousePlan;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.GregorianCalendar;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
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
    private Roomcount numOfRooms;
    private Typology typology;
    private Roofing roof;
    private Boolean featuredState;

   

    public HousePlanController() {
        newPlan = new HousePlan();
        numOfRooms = new Roomcount();
//        typologyFacade = new TypologyFacade();
//        roofingFacade = new RoofingFacade();
        typology = new Typology();
        roof = new Roofing();
    }
    
    /*Create newPlan entity and associated entities and persist to db*/
    public void savePlan(){
        FacesContext.getCurrentInstance().addMessage
        (null, new FacesMessage("Accessed at: #"+GregorianCalendar.getInstance().getTime().toString()));
        newPlan.setUploadDate(GregorianCalendar.getInstance().getTime());
        //TODO: Random 4 digit generator relate both: images and option files
        newPlan.setImgFilesetDir("1902");
        newPlan.setOptFilesetDir("5655");
        newPlan.setFeaturedState(convertBooleanToInt(featuredState));
        
        //get the ids of the selected roof and typology
        typology = typologyFacade.find(typology.getTypologyId());
        roof = roofingFacade.find(roof.getRoofingId());
        typology.getHousePlanCollection().add(newPlan);
        roof.getHousePlanCollection().add(newPlan);
        newPlan.setTypology(typology);
        newPlan.setRoof(roof);
        //create plan
        roomCountFacade.create(numOfRooms);
        newPlan.setRoomCount(numOfRooms);//room count?
        housePlanFacade.create(newPlan);
        FacesContext.getCurrentInstance().addMessage
        (null, new FacesMessage("Saved at: #"+GregorianCalendar.getInstance().getTime().toString()));
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
        return roof;
    }

    public void setRoofType(Roofing roofType) {
        this.roof = roofType;
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

//    public int convertFeaturedState(String state){
//        if("true".equals(state)) return 1;
//        else return 0;
//    }
    
     public Boolean getFeaturedState() {
        return featuredState;
    }

    public void setFeaturedState(Boolean featuredState) {
        this.featuredState = featuredState;
    }

    private int convertBooleanToInt(Boolean state){
        return state ? 1 : 0;
    }
    
}
