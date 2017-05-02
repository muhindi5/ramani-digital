/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.pesi.drammer.model.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ke.pesi.drammer.model.dao.Roofing;

/**
 *
 * @author kelly
 */
@Stateless
public class RoofingFacade extends AbstractFacade<Roofing> {
    @PersistenceContext(unitName = "ramani-digitalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoofingFacade() {
        super(Roofing.class);
    }
    
}
