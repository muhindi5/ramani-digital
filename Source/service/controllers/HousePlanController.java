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
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
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
    private Map<String,Object> mapper;

   

    public HousePlanController() {
        newPlan = new HousePlan();
        numOfRooms = new Roomcount();
        typology = new Typology();
        roof = new Roofing();
        mapper = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }
    
    /*
    * Check if page is from new request, if new remove old dir name and generate
    * new directory name and store name in context. If its a postback request 
    * retain the directory name stored.
    */
    public void generatePlanDirNames(){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String,Object> currentDirKeyStore = context.getSessionMap();;
        String imgDirKey = "img.dir";
        String docDirKey = "doc.dir";
        if(FacesContext.getCurrentInstance().isPostback()){
            Logger.getAnonymousLogger().log(Level.INFO, "Detected postback, retaining dir tokens: {0},  {1}",
                    new Object[]{(String)currentDirKeyStore.get(imgDirKey),
                                 (String)currentDirKeyStore.get(docDirKey)});
            return;
        } //new page request
            if(currentDirKeyStore.containsKey(imgDirKey) && currentDirKeyStore.containsKey(docDirKey)){
                Logger.getAnonymousLogger().log(Level.INFO, "New request. Removing previous dir tokens: {0}, {1}",
                        new Object[]{(String)currentDirKeyStore.get(imgDirKey),
                                     (String)currentDirKeyStore.get(docDirKey)});
                currentDirKeyStore.remove(imgDirKey);
                currentDirKeyStore.remove(docDirKey);
            }
            String newImgDir = new RandomDirNameGen().getDirectoryName();
            String newDocDir = new RandomDirNameGen().getDirectoryName();
            Logger.getAnonymousLogger().log(Level.INFO, "Generated directory names: {0}, {1}",
                    new Object[]{newImgDir,newDocDir});
            currentDirKeyStore.put(imgDirKey, newImgDir);
            currentDirKeyStore.put(docDirKey, newDocDir);
    }
    
    /*Create newPlan entity and associated entities and persist to db*/
    public String savePlan(){
        newPlan.setUploadDate(GregorianCalendar.getInstance().getTime());
        //TODO: Random 4 digit generator relate both: images and option files
        newPlan.setImgFilesetDir((String)mapper.get("img.dir"));
        newPlan.setOptFilesetDir((String)mapper.get("doc.dir"));
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
