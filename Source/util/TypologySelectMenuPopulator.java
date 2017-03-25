/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */
package util;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import model.Typology;
import model.facade.TypologyFacade;

/**
 *
 * @author kelly
 */
@Named(value = "selectTypology")
@SessionScoped
public class TypologySelectMenuPopulator implements Serializable {
    
    
    private List<Typology> typologies;
    private Typology typology;
    @EJB
    private TypologyFacade typologyFacade;

    public List<Typology> getTypologies() {
        return typologies;
    }

    public void setTypologies(List<Typology> typologies) {
        this.typologies = typologies;
    }

    public Typology getTypology() {
        return typology;
    }

    public void setTypology(Typology typology) {
        this.typology = typology;
    }

    /**
     * Creates a new instance of TypologySelectMenuPopulator
     */
    public TypologySelectMenuPopulator() {
    }
    
    @PostConstruct
    public void init(){
        typologies = new ArrayList<>();
        typology = new Typology();
        
        //get typologies from db and poulate list
        typologies = typologyFacade.findAll();
    }
    
    
    
}
