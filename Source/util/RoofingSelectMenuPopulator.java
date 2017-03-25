/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */
package util;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import model.Roofing;
import model.facade.RoofingFacade;

/**
 *
 * @author kelly
 */
@Named(value = "selectRoofing")
@Dependent
public class RoofingSelectMenuPopulator {

    private Roofing roof;
    @EJB
    private RoofingFacade roofingFacade;
    private List<Roofing> roofStyles;
    /**
     * Creates a new instance of RoofingSelectMenuPopulator
     */
    public RoofingSelectMenuPopulator() {
        
    }
    
    @PostConstruct
    public void init(){
        roof = new Roofing();
        roofStyles = roofingFacade.findAll();
        
    }

    public Roofing getRoof() {
        return roof;
    }

    public void setRoof(Roofing roof) {
        this.roof = roof;
    }

    public List<Roofing> getRoofStyles() {
        return roofStyles;
    }

    public void setRoofStyles(List<Roofing> roofStyles) {
        this.roofStyles = roofStyles;
    }
    
    
}
