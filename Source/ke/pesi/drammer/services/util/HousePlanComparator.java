/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */

package ke.pesi.drammer.services.util;

import java.util.Comparator;
import ke.pesi.drammer.model.dao.HousePlan;

/**
 *
 * @project: ramani-digital
 * @author kelly
 * @date May 21, 2017 12:05:13 PM
 * 
 * Compaare the ids of houseplan objects to determine ordering in product 
 * catalog; this will be used in when fetching 
 * query results...similar to orderby DESC
 */
public class HousePlanComparator implements Comparator<HousePlan> {

    @Override
    public int compare(HousePlan o1, HousePlan o2) {
        //reverse the ordering such that the object with a high id will be 
        //placed on top during sorting of this list
        //by convention, 1 is return if o1 > o2
        //-1 if o2 > o1 and 0 if both are equal.
        return (o1.getPlanId() < o2.getPlanId())? 1: 
                (o1.getPlanId() > o2.getPlanId())? -1:
                0;
    }

}
