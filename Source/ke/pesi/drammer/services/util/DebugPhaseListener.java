/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */

package ke.pesi.drammer.services.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @project: ramani-digital
 * @author kelly
 * @date Jun 3, 2017 11:33:57 AM
 * 
 */
public class DebugPhaseListener implements PhaseListener{

    @Override
    public void beforePhase(PhaseEvent event) {
        if(event.getPhaseId() == PhaseId.RESTORE_VIEW){
            System.out.println("Processing new request");
        }
        System.out.println("Before: "+event.getPhaseId());
    }
    
    @Override
    public void afterPhase(PhaseEvent event) {
        System.out.println("After: "+event.getPhaseId());
        if(event.getPhaseId() == PhaseId.RENDER_RESPONSE){
            System.out.println("Done with processing request");
        }
    }


    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}
