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
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
import util.RandomDirNameGen;

@Named(value = "planController")
@RequestScoped
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
    private Map<String,Object> currentDirKeyStore;
    private static final String IMG_DIR_KEY = "img.dir";
    private static final String DOC_DIR_KEY = "doc.dir";

   

    public HousePlanController() {
        newPlan = new HousePlan();
        numOfRooms = new Roomcount();
        typology = new Typology();
        roof = new Roofing();
        currentDirKeyStore = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
        Logger.getAnonymousLogger().log(Level.INFO,"Created new HPController");
    }
    
    /*
    * Check if page is from new request, if new remove old dir name and generate
    * new directory name and store name in context. If its a postback request 
    * retain the directory name stored.
    */
    @PostConstruct
    public void generatePlanDirNames(){
        if(FacesContext.getCurrentInstance().isPostback()){
            Logger.getAnonymousLogger().log(Level.INFO, "Detected postback, retaining dir tokens: {0},  {1}",
                    new Object[]{(String)currentDirKeyStore.get(IMG_DIR_KEY),
                                 (String)currentDirKeyStore.get(DOC_DIR_KEY)});
            return;
        } //new request...create directories
            if(currentDirKeyStore.containsKey(IMG_DIR_KEY) && currentDirKeyStore.containsKey(DOC_DIR_KEY)){
                Logger.getAnonymousLogger().log(Level.INFO, "New request. Removing previous dir tokens: {0}, {1}",
                        new Object[]{(String)currentDirKeyStore.get(IMG_DIR_KEY),
                                     (String)currentDirKeyStore.get(DOC_DIR_KEY)});
                currentDirKeyStore.remove(IMG_DIR_KEY);
                currentDirKeyStore.remove(DOC_DIR_KEY);
            }
            String newImgDir = new RandomDirNameGen().getDirectoryName();
            String newDocDir = new RandomDirNameGen().getDirectoryName();
            Logger.getAnonymousLogger().log(Level.INFO, "Generated directory names: {0}, {1}",
                    new Object[]{newImgDir,newDocDir});
            currentDirKeyStore.put(IMG_DIR_KEY, newImgDir);
            currentDirKeyStore.put(DOC_DIR_KEY, newDocDir);
    }
    
    /*Create newPlan entity and associated entities and persist to db*/
    public String savePlan(){
        newPlan.setUploadDate(GregorianCalendar.getInstance().getTime());
        //TODO: Random 4 digit generator relate both: images and option files
        newPlan.setImgFilesetDir((String)currentDirKeyStore.get(IMG_DIR_KEY));
        newPlan.setOptFilesetDir((String)currentDirKeyStore.get(DOC_DIR_KEY));
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
        (null, new FacesMessage("Plan Saved at: #"+GregorianCalendar.getInstance().getTime().toString()));
        return "product_list?faces-redirect=true";
    }
    
    public void saveAndPublishPlan(){
        housePlanFacade.create(newPlan);
    }
    
    /*View a listing of all plans*/
    public List<HousePlan> getAllPlans(){
        List<HousePlan> plans;
        plans = housePlanFacade.findAll();
        return plans;
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
