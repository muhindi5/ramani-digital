/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.pesi.drammer.model.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ke.pesi.drammer.model.dao.Typology;

/**
 *
 * @author kelly
 */
@Stateless
public class TypologyFacade extends AbstractFacade<Typology> {
    @PersistenceContext(unitName = "ramani-digitalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypologyFacade() {
        super(Typology.class);
    }
    
}
