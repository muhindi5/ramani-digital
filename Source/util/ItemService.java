/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author kelly
 */
@ManagedBean
@SessionScoped
public class ItemService implements org.primefaces.model.SelectableDataModel{

    private List<Item> items = new ArrayList<>();
    /**
     * Creates a new instance of ItemService
     */
    public ItemService() {
    }
    
    @PostConstruct
    public void populateItemsList(){
        for (int i = 0; i < 10; i++) {
            Item x = new Item();
            int random = i+new Random().nextInt()*10;
            x.setId(random);
            x.setDesc("Items"+String.valueOf(random));
            x.setPrice(200.50);
            this.getItems().add(x);
        }
    }

    /**
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public void onRowSelect(SelectEvent event){
        FacesMessage message = new FacesMessage("Selected Item:",((Item)event.getObject()).getDesc());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onRowUnselect(UnselectEvent event){
        FacesMessage message = new FacesMessage("Unselected Item:",((Item)event.getObject()).getDesc());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @Override
    public Object getRowKey(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getRowData(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
