/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */
package ke.pesi.drammer.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import ke.pesi.drammer.model.dao.HousePlan;
import ke.pesi.drammer.model.facade.HousePlanFacade;
import ke.pesi.drammer.services.util.exeptions.EmptyListException;

/**
 *
 * @author kelly
 */
@SessionScoped
public class CatalogItemsController implements Serializable{

    @EJB
    private HousePlanFacade housePlanFacade;
    private List<HousePlan> plans;
    private HousePlan current;
    private int selectedId;
    
    //utilities
    private boolean boqStatus;
    private boolean mtrsStatus;
    private double totalPrice;
    private List<String> fileDownloadFormats;
    private String downloadOption;

    


    /**
     * Creates a new instance of CatalogItemsController
     */
    public CatalogItemsController() {
        boqStatus = true;
        mtrsStatus = true;
        fileDownloadFormats = new ArrayList<>();
        Logger.getAnonymousLogger().log(Level.INFO,"Created session scoped CatalogItemsController");
    }
    
    /*Get list of all available house design plans and populate a collection 
    * that will be available in the session's context
    */
    @PostConstruct
    public void getAllPlans() {
        
        Logger.getAnonymousLogger().log(Level.FINE,"Catalog instance id {0}",this.toString());
        plans = housePlanFacade.findAll();
        Logger.getAnonymousLogger().log(Level.INFO,"Fetched items: {0}",plans.size());
        fileDownloadFormats.add("PDF");
        fileDownloadFormats.add("CAD");

    }
    
    /*Provide list of available designs in the catalog */
    public List<HousePlan> getPlans()throws EmptyListException  {
        if(plans.size() > 0)
        return plans;
        else{
            throw new EmptyListException("The fetched list is empty");
        }
    }

    public void setPlans(List<HousePlan> plans) {
        this.plans = plans;
    }
    
    /*
    * Get plan with specified id
    */
    
    public void updateSelectedPlan(){
        Logger.getAnonymousLogger().log(Level.INFO,"Inside update selectedPlan...selectedid is {0}",selectedId);
//        HousePlan current = null;
        for (HousePlan plan : plans) {
            if(plan.getPlanId() == this.selectedId){
                setCurrent(plan);
            }
        }
    }

    public HousePlan getCurrent() {
        return current;
    }

    private  void setCurrent(HousePlan current) {
        this.current = current;
    }
    
    public int getSelectedId() {
        Logger.getAnonymousLogger().log(Level.INFO,"selectID value = {0}",selectedId);
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
        Logger.getAnonymousLogger().log(Level.INFO,"Selected id set to: {0}",selectedId);
    }
    
    public boolean isBoqStatus() {
        return boqStatus;
    }

    public void setBoqStatus(boolean boqStatus) {
        Logger.getAnonymousLogger().log(Level.INFO, "Boq state set to : {0}",boqStatus);
        this.boqStatus = boqStatus;
    }

    public boolean isMtrsStatus() {
        return mtrsStatus;
    }

    public void setMtrsStatus(boolean mtrsStatus) {
        Logger.getAnonymousLogger().log(Level.INFO, "Mtrs state set to: {0}",mtrsStatus);
        this.mtrsStatus = mtrsStatus;
    }
    
    public List<String> getFileDownloadFormats() {
        return fileDownloadFormats;
    }
    
    public String getDownloadOption() {
        return downloadOption;
    }

    public void setDownloadOption(String downloadOption) {
        this.downloadOption = downloadOption;
    }
    
    //Get design plan pricing...adjust based on options selection (ajax requested)
    public Double updatePriceTotal(){
        double planPrice = current.getPricePlan().doubleValue();
        if(isBoqStatus() && isMtrsStatus()){
            totalPrice = planPrice + current.getPriceMtrSchedule().doubleValue() +
               current.getPriceBoq().doubleValue();
        }
        else if(isBoqStatus()){
            totalPrice = planPrice + current.getPriceBoq().doubleValue();
        }
        else if(isMtrsStatus()){
            totalPrice = planPrice + current.getPriceMtrSchedule().doubleValue();
        }
        else{
            totalPrice = planPrice;
        }
        return totalPrice;
    }
    
    /*Pack details on the selected plan, user id and payment options*/
    public Map createOrderPackage(String fileOpt,String price,String boq,String mtrs){
        Map<String,Object> packageDetails = new HashMap<>();
        String sessionId = FacesContext.getCurrentInstance().getExternalContext().getSessionId(true);
        packageDetails.put("userId", new Integer(sessionId));
        packageDetails.put("planObj", getCurrent());
        PurchaseOptions options = new PurchaseOptions(downloadOption, totalPrice, totalPrice, totalPrice);
        packageDetails.put("purchaseOptions",options);
        return packageDetails;
    }
}

