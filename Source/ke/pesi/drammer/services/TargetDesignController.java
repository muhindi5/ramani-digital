/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */
package ke.pesi.drammer.services;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author kelly
 */
@Named(value = "targetDesignController")
@ConversationScoped
public class TargetDesignController implements Serializable {
    
    private String downloadOption;
    private Boolean billOfQuantities;
    private Boolean materialSchedule;
    private List<String> fileDownloadFormats;

    /**
     * Creates a new instance of TargetDesignController
     */
    public TargetDesignController() {
    }

    @PostConstruct
    public void initDownloadOPtions(){
        fileDownloadFormats = new ArrayList<>();
        fileDownloadFormats.add("PDF");
        fileDownloadFormats.add("CAD");
    }
    
    public String getDownloadOption() {
        return downloadOption;
    }

    public void setDownloadOption(String downloadOption) {
        this.downloadOption = downloadOption;
    }

    public boolean getBillOfQuantities() {
        return billOfQuantities;
    }

    public void setBillOfQuantities(Boolean billOfQuantities) {
        this.billOfQuantities = billOfQuantities;
    }

    public Boolean getMaterialSchedule() {
        return materialSchedule;
    }

    public void setMaterialSchedule(Boolean materialSchedule) {
        this.materialSchedule = materialSchedule;
    }
    
    public List<String> getFileDownloadFormats() {
        return fileDownloadFormats;
    }
}
