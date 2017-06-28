/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */
package ke.pesi.drammer.services;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import ke.pesi.drammer.model.dao.HousePlan;

/**
 *
 * @author kelly
 * Manage a user's 'transaction in purchasing a house design plan.
 * An order will be created and persisted if the use completes the transaction 
 * by making the payment. Otherwise, persist transaction record.
 */
@Named(value = "transactionController")
@SessionScoped
public class TransactionController implements Serializable {

    private String userId; //session id
    private HousePlan plan;
    private PurchaseOptions options;
    private Logger logger;
    
    public TransactionController() {
        logger = Logger.getAnonymousLogger();
        logger.log(Level.INFO, "created transaction controller");
    }
    
    
    /*User completes order by purchase payment being confirmed*/
    public String completeOrder(){
        return "confirmation";
    }
    
    
}
